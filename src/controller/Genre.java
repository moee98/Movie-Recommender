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
import model.Movies;

/**
 * Servlet implementation class Genre
 */
@WebServlet("/Genre")
public class Genre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String action="";
	String adventure ;
	String animation ;
	String children;
	String crime ;
	String comedy;
	String drama ;
	String documentary;
	String fantasy;
	String film_noir ;
	String horror ;
	String musical ;
	String mystery ;
	String romance;
	String sci_fi ;
	String thriller ;
	String wars ;
	String western ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Genre() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		//request data from webpage to result in genre name or null
		
		 action = request.getParameter("action");
		  adventure = request.getParameter("adventure");
		 animation = request.getParameter("animation");
		 children = request.getParameter("children");
		 crime = request.getParameter("crime");
		 comedy = request.getParameter("comedy");
		 drama = request.getParameter("drama");
		 documentary = request.getParameter("documentary");
		 fantasy = request.getParameter("fantasy");
		 film_noir = request.getParameter("film_noir");
		 horror = request.getParameter("horror");
		 musical = request.getParameter("musical");
		 mystery = request.getParameter("mystery");
		 romance = request.getParameter("romance");
		 sci_fi = request.getParameter("sci_fi");
		 thriller = request.getParameter("thriller");
		 wars = request.getParameter("wars");
		 western = request.getParameter("western");
		 
		 
		 
		 ArrayList<String> liked = new ArrayList<String>();
		 ArrayList<String> result = new ArrayList<String>();
		 
		 
		 liked.add(action);
		 liked.add(adventure);
		 liked.add(animation);
		 liked.add(children);
		 liked.add(comedy);
		 liked.add(crime);
		 liked.add(drama);
		 liked.add(documentary);
		 liked.add(fantasy);
		 liked.add(film_noir);
		 liked.add(horror);
		 liked.add(musical);
		 liked.add(mystery);
		 liked.add(romance);
		 liked.add(thriller);
		 liked.add(wars);
		 liked.add(western);
		 liked.add(sci_fi);
		 
		 
		 // delete any null values to find chosen genres 
		 for(String s : liked) {
		       if( s.contains("off")==false  && s.length() > 0) {
		    	   
		    	   if(result.size() == 0) {
		    		   result.add("'"+s+"'");
		    	   }
		    	   else {
		          result.add( "or "+"'"+s+"'" );
		    	   }
		    	   
		    	   
		       }
		    }
		 
		 StringBuffer sb = new StringBuffer();
	      
	      for (String s : result) {
	         sb.append(s);
	         sb.append(" ");
	      }
	      String str = sb.toString();
	      
		 
		 //connect to database and send data request
		 MovieDAO dao = MovieDAO.getSingletonDAO();
			ArrayList<Movies> allMovies = dao.getMoviesByGenre(str);
			
			
			
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
			        
			     //   Movies film = new Movies(movieId, title, genre);
			      
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
