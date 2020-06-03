function loadPeopleinfo(id) {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/getviewer/" + id,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        var n =
          '<div class="row"><h2>' +
          response.username +
          '</h2><button class="btn follow-btn" id="follow_btn_prof" data-toggle="modal" data-target="#followmodal" onclick="save(' +
          id +
          ')"> Follow </button></div>';
        document.getElementById("foldiv").innerHTML += n;
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

function save(i) {
  localStorage.setItem("followId", i);
  console.log(i);
}

function loadUserinfo() {
  var idUser = localStorage.getItem("userid");
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/getviewer/" + idUser,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        var imgsrc = response.profileImage;
        console.log(imgsrc);
        var imgp = document.getElementById("userfolimg");
        imgp.setAttribute("src", "data:image/png;base64," + imgsrc);
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
  var NameUser = localStorage.getItem("name");
  console.log(NameUser);
  document.getElementById("nameuserfol").innerHTML = NameUser;
  var idUser = localStorage.getItem("userid");
  loadUserinfo();
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/followers/" + idUser,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        for (var j = 0; j < response.length; j++) {
          var id = response[j].accountId;
          loadPeopleinfo(id);
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
};
