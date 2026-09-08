function checkUsername() {
    let username=$("#username");
    let info=$("#username-info");

    username.on("blur",function(){
        $.ajax({
            url: "checkUsername",
            type: "get",
            data:{
               username: username.val()
            },
            success: function(resp){
                let result=resp.trim();
                console.log(result);
                if(result==="true"){
                    info.text("account exists.")
                        .css("color","green");
                } else{
                    info.text("account not found.")
                        .css("color","red");
                }
            },
            error: function(resp){
                console.error("check username error");
                info.text("check username error")
                    .css("color","red");
            }
        })
    });
}

checkUsername();