function redirectToIndex() {
    window.location.href = "index";
}
let signupContent = document.querySelector(".signup-form-container"),
    stagebtn1b = document.querySelector(".stagebtn1b"),
    stagebtn2a = document.querySelector(".stagebtn2a"),
    stagebtn2b = document.querySelector(".stagebtn2b"),
    stagebtn3a = document.querySelector(".stagebtn3a"),
    stagebtn3b = document.querySelector(".stagebtn3b"),
    signupContent1 = document.querySelector(".stage1-content"),
    signupContent2 = document.querySelector(".stage2-content"),
    signupContent3 = document.querySelector(".stage3-content");

signupContent2.style.display = "none";
signupContent3.style.display = "none";

function validateStage1() {
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('lname').value;
    let dob = document.getElementById('dob').value;
    let gender = document.getElementById('gender').value; // Updated for dropdown

    // Check for empty fields
    if (!fname || !lname || !dob || gender === "") {
        alert('Please fill out all fields in Stage 1');
        return false;
    }

    // Check if names contain only letters
    if (!/^[A-Za-z]+$/.test(fname)) {
        alert('First name should only contain letters');
        return false;
    }
    if (!/^[A-Za-z]+$/.test(lname)) {
        alert('Last name should only contain letters');
        return false;
    }

    return true;
}
function validateStage2() {
    let phone = document.getElementById('phone').value.trim();
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value.trim();
    let confirmpassword = document.getElementById('confirmpassword').value.trim();

    // Validate Phone
    if (!phone) {
        alert('Phone number is required');
        return false;
    } else if (!/^\d{10}$/.test(phone)) { // Assuming a 10-digit phone number
        alert('Please enter a valid 10-digit phone number');
        return false;
    }

    // Validate Email
    if (!email) {
        alert('Email address is required');
        return false;
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert('Please enter a valid email address');
        return false;
    }

    // Validate Password
    if (!password) {
        alert('Password is required');
        return false;
    } else if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,12}$/.test(password)) {
        alert('Password must be 8-12 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character');
        return false;
    }

    // Validate Confirm Password
    if (!confirmpassword) {
        alert('Please confirm your password');
        return false;
    } else if (password !== confirmpassword) {
        alert('Passwords do not match');
        return false;
    }

    return true;
}


function validateStage3() {
    let tc = document.getElementById('tc').checked;

    if (!tc) {
        alert('You must agree to the terms and conditions');
        return false;
    }
    return true;
}

function stage1to2() {
    if (validateStage1()) {
        signupContent1.style.display = "none";
        signupContent2.style.display = "block";
        signupContent3.style.display = "none";
        document.querySelector(".stageno-1").innerText = "\u2713";
        document.querySelector(".stageno-1").style.backgroundColor = "#52ec61";
        document.querySelector(".stageno-1").style.color = "#fff";
    }
}

function stage2to1() {
    signupContent1.style.display = "block";
    signupContent2.style.display = "none";
    signupContent3.style.display = "none";
}

function stage2to3() {
    if (validateStage2()) {
        signupContent1.style.display = "none";
        signupContent2.style.display = "none";
        signupContent3.style.display = "block";
        document.querySelector(".stageno-2").innerText = "\u2713";
        document.querySelector(".stageno-2").style.backgroundColor = "#52ec61";
        document.querySelector(".stageno-2").style.color = "#fff";
    }
}

function stage3to2() {
    signupContent1.style.display = "none";
    signupContent2.style.display = "block";
    signupContent3.style.display = "none";
}

document.querySelector('form').addEventListener('submit', function(event) {
    if (!validateStage3()) {
        event.preventDefault(); // Prevent form submission if validation fails
    }
    // Otherwise, the form will submit and data will be sent to the backend
});

function showPasswordValidation() {
    document.getElementById('passwordValidation').style.display = 'block';
}

function hidePasswordValidation() {
    var password = document.getElementById('password').value;
    if (!password) {
        document.getElementById('passwordValidation').style.display = 'none';
    }
}

function validatePassword() {
    var password = document.getElementById('password').value;

    var uppercase = /[A-Z]/.test(password);
    var lowercase = /[a-z]/.test(password);
    var number = /\d/.test(password);
    var specialChar = /[@$!%*?&]/.test(password);
    var length = password.length >= 8 && password.length <= 12;

    document.getElementById('uppercase').className = uppercase ? 'valid' : 'invalid';
    document.getElementById('lowercase').className = lowercase ? 'valid' : 'invalid';
    document.getElementById('number').className = number ? 'valid' : 'invalid';
    document.getElementById('specialChar').className = specialChar ? 'valid' : 'invalid';
    document.getElementById('length').className = length ? 'valid' : 'invalid';

    if (uppercase && lowercase && number && specialChar && length) {
        document.getElementById('passwordValidation').style.display = 'none';
    } else {
        document.getElementById('passwordValidation').style.display = 'block';
    }
}

/*function submitLoginDetails(event) {
    event.preventDefault(); // Prevent the default form submission
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    let errorMessage = '';

    // Basic validations
    if (!email) {
        errorMessage += 'Please enter your email.\n';
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        errorMessage += 'Please enter a valid email address.\n';
    }

    if (!password) {
        errorMessage += 'Please enter your password.\n';
    } else if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,12}$/.test(password)) {
        errorMessage += 'Password must be 8-12 characters long, include at least one uppercase letter, one lowercase letter, one number, and one special character.\n';
    }

    if (errorMessage) {
        alert(errorMessage.trim());
        return; // Stop further processing if there are validation errors
    }

    // Prepare form data
    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);

    // Send data to server
    fetch('login', {
        method: 'POST',
        body: formData
    }).then(response => response.text())
      .then(data => {
          if (data.includes('Invalid email or password')) {
              alert('Invalid email or password');
          } else {
              alert('Login successful!');
              localStorage.setItem('isLoggedIn', 'true');
              // Redirect to the appropriate page
              const urlParams = new URLSearchParams(window.location.search);
              const redirectUrl = urlParams.get('redirect') || 'index';
              window.location.href = redirectUrl;
          }
      }).catch(error => {
          console.error('Error:', error);
      });
}
*/
function redirectToIndex() {
    window.location.href = 'index';
}

const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#password');

togglePassword.addEventListener('click', function () {
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    this.classList.toggle('bx-show');
    this.classList.toggle('bx-hide');
});

const toggleConfirmPassword = document.querySelector('#toggleConfirmPassword');
const confirmPassword = document.querySelector('#confirmpassword');

toggleConfirmPassword.addEventListener('click', function () {
    const type = confirmPassword.getAttribute('type') === 'password' ? 'text' : 'password';
    confirmPassword.setAttribute('type', type);
    this.classList.toggle('bx-show');
    this.classList.toggle('bx-hide');
});


