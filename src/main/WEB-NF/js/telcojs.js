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
        headers: {'Authorization': 'Basic ' + btoa('login:password')}}
    )
    .then(response => response.json())
    .then(tickets => {

        const data = tickets.data;
        
        const table = document.createElement('table');
        
                for(let user of data){
                    const li = document.createElement('li');
                    li.innerHTML = `${user.first_name} ${user.last_name}`
                    ul.append(li);
                }
        document.body.append(table);  


     

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
