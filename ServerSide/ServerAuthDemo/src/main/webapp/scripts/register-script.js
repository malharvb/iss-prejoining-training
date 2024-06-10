// Register form submit event handler
const registerForm = document.querySelector('#register-form');

registerForm.addEventListener('submit', (event) => {
	event.preventDefault();
	
    const isValid = validateSigninForm(event);
    
    if (isValid) {
		registerForm.method = 'post';
		registerForm.submit();
	}
});