function login(){

    username = document.getElementById('usr').value;
    password = document.getElementById('pwd').value;


    payload = {
        "username": username,
        "password": password,
        "userCategory": "ADMIN"
    }

    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/login/';

    fetch(url,{

            method:"POST",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa('Pw_418assd:pd_1718Aasd')},
            body: JSON.stringify(payload)
        }

    )

        .then(response => response.json())

        
        .then(response => {   
            const data = response.data;
            localStorage.setItem('userCategory',data.userCategory);
            localStorage.setItem('username',data.username);
            localStorage.setItem('password',data.password);
            localStorage.setItem('userid',data.id);
            localStorage.setItem('fname',data.firstName);
            localStorage.setItem('lname',data.lastName);
            
            if(data.userCategory=="ADMIN") {
                window.location.href="./main_admin.html";
            }
            if(data.userCategory=="CUSTOMER") {
                window.location.href="./customer.html";
            }
            });
}

function isloggedin(){
    if(localStorage.getItem('username')==null){
        window.location.href='login.html';
    }
}
