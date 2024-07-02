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
import jakarta.servlet.http.HttpSession;
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

	@PostMapping("/savebugs")
	public String saveBugMessages(@ModelAttribute Bug bug, @RequestParam("img") MultipartFile file, HttpSession session)
			throws IOException {

		String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		bug.setImage(imageName);
		Bug saveBugs = movieService.saveBugs(bug);

		if (!ObjectUtils.isEmpty(saveBugs)) {
			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/images").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "movie_images" + File.separator
						+ file.getOriginalFilename());

				System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			session.setAttribute("successMsg", "Sent successfully");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}

		return "redirect:/home/bug";
	}

	@PostMapping("/contactform")
	public String contactform(@ModelAttribute Contact contact, HttpSession session)
			throws IOException 
	{
		Contact contactDetails = movieService.addContact(contact);
		System.out.println(contactDetails);
		if (!ObjectUtils.isEmpty(contactDetails)) {
			session.setAttribute("successMsg", "We will get Back to You");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "redirect:/home/contact";
	}
}
