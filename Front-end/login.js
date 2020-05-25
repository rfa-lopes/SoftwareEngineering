captureData = function(event) {
    var data = getFormData($('form[name="login"]'));
    var jsondata = JSON.stringify(data);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/auth/login",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
      
        success: function(response) {
            if(response) {
                // Store token id for later use in localStorage
                localStorage.setItem('userid',response.id);
                localStorage.setItem('username',response.username);
                localStorage.setItem('name',response.name);
                console.log(response);
                window.location.href = "/feed.html"
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            console.log(response);
            alert("Error: "+ response.status);
        },
        data: JSON.stringify(data)
    });
    event.preventDefault();
};

window.onload = function() {
    var frms = $('form[name="login"]');     
    frms[0].onsubmit = captureData;
};

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}