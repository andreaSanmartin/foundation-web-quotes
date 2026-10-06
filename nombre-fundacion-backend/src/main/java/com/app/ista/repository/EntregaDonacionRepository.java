package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.EntregaDonacion;

@Repository
public interface EntregaDonacionRepository extends MongoRepository<EntregaDonacion, Integer>{
	
	@Query(value = "{},{idEntregaDonacion:1}", sort = "{_id: -1}")
	List<EntregaDonacion> id();
	
	List<EntregaDonacion> findByCedulaBeneficiario(String cedulaBeneficiario);
}
