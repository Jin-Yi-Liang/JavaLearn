const myImage=document.querySelector("img");
myImage.onclick=()=>{
  const mySrc=myImage.getAttribute("src");
  if(mySrc==="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9MFA41GsiRS3ptiIXxVrZIFIZNHWTkH0YW1Nt5evo8kRaPXCZOjUNT66U0u9ZPZaK_kA04bD4Z8rx0o6XxssmQblBQp0pml-WDpyfXDs9&s=10"){
    myImage.setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFw7cczKgimGwHz_Lxpzrx45kRMkne8dcjDIW58PSXrFPB5TvNHh-krdda_-jSKXnAkd1fGIgc3mpMfVH9SBW1WOAIZhIP8eIzZL473xxR&s=10");
  }
  else{
    myImage.setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9MFA41GsiRS3ptiIXxVrZIFIZNHWTkH0YW1Nt5evo8kRaPXCZOjUNT66U0u9ZPZaK_kA04bD4Z8rx0o6XxssmQblBQp0pml-WDpyfXDs9&s=10");
  
  }
}