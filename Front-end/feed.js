function loadPeople() {
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
                for(var j=0; j<response.length;j++){
                    var id = response[j].isFollowingId;
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
        data: JSON.stringify(data)
    });
    event.preventDefault();
};

function loadPeopleinfo(idp) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/getviewer/" + idp,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                var usern = response.username;
                loadPub(id, usern); 
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


function loadPub(id, username){

    var jsondata = JSON.stringify(data);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/allpublications/"+id,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
      
        success: function(response) {
            if(response) {
                var n ="";
                
                for(var i=0; i<response.length;i++){
                    n += '<div class="col-lg-4 col-md-6 col-sm-6"><div class="product__item"><div id="'+response[i].id+'" class="product__item__pic set-bg" data-setbg="' + response[i].image + 'data-toggle="modal" data-target="#postModal" onclick="save('+response[i].id+')"><ul class="product__item__pic__hover"><li><a href="#postModal" data-toggle="modal" onclick="save('+response[i].id+')><i class="fa fa-heart"></i></a></li><li><a href="postModal" data-toggle="modal" onclick="save('+response[i].id+')><i class="fa fa-comment"></i></a></li><li><a href="#"><i class="fa fa-retweet"></i></a></li><li><a href="#"><i class="fa fa-plus"></i></a></li></ul></div><div class="product__item__text"><h6><a href="#">' + response[i].description + '</a></h6><h5>' + username + '</h5></div></div></div>';
                }
                document.getElementById().innerHTML = n;

            
            
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


function likePub(i) {
    var id = localStorage.getItem('idpub');
    var uid = localStorage.getItem('userid');
    var jsondata = {"userId":uid, "publicationId":id,"reactionDate":"hoje","type":i}
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/postLike",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
      
        success: function(response) {
            if(response) {
                console.log(liked);
                    switch(i){
                        case 0: 
                            var c1= parseInt(document.getElementById("likec").value,10);
                            c1+=1;
                            document.getElementById("likec").value = c1;
                            break;
                        case 1: 
                            var c2= parseInt(document.getElementById("lovec").value,10);
                            c2+=1;
                            document.getElementById("lovec").value = c2;
                            break;
                        case 2: 
                            var c3= parseInt(document.getElementById("laughc").value,10);
                            c3+=1;
                            document.getElementById("laughc").value = c3;
                            break;
                        case 3: 
                            var c4= parseInt(document.getElementById("wowc").value,10);
                            c4+=1;
                            document.getElementById("wowc").value = c4;
                            break;
                        case 4: 
                            var c5= parseInt(document.getElementById("angryc").value,10);
                            c5+=1;
                            document.getElementById("angryc").value = c5;
                            break;
                        case 5:
                            var c6= parseInt(document.getElementById("sadc").value,10);
                            c6+=1;
                            document.getElementById("sadc").value = c6;
                            break;
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
        data: JSON.stringify(data)
    });
    event.preventDefault();
};
    
function getLikesPub(i) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/publications/allLikes/"+i,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                for(var j=0;j<response.length;j++){
                    switch(response[j].type){
                        case 0: 
                            var c1= parseInt(document.getElementById("likec").value,10);
                            c1+=1;
                            document.getElementById("likec").value = c1;
                            break;
                        case 1: 
                            var c2= parseInt(document.getElementById("lovec").value,10);
                            c2+=1;
                            document.getElementById("lovec").value = c2;
                            break;
                        case 2: 
                            var c3= parseInt(document.getElementById("laughc").value,10);
                            c3+=1;
                            document.getElementById("laughc").value = c3;
                            break;
                        case 3: 
                            var c4= parseInt(document.getElementById("wowc").value,10);
                            c4+=1;
                            document.getElementById("wowc").value = c4;
                            break;
                        case 4: 
                            var c5= parseInt(document.getElementById("angryc").value,10);
                            c5+=1;
                            document.getElementById("angryc").value = c5;
                            break;
                        case 5:
                            var c6= parseInt(document.getElementById("sadc").value,10);
                            c6+=1;
                            document.getElementById("sadc").value = c6;
                            break;
                    }
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
        data: JSON.stringify(data)
    });
    event.preventDefault();
};
    
commentPub = function(event) {
    var comm = document.getElementById("addcomm").value
    var idu = localStorage.getItem("userid");
    var idp = localStorage.getItem("idpub");
    
    var jsondata = {"userId":idu, "publicationId":idp, "comment": comm};
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/postcomment/"+idp,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
      
        success: function(response) {
            if(response) {
                
                var n = '<div class="outgoing_msg"><div class="sent_comm"><p>'+comm+'</p></div></div>';
                document.getElementById("comments").innerHTML += n;
            
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

function getCommentsPub(i) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/publications/allComments/"+i,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                var n ="";
                for(var j=0;j<response.length;i++){
                    n += '<div class="outgoing_msg"><div class="sent_comm"><p>'+response[j].comment+'</p></div></div>';
                } document.getElementById("comments").innerHTML += n;
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
    loadPeople();
    console.log("ola"); $("#postModal").on('show.bs.modal', function (e) {
    
        var id = localStorage.getItem('idpub');
        console.log(id);
        var mimg = document.getElementById(id).getAttribute("data-setbg");
        console.log(mimg);
        document.getElementById("modalimg").innerHTML = '<img class="post" src="' + mimg + '" >';
        getCommentsPub(id);
        
    })
};