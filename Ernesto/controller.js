
names = document.getElementById('name')
last_name = document.getElementById('last_name')
address = document.getElementById('address')
identification = document.getElementById('identification')
submit = document.getElementById('submit')

male_option = document.getElementById('male')
female_option = document.getElementById('female')
undefined_option = document.getElementById('undefined')

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

    if(contains_number(names.value)){
        alert('El nombre del estudiante no debe de contener números')
    }else if(contains_number(last_name.value)){
        alert('El apellido del estudiante no debe de contener números')
    }else if(contains_lyrics(identification.value)){
        alert('El carnet del estudiante no debe de contener letas alfabéticas')
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
        if(isNaN(txt[i]))
            return true
    }

    return false
}
