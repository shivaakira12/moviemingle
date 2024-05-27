package com.moviemingle.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.moviemingle.model.Movie;

public interface MovieService {
	public Movie addMovie(Movie movie);
	public List<Movie> getAllMovies();
	public Boolean deleteMovie(Long id);
	public Movie getMovieById(Long id);
	Page<Movie> findPaginated (int pageNo, int pageSize);
}
