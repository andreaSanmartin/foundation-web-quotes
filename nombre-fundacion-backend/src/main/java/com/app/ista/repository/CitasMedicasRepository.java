package com.app.ista.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.CitasMedicas;


@Repository
public interface CitasMedicasRepository extends MongoRepository<CitasMedicas, Long> {

	@Query(value = "{},{idCitasMedicas:1}", sort = "{_id: -1}")
    List<CitasMedicas> id();
	
	List<CitasMedicas> findByFechaCitaMedica(Date fechaCitaMedica);
        
        
	CitasMedicas findByCentroMedico(String centroMedico);
        
        CitasMedicas findByPaciente(String paciente);
        
        CitasMedicas findByAcompaniante(String acompaniante);
        
        CitasMedicas findByTrabajadorFundacion(String trabajadorFundacion);
}
