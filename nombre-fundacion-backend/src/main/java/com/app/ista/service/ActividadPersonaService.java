package com.app.ista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.ActividadPersona;
import com.app.ista.repository.ActividadPersonaRepository;

@Service
public class ActividadPersonaService {

	@Autowired
	ActividadPersonaRepository actividadPersonaRepository;

	public ActividadPersona guardarActividadPersona(ActividadPersona actividadPersona) {
		if (actividadPersonaRepository.findAll().isEmpty()) {
			actividadPersona.setIdActividadPersona(1);
		} else {
			actividadPersona.setIdActividadPersona(actividadPersonaRepository.id().get(0).getIdActividadPersona() + 1);
		}
		return actividadPersonaRepository.save(actividadPersona);
	}

	public List<ActividadPersona> listarActividadesPersona() {
		return actividadPersonaRepository.findAll();
	}

	public void eliminaActividad(int idActividadPersona) {
		if (actividadPersonaRepository.findByIdActividadPersona(idActividadPersona) != null) {
			this.actividadPersonaRepository.deleteById(idActividadPersona);
		}
	}

	public List<ActividadPersona> busquedaActDiaria(String fechaActividad) {
		return actividadPersonaRepository.findByFechaActividad(fechaActividad);	
	}
	
	public ActividadPersona putUsuario(Integer idUsuario) {
        return actividadPersonaRepository.findByIdActividadPersona(idUsuario);
    }

	public ActividadPersona guardar(ActividadPersona acti){
		return actividadPersonaRepository.save(acti);
	}
	public Optional<ActividadPersona> listarporId( Integer id){
		return actividadPersonaRepository.findById(id);
	}
}
