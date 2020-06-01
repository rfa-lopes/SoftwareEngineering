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
                console.log(response);
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
                console.log(response.username);
                console.log(response.id);
                console.log(idp);
                var usern = response.username;
                loadStories(idp, usern);
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

function loadStories(id,username,count) {
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
                console.log("STORIES YES");
                console.log(response);
                for(var k=0; k<response.length;k++){
                    if(k==0 && count==0){
                         m+='<div class="item active"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="'+response[k].id+'" data-img="'+response[k].image+'" class="story" src="data:image/png;base64,'+response[k].image+'"/><ul class="product__item__pic__hover"><li><a href="#sharestorymodal" data-toggle="modal" onclick="save('+response[k].id+')"><i class="fa fa-retweet"></i></a></li><li><a href="#myModal" data-toggle="modal"><i class="fa fa-envelope"></i></a></li></ul></div><div class="product__discount__item__text"><h4><a>'+username+'</a></h4></div></div></div>';}
                    else{
                        m+='<div class="item"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="'+response[k].id+'" data-img="'+response[k].image+'" class="story" src="data:image/png;base64,'+response[k].image+'"/><ul class="product__item__pic__hover"><li><a href="#sharestorymodal" data-toggle="modal" onclick="save('+response[k].id+')"><i class="fa fa-retweet"></i></a></li><li><a href="#myModal" data-toggle="modal"><i class="fa fa-envelope"></i></a></li></ul></div><div class="product__discount__item__text"><h4><a>'+username+'</a></h4></div></div></div>';
                    }
                }
                document.getElementById("storiestest").innerHTML = m;
                /*for(var t=0; t<response.length;t++){
                    document.getElementById(response[t].id).style.backgroundImage= "url('data:image/png;base64,"+ response[t].image+ "')";
                }*/

            }
            else {
                console.log("STORIES MEH");
                alert("No response");
            }
        },
        error: function(response) {
            console.log("STORIES NO");
            console.log(response);
            alert("Error: "+ response.status);
        },
    });
    event.preventDefault();
};


window.onload = function() {
    loadPeople();
};

