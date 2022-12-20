function redirect(){
    localStorage.clear();
    var url='logout.html'
    window.location.href=url;
    
}

//TICKETS FOR ADMIN///////////////////////////////////////////////////////////////////////////////////////////////////



function createCustomerDropdown(element){
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/';

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(customers => {
            const data = customers.data;
            fillDropdownList(data,element);


}).catch(error => console.error('Network Error...'));

}


function getTicketsForAdmin(){

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets';

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(tickets => {



            const data = tickets.data;




            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Customer ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";


            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket['ticketId']+"</td><td>"+ticket.customerId+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";

            document.getElementById('table').innerHTML = html;






        })

        .catch(error => console.error('Network Error...'));

}

function getTodayForDateOfAction(){

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; 
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("date_of_action").setAttribute("min", today);
}


function getTicketById(id){
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+id;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(tickets => {

            const data = tickets.data;
            document.getElementById('ticket_id_update').value = data.ticketId;
            document.getElementById('customer_id_update').value = data['customerId'];
            document.getElementById('address_update').value = data.addressOfIssue;
            document.getElementById('ticket_status_update').value = data.ticketStatus;
            document.getElementById('ticket_type_update').value = data.ticketType;
            document.getElementById('estimated_cost_update').value = data.estimatedCost;
            document.getElementById('description_update').value = data['description'];
            document.getElementById('date_of_action_update').value = data.dateTimeOfAction;


}).catch(error => console.error('Network Error...'));
}

function updateTicket(){



    if(document.querySelector('.text-danger')!=null){
        return false;
       
    }

    var b = document.querySelectorAll('#formupdateTicket input');

    for(i=0;i<b.length;i++)
    {
        if(b[i].value=="")
        return false;
    }


    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    ticketId = document.getElementById('ticket_id_update').value;
    customerId = document.getElementById('customer_id_update').value;
    addressOfIssue = document.getElementById('address_update').value;
    ticketStatus = document.getElementById('ticket_status_update').value;
    ticketType = document.getElementById('ticket_type_update').value;
    estimatedCost = Number(document.getElementById('estimated_cost_update').value);
    my_description = document.getElementById('description_update').value;
    dateTimeOfAction = document.getElementById('date_of_action_update').value;

    dateTimeOfAction = formatDate(dateTimeOfAction);
    

    payload = {
        "ticketStatus": ticketStatus,
        "dateTimeOfAction": dateTimeOfAction,
        "ticketType": ticketType,
        "estimatedCost": estimatedCost,
        "addressOfIssue": addressOfIssue,
        "description": my_description,
        "customer":{"id":customerId}
    }



    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+ticketId;

    fetch(url,{

        method:"PUT",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)},
        body: JSON.stringify(payload)
        }

    )

        .then(response => response.json())

        .then(response => {
            reload();
        })


}



function passDatatoModal(id){

    document.getElementById('my_id_value').value = id;
}


function deleteTicket(ticketId){

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    var url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+ticketId;

    fetch(url,{

        method:"DELETE",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())
        .then(response => {

            reload();
        })

        .catch(error => console.error('Network Error...'));

}

function createTicket(){
   
    if(document.querySelector('.text-danger')!=null){
        return false;
    }

    var b = document.querySelectorAll('#CreateTicketByAdmin input');

    for(i=0;i<b.length-1;i++)
    {
        if(b[i].value=="")
        return false;
    }
    

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    

    customerId = document.getElementById('ticket_search_customer_id').value;
    addressOfIssue = document.getElementById('address').value;
    ticketStatus = document.getElementById('TicketStatus').value;
    ticketType = document.getElementById('TicketType').value;
    estimatedCost = document.getElementById('estimated_cost').value;
    my_description = document.getElementById('description').value;
    dateTimeOfAction = document.getElementById('date_of_action').value;

    dateTimeOfAction = formatDate(dateTimeOfAction);
    payload = {
        "ticketStatus": ticketStatus,
        "dateTimeOfAction": dateTimeOfAction,
        "ticketType": ticketType,
        "estimatedCost": estimatedCost,
        "addressOfIssue": addressOfIssue,
        "description": my_description,
        "customer":{"id":customerId}
    }


    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/';

    fetch(url,{

            method:"POST",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa(username+":"+ password)},
            body: JSON.stringify(payload)
        }

    )

        .then(response => response.json())

        .then(response => {
            reload();
        })
}

