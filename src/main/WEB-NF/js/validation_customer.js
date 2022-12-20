//Validation For update Customer



const fnameUpdate = document.querySelector('#fname_update');
const messageUpdateFirstName = document.querySelector('#messageUpdateFirstName');

const lnameUpdate = document.querySelector('#lname_update');
const messageUpdateLastName = document.querySelector('#messageUpdateLastName');

const usnameUpdate = document.querySelector('#username_update');
const messageUpdateUsr = document.querySelector('#messageUpdateUsr');

const passUpdate = document.querySelector('#password_update');
const messageUpdatePass = document.querySelector('#messageUpdatePass');

const vatNumUpdate = document.querySelector('#vatNumber_update');
const messageUpdateVatNum = document.querySelector('#messageUpdateVatNum');

const addrUpdate = document.querySelector('#address_update');
const messageUpdateAddress = document.querySelector('#messageUpdateAddress');


const emailsUpdate=document.querySelectorAll('.myEmailinputClassUpdate');
const phonesUpdate=document.querySelectorAll('.myphoneinputClassUpdate');


//Fname update validation



fnameUpdate.addEventListener('focusout', () => {
 
    regEx = /[A-Z][A-Za-z\\s]*$/;
    message = 'First name must contain at least one uppercase, one lowercase letter, one number and one special character'

  if (regEx.test(fnameUpdate.value)) {
    messageUpdateFirstName.innerHTML = 'Success';
    messageUpdateFirstName.className = 'text-success';
  } else {
    messageUpdateFirstName.innerHTML = message;
    messageUpdateFirstName.className = 'text-danger';
    
  }

});

//Lname update validation

lnameUpdate.addEventListener('focusout', () => {
   

    regEx = /[A-Z][A-Za-z\\s]*$/;
    message = 'Last name must contain at least one uppercase, one lowercase letter, one number and one special character'

  if (regEx.test(lnameUpdate.value)) {
    messageUpdateLastName.innerHTML = 'Success';
    messageUpdateLastName.className = 'text-success';
  } else {
    messageUpdateLastName.innerHTML = message;
    messageUpdateLastName.className = 'text-danger';
    
  }

});

//Username update Validation

usnameUpdate.addEventListener('focusout', () => {
  

    regEx = /^[A-Za-z]([A-Za-z0-9_]){7,19}$/;
    message = 'Username must start with character, and cannot contain spaces and special characters(except underscore)'

  if (regEx.test(usnameUpdate.value)) {
    messageUpdateUsr.innerHTML = 'Success';
    messageUpdateUsr.className = 'text-success';
  } else {
    messageUpdateUsr.innerHTML = message;
    messageUpdateUsr.className = 'text-danger';
    
  }

});


//Password update Validation

passUpdate.addEventListener('focusout', () => {
  

    regEx = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&-+=_()])(?=\S+$).{8,20}$/;
    message = "Password must contain :0-9 at least once,a-z at least once,A-Z at least once,Special character at least once, no white spaces are allowed,min chars :8 , max chars:20";
    

  if (regEx.test(passUpdate.value)) {
    messageUpdatePass.innerHTML = 'Success';
    messageUpdatePass.className = 'text-success';
  } else {
    messageUpdatePass.innerHTML = message;
    messageUpdatePass.className = 'text-danger';
    
  }

});

//VatNumber update validation

vatNumUpdate.addEventListener('focusout', () => {
  

    regEx = /^\d{9}$/;
    message = "The vatNumber can't be null and must contain only 9 digits";
    

  if (regEx.test(vatNumUpdate.value)) {
    messageUpdateVatNum.innerHTML = 'Success';
    messageUpdateVatNum.className = 'text-success';
  } else {
    messageUpdateVatNum.innerHTML = message;
    messageUpdateVatNum.className = 'text-danger';
    
  }

});

//Address update validation

addrUpdate.addEventListener('focusout', () => {
  

    regEx = /^[A-Za-z]([A-Za-z0-9_\s]){5,30}$/;
    message = "The address must be between 5 and 30 characters and can't be null";
    

  if (regEx.test(addrUpdate.value)) {
    messageUpdateAddress.innerHTML = 'Success';
    messageUpdateAddress.className = 'text-success';
  } else {
    messageUpdateAddress.innerHTML = message;
    messageUpdateAddress.className = 'text-danger';
    
  }

});


for(let emailUpdt of emailsUpdate ){
    emailUpdt.addEventListener('focusout', () => {
        
         regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      message = 'Must be a valid email address';
    
      if (regEx.test(emailUpdt.value)) {
        emailUpdt.nextElementSibling.innerHTML = 'Success';
       ;
        emailUpdt.nextElementSibling.className = 'text-success';
        
      } else {
        emailUpdt.nextElementSibling.innerHTML = message;
        
        emailUpdt.nextElementSibling.className = 'text-danger';
        
      }
    
    });
}


for(let phoneUpdt of phonesUpdate ){
    phoneUpdt.addEventListener('focusout', () => {
       
      regEx = /^\d{10}$/;
      message = 'Must be a 10 digit phone';
    
      if (regEx.test(phoneUpdt.value)) {
        phoneUpdt.nextElementSibling.innerHTML = 'Success';
       
        phoneUpdt.nextElementSibling.className = 'text-success';
        
      } else {
        phoneUpdt.nextElementSibling.innerHTML = message;
        
        phoneUpdt.nextElementSibling.className = 'text-danger';
        
      }
    
    });
}