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

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";


            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket['ticketId']+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
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
var mm = today.getMonth() + 1; //January is 0!
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
            //console.log(data);
            document.getElementById('address_update').value = data.addressOfIssue;
            document.getElementById('ticket_status_update').value = data.ticketStatus;
            document.getElementById('ticket_type_update').value = data.ticketType;
            document.getElementById('estimated_cost_update').value = data.estimatedCost;
            document.getElementById('description_update').value = data['description'];
            // console.log(data.dateTimeOfAction.reverse());
            document.getElementById('date_of_action_update').value = data.dateTimeOfAction;


}).catch(error => console.error('Network Error...'));
}

function updateTicket(){


    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    ticketId = document.getElementById('ticket_id_update').value;
    customerId = document.getElementById('customer_id_update').value;
    addressOfIssue = document.getElementById('address_update').value;
    ticketStatus = document.getElementById('ticket_status_update').value;
    ticketType = document.getElementById('ticket_type_update').value;
    estimatedCost = document.getElementById('estimated_cost_update').value;
    my_description = document.getElementById('description_update').value;
    dateTimeOfAction = document.getElementById('date_of_action_update').value;

    dateTimeOfAction = formatDate(dateTimeOfAction);
    console.log(ticketId);

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

        .then(response => console.log(JSON.stringify(response)))
}



function passDatatoModal(id){

    document.getElementById('my_id_value').value = id;
}


function deleteTicket(ticketId){

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    console.log(ticketId);
    var url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+ticketId;

    fetch(url,{

        method:"DELETE",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa(username+":"+ password)}}

    )

        .then(response => response.json())
        .then(response => console.log(JSON.stringify(response)))

        .catch(error => console.error('Network Error...'));

}