function searchByDate(){

    const date = document.getElementById('dateFromByOneDate').value

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-creation/'+date;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');



    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})
    

        .then(response =>{
            return response.json()})

        .then(tickets => {

            const data = tickets.data;
            

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.id+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.id+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
           
            document.getElementById('table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}



function searchByDates(){

    const dateFrom = document.getElementById('dateFromBy2Dates').value;
    const dateTo = document.getElementById('dateToBy2Dates').value;


    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-dates-of-creation/'+dateFrom+'&'+dateTo;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


        .then(response =>{
            return response.json()})

        .then(tickets => {

            const data = tickets.data;
            

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.id+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.id+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            
            document.getElementById('table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}

function searchByCustomer(){


    const customerId = document.getElementById('search_customer_id').value;
    event.preventDefault();


    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/tickets/'+customerId;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


        .then(response =>{
            return response.json()})

        .then(tickets => {

            const data = tickets.data;

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.id+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.id+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            document.getElementById('table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}

function formatDate(date){

    String.prototype.replaceAt = function(index, replacement) {
        return this.substring(0, index) + replacement + this.substring(index + replacement.length);
    }

    date = date.replaceAt(10," ");
    date+=":00";
    return date;

}

function fillDropdownList(data, element){
    let select = document.getElementById(element);
    for (let elt of data){
        if (elt.userCategory == "ADMIN") continue;
        let option = document.createElement('option');
        option.text = elt.id;
        option.value = elt.id;
        select.appendChild(option);
    }
}

//END TICKETS BY ADMIN

//TICKETS PENDING FOR ADMIN

function getTicketsForAdminPending(){

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets';

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(tickets => {



            const data = tickets.data;




            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";

            ;let counter =0;
            for(let ticket of data){
            
                if(ticket.ticketStatus=="PENDING" && counter<10){
                    
                html+="<tr><td id='table_ticket_id_pending'>"+ticket['ticketId']+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin_pending\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin_pending\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";
                    counter ++;
                    
                }

            }



            html+="</table>";

            document.getElementById('table_for_pending').innerHTML = html;






        })

        .catch(error => console.error('Network Error...'));

}

function getCustomerById(id){
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/'+id;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(customers => {


            const data = customers.data;
            document.getElementById('customer_id_update').value = id;
            document.getElementById('fname_update').value = data.firstName;
            document.getElementById('lname_update').value = data.lastName;
            document.getElementById('username_update').value = data.username;
            document.getElementById('vatNumber_update').value = data.vatNumber;
            document.getElementById('password_update').value = data.password;
            document.getElementById('address_update').value = data.address;

            document.getElementById('email_input').value = data.emailList[0].email;
            for (let i = 1; i <data.emailList.length; i++) {
                addEmailForUpdate();
                document.getElementById('email_input' + i).value = data.emailList[i].email;
            }

            document.getElementById('phone_number_input').value = data.phones[0].number;
            for (let i = 1; i < data.phones.length; i++) {
                addPhoneForUpdate();
                document.getElementById('phone_number_input' + i).value = data.phones[i].number;
            }


}).catch(error => console.error('Network Error...'));

}


function getSimpleCustomerById(){

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    const id=localStorage.getItem('userid');

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/'+id;

    

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(customers => {


            const data = customers.data;
            document.getElementById('fname_update').value = data.firstName;
            document.getElementById('lname_update').value = data.lastName;
            document.getElementById('username_update').value = data.username;
            document.getElementById('vatNumber_update').value = data.vatNumber;
            document.getElementById('password_update').value = data.password;
            document.getElementById('address_update').value = data.address;

            document.getElementById('email_input').value = data.emailList[0].email;
            for (let i = 1; i < data.emailList.length; i++) {
                addEmailForUpdate();
                document.getElementById('email_input' + i).value = data.emailList[i].email;
            }

            document.getElementById('phone_number_input').value = data.phones[0].number;
            for (let i = 1; i < data.phones.length; i++) {
                addPhoneForUpdate();
                document.getElementById('phone_number_input' + i).value = data.phones[i].number;
            }


}).catch(error => console.error('Network Error...'));

}

function updateCustomer(){


    if(document.querySelector('.text-danger')!=null){
        return false;
    }

    var b = document.querySelectorAll('#formUpdateCustomer input');

    for(i=0;i<b.length;i++)
    {
        if(b[i].value=="")
        return false;
    }


    const admin_username= localStorage.getItem('username');

    const admin_password= localStorage.getItem('password');
    
    var emailCount = document.getElementById("emailCount_update").value;
    var phoneCount = document.getElementById("phoneCount_update").value;

    let customerId = document.getElementById('customer_id_update').value;
    let firstName = document.getElementById("fname_update").value;
    let lastName = document.getElementById("lname_update").value;
    let username = document.getElementById("username_update").value;
    let vatNumber = document.getElementById("vatNumber_update").value;
    let password = document.getElementById("password_update").value;
    let address = document.getElementById("address_update").value;
  
    let phoneArray = new Array();
    phoneArray.push(document.getElementById("phone_number_input").value);
    for(i = 1; i<=phoneCount;i++){
        str = "phone_number_input" + i;
        phoneArray.push(document.getElementById(str).value);
    }

    let emailArray = new Array();
    emailArray.push(document.getElementById("email_input").value);
    for(i = 1; i<=emailCount;i++){
        str = "email_input" + i;
        emailArray.push(document.getElementById(str).value);
    }
    
    payload={
        "vatNumber" : vatNumber,
        "userCategory": "CUSTOMER",
        "firstName": firstName,
        "lastName": lastName,
        "username": username,
        "password": password,
        "address": address,
        "emailList": emailArray,
        "phones": phoneArray
    }

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/' + customerId;


    fetch(url,{

            method:"PUT",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa(admin_username+":"+ admin_password)
            },
            body: JSON.stringify(payload)
        }
    )
  
        .then(response => response.json())

        .then(response =>{
            reload();
        })
}


function updateSimpleCustomer(){

    const admin_username= localStorage.getItem('username');

    const admin_password= localStorage.getItem('password');

    const id=localStorage.getItem('userid');
    
    var emailCount = document.getElementById("emailCount_update").value;
    var phoneCount = document.getElementById("phoneCount_update").value;
    let firstName = document.getElementById("fname_update").value;
    let lastName = document.getElementById("lname_update").value;
    let username = document.getElementById("username_update").value;
    let vatNumber = document.getElementById("vatNumber_update").value;
    let password = document.getElementById("password_update").value;
    let address = document.getElementById("address_update").value;
   
    let phoneArray = new Array();
    phoneArray.push(document.getElementById("phone_number_input").value);
    for(i = 1; i<=phoneCount;i++){
        str = "phone_number_input" + i;
        phoneArray.push(document.getElementById(str).value);
    }

    let emailArray = new Array();
    emailArray.push(document.getElementById("email_input").value);
    for(i = 1; i<=emailCount;i++){
        str = "email_input" + i;
        emailArray.push(document.getElementById(str).value);
    }
    
    payload={
        "vatNumber" : vatNumber,
        "userCategory": "CUSTOMER",
        "firstName": firstName,
        "lastName": lastName,
        "username": username,
        "password": password,
        "address": address,
        "emailList": emailArray,
        "phones": phoneArray
    }

   
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/' + id;


    fetch(url,{

            method:"PUT",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa(admin_username+":"+ admin_password)
            },
            body: JSON.stringify(payload)
        }
    )
   
        .then(response => response.json())

        .then(response => JSON.stringify(response))

        reload();
}







function removePhone(){
    var counter = document.getElementById("phoneCount").value;
    var newCount = Number(counter);
    if(newCount>1){
        const element = document.getElementById('outerPhonediv'+counter);
        element.remove();
        newCount--;
        document.getElementById("phoneCount").value = String(newCount);
    }
}

function removePhoneForUpdate(){
    var counter = document.getElementById("phoneCount_update").value;
    var newCount = Number(counter);
    if(newCount>0){
        const element = document.getElementById('outerPhonediv1'+counter);
        element.remove();
        newCount--;
        document.getElementById("phoneCount_update").value = String(newCount);
    }
}

// function addPhone(){
//     var counter = document.getElementById("phoneCount").value;
//     var newCount = Number(counter);
//     newCount++;
//     var html = "<div id=\"outerPhonediv"+newCount+"\" class=\"mb-3 mt-3\">";
//     html+="<label for=\"phone\" class=\"form-label\">Phone:</label>";
//     html+="<input type=\"text\" class=\"form-control\" id=\"phone_number"+ newCount + "\" placeholder=\"Enter the phone\" name=\"phone\" required>";
//     html+="</div>"
//     var div = document.createElement('div');
//     div.innerHTML = html;
//     document.getElementById("phoneCount").value = String(newCount);
//     div.setAttribute('class', 'mb-3 mt-3');
//     document.getElementById("phoneDivs").prepend(div);
// }


function addEmail(){
    var counter = document.getElementById("emailCount").value;
    var newCount = Number(counter);
    newCount++;
    var html = "<div id=\"outerEmaildiv"+newCount+"\" class=\"mb-3 mt-3\">";
    html+="<label for=\"email\" class=\"form-label\">Email:</label>";
    html+="<input type=\"text\" class=\"form-control myEmailinputClass\" id=\"email"+ newCount + "\" placeholder=\"Enter the email\" name=\"email\" required>";
    html+="<span id=\"em_span"+ newCount +"\"</span>";
    html+="</div>";
    var div = document.createElement('div');
    
    div.innerHTML = html;
    document.getElementById("emailCount").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("emailDivs").prepend(div);

    emailInput = document.getElementById("email"+newCount);
    var helpMessage_new = document.getElementById("em_span"+newCount);

    emailInput.addEventListener('focusout', () => {
         regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      message = 'Must be a valid email address';
    
      if (regEx.test(emailInput.value)) {
        helpMessage_new.innerHTML = 'Success';
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-success';
        
      } else {
        helpMessage_new.innerHTML = message;
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-danger';
        
      }
    
    });
}

function addEmailForUpdate(){
    
    var counter = document.getElementById("emailCount_update").value;
    var newCount = Number(counter);
    newCount++;
    var html = "<div id=\"outerEmaildiv_update"+newCount+"\" class=\"mb-3 mt-3\">";
    html+="<label for=\"email\" class=\"form-label\">Email:</label>";
    html+="<input type=\"text\" class=\"form-control myEmailinputClassUpdate\" id=\"email_input"+ newCount + "\" placeholder=\"Enter the email\" name=\"email\" required>";
    html+="<span id=\"em_span_update"+ newCount +"\"</span>";
    html+="</div>";
    var div = document.createElement('div');
    
    div.innerHTML = html;
    document.getElementById("emailCount_update").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("emailDiv1_update").prepend(div);

    emailInput = document.getElementById("email_input"+newCount);
    var helpMessage_new = document.getElementById("em_span_update"+newCount);

    emailInput.addEventListener('focusout', () => {
        debugger;
    regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      message = 'Must be a valid email address';
    
      if (regEx.test(emailInput.value)) {
        helpMessage_new.innerHTML = 'Success';
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-success';
        
      } else {
        helpMessage_new.innerHTML = message;
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-danger';
        
      }
    
    });




}

function removeEmailForUpdate(){
    var counter = document.getElementById("emailCount_update").value;
    var newCount = Number(counter);
    if(newCount>0){
        const element = document.getElementById("outerEmaildiv_update"+counter);
        element.remove();
        newCount--;
        document.getElementById("emailCount_update").value = String(newCount);
    }
}

function removeEmail(){
    var counter = document.getElementById("emailCount").value;
    var newCount = Number(counter);
    if(newCount>1){
        const element = document.getElementById('outerEmaildiv'+counter);
        element.remove();
        newCount--;
        document.getElementById("emailCount").value = String(newCount);
    }
}

function removePhone(){
    var counter = document.getElementById("phoneCount").value;
    var newCount = Number(counter);
    if(newCount>1){
        const element = document.getElementById('outerPhonediv'+counter);
        element.remove();
        newCount--;
        document.getElementById("phoneCount").value = String(newCount);
    }
}

function addPhone(){
    var counter = document.getElementById("phoneCount").value;
    var newCount = Number(counter);
    newCount++;
    var html = "<div id=\"outerPhonediv"+newCount+"\" class=\"mb-3 mt-3\">";
    html+="<label for=\"phone\" class=\"form-label\">Phone:</label>";
    html+="<input type=\"text\" class=\"form-control myphoneinputClass\" id=\"phone_number"+ newCount + "\" placeholder=\"Enter the phone\" name=\"phone\" required>";
    html+="<span id=\"ph_span"+ newCount +"\"</span>";
    html+="</div>"
    var div = document.createElement('div');
    div.innerHTML = html;
    document.getElementById("phoneCount").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("phoneDivs").prepend(div);


    phoneInput = document.getElementById("phone_number"+newCount);
    var helpMessage_new = document.getElementById("ph_span"+newCount);

    phoneInput.addEventListener('focusout', () => {
    regEx = /^\d{10}$/;
    message = 'Must be a ten digit phone';
    
      if (regEx.test(phoneInput.value)) {
        helpMessage_new.innerHTML = 'Success';
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-success';
        
      } else {
        helpMessage_new.innerHTML = message;
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-danger';
        
      }
    
    });

}




function addPhoneForUpdate(){
    
    debugger;
    var counter = document.getElementById("phoneCount_update").value;
    var newCount = Number(counter);
    newCount++;
    var html = "<div id=\"outerPhonediv1"+newCount+"\" class=\"mb-3 mt-3\">";
    html+="<label for=\"phone\" class=\"form-label\">Phone:</label>";
    html+="<input type=\"text\" class=\"form-control myphoneinputClassUpdate\" id=\"phone_number_input"+ newCount + "\" placeholder=\"Enter the phone\" name=\"phone_number_input\" required>";
    html+="<span id=\"ph_span_update"+ newCount +"\"</span>";
    html+="</div>"
    var div = document.createElement('div');
    div.innerHTML = html;
    document.getElementById("phoneCount_update").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("phoneDiv1_update").prepend(div);

    phoneInput = document.getElementById("phone_number_input"+newCount);
    var helpMessage_new = document.getElementById("ph_span_update"+newCount);

    phoneInput.addEventListener('focusout', () => {
        debugger;
    regEx = /^\d{10}$/;
    message = 'Must be a ten digit phone';
    debugger;
    
      if (regEx.test(phoneInput.value)) {
        debugger;
        helpMessage_new.innerHTML = 'Success';
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-success';
        
      } else {
        debugger;
        helpMessage_new.innerHTML = message;
        console.log(helpMessage_new);
        helpMessage_new.className = 'text-danger';
        
      }
    
    });

}




function createCustomer(){

    if(document.querySelector('.text-danger')!=null){return false;}

    var b = document.querySelectorAll('#formCreateCustomer input');

    for(i=0;i<b.length;i++){if(b[i].value=="") return false;}


    const admin_username= localStorage.getItem('username');

    const admin_password= localStorage.getItem('password');

    
    
    var emailCount = document.getElementById("emailCount").value;
    var phoneCount = document.getElementById("phoneCount").value;

    let firstName = document.getElementById("fname").value;
    let lastName = document.getElementById("lname").value;
    let username = document.getElementById("username").value;
    let vatNumber = document.getElementById("vatNumber").value;
    let password = document.getElementById("password").value;
    let address = document.getElementById("address").value;
    
    let phoneArray = new Array();
    for(i = 1; i<=phoneCount;i++){
        str = "phone_number" + i;
        phoneArray.push(document.getElementById(str).value);
    }
    let emailArray = new Array();
    for(i = 1; i<=emailCount;i++){
        str = "email" + i;
        emailArray.push(document.getElementById(str).value);
    }


    
    payload={
        "vatNumber" :vatNumber,
        "userCategory": "CUSTOMER",
        "firstName": firstName,
        "lastName": lastName,
        "username": username,
        "password": password,
        "address": address,
        "emailList": emailArray,
        "phones": phoneArray
    }

   
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/';


    fetch(url,{

            method:"POST",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa(admin_username+":"+ admin_password)
            },
            body: JSON.stringify(payload)
        }
    )

        .then(response => response.json())

        .then(response => {

            reload();
        })
        .catch(error => error('Network Error...'+error));
}

function getCustomersForAdmin(){


    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers';

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(customers => {

            const data = customers.data;
            
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
                "<th>User Category</th><th>Vat number</th><th>Address</th>" +
                "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

                    
                        for (let customer of data) {

                            if(customer.userCategory=="CUSTOMER") {

                            html += "<tr><td id='customer_id'>" + customer['id'] + "</td><td>" + customer.firstName + "</td><td>" + customer.lastName + "<td>" + customer.username + "" +
                                "</td><td>" + customer.userCategory + "" +
                                "</td><td>" + customer.vatNumber + "</td><td>" + customer.address + "</td><td><a style = 'cursor:pointer;color:blue;' data-bs-toggle=\"modal\" data-bs-target=\"#viewCustomerEmails\" onclick= 'getEmailList("+customer['id']+")'>" + customer.emailList[0].email +"</a></td><td>" + customer.phones[0].number + "</td><td><button  onclick='getCustomerById(" + customer['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
                                "  Edit Customer\n" +
                                "</button></td><td><button onclick='passDatatoModalForCustomerByAdmin(" + customer['id'] + ")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_Customer_by_admin\">\n" +
                                "  Delete Customer\n" +
                                "</button></td></tr>";

                        }
                    }


            html+="</table>";

            document.getElementById('tableForCustomers').innerHTML = html;

        })

        .catch(error => console.error('Network Error...'+error));

}


function passDatatoModalForCustomerByAdmin(id){

    document.getElementById('my_value').value = id;
}

function passDatatoModalForUpdate(id){

    document.getElementById('customer_id_update').value = id;
}


function deleteCustomer(CustomerId){

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    var url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/'+CustomerId;

    fetch(url,{

        method:"DELETE",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())
        .then(response => {
            reload();
        })

        .catch(error => console.error('Network Error...'+error));

}


function searchCustomerByVatNumber(){

    

    const vat = document.getElementById('VatNumberSearch').value;
    event.preventDefault();

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/vat/'+vat;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


    .then(response => response.json())

    .then(customers => {

        const data = customers.data;

        if(data.length!=0){
        var html="<table class='table table-hover'>";

        html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

                        html += "<tr><td id='customer_id'>" + data['id'] + "</td><td>" + data.firstName + "</td><td>" + data.lastName + "<td>" + data.username + "" +
                            "</td><td>" + data.userCategory + "" +
                            "</td><td>" + data.vatNumber + "</td><td>" + data.address + "</td><td>" + data.emailList[0].email +"</td><td>" + data.phones[0].number + "</td><td><button  onclick='getCustomerById(" + data['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
                            "  Edit Customer\n" +
                            "</button></td><td><button onclick='passDatatoModalForCustomerByAdmin("+data['id']+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_Customer_by_admin\">\n" +
                            "  Delete Customer\n" +
                            "</button></td></tr>";


        html+="</table>";
        }else{
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

            html+="</table>";
        }
        document.getElementById('tableForCustomers').innerHTML = html;

    })

    .catch(error => console.error('Network Error...'+error));
}


function searchCustomerByemail(){

    

    const emailSearch = document.getElementById('customerEmail').value;
    event.preventDefault();

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/email/'+emailSearch;

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


    .then(response => response.json())

    .then(customers => {

        const data = customers.data;
        
        if(data.length!=0){
        var html="<table class='table table-hover'>";
        
        html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";
                                  
                        html += "<tr><td id='customer_id'>" + data[0]['id'] + "</td><td>" + data[0].firstName + "</td><td>" + data[0].lastName + "<td>" + data[0].username + "" +
                            "</td><td>" + data[0].userCategory + "" +
                            "</td><td>" + data[0].vatNumber + "</td><td>" + data[0].address + "</td><td>" + data[0].emailList[0].email +"</td><td>" + data[0].phones[0].number + "</td><td><button  onclick='getCustomerById(" + data['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
                            "  Edit Customer\n" +
                            "</button></td><td><button onclick='passDatatoModalForCustomerByAdmin(" + data['id'] + ")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_Customer_by_admin\">\n" +
                            "  Delete Customer\n" +
                            "</button></td></tr>";


        html+="</table>";
        }else{
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

            html+="</table>";
        }
        document.getElementById('tableForCustomers').innerHTML = html;

    })

    .catch(error => console.error('Network Error...'+error));

}

function reload() {
    window.location.reload(true);
}

function clickBody(){
    window.location.reload(true);
}


//Get tickets for simple customer

function getTicketsForSimpleCustomer(){

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    const id=localStorage.getItem('userid');

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/tickets/'+id;



    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(tickets => {

            const data = tickets.data;
           
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID Ticket</th><th>ID Customer</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th>";


            for(let ticket of data){

                html+="<tr><td id='tickId'>"+ticket.id+"</td><td id='table_cust_id'>"+id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td></tr>";

            }

            html+="</table>";

            document.getElementById('cust_table').innerHTML = html;

        })

        .catch(error => console.error('Network Error...'));

}

function printCustomerNames(){
    html=`${localStorage.getItem('fname')} ${localStorage.getItem('lname')}`
    document.getElementById('cstNames').innerHTML=html;
}

function getEmailList(id){
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/'+id;

    const first_div_element = document.getElementById("emailLista");

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())

        .then(customers => {


            const data = customers.data;
            html="";
            html+="<table class='table table-hover' style='text-align:center'>";
            html+="<tr><th>Customer Email List</th></tr>"
            for (let i = 0;i<data.emailList.length;i++){
                html += "<tr><td>"+data.emailList[i].email+"</td></tr>"
            }
            div = document.createElement('div');
            div.innerHTML = html;
            first_div_element.appendChild(div);
           


}).catch(error => console.error('Network Error...'));
}