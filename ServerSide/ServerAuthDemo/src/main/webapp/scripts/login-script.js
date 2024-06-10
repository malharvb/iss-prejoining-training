// Login form submit event handler
const loginForm = document.querySelector('#login-form');

loginForm.addEventListener('submit', (event) => {
	event.preventDefault();
	
    const isValid = validateSigninForm(event);
    
    if (isValid) {
		loginForm.method = "post";
		loginForm.submit();
	}   
});

