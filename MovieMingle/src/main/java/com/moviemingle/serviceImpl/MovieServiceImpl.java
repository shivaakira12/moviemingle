package com.moviemingle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.moviemingle.model.Movie;
import com.moviemingle.repository.MovieRepository;
import com.moviemingle.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

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
	public Movie getMovieById(Long id) {
		Movie movieDetails = movieRepository.findById(id).orElse(null);
		return movieDetails;
	}

	@Override
	public Page<Movie> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return movieRepository.findAll(pageable);
	}
}
