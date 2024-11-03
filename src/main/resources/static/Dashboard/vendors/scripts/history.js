document.addEventListener('DOMContentLoaded', function () {
    const calendar = document.getElementById('calendar');
    const summaryTable = document.getElementById('summary-table');
    const monthYearDisplay = document.getElementById('month-year');
    const prevButton = document.getElementById('prev-month');
    const nextButton = document.getElementById('next-month');

    let currentDate = new Date();

    // Fetch attendance data from the Spring Boot backend
    async function fetchAttendanceData() {
        const response = await fetch('/api/attendance');
        if (!response.ok) {
            throw new Error('Failed to fetch attendance data');
        }
        return await response.json();
    }

    // Render the calendar
    function renderCalendar(date, videoHistory) {
        calendar.innerHTML = `
            <div class="calendar-header">Sun</div>
            <div class="calendar-header">Mon</div>
            <div class="calendar-header">Tue</div>
            <div class="calendar-header">Wed</div>
            <div class="calendar-header">Thu</div>
            <div class="calendar-header">Fri</div>
            <div class="calendar-header">Sat</div>
        `;

        const year = date.getFullYear();
        const month = date.getMonth();
        const firstDay = new Date(year, month, 1).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        // Update month and year display
        monthYearDisplay.textContent = `${date.toLocaleString('default', { month: 'long' })} ${year}`;

        // Render previous month's days if needed
        for (let i = 0; i < firstDay; i++) {
            const emptyCell = document.createElement('div');
            emptyCell.className = 'calendar-day empty';
            calendar.appendChild(emptyCell);
        }

        // Render current month's days
        for (let day = 1; day <= daysInMonth; day++) {
            const dateString = `${year}-${String(month + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`;

            const dateElement = document.createElement('div');
            dateElement.className = 'calendar-day';
            dateElement.textContent = day;
            dateElement.dataset.date = dateString;

            // Highlight today's date
            if (dateString === new Date().toISOString().split('T')[0]) {
                dateElement.classList.add('highlight');
            }

            // Mark days with workout data
            if (videoHistory[dateString]) {
                dateElement.classList.add('workout-day');
            }

            calendar.appendChild(dateElement);
        }
    }

    // Format duration as MM:SS
    function formatDuration(seconds) {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = Math.floor(seconds % 60);
        return `${String(minutes).padStart(2, '0')}:${String(remainingSeconds).padStart(2, '0')}`;
    }

    // Populate the workout summary table
    async function populateSummaryTable(date, videoHistory) {
        summaryTable.innerHTML = '';
        if (videoHistory[date]) {
            const videoArray = Array.isArray(videoHistory[date]) ? videoHistory[date] : [];
            videoArray.forEach(video => {
                if (video && typeof video.name === 'string' && typeof video.duration === 'number') {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${date}</td>
                        <td>${video.name}</td>
                        <td>${formatDuration(video.duration)}</td>
                    `;
                    summaryTable.appendChild(row);
                } else {
                    console.error(`Invalid video data for ${date}:`, video);
                }
            });
        } else {
            const row = document.createElement('tr');
            row.innerHTML = `<td colspan="3">No workout data for ${date}</td>`;
            summaryTable.appendChild(row);
        }
    }

    // Event listener for calendar day clicks
    calendar.addEventListener('click', function (event) {
        const target = event.target;
        if (target.classList.contains('calendar-day') && target.dataset.date) {
            const selectedDate = target.dataset.date;
            populateSummaryTable(selectedDate, videoHistory);
        }
    });

    // Update the current month and re-render the calendar when navigating
    prevButton.addEventListener('click', function () {
        currentDate.setMonth(currentDate.getMonth() - 1); // Move to the previous month
        renderCalendar(currentDate, videoHistory);
    });

    nextButton.addEventListener('click', function () {
        currentDate.setMonth(currentDate.getMonth() + 1); // Move to the next month
        renderCalendar(currentDate, videoHistory);
    });

    // Listen for workout data updates and refresh the table
    window.addEventListener('workoutDataUpdated', function (event) {
        const { date } = event.detail;
        populateSummaryTable(date, videoHistory);
        renderCalendar(currentDate, videoHistory);
    });

    // Initial render
    let videoHistory = {};
    fetchAttendanceData().then(data => {
        videoHistory = data.reduce((acc, record) => {
            if (!acc[record.date]) {
                acc[record.date] = [];
            }
            acc[record.date].push({ name: record.videoTitle, duration: record.duration });
            return acc;
        }, {});

        renderCalendar(currentDate, videoHistory);
        populateSummaryTable(new Date().toISOString().split('T')[0], videoHistory);
    }).catch(error => console.error('Error fetching attendance data:', error));
});
