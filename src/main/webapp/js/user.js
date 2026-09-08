function query(){
    let query=$("#query");
    let tbody=$("#content");
    query.on("click",_=>{
        $.ajax({
            url:"/user/query",
            method:"GET",
            dataType:"json",
            success:function(student){
                console.log("get student data successfully",student);
                tbody.empty();
                let tr=$("<tr></tr>");
                tr.append($("<td></td>").text(student.id));
                tr.append($("<td></td>").text(student.student_no));
                tr.append($("<td></td>").text(student.name));
                tr.append($("<td></td>").text(student.age));
                tr.append($("<td></td>").text(student.grade));
                tbody.append(tr);
            },
            error:function(data){
                console.log("query error"+data);
            }
        })
    })
}

function upload(){
    const form=document.getElementById("upload_form");
    form.addEventListener("submit",async function(event){
        event.preventDefault();
        const formData = new FormData(form);
        try{
            const response=await fetch(
                form.action,
                {
                    method:"POST",
                    body:formData
                });
            const result=await response.json();

            if(!response.ok){
                throw new Error(result.message || "upload files went wrong");
            }

            alert(JSON.stringify(result,null,2));
        }catch(err){
            alert("upload files error"+err.message);
        }
    })
}

query();
upload();