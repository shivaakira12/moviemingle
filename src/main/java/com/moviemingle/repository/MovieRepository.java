package com.moviemingle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviemingle.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
