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

function customer_field_check(){
    const search_type = document.getElementById('searchType').value;
    if(search_type=="byVat"){
        document.getElementById('vatSearch').style.display = 'inline-block';
        document.getElementById('emailSearch').style.display = 'none';
    }
    if(search_type=="byEmail"){
        document.getElementById('vatSearch').style.display = 'none';
        document.getElementById('emailSearch').style.display = 'inline-block';
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

function s_customers_search_admin(){
    // const customerId = localStorage.getItem('userid');
    const username= localStorage.getItem('username');
    const password= localStorage.getItem('password');
    const search_type = document.getElementById('searchType').value;
    const vat = document.getElementById('vatSearch').value;
    const email = document.getElementById('emailSearch').value;

    var url="";


    if(search_type=="byVat"){
        if(vat=="") return "You have to enter VAT number";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/vat/'+vat;}
    if(search_type=="byEmail"){
        if(email=="") return "You have to enter VAT number";
        url = 'http://localhost:8080/advantage-telco-project-training-2022/api/customers/find/email/'+email;}
    
    
        fetch(url, {method:"GET", headers: {'Authorization': 'Basic ' + btoa(username+":"+ password)}})


        .then(response => response.json())
    
        .then(customers => {
    
            const data = customers.data;
            if(data==null){
                var html="<table class='table table-hover'>";
    
                html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
                "<th>User Category</th><th>Vat number</th><th>Address</th>" +
                "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";
    
                html+="</table>";
            }
            else if(data.length!=0){
            if(search_type=="byEmail"){var data2=data[0];}
            else{var data2 = data;}
            var html="<table class='table table-hover'>";
            html+="<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th>" +
                "<th>User Category</th><th>Vat number</th><th>Address</th>" +
                "<th>Email List</th><th>Phone List</th><th>Edit</th><th>Delete</th>";
    
                            html += "<tr><td id='customer_id'>" + data2['id'] + "</td><td>" + data2.firstName + "</td><td>" + data2.lastName + "<td>" + data2.username + "" +
                                "</td><td>" + data2.userCategory + "" +
                                "</td><td>" + data2.vatNumber + "</td><td>" + data2.address + "</td><td><a style = 'cursor:pointer;color:blue;' data-bs-toggle=\"modal\" data-bs-target=\"#viewCustomerEmails\" onclick= 'getEmailList("+data2['id']+")'>" + data2.emailList[0].email +"</td><td>" + data2.phones[0].number + "</td><td><button  onclick='getCustomerById(" + data2['id'] + ")' type=\"button\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#edit_Customer_by_admin\">\n" +
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
    
        }).catch(error => console.error('Network Error...'+ error));
}
