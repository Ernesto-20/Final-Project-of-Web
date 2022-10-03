const subject = document.getElementById('subject')
const submit = document.getElementById('submit')

submit.onclick = function (){
    if(contains_number(subject.value)){
        alert('El nombre de la asignatura no debe contener números')
    }    
}

function contains_number(txt){
    let check = fasle
     for (let i=0; i<txt.length && !check; i++)
        if(!isNaN(txt[i]))
            check = true
    return check
}