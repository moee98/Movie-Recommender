/**
 * 
 */
//helloooo its me again



// Get the browser-specific request object, either for
// Firefox, Safari, Opera, Mozilla, Netscape, or IE 7 (top entry);
// or for Internet Explorer 5 and 6 (bottom entry). 



function toggleImage(genre) {
   var img1 = "images/"+genre+"(1).png";
   var img2 = "images/"+genre+".png";

   var checkBox = document.getElementById(genre);
   
   var img = document.getElementById(genre+'Image');
   

   if (checkBox.checked == true){
     img.src=img2;
      checkBox.value = genre;
   }
   else{
    
     img.src=img1;
     checkBox.value = "off"
     
   }
}

function showGenres() {
  var x = document.getElementById("grid-container-genre");
  if (x.style.display === "grid") {
    x.style.display = "none";
  } else {
    x.style.display = "grid";
  }
var del = document.getElementById("intro");
	del.remove();
}


function loader() {
  var x = document.getElementById("loader");
  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
}


function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}

// Insert the html data into the element 
// that has the specified id.

function htmlInsert(id, htmlData) {

  
      document.getElementById(id).innerHTML = htmlData;;

 
}

// Return escaped value of textfield that has given id.
// The builtin "escape" function url-encodes characters.

function getValue(id) {
 
  return(escape(document.getElementById(id).value));
}

// Generalized version of ajaxResultPost. In this
// version, you pass in a response handler function
// instead of just a result region.

function ajaxPost(address, data, responseHandler) {
  var request = getRequestObject();
  request.onreadystatechange = 
    function() { responseHandler(request); };
  request.open("POST", address, true);
  request.setRequestHeader("Content-Type", 
                           "application/x-www-form-urlencoded");
  request.send(data);
}

// Given an element, returns the body content.

function getBodyContent(element) {
  element.normalize();
  return(element.childNodes[0].nodeValue);
}



// Given an element object and an array of sub-element names,
// returns an array of the values of the sub-elements.
// E.g., for <foo><a>one</a><c>two</c><b>three</b></foo>,
// if the element points at foo,
// getElementValues(element, ["a", "b", "c"]) would return
// ["one", "three", "two"]

function getElementValues(element, subElementNames) {
  var values = new Array(subElementNames.length);
  for(var i=0; i<subElementNames.length; i++) {
    var name = subElementNames[i];
    var subElement = element.getElementsByTagName(name)[0];
    values[i] = getBodyContent(subElement);
  }
  return(values);
}

// Takes as input an array of headings (to go into th elements)
// and an array-of-arrays of rows (to go into td
// elements). Builds an xhtml table from the data.





function getTable(movieId,title, genres) {
  var table = "<h3> Here are some movies we think you may like...</h3><br><br><div class='grid-container-movie'>" +
              getTableBody(movieId,title,genres) +
              "</div>";
  return(table);
}




function getTableBody(movieId,title,genres) {
  var body = "";
   
  for(var i=0; i<title.length; i++) {
  var data = "rateFilm('"+title[i]+"','score"+title[i]+"', '"+movieId[i]+"','results','User"+i+"')";

    body += "<div id='card"+movieId[i]+"'><div id='grid-item'>"+
"<div class = 'container'><div class='row'><div class='card'>"+
"<div class='col-xs'><h><i class='fas fa-film'></i></h></div></div><div class='col'><div class='card'>" 
+'<div class="card-body">'+
    "<h3 class='card-title'>" + title[i] + "</h3>"
    + '<p class="card-text">' + genres[i] + "</p>"+
    "<div id='"+title[i]+ "' value='"+title[i]+"'><label for='score'>Rate Movie</label>"
    +'<select   id="score'+title[i]+'">'
    +'<option value="0">0</option>'
    +'<option value="0.5">0.5</option>'
    +'<option value="1">1</option>'
    +'<option value="1.5">1.5</option>'
    +'<option value="2">2</option>'
    +'<option value="2.5">2.5</option>'
    +'<option value="3">3</option>'
    +'<option value="3.5">3.5</option>'
    +'<option value="4">4</option>'
    +'<option value="4.5">4.5</option>'
    +'<option value="5">5</option>'
    +'</select>'
+'<input type="checkbox" id="User'+i+'""><a class="btn btn-primary btn-lg" onclick="'+data+' " role="button">Go</a>'
+'</div></div></div></div></div></div></div></div>';
  
  }

  return(body); 
}

