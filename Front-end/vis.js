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
        var n = "<br><h2>" + response.username + "</h2>";
        document.getElementById("visdiv").innerHTML += n;
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
        var imgp = document.getElementById("uservisimg");
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
  document.getElementById("nameuservis").innerHTML = NameUser;
  var idUser = localStorage.getItem("userid");
  loadUserinfo();
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/accounts/viweres/" + idUser,
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",

    success: function (response) {
      if (response) {
        console.log(response);
        if (response.length == 0) {
          document.getElementById("visdiv").innerHTML =
            "<br><h2> No one's been here... </h2>";
        } else {
          for (var j = 0; j < response.length; j++) {
            var id = response[j].viewerId;
            loadPeopleinfo(id);
          }
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
