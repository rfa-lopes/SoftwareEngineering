function loadPeopleinfo(id){
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/getviewer/"+id,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                var n = '<br><h2>' + response.username + '</h2>';
                document.getElementById("visdiv").innerHTML += n;
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            console.log(response);
            alert("Error: "+ response.status);
        },
        
    });
    event.preventDefault();
};


window.onload = function() {
    var idUser = localStorage.getItem('userid');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/viweres/"+idUser,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
      
        success: function(response) {
            if(response) {
                console.log(response);
                for(var j = 0 ;j < response.length; j++) {
                    var id = response[i].id;
                    loadPeopleinfo(id);
                }
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            console.log(response);
            alert("Error: "+ response.status);
        },
    });
    event.preventDefault();
};
