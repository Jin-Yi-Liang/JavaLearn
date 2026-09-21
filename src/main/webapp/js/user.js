function query(){
    let query=$("#query");
    let tbody=$("#content");
    query.on("click",_=>{
        //read the id typed by the user
        let id=$("#student-id").val().trim();
        if(id===""){
            alert("please input student id");
            return;
        }

        $.ajax({
            url:"/mvc/user/query",
            method:"GET",
            data:{id: id},
            dataType:"json",
            success:function(student){
                console.log("get student data successfully",student);
                //the server replies with JSON null when no row matches
                if(student===null){
                    tbody.empty();
                    alert("student not found, id="+id);
                    return;
                }
                tbody.empty();
                let tr=$("<tr></tr>");
                tr.append($("<td></td>").text(student.id));
                tr.append($("<td></td>").text(student.student_no));
                tr.append($("<td></td>").text(student.name));
                tr.append($("<td></td>").text(student.age));
                tr.append($("<td></td>").text(student.grade));
                tbody.append(tr);
            },
            error:function(xhr){
                //surface the failure instead of only logging it
                console.log("query error",xhr.status,xhr.responseText);
                alert("query failed: HTTP "+xhr.status);
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