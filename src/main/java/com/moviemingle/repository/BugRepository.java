package com.moviemingle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviemingle.model.Bug;

public interface BugRepository extends JpaRepository<Bug, Integer>{

}
