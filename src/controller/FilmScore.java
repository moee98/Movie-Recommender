package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MovieDAO;

@WebServlet("/FilmScore")
public class FilmScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FilmScore() {
        super();
        
    }
    
    String title;
    String score;
    String filmId;
    String user;
    Float rating;
    Integer movieId;
    Integer userId;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		title = request.getParameter("title");
		score = request.getParameter("score");
		filmId = request.getParameter("movieId");
		user = request.getParameter("user");

		
		
		
		
		
		rating = Float.parseFloat(score);
		movieId = Integer.parseInt(filmId);
		userId = Integer.parseInt(user);
		
		System.out.println("movieId  "+movieId);
		System.out.println(title);
		System.out.println("rating "+ rating);
		System.out.println(userId);
		
		
		
		
		MovieDAO dao = MovieDAO.getSingletonDAO();
		
		 dao.insertRating(userId, movieId, rating);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
