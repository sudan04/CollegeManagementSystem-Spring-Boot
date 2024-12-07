function validateForm() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const firstNameError = document.getElementById("firstName-error");
    const lastNameError = document.getElementById("lastName-error");
    const usernameError = document.getElementById("username-error");
    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");

    firstNameError.innerHTML = "";
    lastNameError.innerHTML = "";
    usernameError.innerHTML = "";
    emailError.innerHTML = "";
    passwordError.innerHTML = "";

    let isValid = true;

    if (firstName === "") {
        firstNameError.innerHTML = "First Name is required.";
        isValid = false;
    } else if (firstName.length < 3) {
        firstNameError.innerHTML = "First Name must be at least 3 characters long.";
        isValid = false;
    }

    if (lastName === "") {
        lastNameError.innerHTML = "Last Name is required.";
        isValid = false;
    } else if (lastName.length < 3) {
        lastNameError.innerHTML = "Last Name must be at least 3 characters long.";
        isValid = false;
    }

    if (username === "") {
        usernameError.innerHTML = "Username is required.";
        isValid = false;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email === "") {
        emailError.innerHTML = "Email is required.";
        isValid = false;
    } else if (!emailPattern.test(email)) {
        emailError.innerHTML = "Please enter a valid email address.";
        isValid = false;
    }

    if (password === "") {
        passwordError.innerHTML = "Password is required.";
        isValid = false;
    } else if (password.length < 6) {
        passwordError.innerHTML = "Password must be at least 6 characters long.";
        isValid = false;
    }

    return isValid;
}

function togglePassword(fieldId, iconId) {
    const passwordField = document.getElementById(fieldId);
    const eyeIcon = document.getElementById(iconId);
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
    } else {
        passwordField.type = 'password';
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
    }
}
