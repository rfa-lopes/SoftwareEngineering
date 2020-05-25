function loadPeopleinfo() {
    var idUser = localStorage.getItem('userid');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/accounts/getviewer/"+idUser,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
        success: function(response) {
            if(response) {
                document.getElementById("imguser").setAttribute("src",response.profileImage)
                
                
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

function loadPub() {
    var idUser = localStorage.getItem('userid');
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/allpublications/"+idUser,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
      
        success: function(response) {
            if(response) {
                var n ="";
                
                for(var i=0; i<response.length;i++){
                    n += '<div class="col-lg-4 col-md-6 col-sm-6"><div class="product__item"><div id="'+response[i].id+'" class="product__item__pic set-bg" data-setbg="' + response[i].image + 'data-toggle="modal" data-target="#postModal" onclick="save('+response[i].id+')"></div><div class="product__item__text"><h6><a href="#">' + response[i].description + '</a></h6></div></div></div>';
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
                
                //FAZER CENAAS
            
            
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
                
                //FAZER CENAS
                
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
                    n += '<div class="outgoing_msg"><div class="sent_comm"><p>'+response[j].comment+'</p></div></div></div>';
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
    var NameUser = localStorage.getItem('name');
    document.getElementById("nameuser").value = NameUser;
    $("#postModal").on('show.bs.modal', function (e) {
    
        var id = localStorage.getItem('idpub');
        console.log(id);
        var mimg = document.getElementById(id).getAttribute("data-setbg");
        console.log(mimg);
        document.getElementById("modalimg").innerHTML = '<img class="post" src="' + mimg + '" >';
        getCommentsPub(id);
        
    })
    loadPeople();
    loadPub();
};