package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.AgendamientoActividad;

@Repository
public interface AgendamientoActividadRepository extends MongoRepository<AgendamientoActividad, Long> {

	@Query(value = "{},{agendamientoActividad:1}", sort = "{_id: -1}")
	List<AgendamientoActividad> id();

}
