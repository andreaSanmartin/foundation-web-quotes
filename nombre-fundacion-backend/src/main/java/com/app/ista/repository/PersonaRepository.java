package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, Integer>{
	
	@Query(value = "{},{idPersona:1}", sort = "{_id: -1}")
    List<Persona> id();
	
	Persona findByCorreo(String correo);

	Persona findByCedula(String cedula);
	
	Persona deleteByCedula(String cedula);
	
	List<Persona> findByEstadoActivoAndBeneficiario(boolean estadoActivo, boolean beneficiario);
	
	Persona findByIdPersona(int idPersona);
}
