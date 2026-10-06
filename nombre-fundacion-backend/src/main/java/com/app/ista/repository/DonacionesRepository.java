package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.Donaciones;

@Repository
public interface DonacionesRepository extends MongoRepository <Donaciones, Long>{
	
	@Query(value = "{},{idDonacion:1}", sort = "{_id: -1}")
	List<Donaciones> id();
	
	List<Donaciones> findByNombreDonacion(String nombreDonacion);
	
	List<Donaciones> findByCategoria(String categoria);
	
}
