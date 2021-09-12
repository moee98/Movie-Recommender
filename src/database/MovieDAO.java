package database;

import java.util.ArrayList;
import model.Tags;
import model.User;
import model.Genres;
import model.Movies;
import model.Ratings;

import java.sql.*;

public class MovieDAO {

	private static MovieDAO dao;

	public static MovieDAO getSingletonDAO() {
		if (dao == null) {
			dao = new MovieDAO();
		}
		return dao;
	}

	Tags oneFilm = null;

	Movies rsInteger = null;
	Movies Movie = null;
	Ratings Rating = null;
	Ratings rsInt = null;
	static Connection conn = null;
	public static Statement stmt = null;
	static String user = "ebrahimm";
	static String password = "Majister5";
	static String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

	private MovieDAO() {
	}

	
	public static Connection openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for database, username , password
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	
	

	private Movies getNextMovie(ResultSet rs) {
		Movies thisFilm = null;
		try {
			thisFilm = new Movies(rs.getInt("movieId"), rs.getString("title"), rs.getString("genres"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return thisFilm;
	}
	

	private User getNextUser(ResultSet rs) {
		User thisFilm = null;
		try {
			thisFilm = new User(rs.getInt("userId"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return thisFilm;
	}
	

	private Genres getNextGenre(ResultSet rs) {
		Genres thisFilm = null;
		try {
			thisFilm = new Genres(rs.getString("genre"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return thisFilm;
	}
	

	public ArrayList<Movies> getAllMovies() {

		ArrayList<Movies> allMovies = new ArrayList<Movies>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from movies";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			System.out.println("Get All Films Invoked");
			// Retrieve the results
			while (rs1.next()) {
				Movie = getNextMovie(rs1);
				allMovies.add(Movie);

			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		if (allMovies.size() > 1) {
			System.out.println("Get All Movies Successful");
		}

		System.out.println(allMovies.size() + " Movies Returned");
		return allMovies;
	}
	
	

	public ArrayList<Movies> getMoviesByGenre(String genre) {

		ArrayList<Movies> allMovies = new ArrayList<Movies>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from movies join genres on genres.movieId=movies.movieId where genre=" + genre
					+ " group by movies.movieId  limit 20;";

			ResultSet rs1 = stmt.executeQuery(selectSQL);
			System.out.println("Get All Films Invoked");
			// Retrieve the results
			while (rs1.next()) {
				Movie = getNextMovie(rs1);
				allMovies.add(Movie);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		if (allMovies.size() > 1) {
			System.out.println("Get All Films Successful");
		}

		System.out.println(allMovies.size() + " Films Returned");
		return allMovies;
	}
	

	public ArrayList<Ratings> insertRating(Integer userId, Integer movieId, Float rating) {

		ArrayList<Ratings> allMovies = new ArrayList<Ratings>();
		openConnection();

		// Create select statement and execute it
		try {
			String insertSQL = "insert into ratings (userId,movieId,rating) values ('" + userId + "','" + movieId
					+ "','" + rating + "');";

			rsInt = null;

			int rsInt = stmt.executeUpdate(insertSQL);
			System.out.println("Rating entered");

			if (rsInt == 1) {

				System.out.println("Insert Successful!");
				System.out.println(userId + " " + movieId + " " + rating);

			} else {
				System.out.println("Failed to insert rating!");
			}

			stmt.close();
			closeConnection();

		}

		catch (SQLException se) {
			System.out.println(se);
		}

		return allMovies;
	}

	public Movies insertMovie(int movieId, String title, String genres) {
		openConnection();
		rsInt = null;
		// System.out.println("Insert Film Invoked");
		try {
			String insertSQL = "insert into movies(movieId,title,genres) values('" + movieId + "','" + title + "','"
					+ genres + "')";
			int rsInt = stmt.executeUpdate(insertSQL);
			stmt.close();
			closeConnection();
			System.out.println(rsInt);
		}

		catch (SQLException se) {
			System.out.println(se);
		}

		return null;
	}

	public ArrayList<User> newUser(int sessionNumber) {

		String selectSQL = null;
		ArrayList<User> nUser = new ArrayList<User>();
		openConnection();
		User user = null;

		// Create select statement and execute it
		try {

			if (sessionNumber == 1) {
				selectSQL = "select MAX(userId)+1 as 'userId' from ratings";
			} else {
				selectSQL = "select MAX(userId) as 'userId' from ratings";
			}

			ResultSet rs1 = stmt.executeQuery(selectSQL);
			System.out.println("Get New User Invoked ");
			// Retrieve the results
			while (rs1.next()) {
				user = getNextUser(rs1);
				nUser.add(user);

			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		if (nUser.size() > 1) {
			System.out.println("Get new user successful");
		}

		return nUser;
	}

	public ArrayList<Genres> Recommender(int userId) {

		ArrayList<Genres> genres = new ArrayList<Genres>();
		openConnection();
		Genres g = null;

		// Create select statement and execute it
		try {
			String selectSQL = "select genre from movies join ratings using (movieId) join genres using(movieId) where userId ='"
					+ userId + "' group by genre having AVG(rating)>3 ;";
			ResultSet rs = stmt.executeQuery(selectSQL);
			System.out.println("Get New User Invoked ");
			// Retrieve the results
			while (rs.next()) {
				g = getNextGenre(rs);
				genres.add(g);

			}
			stmt.close();
			closeConnection();
		}

		catch (SQLException se) {
			System.out.println(se);
		}
		if (genres.size() > 1) {
			System.out.println("Get favourite genres successful");
		}

		return genres;

	}

	public ArrayList<Movies> getMoviesByRating(String genre, int userId) {

		ArrayList<Movies> allMovies = new ArrayList<Movies>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from movies join genres using(movieId) where movieId NOT IN (select movieId from ratings where userId ="
					+ userId + " ) and genre=" + genre + " group by movieId  limit 20;";

			ResultSet rs1 = stmt.executeQuery(selectSQL);
			System.out.println("Get All Films Invoked");
			// Retrieve the results
			while (rs1.next()) {
				Movie = getNextMovie(rs1);
				allMovies.add(Movie);

			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		if (allMovies.size() > 1) {
			System.out.println("Get All Films Successful");
		}

		System.out.println(allMovies.size() + " Films Returned");
		return allMovies;
	}

}
