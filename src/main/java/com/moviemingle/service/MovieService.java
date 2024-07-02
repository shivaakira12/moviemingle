package com.moviemingle.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.moviemingle.model.Bug;
import com.moviemingle.model.Contact;
import com.moviemingle.model.Movie;

public interface MovieService {
	public Movie addMovie(Movie movie);
	public List<Movie> getAllMovies();
	public Boolean deleteMovie(Long id);
	public Movie getMovieById(Long id);
	Page<Movie> findPaginated (int pageNo, int pageSize);
	public Bug saveBugs(Bug bug);
	Page<Bug> findBugPaginated(int pageNo,int pageSize);
	public Boolean deleteBug(int id);
	public Contact addContact(Contact contact);
	public Page<Contact> findContactPaginated(int pageNo, int pageSize);
	public Boolean deleteContact(int id);
	public List<Contact> searchContacts(String query);
}
