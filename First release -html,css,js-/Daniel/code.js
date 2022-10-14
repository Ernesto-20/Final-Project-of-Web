const usernameInput = document.querySelector('#username-input')
const passwordInput = document.querySelector('#password-input')
const submitButton = document.querySelector('#submit-button')

// Validator
const isInputEmpty = value => {
    return value.length === 0
}

const restartInput = event => {
    event.target.className = ''
    event.target.placeholder = ''
    event.target.removeEventListener('change', restartInput)
}

const markInputError = element => {
    element.className = 'input-error'
    element.placeholder = '* Campo obligatorio'
    element.addEventListener('change', restartInput)
}

submitButton.addEventListener('click', event => {
    event.preventDefault()

    const usernameEmpty = isInputEmpty(usernameInput.value)
    const passwordEmpty = isInputEmpty(passwordInput.value)

    if (usernameEmpty) markInputError(usernameInput)
    if (passwordEmpty) markInputError(passwordInput)

    if (!usernameEmpty && !passwordEmpty){
        // Send to the server
        alert('Sending data to the server')
    }
})