package com.app.ista.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.ista.model.DocumentosBeneficiario;

@Repository
public interface DocumentosBeneficiarioRepository extends MongoRepository <DocumentosBeneficiario, Long> {
    
	@Query(value = "{},{idDocumentos:1}", sort = "{_id: -1}")
    List<DocumentosBeneficiario> id();
    
    List<DocumentosBeneficiario> findByCedulaPersona(String cedulaPersona);
    
    DocumentosBeneficiario deleteByCedulaPersona(String cedulaPersona);
    
    DocumentosBeneficiario findByIdDocumentos (int idDocumentos);
    
    @Transactional
	Integer deleteByIdDocumentos(int idDocumentos);
    
}