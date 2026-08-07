let myButton=document.querySelector("button");
let myHeading=document.querySelector("h1");

function changeTitle(){
    let userName=prompt("Enter your name");
    localStorage.setItem("userName",userName);
    if(userName===null||userName===""){
        return;
    }
    myHeading.textContent="Mozilla is cool,"+userName;
}

myButton.onclick=()=>{
    changeTitle();
}

if(localStorage.getItem("userName")!=null){
    myHeading.textContent="Mozilla is cool,"+localStorage.getItem("userName");
}