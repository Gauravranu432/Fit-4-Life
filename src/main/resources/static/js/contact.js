
    document.getElementById('contactForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent form from submitting immediately

        // Get form values
        const name = document.getElementById('name').value.trim();
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();
        const message = document.getElementById('message').value.trim();

        let valid = true;
        const errorMessages = document.querySelectorAll('.error-message');
        
        // Clear previous error messages
        errorMessages.forEach((error) => {
            error.textContent = '';
        });

        // Validate Name
        if (name === '') {
            document.getElementById('name').nextElementSibling.textContent = 'Name is required';
            valid = false;
        }

        // Validate Email
        const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
        if (!emailPattern.test(email)) {
            document.getElementById('email').nextElementSibling.textContent = 'Invalid email address';
            valid = false;
        }

        // Validate Phone
        const phonePattern = /^\+?[1-9]\d{1,14}$/; // International phone number format
        if (!phonePattern.test(phone)) {
            document.getElementById('phone').nextElementSibling.textContent = 'Invalid phone number';
            valid = false;
        }

        // Validate Message
        if (message === '') {
            document.getElementById('message').nextElementSibling.textContent = 'Message is required';
            valid = false;
        }

        if (valid) {
            // You can proceed to submit the form or do any other actions
            console.log('Form submitted successfully');

            // Example: You could submit the form using AJAX here
            // $.post('/api/submitContactForm', {name, email, phone, message})
            //     .done(function(response) {
            //         // Handle success
            //     })
            //     .fail(function(error) {
            //         // Handle error
            //     });

            alert('Form submitted successfully!');
        }
    });
