package com.moviemingle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moviemingle.model.Movie;
import com.moviemingle.service.MovieService;
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String loadHomePage(Model m) {
		List<Movie> movies = movieService.getAllMovies();
		m.addAttribute("movies", movies);
		return "home/home";
	}
	@GetMapping("/about")
	public String loadAboutPage() {
		return "home/about";
	}
	@GetMapping("/contact")
	public String loadContactPage() {
		return "home/contact";
	}
	@GetMapping("/bug")
	public String loadbugPage() {
		return "home/bug";
	}
	@GetMapping("/movie/{id}")
	public String loadmovieSinglePage(@PathVariable Long id, Model m) {
		System.out.println(id);
		Movie movieById = movieService.getMovieById(id);
		System.out.println(movieById);
		m.addAttribute("movie", movieById);
		return "home/movieSinglePage";
	}

}
