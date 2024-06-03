// Register form submit event handler
const registerForm = document.querySelector('#register-form');

registerForm.addEventListener('submit', (event) => {
    validateSigninForm(event, registerForm)
});