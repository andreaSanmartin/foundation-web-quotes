package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.CentroMedico;

@Repository
public interface CentroMedicoRepository extends MongoRepository<CentroMedico, Long>{

	@Query(value = "{},{idCentroMedico:1}", sort = "{_id: -1}")
    List<CentroMedico> id();
	
}
