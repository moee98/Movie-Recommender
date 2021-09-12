package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.MovieDAO;
import model.Genres;
import model.Movies;

/**
 * Servlet implementation class Recommendations
 */
@WebServlet("/Recommendations")
public class Recommendations extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	String uID;
	int userId;
	
    public Recommendations() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> result = new ArrayList<String>();
		uID=request.getParameter("userId");
		
		userId = Integer.parseInt(uID);
		
		
		MovieDAO dao = MovieDAO.getSingletonDAO();
		ArrayList<Genres> favGenre = dao.Recommender(userId);
		
	
		for(Genres s : favGenre) {
		      
		    	   
		    	   if(result.size() == 0) {
		    		   result.add("'"+s+"'");
		    	   }
		    	   else {
		          result.add( "or "+"'"+s+"'" );
		    	   }
		    	   
		    	   
		       
		    }
		 
		 StringBuffer sb = new StringBuffer();
	      
	      for (String s : result) {
	         sb.append(s);
	         sb.append(" ");
	      }
	      String str = sb.toString();
	      System.out.println("Find movies where genre = "+str);
	      System.out.println("userId = "+userId);
	      ArrayList<Movies> allMovies = dao.getMoviesByRating(str,userId);
		
		String data = "";
		String address="";
		
		
		Gson gson = new Gson();
		data = gson.toJson(allMovies);
		
		
		address = "results";
		response.setContentType("application/javascript");
		
		request.setAttribute("data", data);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("jsp/"+address+".jsp");
		        dispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
