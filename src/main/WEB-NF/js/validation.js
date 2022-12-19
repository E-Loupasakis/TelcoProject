const fname = document.querySelector('#fname');
const helpMessage = document.querySelector('#help-message');

search.addEventListener('focusout', () => {

  // let regEx = /(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter';

  // let regEx = /(?=.*\d)(?=.*[a-zA-Z])/ ;
  // message = 'Must contain at least one letter and one number';

  // let regEx = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])/
  // message = 'Must contain at least one uppercase, one lowercase letter and one number'

    regEx = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W)/;
    message = 'Must contain at least one uppercase, one lowercase letter, one number and one special character'

  // regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  // message = 'Must be a valid email address';



  if (regEx.test(fname.value)) {
    helpMessage.innerHTML = 'Success';
    helpMessage.className = 'text-success';
  } else {
    helpMessage.innerHTML = message;
    helpMessage.className = 'text-danger';
  }

});