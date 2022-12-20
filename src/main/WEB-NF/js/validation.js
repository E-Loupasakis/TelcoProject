
//Validation for Customer's create form


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


//Fname validation



fname.addEventListener('focusout', () => {
 
    regEx = /[A-Z][A-Za-z\\s]*$/;
    message = 'First name must contain at least one uppercase, one lowercase letter, one number and one special character'

  if (regEx.test(fname.value)) {
    helpMessage.innerHTML = 'Success';
    helpMessage.className = 'text-success';
  } else {
    helpMessage.innerHTML = message;
    helpMessage.className = 'text-danger';
    
  }

});

//Lname validation

lname.addEventListener('focusout', () => {
   

    regEx = /[A-Z][A-Za-z\\s]*$/;
    message = 'Last name must contain at least one uppercase, one lowercase letter, one number and one special character'

  if (regEx.test(fname.value)) {
    helpMessagelname.innerHTML = 'Success';
    helpMessagelname.className = 'text-success';
  } else {
    helpMessagelname.innerHTML = message;
    helpMessagelname.className = 'text-danger';
    
  }

});

//Username Validation

usname.addEventListener('focusout', () => {
  

    regEx = /^[A-Za-z]([A-Za-z0-9_]){7,19}$/;
    message = 'Username must start with character, and cannot contain spaces and special characters(except underscore)'

  if (regEx.test(fname.value)) {
    helpMessageusname.innerHTML = 'Success';
    helpMessageusname.className = 'text-success';
  } else {
    helpMessageusname.innerHTML = message;
    helpMessageusname.className = 'text-danger';
    
  }

});


//Password Validation

pass.addEventListener('focusout', () => {
  

    regEx = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&-+=_()])(?=\\S+$).{8,20}$/;
    message = "Password must contain :0-9 at least once,a-z at least once,A-Z at least once,Special character at least once, no white spaces are allowed,min chars :8 , max chars:20";
    

  if (regEx.test(fname.value)) {
    helpMessagepass.innerHTML = 'Success';
    helpMessagepass.className = 'text-success';
  } else {
    helpMessagepass.innerHTML = message;
    helpMessagepass.className = 'text-danger';
    
  }

});

//VatNumber validation

vatNum.addEventListener('focusout', () => {
  

    regEx = /^(?!\s*$).+/;
    message = "The vatNumber can't be null";
    

  if (regEx.test(fname.value)) {
    helpMessageVat.innerHTML = 'Success';
    helpMessageVat.className = 'text-success';
  } else {
    helpMessageVat.innerHTML = message;
    helpMessageVat.className = 'text-danger';
    
  }

});

//Address validation

addr.addEventListener('focusout', () => {
  

   //regEx = /^(?!\s*$).{5,30}$/;
    message = "The vatNumber can't be null";
    

  if (regEx.test(fname.value)) {
    helpMessageaddr.innerHTML = 'Success';
    helpMessageaddr.className = 'text-success';
  } else {
    helpMessageaddr.innerHTML = message;
    helpMessageaddr.className = 'text-danger';
    
  }

});



// for(let email of emails ){
// email.addEventListener('focusout', () => {
//     debugger;
//      regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
//   message = 'Must be a valid email address';

//   if (regEx.test(email.value)) {
//     email.nextElementSibling.innerHTML = 'Success';
//     console.log(email);
//     email.nextElementSibling.className = 'text-success';
    
//   } else {
//     email.nextElementSibling.innerHTML = message;
//     console.log(email);
//     email.nextElementSibling.className = 'text-danger';
    
//   }

// });
// }


 // regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  // message = 'Must be a valid email address';



// message = 'Must contain at least one uppercase, one lowercase letter and one number'


// let regEx = /(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter';

  // let regEx = /(?=.*\d)(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter and one number';

  // let regEx = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])/