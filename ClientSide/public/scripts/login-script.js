// Login form submit event handler
const loginForm = document.querySelector('#login-form');

loginForm.addEventListener('submit', (event) => {
    validateSigninForm(event, loginForm)
});

