// Function to validate user data from login and register form
function validateSigninForm(event) {

    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;

    const errorElement = document.querySelector('.error');
    errorElement.textContent = '';
    errorElement.classList.remove('show-error');

    // Email validation
    
    // To ensure that the email is real and valid a email should be send to the address for the verification from the backend 
    // Regex vaildation for frontend validation
    const emailPattern = /^\S+@\S+\.\S+$/g;

    if (!email.match(emailPattern)) {
        errorElement.textContent = 'Please enter a valid email';
        errorElement.classList.add('show-error');
        return false;
    }

    // If in login form don't perform register checks
    if (event.target.id === 'login-form') {
        return true;
    }
 
    // Pasword validation

    let passwordError = [];

    // Lowercase letter validation
    const lowerCaseLettersPattern = /[a-z]/g;
    if (!password.match(lowerCaseLettersPattern)) {
        passwordError.push('It has a lower-case letter');
    }

    // Capital letter validation
    const upperCaseLettersPattern = /[A-Z]/g;
    if (!password.match(upperCaseLettersPattern)) {
        passwordError.push('It has an upper-case letter');
    }

    // Numbers validation
    const numbersPattern = /[0-9]/g;
    if (!password.match(numbersPattern)) {
        passwordError.push('It has a number');
    }

    // Length validation
    if (password.length <= 8) {
        passwordError.push('It is longer than 8 characters');
    }

    // Matches confirm password validation
    if (password !== document.querySelector('#confirm-password').value) {
        passwordError.push('Password and confirm password fields should match');
    }
    

    if (passwordError.length > 0) {
        errorElement.classList.add('show-error');
        errorElement.textContent = 'Please make sure your password meets the below criteria: \r\n';

        passwordError.forEach((error) => {
            errorElement.textContent += '- ' + error + '\r\n';
        })
        return false;
    }


	return true;
}