function login(){

    username = document.getElementById('usr').value;
    password = document.getElementById('pwd').value;



    payload = {
        "username": username,
        "password": password,
        "userCategory": "ADMIN"
    }
debugger;
    const url = 'http://localhost:8080/advantage-telco-project-training-2022/api/login/';

    fetch(url,{

            method:"POST",

            headers: {

                'Accept': 'application/json',

                'Content-Type': 'application/json',

                'Authorization': 'Basic ' + btoa('root1234:Ro_ot1234')},
            body: JSON.stringify(payload)
        }

    )

        .then(response => response.json())

        .then(response => {
            window.localStorage.setItem('userCategory',response.data.userCategory);
            window.localStorage.setItem('username',response.data.username);
            window.localStorage.setItem('password',response.data.password);
            window.localStorage.setItem('userid',response.data.id);
            if(response.data.userCategory=="ADMIN") {
                window.location.href="./main_admin.html";}
            if(response.data.userCategory=="CUSTOMER") {
                window.location.href="./customer.html";}
            console.log(JSON.stringify(response))})
}

function isloggedin(){
    if(localStorage.getItem('username')==null){
        window.location.href='login.html';
    }
}
