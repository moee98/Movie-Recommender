package testing;


import static org.junit.Assert.assertEquals;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MovieDAO;

class UnitTests {

	
	
	
	@org.junit.jupiter.api.Test
	void testOpenConnection() {
		java.sql.Connection result = MovieDAO.openConnection();
		assertEquals(result !=null,true);
	}
	
	
	
	@org.junit.jupiter.api.Test
	public void testInsertRating() throws Exception {
		String insertSQL="insert into ratings (userId,movieId,rating) values (1000,6,2.5);";
		try {
			MovieDAO.openConnection(); 
			int rs = MovieDAO.stmt.executeUpdate(insertSQL);
			assertEquals(1,rs);
		
			MovieDAO.closeConnection();
			MovieDAO.stmt.close();
				
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		 
	}
	
	@org.junit.jupiter.api.Test
	public void  testNewUser() throws SQLException{
		
		String selectSQL;
		
		int sessionNumber=1;
		
		if (sessionNumber == 1) {
			selectSQL = "select MAX(userId)+1 as 'userId' from ratings";
		} else {
			selectSQL = "select MAX(userId) as 'userId' from ratings";
		}
		
			MovieDAO.openConnection(); 
			ResultSet rs1 = MovieDAO.stmt.executeQuery(selectSQL);
			rs1.next();
			int userId = rs1.getInt("userId");
			
			//resets value to test again
			MovieDAO.stmt.executeUpdate("delete from ratings where userId=1000");

			MovieDAO.stmt.close();
			MovieDAO.closeConnection();
			
			assertEquals(1001,userId);
		
				
		
	}
	
	@org.junit.jupiter.api.Test
	public void  testFindMovie() throws SQLException{
		
			String selectSQL = "select movieId,title,genre from movies join genres using(movieId) where movieId=102";
		
		
			MovieDAO.openConnection(); 
			ResultSet rs1 = MovieDAO.stmt.executeQuery(selectSQL);
			rs1.next();
			// Retrieve the results
			int movieId = rs1.getInt("movieId");
			String title = rs1.getString("title");
			String genres = rs1.getString("genre");
			
			
			

			MovieDAO.stmt.close();
			MovieDAO.closeConnection();
			
			assertEquals(102,movieId);
			assertEquals("Mr. Wrong (1996)",title);
			assertEquals("Comedy",genres);
		
				
		
	}
	
	@org.junit.jupiter.api.Test
	public void testInsertMovie() throws Exception {
		String insertSQL="insert into movies (movieId,title,genres) values (1000,'new movie','Mystery');";
		try {
			MovieDAO.openConnection(); 
			int rs = MovieDAO.stmt.executeUpdate(insertSQL);
			assertEquals(1,rs);
			//resets value to test again
		MovieDAO.stmt.executeUpdate("delete from movies where movieId=1000");
			MovieDAO.closeConnection();
			MovieDAO.stmt.close();
				
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		 
	}
	
	
	
	
	
	
	

}
