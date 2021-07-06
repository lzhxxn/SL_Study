//JavaScript - jQuery 

var form = new FormData();
form.append("message", "SecuLayer");

var settings = {
  "url": "https://notify-api.line.me/api/notify",
  "method": "POST",
  "timeout": 0,
  "headers": {
    "Authorization": "Bearer wjEwZ33oSXoVupQhetxvmIEyLFxgqM4dIssTL2raqHi"
  },
  "processData": false,
  "mimeType": "multipart/form-data",
  "contentType": false,
  "data": form
};

$.ajax(settings).done(function (response) {
  console.log(response);
});

// POST API
