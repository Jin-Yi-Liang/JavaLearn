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
                addStudentIntoTable(tbody,student);
            },
            error:function(xhr){
                //surface the failure instead of only logging it
                console.log("query error",xhr.status,xhr.responseText);
                alert("query failed: HTTP "+xhr.status);
            }
        })
    })
}

function queryAll(){
    let tbody=$("#content");
    let queryAll=$("#queryAll");
    queryAll.on("click",_=>{
        $.ajax({
            url:"/mvc/user/queryAll",
            method:"GET",
            dataType:"json",
            success:function(students){
                console.log("get student data successfully",students);
                if(students===null){
                    tbody.empty();
                    alert("student not found, id="+id);
                    return;
                }
                tbody.empty();
                for(let i=0;i<students.length;++i) {
                    addStudentIntoTable(tbody,students[i]);
                }
            },
            error:function(xhr){
                console.log("query error",xhr.status,xhr.responseText);
                alert("query failed: HTTP "+xhr.status);
            }
        })
    })
}

function addStudentIntoTable(tbody,student){
    let tr=$("<tr></tr>");
    tr.append($("<td></td>").text(student.id));
    tr.append($("<td></td>").text(student.student_no));
    tr.append($("<td></td>").text(student.name));
    tr.append($("<td></td>").text(student.age));
    tr.append($("<td></td>").text(student.grade));
    tbody.append(tr);
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

function addStudent(){
    const form=document.getElementById("addStudent_form");
    form.addEventListener("submit",async function(event){
        event.preventDefault();
        const student={
            student_no:form.elements.student_no.value,
            name:form.elements.name.value,
            age:form.elements.age.value,
            grade:form.elements.grade.value,
        };
        $.ajax({
            url:"/mvc/user/addStudent",
            method:"POST",
            contentType:"application/json;charset=UTF-8,",
            data:JSON.stringify(student),
            dataType:"json",
            success:function(response){
                alert("student add successfully,affected rows= "+response);
            },
            error:function(xhr){
                alert("add student error"+xhr.status);
            }
        })
    })
}

function register(){
    query();
    queryAll();
    upload();
    addStudent();
}

register();