package com.moviemingle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.moviemingle.model.Bug;
import com.moviemingle.model.Contact;
import com.moviemingle.model.Movie;
import com.moviemingle.repository.BugRepository;
import com.moviemingle.repository.ContactRepository;
import com.moviemingle.repository.MovieRepository;
import com.moviemingle.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private BugRepository bugRepository;
	
	@Autowired 
	private ContactRepository contactRepository;

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Boolean deleteMovie(Long id) {
		Movie deleteMovie = movieRepository.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(deleteMovie)) {
			movieRepository.delete(deleteMovie);
			return true;
		}
		return false;
	}
	@Override
	public Boolean deleteBug(int id) {
		Bug deleteMovie = bugRepository.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(deleteMovie)) {
			bugRepository.delete(deleteMovie);
			return true;
		}
		return false;
	}
	@Override
	public Movie getMovieById(Long id) {
		Movie movieDetails = movieRepository.findById(id).orElse(null);
		return movieDetails;
	}

	@Override
	public Page<Movie> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return movieRepository.findAll(pageable);
	}

	@Override
	public Bug saveBugs(Bug bug) {
		System.out.println("Gello");
		return bugRepository.save(bug);
	}

	@Override
	public Page<Bug> findBugPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return bugRepository.findAll(pageable);
	}

	@Override
	public Contact addContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Page<Contact> findContactPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return contactRepository.findAll(pageable);
	}

	@Override
	public Boolean deleteContact(int id) {
		Contact deleteMovie = contactRepository.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(deleteMovie)) {
			contactRepository.delete(deleteMovie);
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> searchContacts(String query) {
        return contactRepository.searchByQuery(query);
    }
}
