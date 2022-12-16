function redirect(){
    
    var url='logout.html'
    window.location.href=url;
    
}


function getTicketsForAdmin(){

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets';

    const username="pw_418asd";

    const password="pd_1718Aasd";

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')}}

    )

        .then(response => response.json())

        .then(tickets => {



            const data = tickets.data;

            console.log(data[0]);



            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th>><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.ticketId+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
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

    const username="pw_418asd";

    const password="pd_1718Aasd";

    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')}}

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


    const username="pa_418assd";

    const password="psa_7178Aasd";

    ticketId = document.getElementById('ticket_id_update').value;
    customerId = document.getElementById('customer_id_update').value;
    addressOfIssue = document.getElementById('address_update').value;
    ticketStatus = document.getElementById('ticket_status_update').value;
    ticketType = document.getElementById('ticket_type_update').value;
    estimatedCost = document.getElementById('estimated_cost_update').value;
    my_description = document.getElementById('description_update').value;
    dateTimeOfAction = document.getElementById('date_of_action_update').value;

    console.log(ticketId);

    payload = {
        "ticketStatus": ticketStatus,
        "dateTimeOfAction": "2023-01-13 23:00:00",
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

            'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')},
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
    console.log(ticketId);
    debugger;
    var url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+ticketId;
console.log(url);
debugger;

    fetch(url,{

        method:"DELETE",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')}}

    )

        .then(response => response.json())
        .then(response => console.log(JSON.stringify(response)))

        .catch(error => console.error('Network Error...'));

}

function createTicket(){
    const username="pa_418assd";

    const password="psa_7178Aasd";

    ticketId = document.getElementById('ticket_id_update').value;
    customerId = document.getElementById('cust_id').value;
    addressOfIssue = document.getElementById('address').value;
    ticketStatus = document.getElementById('TicketStatus').value;
    ticketType = document.getElementById('TicketType').value;
    estimatedCost = document.getElementById('estimated_cost').value;
    my_description = document.getElementById('description').value;
    dateTimeOfAction = document.getElementById('date_of_action_update').value;

    console.log(ticketId);

    payload = {
        "ticketStatus": ticketStatus,
        "dateTimeOfAction": "2023-01-13 23:00:00",
        "ticketType": ticketType,
        "estimatedCost": estimatedCost,
        "addressOfIssue": addressOfIssue,
        "description": my_description,
        "customer":{"id":customerId}
    }



    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/'+ticketId;

    fetch(url,{

            method:"POST",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')},
            body: JSON.stringify(payload)
        }

    )

        .then(response => response.json())

        .then(response => console.log(JSON.stringify(response)))
}

function searchByDate(){
    alert("aksjdnaksjdhalskdn");

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-creation/2022-12-17';

    const username="pa_418assd";

    const password="pd_1718Aasd";


    fetch(url,{

        method:"GET",

        headers: {

            'Accept': 'application/json',

            'Content-Type': 'application/json',

            'Authorization': 'Basic ' + btoa('pa_418assd:psa_7178Aasd')}}

    )


        .then(response =>{response.json()})

        .then(tickets => {


            const data = tickets.data;

            document.getElementById('table').innerHTML= "";
            document.getElementById('search_by_date').innerHTML = "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th>><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th><th>Edit</th><th>Delete</th>";



            for(let ticket of data){

                html+="<tr><td id='table_ticket_id'>"+ticket.ticketId+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.ticketId+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.ticketId+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";

            }



            html+="</table>";
            console.log(html);
            document.getElementById('search_by_date').innerHTML = html;


        }).catch(error => console.error('Network Error...'));
}