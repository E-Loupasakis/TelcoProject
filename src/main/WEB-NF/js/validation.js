const fname = document.querySelector('#fname');
const helpMessage = document.querySelector('#help-message');

const lname = document.querySelector('#lname');
const helpMessagelname = document.querySelector('#helpMessagelname');

const usname = document.querySelector('#username');
const helpMessageusname = document.querySelector('#helpMessageusname');

const pass = document.querySelector('#password');
const helpMessagepass = document.querySelector('#helpMessagepass');

const vatNum = document.querySelector('#vatNumber');
const helpMessageVat = document.querySelector('#helpMessageVat');

const addr = document.querySelector('#address');
const helpMessageaddr = document.querySelector('#helpMessageaddr');

const emails=document.querySelectorAll('.myEmailinputClass');

const emailCount=Number(document.getElementById('emailCount').value);
let emailArray = new Array();

// for(var i=1;i<emailCount;i++){
// let str= "email_input"+i;
// emailArray.push(document.getElementById(str).value);
// }


fname.addEventListener('focusout', () => {
    debugger;

    regEx = /[A-Z][A-Za-z\\s]*$/;
    message = 'Must contain at least one uppercase, one lowercase letter, one number and one special character'

  if (regEx.test(fname.value)) {
    helpMessage.innerHTML = 'Success';
    helpMessage.className = 'text-success';
  } else {
    helpMessage.innerHTML = message;
    helpMessage.className = 'text-danger';
    
  }

});


for(let email of emails ){
email.addEventListener('focusout', () => {
    debugger;
     regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  message = 'Must be a valid email address';

  if (regEx.test(email.value)) {
    email.nextElementSibling.innerHTML = 'Success';
    console.log(email);
    email.nextElementSibling.className = 'text-success';
    
  } else {
    email.nextElementSibling.innerHTML = message;
    console.log(email);
    email.nextElementSibling.className = 'text-danger';
    
  }

});
}


 // regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  // message = 'Must be a valid email address';



// message = 'Must contain at least one uppercase, one lowercase letter and one number'


// let regEx = /(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter';

  // let regEx = /(?=.*\d)(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter and one number';

  // let regEx = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])/