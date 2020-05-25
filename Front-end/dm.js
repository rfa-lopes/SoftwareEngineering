function loadMessages(toId) {
    var idUser = localStorage.getItem('userid');
    localStorage.setItem('messageToId',toId);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/messages/allmessages/"+ idUser +"/"+toID,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
            var n = "";
                for(var i=0; i<response.length; i++){
                    if(response[i].propertyMap.toUserID == idUser){
                        n += '<div class="incoming_msg"><div class="received_msg"><div class="received_withd_msg"><p>' + response[i].propertyMap.messageText + '</p></div></div></div>';
                    }
                    if(response[i].propertyMap.fromUserID == idUser){
                        n += '<div class="outgoing_msg"><div class="sent_msg"><p>' + response[i].propertyMap.messageText + '</p></div></div>';
                    }
                }
                document.getElementById("chat").innerHTML = n;
                
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


sendMessage = function(event) {
    var idUser = localStorage.getItem('userid');
    var idUserTo = localStorage.getItem('messageToId');
    var msg = document.getElementById("textinput").value;
    var jsondata = {"messageText":msg, "fromUserId": idUser, "toUserId": idUserTo};
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/messages/sendmessage",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
      
        success: function(response) {
            if(response) {
                var n = '<div class="outgoing_msg"><div class="sent_msg"><p>' + msg + '</p></div></div>'
                document.getElementById("chat").innerHTML += n;

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

function loadPeopleinfo(idp, i) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/getviewer/" + idp,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                if(i==0){
                    document.getElementById("peoplechat").innerHTML += '<div class="chat_list active_chat"><div class="chat_people" onclick = "loadMessages(idp)"><div class="chat_ib"><h5>' + response.propertyMap.name  +  '</h5></div></div></div>';
                }
                else{
                    document.getElementById("peoplechat").innerHTML += '<div class="chat_people" onclick = "loadMessages(idp)"> <div class="chat_ib"><h5>' + response.propertyMap.name + '</h5></div></div>';
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


window.onload = function() {
    var idUser = localStorage.getItem('userid');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/followings/"+idUser,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                
                Document.getElementById("peoplechat").innerHTML = '<div class="chat_list">';
                for(var i=0; i<response.length; i++){
                    var idp = response[i].propertyMap.isFollowingId;
	                loadPeopleinfo(idp, i);
                    if(i==0){
                        loadMessages(idp);
                    }
                }
                Document.getElementById("peoplechat").innerHTML += '</div>';
                
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

