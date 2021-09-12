/**
 * 
 */

/**
 * 
 */

 


 function makeString(title,score,movieId,user) {
    var paramString =
      "title=" +title+
      "&score=" + getValue(score) +
      "&movieId="+movieId +
	"&user="+user ;
    return(paramString);
  }

  
  function rateFilm(title,score, movieId, resultRegion,user) {
    var address = "FilmScore";
	
    console.log(getValue(user));
    console.log(title);
    console.log(getValue(score));
   var data=  makeString(title,score,movieId,getValue(user));
    ajaxPost(address, data, 
             function(request) { 
               Search(request, resultRegion); 
             });
var del = document.getElementById("card"+movieId);
	del.remove();
  }
  
  function Search(request, resultRegion) {
   
    }
  
  
  
  
  
  