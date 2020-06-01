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
                console.log(response);
                var imgsrc = response.profileImage;
                console.log(imgsrc);
                var imgp = document.getElementById("imguser");
                imgp.setAttribute("src","data:image/png;base64,"+imgsrc);
                
                
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

function loadPub() {
    var idUser = localStorage.getItem('userid');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/publications/allpublications/"+idUser,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
      
        success: function(response) {
            if(response) {
                var p ="";
                console.log(response);
                for(var k=0; k<response.length;k++){
                    p += '<div class="col-lg-4 col-md-6 col-sm-6"><div class="product__item"><div id="'+response[k].id+'" class="product__item__pic set-bg" data-setbg=data:image/png;base64,"' + response[k].image + 'data-toggle="modal" data-target="#postModal" onclick="save('+response[k].id+')"></div><div class="product__item__text"><h6><a href="#">' + response[k].description + '</a></h6></div></div></div>';
                }
                document.getElementById("mypostsdiv").innerHTML = p;
                for(var j=0; j<response.length;j++){
                    document.getElementById(response[j].id).style.backgroundImage= "url('data:image/png;base64,"+ response[j].image+ "')";
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

function loadStories(id,username) {
    var idUser = localStorage.getItem('userid');
    console.log("STORIES");
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stories/allstories/"+id,
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
      
      
        success: function(response) {
            if(response) {
                var m ="";
                console.log(response);
                for(var k=0; k<response.length;k++){
                    if(k==0){
                         m+='<div class="item active"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="'+response[k].id+'" data-img="'+response[k].image+'" class="story" src="data:image/png;base64,'+response[k].image+'"/></div><div class="product__discount__item__text"><h4><a>'+username+'</a></h4></div></div></div>';}
                    else{
                        m+='<div class="item"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="'+response[k].id+'" data-img="'+response[k].image+'" class="story" src="data:image/png;base64,'+response[k].image+'"/></div><div class="product__discount__item__text"><h4><a>'+username+'</a></h4></div></div></div>';
                    }
                }
                document.getElementById("mystories").innerHTML = m;
                /*for(var t=0; t<response.length;t++){
                    document.getElementById(response[t].id).style.backgroundImage= "url('data:image/png;base64,"+ response[t].image+ "')";
                }*/

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

function likePub(i) {
    var id = localStorage.getItem('idpub');
    var uid = localStorage.getItem('userid');
    var jsondata = {"userId":uid, "publicationId":id,"reactionDate":"hoje","type":i}
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/postlike",
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
                            var c1= parseInt(document.getElementById("likec").innerHTML,10);
                            c1+=1;
                            document.getElementById("likec").innerHTML = c1;
                            break;
                        case 1: 
                            var c2= parseInt(document.getElementById("lovec").innerHTML,10);
                            c2+=1;
                            document.getElementById("lovec").innerHTML = c2;
                            break;
                        case 2: 
                            var c3= parseInt(document.getElementById("laughc").innerHTML,10);
                            c3+=1;
                            document.getElementById("laughc").innerHTML = c3;
                            break;
                        case 3: 
                            var c4= parseInt(document.getElementById("wowc").innerHTML,10);
                            c4+=1;
                            document.getElementById("wowc").innerHTML = c4;
                            break;
                        case 4: 
                            var c5= parseInt(document.getElementById("angryc").innerHTML,10);
                            c5+=1;
                            document.getElementById("angryc").innerHTML = c5;
                            break;
                        case 5:
                            var c6= parseInt(document.getElementById("sadc").innerHTML,10);
                            c6+=1;
                            document.getElementById("sadc").innerHTML = c6;
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
        data: JSON.stringify(jsondata)
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
                            var c1= parseInt(document.getElementById("likec").innerHTML,10);
                            c1+=1;
                            document.getElementById("likec").innerHTML = c1;
                            break;
                        case 1: 
                            var c2= parseInt(document.getElementById("lovec").innerHTML,10);
                            c2+=1;
                            document.getElementById("lovec").innerHTML = c2;
                            break;
                        case 2: 
                            var c3= parseInt(document.getElementById("laughc").innerHTML,10);
                            c3+=1;
                            document.getElementById("laughc").innerHTML = c3;
                            break;
                        case 3: 
                            var c4= parseInt(document.getElementById("wowc").innerHTML,10);
                            c4+=1;
                            document.getElementById("wowc").innerHTML = c4;
                            break;
                        case 4: 
                            var c5= parseInt(document.getElementById("angryc").innerHTML,10);
                            c5+=1;
                            document.getElementById("angryc").innerHTML = c5;
                            break;
                        case 5:
                            var c6= parseInt(document.getElementById("sadc").innerHTML,10);
                            c6+=1;
                            document.getElementById("sadc").innerHTML = c6;
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
    });
    event.preventDefault();
};
    
function commentPub() {
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
        data: JSON.stringify(jsondata)
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
                } document.getElementById("comments").innerHTML = n;
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
    var NameUser = localStorage.getItem('name');
    var idu = localStorage.getItem("userid");
    console.log(NameUser);
    document.getElementById("nameuser").innerHTML = NameUser;
    $("#postModal").on('show.bs.modal', function (e) {
    
        var id = localStorage.getItem('idpub');
        console.log(id);
        var mimg = document.getElementById(id).getAttribute("data-setbg");
        console.log(mimg);
        document.getElementById("modalimg").innerHTML = '<img class="post" src="' + mimg + '" >';
        getCommentsPub(id);
        
    })
    loadPeopleinfo();
    loadPub();
    loadStories(idu,NameUser);
};