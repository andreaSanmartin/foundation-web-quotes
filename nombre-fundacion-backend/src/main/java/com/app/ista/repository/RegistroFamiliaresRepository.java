package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.ista.model.RegistroFamliares;

@Repository
public interface RegistroFamiliaresRepository extends MongoRepository <RegistroFamliares, Integer>{
	
	/*@Query(value = "{},{idRegistroFamiliares:1}", sort = "{_id: -1}")
	List<RegistroFamliares> id();*/
	
	List<RegistroFamliares> findByCedulaPersona(String cedulaPersona);
	
 //   RegistroFamliares findByIdRegistroFamiliares(int idRegistroFamiliares);
    
    @Transactional
	String deleteByCedulaPersona(String cedulaPersona);
    
  //  @Transactional
//	Integer deleteByIdRegistroFamiliares(int idRegistroFamiliares);
    
}	
