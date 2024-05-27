package com.moviemingle.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.moviemingle.model.Movie;
import com.moviemingle.service.MovieService;
import org.springframework.data.domain.Page;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/")
	public String index(Model m) {
		//m.addAttribute("allMovies", movieService.getAllMovies());
		//m.addAttribute("movieSize", movieService.getAllMovies().size());
		//List<Movie> all = movieService.getAllMovies();
		moviePagination(1,m);
		return "admin/index";
	}

	@GetMapping("/addMovie")
	public String loadAddMoviePage(Model m) {
		return "/admin/addmovie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute Movie movie, HttpSession session, @RequestParam("file") MultipartFile file)
			throws IOException {
		String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		movie.setImage(imageName);
		Movie saveMovie = movieService.addMovie(movie);
		System.out.println(saveMovie);
		if (!ObjectUtils.isEmpty(saveMovie)) {
			File saveFile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "movie_images" + File.separator
					+ file.getOriginalFilename());
			System.out.println(path);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			session.setAttribute("successMsg", "Movie Saved Success");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "redirect:/admin/addMovie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable Long id, HttpSession session) {
		Boolean deleteMovie = movieService.deleteMovie(id);
		if (deleteMovie) {
			session.setAttribute("deleteMsg", "Movie Deleted Successfully");
		} else {
			session.setAttribute("errorMsg", "Internal Server Issue");
		}
		return "redirect:/admin/";
	}

	@GetMapping("/editMovie/{id}")
	public String loadEditMoviePage(Model m, @PathVariable Long id) {
		m.addAttribute("movieDetails", movieService.getMovieById(id));
		return "/admin/editmovie";
	}

	@PostMapping("/updateMovie")
	public String updateMovie(@ModelAttribute Movie movie, HttpSession session,
			@RequestParam("file") MultipartFile file) throws IOException {
		Movie updateMovie = movieService.getMovieById(movie.getMovieId());
		String imageName = file.isEmpty() ? updateMovie.getImage() : file.getOriginalFilename();
		if (!ObjectUtils.isEmpty(updateMovie)) {
			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "movie_images" + File.separator
						+ file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			updateMovie.setMovieTitle(updateMovie.getMovieTitle());
			updateMovie.setMovieCast(updateMovie.getMovieCast());
			updateMovie.setMovieDirector(updateMovie.getMovieDirector());
			updateMovie.setMovieGenre(updateMovie.getMovieGenre());
			updateMovie.setMovieReleaseYear(updateMovie.getMovieReleaseYear());
			updateMovie.setMovieSynopsis(updateMovie.getMovieSynopsis());
			updateMovie.setImage(imageName);
		}
		Movie updatedMovie = movieService.addMovie(updateMovie);
		if (!ObjectUtils.isEmpty(updatedMovie)) {
			session.setAttribute("successMsg", "Updated MovieDetails Successfully !");
		}
		else {
			session.setAttribute("errorMsg", "Internal Server Issue!");
		}
		return "redirect:/admin/editMovie/" + movie.getMovieId();
	}
	/*
	 * Code for the Pagination
	 */
	@GetMapping("/page/{pageNo}")
	public String moviePagination(@PathVariable (value = "pageNo") int pageNo, Model m) {
		int pageSize = 5;
		Page <Movie> page = movieService.findPaginated(pageNo, pageSize);
		List<Movie> listOfMovie = page.getContent(); // List of movies that is paginated
		
		m.addAttribute("currentPage",pageNo);
		m.addAttribute("totalPages",page.getTotalPages());
		m.addAttribute("totalMovies",page.getTotalElements());
		m.addAttribute("listofmovies",listOfMovie);
		return "admin/index";
	}

}
