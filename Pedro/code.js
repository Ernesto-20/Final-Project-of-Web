subject = document.getElementById('subject')
submit = document.getElementById('submit')

submit.onclick = function (){
    if(contains_number(subject.value)){
        alert('El nombre de la asignatura no debe contener números')
    }    
}

function contains_number(txt){
     for (i=0; i<txt.length; i++)
        if(!isNaN(txt[i]))
            return true
    return false
}