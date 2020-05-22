captureData = function(event) {


    var data = getFormData($('form[name="register"]'));
    console.log(data);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/register/account",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        data: JSON.stringify(data),
        success: function(response) {
            if(response) {
                alert("Got token with id: " + response.tokenID);
                // Store token id for later use in localStorage
                localStorage.setItem('tokenID', response.tokenID);
                localStorage.setItem('username',response.username);
                
                window.location.href = "/login.html"
        
            }
            else {
                alert("No response");
            }
        },
        error: function(response) {
            alert("Error:" + response.status);
        }
    });

    event.preventDefault();
}


	

window.onload = function() {
    var frms = $('form[name="register"]');    
    frms[0].onsubmit = captureData;
}


function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}
    
