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
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/allMovies")
public class AllMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    
    public AllMovies() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MovieDAO dao = MovieDAO.getSingletonDAO();
		ArrayList<Movies> allMovies = dao.getAllMovies();
		
		
		
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
