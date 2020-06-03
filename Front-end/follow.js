function unfollow() {
  var idUser = localStorage.getItem("userid");
  var iduserp = localStorage.getItem("userIdPub");
  var jsondata = { accountId: idUser, isFollowingId: iduserp };
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/accounts/unfollow",
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",
    data: jsondata,

    success: function (response) {
      if (response) {
        console.log(response);
        window.location.href = "/feed.html";
        //$("#unfollowAmodal").modal("show");
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      if (response.status == 200) {
        window.location.href = "/feed.html";
        //$("#unfollowAmodal").modal("show");
      } else {
        alert("Error: " + response.status);
      }
    },
    data: JSON.stringify(jsondata),
  });
  event.preventDefault();
}

function follow() {
  var idUser = parseInt(localStorage.getItem("userid"), 10);
  var iduserp = parseInt(localStorage.getItem("followId"), 10);
  console.log(idUser);
  console.log(iduserp);
  var data = { accountId: idUser, isFollowingId: iduserp };
  var jsondata = JSON.stringify(data);
  console.log(jsondata);
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/accounts/follow",
    contentType: "application/json; charset=utf-8",
    crossDomain: true,
    //headers: "Access-Control-Allow-Origin: *",
    dataType: "json",
    data: jsondata,

    success: function (response) {
      if (response) {
        console.log(response);
        alert("Account Followed");
        //document.getElementById("followedmodal").modal({show: true});
      } else {
        alert("No response");
      }
    },
    error: function (response) {
      console.log(response);
      if (response.status == 200) {
        alert("Account Followed");
        //document.getElementById("followedmodal").modal({show: true});
      } else if (response.status == 409) {
        alert("You are already following this account!");
        //document.getElementById("followAmodal").showModal();
      } else {
        alert("Error: " + response.status);
      }
    },
  });
  event.preventDefault();
}
