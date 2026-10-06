package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.FichaSocioeconomica;

@Repository
public interface FichaSocioeconomicaRepository extends MongoRepository<FichaSocioeconomica, Long>{

	@Query(value = "{},{idFichaSocioeconomica:1}", sort = "{_id: -1}")
    List<FichaSocioeconomica> id();
    FichaSocioeconomica findByCedulaPersona(String cedulaPersona);
    FichaSocioeconomica deleteByCedulaPersona(String cedulaPersona);
    FichaSocioeconomica findByIdFichaSocioeconomica(int idFichaSocioeconomica);
}
