function validateForm() {
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.getElementById("role").value;

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

    if (!firstName) {
        firstNameError.innerHTML = "First Name is required.";
        isValid = false;
    } else if (firstName.length < 3) {
        firstNameError.innerHTML = "First Name must be at least 3 characters long.";
        isValid = false;
    }

    if (!lastName) {
        lastNameError.innerHTML = "Last Name is required.";
        isValid = false;
    } else if (lastName.length < 3) {
        lastNameError.innerHTML = "Last Name must be at least 3 characters long.";
        isValid = false;
    }

    if (!username) {
        usernameError.innerHTML = "Username is required.";
        isValid = false;
    } else if (username.length < 3 || /\s/.test(username)) {
        usernameError.innerHTML = "Username must be at least 3 characters and not contain spaces.";
        isValid = false;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email) {
		console.log(email);
        emailError.innerHTML = "Email is required.";
        isValid = false;
    } else if (!emailPattern.test(email)) {
        emailError.innerHTML = "Please enter a valid email address.";
        isValid = false;
    }

    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
    if (!password) {
        passwordError.innerHTML = "Password is required.";
        isValid = false;
    } else if (!passwordPattern.test(password)) {
        passwordError.innerHTML = "Password must be at least 6 characters, include upper, lower, number, and special character.";
        isValid = false;
    }

    if (!role) {
        alert("Please select a valid role.");
        isValid = false;
    }

    return isValid;
}
