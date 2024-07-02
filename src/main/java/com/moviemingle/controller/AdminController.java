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

import com.moviemingle.model.Bug;
import com.moviemingle.model.Contact;
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
		showContactMessages(1,m);
		movieBugPagination(1,m);
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
			updateMovie.setMovieTitle(movie.getMovieTitle());
			updateMovie.setMovieCast(movie.getMovieCast());
			updateMovie.setMovieDirector(movie.getMovieDirector());
			updateMovie.setMovieGenre(movie.getMovieGenre());
			updateMovie.setMovieReleaseYear(movie.getMovieReleaseYear());
			updateMovie.setMovieSynopsis(movie.getMovieSynopsis());
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
	@GetMapping("/bugReport")
	public String loadBugReportPage(Model m) {
		movieBugPagination(1,m);
		return "admin/bugMessage";
	}
	@GetMapping("/contactMessage")
	public String loadContactReportPage(Model m) {
		showContactMessages(1,m);
		return "admin/contact";
	}
	
	/*
	 * Code for the Pagination
	 */
	
	@GetMapping("/contactform/{pageNo}")
	public String showContactMessages(@PathVariable(value = "pageNo")int pageNo , Model m) {
		int pageSize = 5;
		Page <Contact> pagea = movieService.findContactPaginated(pageNo, pageSize);
		List<Contact> listOfBugMessages = pagea.getContent();
		// List of bugs that is paginated
		System.out.println("List of Bugs"+listOfBugMessages.size());
		System.out.println(pagea.getTotalElements());
		m.addAttribute("currentPage",pageNo);
		m.addAttribute("totalPages",pagea.getTotalPages());
		m.addAttribute("totalBugs", pagea.getTotalElements());
		m.addAttribute("listofbugs",listOfBugMessages);
		return "admin/contact";
	}
	
	/*
	 * Code for the Pagination
	 */
	@GetMapping("/bugpage/{pageNo}")
	public String movieBugPagination(@PathVariable (value = "pageNo") int pageNo, Model m) {
		//System.out.println("Logs comming");
		int pageSize = 5;
		Page <Bug> pagea = movieService.findBugPaginated(pageNo, pageSize);
		List<Bug> listOfBugMessages = pagea.getContent();
		// List of bugs that is paginated
		System.out.println("List of Bugs"+listOfBugMessages.size());
		System.out.println(pagea.getTotalElements());
		m.addAttribute("currentPage",pageNo);
		m.addAttribute("totalPages",pagea.getTotalPages());
		m.addAttribute("totalContact", pagea.getTotalElements());
		m.addAttribute("listofbugs",listOfBugMessages);
		return "admin/bugMessage";
	}
	
	
	@GetMapping("/deleteBug/{id}")
	public String deleteBugMessage(@PathVariable int id, HttpSession session) {
		Boolean deleteMovie = movieService.deleteBug(id);
		if (deleteMovie) {
			session.setAttribute("deleteMsg", "Movie Deleted Successfully");
		} else {
			session.setAttribute("errorMsg", "Internal Server Issue");
		}
		return "redirect:/admin/bugReport";
	}
	@GetMapping("/deleteContact/{id}")
	public String deleteContactMessage(@PathVariable int id, HttpSession session) {
		Boolean deleteContact = movieService.deleteContact(id);
		if (deleteContact) {
			session.setAttribute("deleteMsg", "Contact Deleted Successfully");
		} else {
			session.setAttribute("errorMsg", "Internal Server Issue");
		}
		return "redirect:/admin/contactMessage";
	}
	 @GetMapping("/contactform/search")
	    public String searchContactMessages(@RequestParam("query") String query, Model model) {
	        List<Contact> listofContact = movieService.searchContacts(query);
	        model.addAttribute("totalContact", listofContact);
	        return "admin/contact";
	    }
	
}
