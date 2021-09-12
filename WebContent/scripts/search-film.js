/**
 * 
 */

/**
 * 
 */

 


function makeString(id1) {
  var paramString =
    "id1=" + getValue(id1) ;
  return(paramString);
}

function makeFilmString(action, adventure,animation,children, crime ,comedy, drama ,
  documentary,fantasy,film_noir ,horror ,musical,mystery , romance,sci_fi , thriller , wars , western){

    


	var paramString =
    "action=" + getValue(action) + 
    "&adventure=" + getValue(adventure) +
	"&animation=" + getValue(animation)+
  "&children=" + getValue(children)+ 
  "&crime=" + getValue(crime)+ 
  "&comedy=" + getValue(comedy)+  
  "&drama=" + getValue(drama)+ 
  "&documentary=" + getValue(documentary)+ 
  "&fantasy=" + getValue(fantasy)+ 
  "&film_noir=" + getValue(film_noir)+ 
  "&horror=" + getValue(horror)+ 
  "&musical=" + getValue(musical)+ 
  "&mystery=" + getValue(mystery)+ 
  "&romance=" + getValue(romance)+ 
  "&sci_fi=" + getValue(sci_fi)+ 
  "&thriller=" + getValue(thriller)+ 
  "&wars=" + getValue(wars)+ 
  "&western=" + getValue(western);

  return(paramString);
}



function getFilmsTable(rows) {
  var headings = 
    [ "Movie ID", "Title", "Genres" ];
  return(getTable(headings, rows));
}





function searchMovie(action, adventure,animation,children, crime ,comedy, drama ,
  documentary,fantasy,film_noir ,horror ,musical,mystery , romance,sci_fi , thriller , wars , western , resultRegion) {
  
  var data = makeFilmString( action, adventure,animation,children, crime ,comedy, drama ,
    documentary,fantasy,film_noir ,horror ,musical,mystery , romance,sci_fi , thriller , wars , western);


if(getValue(action)=="off" && getValue(adventure)== "off" && getValue(animation)=="off" && getValue(children)=="off" && getValue(comedy)=="off" 
&& getValue(crime)== "off" && getValue(drama)=="off" && getValue(documentary)=="off" && getValue(fantasy)=="off" 
&& getValue(film_noir)== "off" && getValue(horror)=="off" && getValue(musical)=="off" && getValue(mystery)=="off" 
&& getValue(romance)== "off" && getValue(sci_fi)=="off" && getValue(thriller)=="off" && getValue(wars)=="off" && getValue(western)== "off"){
	
	resultRegion="message";
var	data="<h4>PLEASE CHOOSE AT </h4><h4>LEAST ONE GENRE</h4>";
	htmlInsert(resultRegion, data);
}


else{
	var del = document.getElementById("chooseGenres");
	del.remove();
	loader();
var address = "Genre";
  ajaxPost(address, data, 
           function(request) { 
             showSearch(request, resultRegion); 
           });
}


}

function showSearch(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var rawData = request.responseText;
    var films = eval("(" + rawData + ")");
    var movieId = new Array();
    var title = new Array();
    var genres = new Array();
    for(var i=0; i<films.length; i++) { 
      var film = films[i];
      title[i] = [ film.title];
      genres[i] = [ film.genres];
      movieId[i]=[film.movieId];

    }
    var table = getTable(movieId,title,genres);
    htmlInsert(resultRegion, table);

newUser(1,"uID");
  loader();
 }
}


function showRecommendations(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var rawData = request.responseText;
    var films = eval("(" + rawData + ")");
    var movieId = new Array();
    var title = new Array();
    var genres = new Array();
    for(var i=0; i<films.length; i++) { 
      var film = films[i];
      title[i] = [ film.title];
      genres[i] = [ film.genres];
      movieId[i]=[film.movieId];

    }
    var table = getTable(movieId,title,genres);
    htmlInsert(resultRegion, table);

newUser(2,"uID");
  
 }
}




