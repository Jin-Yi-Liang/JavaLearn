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

query();