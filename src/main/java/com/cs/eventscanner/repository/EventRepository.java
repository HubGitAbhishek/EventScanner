package com.cs.eventscanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cs.eventscanner.modal.Events;

public interface EventRepository extends JpaRepository<Events, String> {

	@Query("FROM Events WHERE alert = ?1")
	List<Events> findByAlertValue(boolean alert);

}
