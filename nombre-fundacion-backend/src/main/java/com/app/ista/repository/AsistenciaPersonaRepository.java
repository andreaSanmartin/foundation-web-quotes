package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.ista.model.AsistenciaPersona;

@Repository
public interface AsistenciaPersonaRepository extends MongoRepository<AsistenciaPersona, Integer>{
	
	@Query(value = "{},{idAsistencia:1}", sort = "{_id: -1}")
    List<AsistenciaPersona> id();
	
	List<AsistenciaPersona> findByCedulaPersona (String cedulaPersona);
	
	AsistenciaPersona findByIdAsistencia(int idAsistencia);
	
	@Transactional
	Integer deleteByIdAsistencia(int idAsistencia);

}
