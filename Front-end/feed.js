function loadPeople() {
  var idUser = localStorage.getItem("userid");
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/followings/" + idUser,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        for (var j = 0; j < response.length; j++) {
          var id = response[j].isFollowingId;
          loadPeopleinfo(id, j);
        }
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

function loadPeopleinfo(idp, count) {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/getviewer/" + idp,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response.username);
        console.log(response.id);
        console.log(idp);
        var usern = response.username;
        loadPub(idp, usern);
        loadStories(idp, usern, count);
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

function loadStories(id, username, count) {
  var idUser = localStorage.getItem("userid");
  console.log("STORIES");
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/stories/allstories/" + id,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        var m = document.getElementById("storyfeed").innerHTML;
        console.log(response);
        if (response.length > 0) {
          var firstnotempty = parseInt(localStorage.getItem("notempstory"), 10);
          if (firstnotempty < 0) {
            localStorage.setItem("notempstory", count);
          }
        }
        for (var k = 0; k < response.length; k++) {
          if (k == 0 && firstnotempty < 0) {
            m +=
              '<div class="item active"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="' +
              response[k].id +
              '" data-img="' +
              response[k].image +
              '" class="story" src="data:image/png;base64,' +
              response[k].image +
              '"/><ul class="product__item__pic__hover"><li><a href="#sharestorymodal" data-toggle="modal" onclick="save(' +
              response[k].id +
              ')"><i class="fa fa-retweet"></i></a></li><li><a href="#myModal" data-toggle="modal"><i class="fa fa-envelope"></i></a></li></ul></div><div class="product__discount__item__text"><h4><a>' +
              username +
              "</a></h4></div></div></div>";
          } else {
            m +=
              '<div class="item"><div class="product__discount__item"><div class="product__discount__item__pic set-bg" ><img id="' +
              response[k].id +
              '" data-img="' +
              response[k].image +
              '" class="story" src="data:image/png;base64,' +
              response[k].image +
              '"/><ul class="product__item__pic__hover"><li><a href="#sharestorymodal" data-toggle="modal" onclick="save(' +
              response[k].id +
              ')"><i class="fa fa-retweet"></i></a></li><li><a href="#myModal" data-toggle="modal"><i class="fa fa-envelope"></i></a></li></ul></div><div class="product__discount__item__text"><h4><a>' +
              username +
              "</a></h4></div></div></div>";
          }
        }
        document.getElementById("storyfeed").innerHTML = m;
        /*for(var t=0; t<response.length;t++){
                    document.getElementById(response[t].id).style.backgroundImage= "url('data:image/png;base64,"+ response[t].image+ "')";
                }*/
      } else {
        console.log("STORIES MEH");
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

function loadPub(id, username) {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/publications/allpublications/" + id,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        var n = "";
        console.log(response);
        for (var i = 0; i < response.length; i++) {
          n +=
            '<div class="col-lg-4 col-md-6 col-sm-6"><div class="product__item"><div id="' +
            response[i].id +
            '" class="product__item__pic set-bg" data-setbg="data:image/jpeg;base64,' +
            response[i].image +
            '" data-desc="' +
            response[i].description +
            '" data-img="' +
            response[i].image +
            '"><ul class="product__item__pic__hover"><li><a href="#postModal" data-toggle="modal" onclick="save(' +
            response[i].id +
            ')"><i class="fa fa-heart"></i></a></li><li><a href="#postModal" data-toggle="modal" onclick="save(' +
            response[i].id +
            ')"><i class="fa fa-comment"></i></a></li><li><a href="#sharepostmodal" data-toggle="modal" onclick="save(' +
            response[i].id +
            ')"><i class="fa fa-retweet"></i></a></li><li><a href="#unfollowmodal" data-toggle="modal" onclick="saveU(' +
            response[i].ownerId +
            ')"><i class="fa fa-times"></i></a></li></ul></div><div class="product__item__text"><h6><a href="#">' +
            response[i].description +
            "</a></h6><h5>" +
            username +
            "</h5></div></div></div>";
        }
        document.getElementById("postsdiv").innerHTML += n;
        for (var j = 0; j < response.length; j++) {
          document.getElementById(response[j].id).style.backgroundImage =
            "url('data:image/jpeg;base64," + response[j].image + "')";
        }
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

function likePub(i) {
  var id = localStorage.getItem("idpub");
  var uid = localStorage.getItem("userid");
  var jsondata = {
    userId: uid,
    publicationId: id,
    reactionDate: "hoje",
    type: i,
  };
  console.log(jsondata);
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/publications/postlike",
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",
    data: jsondata,

    success: function (response) {
      if (response) {
        console.log("liked");
        console.log(response);
        switch (i) {
          case 0:
            var c1 = parseInt(document.getElementById("likec").innerHTML, 10);
            c1 += 1;
            document.getElementById("likec").innerHTML = c1;
            break;
          case 1:
            var c2 = parseInt(document.getElementById("lovec").innerHTML, 10);
            c2 += 1;
            document.getElementById("lovec").innerHTML = c2;
            break;
          case 2:
            var c3 = parseInt(document.getElementById("laughc").innerHTML, 10);
            c3 += 1;
            document.getElementById("laughc").innerHTML = c3;
            break;
          case 3:
            var c4 = parseInt(document.getElementById("wowc").innerHTML, 10);
            c4 += 1;
            document.getElementById("wowc").innerHTML = c4;
            break;
          case 4:
            var c5 = parseInt(document.getElementById("angryc").innerHTML, 10);
            c5 += 1;
            document.getElementById("angryc").innerHTML = c5;
            break;
          case 5:
            var c6 = parseInt(document.getElementById("sadc").innerHTML, 10);
            c6 += 1;
            document.getElementById("sadc").innerHTML = c6;
            break;
        }
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      if (response.status == 409) {
        alert("You already liked this post!");
        //document.getElementById("followAmodal").showModal();
      } else {
        alert("Error: " + response.status);
      }
    },
    data: JSON.stringify(jsondata),
  });
  event.preventDefault();
}

function getLikesPub(i) {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/publications/allLikes/" + i,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        var c1 = 0;
        var c2 = 0;
        var c3 = 0;
        var c4 = 0;
        var c5 = 0;
        var c6 = 0;
        for (var j = 0; j < response.length; j++) {
          console.log(response[j].type);
          switch (response[j].type) {
            case "LIKE":
              console.log("likeenter");
              c1 += 1;
              break;
            case "LOVE":
              console.log("loveenter");
              c2 += 1;
              break;
            case "AHAH":
              console.log("ahahenter");
              c3 += 1;
              break;
            case "WOW":
              console.log("wowenter");
              c4 += 1;
              break;
            case "ANGRY":
              console.log("angryenter");
              c5 += 1;
              break;
            case "SAD":
              console.log("sadenter");
              c6 += 1;
              break;
            default:
              console.log("defaultenter");
              break;
          }
        }
        console.log(c1);
        console.log(c2);
        console.log(c3);
        console.log(c4);
        console.log(c5);
        console.log(c6);
        document.getElementById("likec").innerHTML = c1;
        document.getElementById("lovec").innerHTML = c2;
        document.getElementById("laughc").innerHTML = c3;
        document.getElementById("wowc").innerHTML = c4;
        document.getElementById("angryc").innerHTML = c5;
        document.getElementById("sadc").innerHTML = c6;
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

function commentPub() {
  var comm = document.getElementById("addcomm").value;
  if (comm.length == 0) {
    return;
  }
  var idu = localStorage.getItem("userid");
  var idp = localStorage.getItem("idpub");
  var jsondata = { userId: idu, publicationId: idp, comment: comm };
  console.log(jsondata);
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/publications/postcomment/" + idp,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",
    data: jsondata,

    success: function (response) {
      if (response) {
        console.log(response);
        var n =
          '<div class="outgoing_msg"><div class="sent_comm"><p>' +
          comm +
          "</p></div></div>";
        document.getElementById("comments").innerHTML += n;
        document.getElementById("addcomm").value = "";
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
    data: JSON.stringify(jsondata),
  });
  event.preventDefault();
}

function getCommentsPub(i) {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/publications/allComments/" + i,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        var n = "";
        console.log(response);
        for (var j = 0; j < response.length; j++) {
          n +=
            '<div class="outgoing_msg"><div class="sent_comm"><p>' +
            response[j].comment +
            "</p></div></div>";
        }
        document.getElementById("comments").innerHTML = n;
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      alert("Error: " + response.status);
    },
  });
  event.preventDefault();
}

window.onload = function () {
  localStorage.setItem("notempstory", -1);
  loadPeople();
  console.log("ola");
  $("#postModal").on("show.bs.modal", function (e) {
    var id = localStorage.getItem("idpub");
    console.log(id);
    var mimg = document.getElementById(id).getAttribute("data-setbg");
    console.log(mimg);
    document.getElementById("modalimg").innerHTML =
      '<img class="post" src="' + mimg + '" >';
    getCommentsPub(id);
    getLikesPub(id);
  });
};
