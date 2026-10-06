package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.RegistroCalificacion;

@Repository
public interface RegistroCalificacionRepository extends MongoRepository <RegistroCalificacion, Long> {
	
	@Query(value = "{},{idRegistroCalificacion:1}", sort = "{_id: -1}")
	List<RegistroCalificacion> id();
	
}
