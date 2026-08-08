const btn=document.querySelector("button");
btn.addEventListener("click",()=>{
    btn.textContent="You clicked me!";
    setTimeout(()=>{
        btn.textContent="Click me!";
    },1000);
});