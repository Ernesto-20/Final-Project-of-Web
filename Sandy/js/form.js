// Elementos del formulario
const userName = document.getElementById("input_name")
const pass = document.getElementById("input_pass")
const passConf = document.getElementById("input_pass_conf")
const access_level = document.querySelector("select")
const btn = document.querySelector("#send")

// Mensajes de error
const userNameError = document.querySelector('#name-error-msg > span.error');
const passError = document.querySelector('#pass-error-msg > span.error');
const accessError = document.querySelector('#access-error-msg > span.error');

btn.addEventListener("click", function (event) {
  
  if (!userName.validity.valid || !pass.validity.valid || !passConf.validity.valid || pass.value !== passConf.value || !access_level.validity.valid) {
    
    checkUserName()
    checkPassword()
    checkAccess()
    
    event.preventDefault()
  } 
  
})

function checkUserName() {
  if(!userName.validity.valid) {
    showUserNameError();
  } else {
    // En caso de que haya un mensaje de error visible, si el campo es válido, eliminamos el mensaje de error.
    userNameError.textContent = ''; // Restablece el contenido del mensaje
    userNameError.className = 'error'; // Restablece el estado visual del mensaje
  }
}

function checkPassword() {
  if (!pass.validity.valid || !passConf.validity.valid || pass.value !== passConf.value) {
    showPassError()
  } else {
    // En caso de que haya un mensaje de error visible, si el campo es válido, eliminamos el mensaje de error.
    passError.textContent = ''; // Restablece el contenido del mensaje
    passError.className = 'error'; // Restablece el estado visual del mensaje
  }
}

function checkAccess() {
  if (!access_level.validity.valid) {
    console.log("object");
    showAccessError()
  } else {
    // En caso de que haya un mensaje de error visible, si el campo es válido, eliminamos el mensaje de error.
    accessError.textContent = ''; // Restablece el contenido del mensaje
    accessError.className = 'error'; // Restablece el estado visual del mensaje
  }
}

function showUserNameError() {
  if (userName.validity.valueMissing) {

    // Si el campo está vacío muestra el mensaje de error siguiente.
    userNameError.textContent = 'Introduce tu nombre de usuario';
  }
  
  // Establece el estilo apropiado
  userNameError.className = 'error active';
}

function showPassError() {
  if (pass.validity.valueMissing) {
    passError.textContent = 'Introduce una contraseña';

  } else if (pass.validity.tooShort) {
    passError.textContent = 'La contraseña debe tener mínimo 8 caracteres';
    
  } else if (passConf.validity.valueMissing) {
    passError.textContent = 'Confirma tu contraseña';
    
  } else if (pass.value !== passConf.value) {
    passError.textContent = 'Las contraseñas no coinciden. Inténtalo de nuevo';
    console.log('error');

  }
  
  passError.className = 'error active';
}

function showAccessError() {
  if (access_level.validity.valueMissing) {
    accessError.textContent = 'Seleccione un nivel de acceso';

  }
  
  accessError.className = 'error active';
}




