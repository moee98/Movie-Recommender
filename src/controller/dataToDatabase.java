package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

import database.MovieDAO;
import model.MovieResult;
import model.Movies;

public class dataToDatabase {

	public static void main(String[] args) {
		BufferedReader br = null;
		int movieId;
		String title;
		String genres;
		
		MovieDAO dao = MovieDAO.getSingletonDAO();
		
		
		try {
			Gson gson = new Gson();

			br = new BufferedReader(new FileReader("Movies.json"));
			MovieResult result = gson.fromJson(br, MovieResult.class);

			if (result != null) {
				for (Movies film : result.getMovie()) {
					System.out.println(film.getMovieId() + " " + film.getTitle());
					movieId = film.getMovieId();
					title = film.getTitle();
					genres = film.getGenres();

					dao.insertMovie(movieId, title, genres);

				}

			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
