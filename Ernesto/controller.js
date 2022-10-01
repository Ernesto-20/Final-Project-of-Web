const names = document.getElementById('name')
const last_name = document.getElementById('last_name')
const address = document.getElementById('address')
const identification = document.getElementById('identification')
const submit = document.getElementById('submit')

const male_option = document.getElementById('male')
const female_option = document.getElementById('female')
const undefined_option = document.getElementById('undefined')

female_option.checked = true

// Single Selection Validation
document.querySelectorAll(".gender").forEach(el => {
    el.addEventListener("click", e => {
      const id = e.target.getAttribute("id");
      let note =`El elemento seleccionado es ${id}`
      console.log(note)
      
      if (id === 'male'){
        female_option.checked = false
        undefined_option.checked = false
      }
      else if (id === 'female'){
        male_option.checked = false
        undefined_option.checked = false
      }
      else if (id === 'undefined'){
        female_option.checked = false
        male_option.checked = false
      }
    });
  });


//   Simple Validation with alerts.
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
    let check = false
    for (let i=0; i<txt.length && !check; i++){
        if(!isNaN(txt[i]))
            check = true
    }

    return check
}

function contains_lyrics(txt){
    let check = false
    for (let i=0; i<txt.length && !check; i++){
        if(isNaN(txt[i]))
            check = true
    }

    return check
}
