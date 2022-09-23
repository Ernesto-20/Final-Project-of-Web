
const names = document.getElementById('name')
const last_name = document.getElementById('last_name')
const address = document.getElementById('address')
const identification = document.getElementById('identification')
const submit = document.getElementById('submit')

const male_option = document.getElementById('male')
const female_option = document.getElementById('female')
const undefined_option = document.getElementById('undefined')

female_option.checked = true

male_option.onclick = function (){
    if (female_option.checked)
        female_option.checked = false
    else if(undefined_option.checked)
        undefined_option.checked = false
}
female_option.onclick = function (){
    if (undefined_option.checked)
        undefined_option.checked = false
    else if(male_option.checked)
        male_option.checked = false
}
undefined_option.onclick = function (){
    if (female_option.checked)
        female_option.checked = false
    else if(male_option.checked)
        male_option.checked = false
}



submit.onclick = function (){
    validation();
    
}


function validation(){
    if(contains_number(names.value)){
        alert('El nombre del estudiante no debe de contener números')
    }else if(contains_number(last_name.value)){
        alert('El apellido del estudiante no debe de contener números')
    }else if(contains_lyrics(identification.value)){
        alert('El carnet del estudiante no debe de contener letas alfabéticas')
    }else if(identification.length != 11){
        alert('El carnet del estudiante debe de contener 11 caracteres')
    }

}

function contains_number(txt){
    for (i=0; i<txt.length; i++){
        if(!isNaN(txt[i]))
            return true
    }

    return false
}

function contains_lyrics(txt){
    for (i=0; i<txt.length; i++){
        if(isNaN(txt[i]) || txt[i]==' ')
            return true
    }

    return false
}
