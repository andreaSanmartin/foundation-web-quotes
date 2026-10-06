package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.DocumentosBeneficiario;
import com.app.ista.repository.DocumentosBeneficiarioRepository;

@Service
public class DocumentosBeneficiarioService {
    @Autowired
    private DocumentosBeneficiarioRepository documentosBeneficiarioRepository;

    public DocumentosBeneficiario guardarDocumentos(DocumentosBeneficiario documentosBeneficiario){
        if(documentosBeneficiarioRepository.findAll().isEmpty()){
            documentosBeneficiario.setIdDocumentos(1);
        }else{
            documentosBeneficiario.setIdDocumentos(documentosBeneficiarioRepository.id().get(0).getIdDocumentos()+1);
        }
        return documentosBeneficiarioRepository.save(documentosBeneficiario);
        
    }
    
    public DocumentosBeneficiario guardardoc(DocumentosBeneficiario documentosBeneficiario){
        return documentosBeneficiarioRepository.save(documentosBeneficiario);
    }
    
    public List<DocumentosBeneficiario>listardoDocumetnos(){
        return documentosBeneficiarioRepository.findAll();
    }
    
    public List<DocumentosBeneficiario> porCedula(String cedulaPersona){
        return documentosBeneficiarioRepository.findByCedulaPersona(cedulaPersona);
    }
    
    public DocumentosBeneficiario porId(int idDocumentos) {
    	return documentosBeneficiarioRepository.findByIdDocumentos(idDocumentos);
    }
    
    public Integer eliminarDocumento(Integer idDocumentos) {
        return documentosBeneficiarioRepository.deleteByIdDocumentos(idDocumentos);
    }
}
