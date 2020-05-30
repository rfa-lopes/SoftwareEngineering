function sharepost() {
    var idUser = localStorage.getItem('userid'); 
    var idp = localStorage.getItem('idpub');
    var post = document.getElementById(idp);
    var desc = document.getElementById(idp).getAttribute("data-desc");
    var img = document.getElementById(idp).getAttribute("data-img");
    console.log(idUser);
    console.log(idp);
    console.log(desc);
    console.log(img);
    var jsondata = { "ownerId":  idUser , "description": desc, "image": img};
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/post",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
        success: function(response) {
            if(response) {
                console.log(response);
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            console.log(response);
            alert("Error: "+ response.status);
        },
        data: JSON.stringify(jsondata)
    });
    event.preventDefault();
};

function sharestories() {
    var idUser = localStorage.getItem('userid'); 
    var idp = localStorage.getItem('idpub');
    var img = document.getElementById(idp).getAttribute("data-img");
   var jsondata = { "ownerId":  idUser , "image": img };
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/stories/poststory",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
        success: function(response) {
            if(response) {
                console.log(response);
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            console.log(response);
            alert("Error: "+ response.status);
        },
        data: JSON.stringify(jsondata)
    });
    event.preventDefault();
};