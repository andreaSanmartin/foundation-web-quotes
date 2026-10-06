package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.ista.model.Usuarios;

@Repository
public interface UsuariosRepository extends MongoRepository<Usuarios, Integer>{
	
	@Query(value = "{},{idUsuario:1}", sort = "{_id: -1}")
    List<Usuarios> id();

	Usuarios findByUsuarioCedulaAndUsuarioContraseniaAndUsuarioTipo(String usuarioCedula, String usuarioContrasenia, int usuarioTipo);
	
	Usuarios findByIdUsuario(int idUsuario);
	
	Usuarios findByUsuarioCedula(String usuarioCedula);
	
	@Transactional
	Integer deleteByIdUsuario(int idUsuario);
}
