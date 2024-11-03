(function ($) {
    "use strict";
    
    // Dropdown on mouse hover
    $(document).ready(function () {
        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }
        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });

    // cads code
// Array of fitness image URLs
        const fitnessImages = [
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236285/banner5_lntomc.png',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236236/banner4_pjm22p.jpg',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236227/banner7_zrw7zw.jpg',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236224/banner6_mw0iwb.jpg',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236222/banner3_yhpyz3.jpg',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236217/banner2_dubj0v.jpg',
            'https://res.cloudinary.com/deup6t83x/image/upload/v1721236217/banner_opykal.jpg',
        ];

        // Function to get a random image URL
        function getRandomImage() {
            const randomIndex = Math.floor(Math.random() * fitnessImages.length);
            return fitnessImages[randomIndex];
        }

        // Set random images on page load
        document.getElementById('dynamic-img-1').src = getRandomImage();
        document.getElementById('dynamic-img-2').src = getRandomImage();
        document.getElementById('dynamic-img-3').src = getRandomImage();
    

        // card animation code
    document.addEventListener('scroll', function() {
        const cards = document.querySelectorAll('.card');
        const viewportHeight = window.innerHeight;
        cards.forEach((card, index) => {
            const cardTop = card.getBoundingClientRect().top;
            if (cardTop < viewportHeight - 100) {
                card.classList.add('visible');
            } else {
                card.classList.remove('visible');
            }
        });
    });
    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: false,
        loop: true,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
    });
    
})(jQuery);

