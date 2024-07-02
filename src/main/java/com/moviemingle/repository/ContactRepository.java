package com.moviemingle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moviemingle.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	 @Query("SELECT c FROM Contact c WHERE c.contactName LIKE %?1% OR c.contactEmail LIKE %?1% OR c.contactSubject LIKE %?1% OR c.contactMessage LIKE %?1%")
	    List<Contact> searchByQuery(String query);
	
}
