function loadMessages(toId) {
    var idUser = localStorage.getItem('userid');
    localStorage.setItem('messageToId',toId);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/messages/allmessages/"+ idUser +"/"+toId,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                console.log(response);
                var n = "";
                for(var i=0; i<response.length; i++){
                    console.log(response[i].toUserId);
                    console.log(response[i].fromUserId);
                    if(response[i].toUserId == idUser){
                        n += '<div class="incoming_msg"><div class="received_msg"><div class="received_withd_msg"><p>' + response[i].messageText + '</p></div></div></div>';
                    }
                    if(response[i].fromUserId == idUser){
                        n += '<div class="outgoing_msg"><div class="sent_msg"><p>' + response[i].messageText + '</p></div></div>';
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
    var msg = document.getElementById("writemsg").value;
    var jsondata = {"messageText":msg, "fromUserId": idUser, "toUserId": idUserTo};
    console.log(jsondata);
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
                console.log(response);
                var n = '<div class="outgoing_msg"><div class="sent_msg"><p>' + msg + '</p></div></div>'
                document.getElementById("chat").innerHTML += n;
                document.getElementById("writemsg").value = '';

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
    //event.preventDefault();
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
                    document.getElementById("peoplechat").innerHTML += '<div class="chat_list active_chat"><div class="chat_people" onclick = "loadMessages('+idp+')"><div class="chat_ib"><h5>' + response.name  +  '</h5></div></div></div>';
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
                
                document.getElementById("peoplechat").innerHTML = '<div class="chat_list">';
                for(var i=0; i<response.length; i++){
                    var idp = response[i].isFollowingId;
	                loadPeopleinfo(idp, i);
                    if(i==0){
                        loadMessages(idp);
                    }
                }
                document.getElementById("peoplechat").innerHTML += '</div>';
                
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

