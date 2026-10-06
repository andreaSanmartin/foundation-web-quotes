package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.Especialidad;

@Repository
public interface EspecialidadRepository extends MongoRepository <Especialidad, Long> {
	
	@Query(value = "{},{idEspecialidad:1}", sort = "{_id: -1}")
	List<Especialidad> id();
}
