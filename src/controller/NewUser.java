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
import model.User;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NewUser() {
        super();
        
    }
String s;
int sessionNumber;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		s = request.getParameter("session");
		sessionNumber=Integer.parseInt(s);
		MovieDAO dao = MovieDAO.getSingletonDAO();
		
		ArrayList<User> user = dao.newUser(sessionNumber);
		
		
		
		String data = "";
		String address="";
		
		Gson gson = new Gson();
		data = gson.toJson(user);
		
		
		address = "results";
		response.setContentType("application/javascript");
		
		request.setAttribute("data", data);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("jsp/"+address+".jsp");
		        dispatcher.forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
