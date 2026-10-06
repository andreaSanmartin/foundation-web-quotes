package com.app.ista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.CentroMedico;
import com.app.ista.repository.CentroMedicoRepository;

//cambios
@Service
public class CentroMedicoService {
	
	@Autowired
	private CentroMedicoRepository centroMedicoRepository;
	
	public CentroMedico guardarCentroMedico(CentroMedico centroMedico) {
		if (centroMedicoRepository.findAll().isEmpty()) {
			centroMedico.setIdCentroMedico(1);
		} else {
			centroMedico.setIdCentroMedico(centroMedicoRepository.id().get(0).getIdCentroMedico()+ 1);
		}
        return centroMedicoRepository.save(centroMedico);
	}
	
	
	public List<CentroMedico>listarCentroMedico(){
		return centroMedicoRepository.findAll();
	}

	public Optional<CentroMedico> listarCentroId(Long id){
        return  centroMedicoRepository.findById(id);
    }

	public void eliminarCentroMedico(Long idcentroMedico) {
		
         centroMedicoRepository.deleteById(idcentroMedico);
	}

	public CentroMedico editarCentroMedico(Long idCentro, CentroMedico centroMedico) {
		
        return centroMedicoRepository.save(centroMedico);
	}
	

}