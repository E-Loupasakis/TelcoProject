////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// Validation for tickets

const addrTicket = document.querySelector('#address');
const addrTicketMessage = document.querySelector('#message-addr-create');

const costTicketCreation = document.querySelector('#estimated_cost');
const CostTicketMessage = document.querySelector('#message-costTicket-create');

const descriptionTicket = document.querySelector('#description');
const descriptionTicketMessage = document.querySelector('#message-description-create');



addrTicket.addEventListener('focusout', () => {
  

    regEx = /^[A-Za-z]([A-Za-z0-9_\s]){10,100}$/;
    message = "The address of the ticket must be between 10 and 100 characters and can't be null";
    

  if (regEx.test(addrTicket.value)) {
    addrTicketMessage.innerHTML = 'Success';
    addrTicketMessage.className = 'text-success';
  } else {
    addrTicketMessage.innerHTML = message;
    addrTicketMessage.className = 'text-danger';
    
  }

});


costTicketCreation.addEventListener('focusout', () => {

    message = "The cost of the ticket can't be higher than 100000 euro or less than zero";

  if (costTicketCreation.value >0 && costTicketCreation.value<=100000) {
    CostTicketMessage.innerHTML = 'Success';
    CostTicketMessage.className = 'text-success';
  } else {
    CostTicketMessage.innerHTML = message;
    CostTicketMessage.className = 'text-danger';
    
  }

});



descriptionTicket.addEventListener('focusout', () => {
   

   
    regEx = /^[A-Za-z]([A-Za-z0-9_\s]){5,200}$/;
    message = "The description of the ticket must start with a letter and must be between 5 and 200 characters and can't be null";
    

  if (regEx.test(descriptionTicket.value)) {
    descriptionTicketMessage.innerHTML = 'Success';
    descriptionTicketMessage.className = 'text-success';
  } else {
    descriptionTicketMessage.innerHTML = message;
    descriptionTicketMessage.className = 'text-danger';
    
  }
  });