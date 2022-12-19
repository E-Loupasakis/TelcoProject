function field_check(){
    const search_type = document.getElementById('searchType').value;
    if(search_type=="byCreation"){
        document.getElementById('dateSearch').style.display = 'inline-block';
        document.getElementById('dateFrom').style.display = 'none';
        document.getElementById('dateTo').style.display = 'none';
        document.getElementById('search_customer_id').style.display = 'none';
    }
    if(search_type=="byCreationRange"){
        document.getElementById('dateSearch').style.display = 'none';
        document.getElementById('dateFrom').style.display = 'inline-block';
        document.getElementById('dateTo').style.display = 'inline-block';
        document.getElementById('search_customer_id').style.display = 'none';
    }
    if(search_type=="byAction"){
        document.getElementById('dateSearch').style.display = 'inline-block';
        document.getElementById('dateFrom').style.display = 'none';
        document.getElementById('dateTo').style.display = 'none';
        document.getElementById('search_customer_id').style.display = 'none';
    }
    if(search_type=="byActionRange"){
        document.getElementById('dateSearch').style.display = 'none';
        document.getElementById('dateFrom').style.display = 'inline-block';
        document.getElementById('dateTo').style.display = 'inline-block';
        document.getElementById('search_customer_id').style.display = 'none';
    }
    if(search_type=="byCustomerId"){
        document.getElementById('dateSearch').style.display = 'none';
        document.getElementById('dateFrom').style.display = 'none';
        document.getElementById('dateTo').style.display = 'none';
        document.getElementById('search_customer_id').style.display = 'inline-block';
    }

}


function s_date_search(){


    const customerId = localStorage.getItem('userid');
    const username= localStorage.getItem('username');
    const password= localStorage.getItem('password');
    const search_type = document.getElementById('searchType').value;
    const date = document.getElementById('dateSearch').value;
    const dateFrom = document.getElementById('dateFrom').value;
    const dateTo = document.getElementById('dateTo').value;

    var url="";


    if(date==""&&(dateFrom==""||dateTo=="")) return "You have to set a date";

    if(search_type=="byCreation"){
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-creation/search-by-customer/'+date+'&'+customerId;}
    if(search_type=="byCreationRange"){
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-dates-of-creation/search-by-customer/'+ dateFrom +'&'+ dateTo + '&'+customerId;}
    if(search_type=="byAction"){
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-action/search-by-customer/'+date+'&'+customerId;}
    if(search_type=="byActionRange"){
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-dates-of-action/search-by-customer/'+ dateFrom +'&'+ dateTo + '&'+ customerId;}
    
    
    fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})
    
        .then(response =>{
            return response.json()})

        .then(tickets => {

            const data = tickets.data;
            console.log(data);

            document.getElementById('cust_table').innerHTML= "";
            var html="<table class='table table-hover'>";

            html+="<tr><th>ID</th><th>Ticket Type</th><th>Ticket Status</th><th>Cost</th><th>Address</th>" +
                "<th>Description</th><th>Date of Creation</th>" +
                "<th>Date of Action</th>";


            for(let ticket of data){
                html+="<tr><td id='table_ticket_id'>"+ticket.id+"</td><td>"+ticket.ticketType+"</td><td>"+ticket.ticketStatus+"<td>"+ticket.estimatedCost+"" +
                    "</td><td>"+ticket.addressOfIssue+"</td><td>"+ticket['description']+"" +
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td></tr>";
            }

            html+="</table>";
            console.log(html);
            document.getElementById('cust_table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}


function s_date_search_admin(){


    const username= localStorage.getItem('username');
    const password= localStorage.getItem('password');
    const search_type = document.getElementById('searchType').value;
    const date = document.getElementById('dateSearch').value;
    const dateFrom = document.getElementById('dateFrom').value;
    const dateTo = document.getElementById('dateTo').value;
    const customerId = document.getElementById('search_customer_id').value;
    
    var url="";



    if(search_type=="byCreation"){
        if(date=="") return "You have to set a date";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-creation/'+date;}
    if(search_type=="byCreationRange"){
        if(dateFrom==""||dateTo=="") return "You have to set the dates";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-dates-of-creation/'+ dateFrom +'&'+ dateTo;}
    if(search_type=="byAction"){
        if(date=="") return "You have to set a date";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-date-of-action/'+date;}
    if(search_type=="byActionRange"){
        if(dateFrom==""||dateTo=="") return "You have to set the dates";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/tickets/search-by-dates-of-action/'+ dateFrom +'&'+ dateTo;}
    if(search_type=="byCustomerId"){
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/tickets/'+customerId;}
    
    
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
                    "</td><td>"+ticket.dateTimeOfCreation+"</td><td>"+ticket.dateTimeOfAction+"</td><td><button  onclick='getTicketById("+ticket.id+")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_ticket_by_admin\">\n" +
                    "  Edit Ticket\n" +
                    "</button></td><td><button onclick='passDatatoModal("+ticket.id+")' type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#delete_ticket_by_admin\">\n" +
                    "  Delete Ticket\n" +
                    "</button></td></tr>";
            }

            html+="</table>";
            console.log(html);
            document.getElementById('table').innerHTML = html;


        }).catch(error => console.error('Network Error...'+ error));
}


