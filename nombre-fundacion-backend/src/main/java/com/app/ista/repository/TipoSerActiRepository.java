package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ista.model.TipoServicio;

@Repository
public interface TipoSerActiRepository extends MongoRepository<TipoServicio, Integer>{
    
    @Query(value = "{},{idTipoActividad:1}", sort = "{_id: -1}")
    List<TipoServicio> id();

    TipoServicio findByNombreActividad(String nombreActividad);

    TipoServicio findByIdTipoActividad(int idTipoActividad);
}
