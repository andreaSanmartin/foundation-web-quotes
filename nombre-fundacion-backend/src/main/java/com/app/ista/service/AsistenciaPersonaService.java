package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.AsistenciaPersona;
import com.app.ista.repository.AsistenciaPersonaRepository;

@Service
public class AsistenciaPersonaService{
	
	@Autowired
	private AsistenciaPersonaRepository asistenciapersonarepository;
	
	public AsistenciaPersona guardarAsistenciaPersona(AsistenciaPersona asistencia) {
		if (asistenciapersonarepository.findAll().isEmpty()) {
			asistencia.setIdAsistencia(1);
		} else {
			asistencia.setIdAsistencia(asistenciapersonarepository.id().get(0).getIdAsistencia()+1);
		}
		return asistenciapersonarepository.save(asistencia);
	}
	
	public AsistenciaPersona guardar(AsistenciaPersona asistencia) {
		return asistenciapersonarepository.save(asistencia);
	}
	
	public List<AsistenciaPersona> listarAsistencias(){
		return asistenciapersonarepository.findAll();
	}
	
	public List<AsistenciaPersona> buscarPorCedula(String cedulaPersona) {
		return asistenciapersonarepository.findByCedulaPersona(cedulaPersona);
	}
	
	public AsistenciaPersona buscarPorIdAsistencia(int idAsistencia) {
		return asistenciapersonarepository.findByIdAsistencia(idAsistencia);
	}
	
	public Integer eliminarAsistencia(Integer idAsistencia) {
		return asistenciapersonarepository.deleteByIdAsistencia(idAsistencia);
	}
}
