captureData = function(event) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/auth/logout",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
      
      
        success: function(response) {
            if(response) {
                
                console.log(response);
                localStorage.clear();
                window.location.href = "/login.html"
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
    var btn = document.getElementById("logout_btn_feed");     
    btn.onclick=captureData;
};