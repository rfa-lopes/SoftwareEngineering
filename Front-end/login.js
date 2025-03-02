captureData = function (event) {
  var jsondata = JSON.stringify(getFormData($('form[name="login"]')));
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/auth/login",
    contentType: "application/json",
    data: jsondata,
    success: function (response) {
      if (response) {
        // Store token id for later use in localStorage
        localStorage.setItem("userid", response.data.id);
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("name", response.data.name);
        localStorage.setItem("token", response.data.cookie);
        //FALTA AINDA ALGUNS PARAMETROS? EMAIL IMAGEM
        window.location.href = "/feed.html";
      }
    },
    error: function (response) {
      //FALTA VERIFICAR UNAUTHORIZED - AVISO NA PÁGINA(?)
      if (response.status == 401) alert("UNAUTHORIZED");
      else alert("Error:" + response.status);
    },
  });
  event.preventDefault();
};

window.onload = function () {
  var frms = $('form[name="login"]');
  frms[0].onsubmit = captureData;
};

function getFormData($form) {
  var unindexed_array = $form.serializeArray();
  var indexed_array = {};

  $.map(unindexed_array, function (n, i) {
    indexed_array[n["name"]] = n["value"];
  });

  return indexed_array;
}
