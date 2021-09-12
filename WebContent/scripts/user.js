/**
 * 
 */


function makeUserString(userId) {
  var paramString =
    "userId=" + userId ;
  return(paramString);
}

function makeSessionString(session) {
  var paramString =
    "session=" + session ;
  return(paramString);
}

function newUser(session,resultRegion) {
  var address = "NewUser";
  var data = makeSessionString(session);
 var s = ajaxPost(address, data, 
           function(request) { 
             showUser(request, resultRegion); 
           });
return(s)
}


function showUser(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var rawData = request.responseText;
    var users = eval("(" + rawData + ")");
    var userId = new Array();
    
    for(var i=0; i<users.length; i++) { 
      var user = users[i];
      userId[i]=[user.userId];
    }
    var table = getUser(userId);
    htmlInsert(resultRegion, table);
return(userId);
  }
}

function getUser(userId){
	var data = "refresh('"+userId+"','results')";
  var table = '<a class="btn btn-primary btn-lg" onclick="'+data+' "   role="button">Refresh</a>';
for(var i=0; i<20; i++) { 
      var u = document.getElementById("User"+i);
if(u.checked==false){
	u.value= userId;
}
    }
     
  return (table);
}

function refresh(userId,resultRegion){
	console.log(userId);
	var address = "Recommendations";
  var data = makeUserString(userId);
ajaxPost(address, data, 
           function(request) { 
             showRecommendations(request, resultRegion); 
           });
}

window.onbeforeunload = function() {
}

