captureData = function(event) {
    var idUser = localStorage.getItem('userid'); 
    var filter = document.getElementById("filters").value;
    console.log(filter);
    var imageContaainer = document.getElementById("blah");
    var foto = imageContaainer.getAttribute('src');
    if(foto == '#'){
        alert("Looks like you forgot to upload a photo!");
        return;
    }
    var canvas = document.createElement('canvas');
    canvas.id="canvasPhoto";
    canvas.width = imageContaainer.width;
    canvas.height = imageContaainer.height;
    var ctx = canvas.getContext('2d');
    switch(filter){
        case "blur" :
            ctx.filter = 'blur(4px)';
                break;
        case "brightness" :
            ctx.filter = 'brightness(250%)';
                break;
        case "contrast" :
            ctx.filter = 'contrast(180%)';
                break;
        case "grayscale" :
            ctx.filter = 'grayscale(100%)';
                break;
        case "huerotate" :
            ctx.filter = 'hue-rotate(180deg)';
                break;
        case "invert" :
            ctx.filter = 'invert(100%)';
                break;
        case "opacity" :
            ctx.filter = 'opacity(50%)';
                break;
        case "saturate" :
            ctx.filter = 'saturate(7)';
                break;
        case "sepia" :
            ctx.filter = 'sepia(100%)';
                break;
    }
    
    ctx.drawImage(imageContaainer, 0, 0, canvas.width, canvas.height);
    var img64 = canvas.toDataURL("image/png");
    console.log(img64);
    var split = img64.split("data:image/png;base64,");
    var imgb64 = split[1];
    var desc = document.getElementById("descid").value;
    var jsondata = { "ownerId":  idUser , "description": desc, "image": imgb64};
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/publications/post",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        //headers: "Access-Control-Allow-Origin: *",
        dataType: "json",
        data: jsondata,
      
        success: function(response) {
            if(response) {
                console.log(response);
                window.location.href = "/feed.html"
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

window.onload = function() {
    var frms = $('form[name="addstory"]');     
    frms[0].onsubmit = captureData;
};
