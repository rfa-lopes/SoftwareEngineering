captureData = function (event) {
  //FALTA VERIFICAR CAMPOS VAZIOS
  var jsondata = JSON.stringify(getFormData($('form[name="register"]')));
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/register/account/",
    contentType: "application/json",
    data: jsondata,
    success: function (response) {
      alert("REGISTER OK");
      window.location.href = "/login.html";
    },
    error: function (response) {
      //FALTA VERIFICAR CONFLITOS (USER ALREADY EXIST) - AVISO NA PÁGINA(?)
      if (response.status == 409) alert("USER ALREADY EXIST");
      else alert("Error:" + response.status);
    },
  });
  event.preventDefault();
};

window.onload = function () {
  var frms = $('form[name="register"]');
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