function createTicket(){
    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');

    //ticketId = document.getElementById('ticket_id_update').value;
    customerId = document.getElementById('cust_id').value;
    addressOfIssue = document.getElementById('address').value;
    ticketStatus = document.getElementById('TicketStatus').value;
    ticketType = document.getElementById('TicketType').value;
    estimatedCost = document.getElementById('estimated_cost').value;
    my_description = document.getElementById('description').value;
    dateTimeOfAction = document.getElementById('date_of_action').value;




    console.log(dateTimeOfAction);
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

        .then(response => console.log(JSON.stringify(response)))
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
            console.log(data);

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            console.log(html);
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
            console.log(data);

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            console.log(html);
            document.getElementById('table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}

function searchByCustomer(){


    const customerId = document.getElementById('search_customer_id').value;
    event.preventDefault();


    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/tickets/'+customerId

    const username= localStorage.getItem('username');

    const password= localStorage.getItem('password');


    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


        .then(response =>{
            return response.json()})

        .then(tickets => {

            const data = tickets.data;
            console.log(data);

            document.getElementById('table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            console.log(html);
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


function addEmail(){
    var counter = document.getElementById("emailCount").value;
    var newCount = Number(counter);
    newCount++;
    var html = "<div id=\"outerEmaildiv"+newCount+"\" class=\"mb-3 mt-3\">";
    html+="<label for=\"email\" class=\"form-label\">Email:</label>";
    html+="<input type=\"text\" class=\"form-control\" id=\"email"+ newCount + "\" placeholder=\"Enter the email\" name=\"email\" required>";
    html+="</div>"
    var div = document.createElement('div');

    div.innerHTML = html;
    document.getElementById("emailCount").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("emailDivs").prepend(div);
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
    html+="<input type=\"text\" class=\"form-control\" id=\"phone_number"+ newCount + "\" placeholder=\"Enter the phone\" name=\"phone\" required>";
    html+="</div>"
    var div = document.createElement('div');
    div.innerHTML = html;
    document.getElementById("phoneCount").value = String(newCount);
    div.setAttribute('class', 'mb-3 mt-3');
    document.getElementById("phoneDivs").prepend(div);
}


class Customer{
    constructor(){
        this.emailCount = document.getElementById("emailCount").value;
        this.phoneCount = document.getElementById("phoneCount").value;
        this.userCategory = "CUSTOMER";
        this.firstName = document.getElementById("fname").value;
        this.lastName = document.getElementById("lname").value;
        this.username = document.getElementById("username").value;
        this.vatNumber = document.getElementById("vatNumber").value;
        this.password = document.getElementById("password").value;
        this.address = document.getElementById("address").value;
        this.phoneArray = new Array();
        for(i = 1; i<=this.phoneCount;i++){
            str = "phone_number" + i;
            this.phoneArray.push(document.getElementById(str).value);
        }
        let emailArray = new Array();
        for(i = 1; i<=this.emailCount;i++){
            str = "email" + i;
            this.emailArray.push(document.getElementById(str).value);
        }
    }
}

function createCustomer(){

    const admin_username= localStorage.getItem('username');

    const admin_password= localStorage.getItem('password');

    const customer = new Customer();
    
    // var emailCount = document.getElementById("emailCount").value;
    // var phoneCount = document.getElementById("phoneCount").value;

    // let firstName = document.getElementById("fname").value;
    // let lastName = document.getElementById("lname").value;
    // let username = document.getElementById("username").value;
    // let vatNumber = document.getElementById("vatNumber").value;
    // let password = document.getElementById("password").value;
    // let address = document.getElementById("address").value;
    // debugger;
    // let phoneArray = new Array();
    // for(i = 1; i<=phoneCount;i++){
    //     str = "phone_number" + i;
    //     phoneArray.push(document.getElementById(str).value);
    // }
    // let emailArray = new Array();
    // for(i = 1; i<=emailCount;i++){
    //     str = "email" + i;
    //     emailArray.push(document.getElementById(str).value);
    // }
    
    payload={
        "vatNumber" : customer.vatNumber,
        "userCategory": customer.userCategory,
        "firstName": customer.firstName,
        "lastName": customer.lastName,
        "username": customer.username,
        "password": customer.password,
        "address": customer.address,
        "emailList": customer.emailArray,
        "phones": customer.phoneArray
    }

    alert(JSON.stringify(payload));
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
    // console.log(JSON.stringify(payload))
    // console.log(JSON.stringify(response))
        .then(response => response.json())

        .then(response => alert(JSON.stringify(response)))
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
            console.log(data);
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th>" +
                "<th>User Category</th><th>Vat number</th><th>Address</th>" +
                "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

                    
                        for (let customer of data) {
                            
                           // Object.keys(stringArray).map(str => console.log(stringArray[str]))     
                            
                            if(customer.userCategory=="CUSTOMER") {
                            html += "<tr><td id='customer_id'>" + customer['id'] + "</td><td>" + customer.firstName + "</td><td>" + customer.lastName + "<td>" + customer.username + "" +
                                "</td><td>" + customer.password + "</td><td>" + customer.userCategory + "" +
                                "</td><td>" + customer.vatNumber + "</td><td>" + customer.address + "</td><td>" + customer.emailList[0].email +"</td><td>" + customer.phones[0].number + "</td><td><button  onclick='getCustomerById(" + customer['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
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
        .then(response => console.log(JSON.stringify(response)))

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
        console.log(data);
        if(data!=null){
        var html="<table class='table table-hover'>";

        html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";
                        
                       // Object.keys(stringArray).map(str => console.log(stringArray[str]))     
                        
                       
                        html += "<tr><td id='customer_id'>" + data['id'] + "</td><td>" + data.firstName + "</td><td>" + data.lastName + "<td>" + data.username + "" +
                            "</td><td>" + data.password + "</td><td>" + data.userCategory + "" +
                            "</td><td>" + data.vatNumber + "</td><td>" + data.address + "</td><td>" + data.emailList[0].email +"</td><td>" + data.phones[0].number + "</td><td><button  onclick='getCustomerById(" + data['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
                            "  Edit Customer\n" +
                            "</button></td><td><button onclick='passDatatoModalForCustomerByAdmin(" + data['id'] + ")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_Customer_by_admin\">\n" +
                            "  Delete Customer\n" +
                            "</button></td></tr>";


        html+="</table>";
        }else{
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th>" +
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
        console.log(data);
        if(data!=null){
        var html="<table class='table table-hover'>";
        
        html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";
                   
                       // Object.keys(stringArray).map(str => console.log(stringArray[str]))     
                        
                       
                        html += "<tr><td id='customer_id'>" + data[0]['id'] + "</td><td>" + data[0].firstName + "</td><td>" + data[0].lastName + "<td>" + data[0].username + "" +
                            "</td><td>" + data[0].password + "</td><td>" + data[0].userCategory + "" +
                            "</td><td>" + data[0].vatNumber + "</td><td>" + data[0].address + "</td><td>" + data[0].emailList[0].email +"</td><td>" + data[0].phones[0].number + "</td><td><button  onclick='getCustomerById(" + data['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
                            "  Edit Customer\n" +
                            "</button></td><td><button onclick='passDatatoModalForCustomerByAdmin(" + data['id'] + ")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_Customer_by_admin\">\n" +
                            "  Delete Customer\n" +
                            "</button></td></tr>";


        html+="</table>";
        }else{
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th>" +
            "<th>User Category</th><th>Vat number</th><th>Address</th>" +
            "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";

            html+="</table>";
        }
        document.getElementById('tableForCustomers').innerHTML = html;

    })

    .catch(error => console.error('Network Error...'+error));
}