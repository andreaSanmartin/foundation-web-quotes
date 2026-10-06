package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.RegistroCalificacion;
import com.app.ista.repository.RegistroCalificacionRepository;

@Service
public class RegistroCalificacionService {
	
	@Autowired
	private RegistroCalificacionRepository regiscalifrepo;

	public RegistroCalificacion guardarRegistroCalificacion(RegistroCalificacion registrocalificacion) {
		if (regiscalifrepo.findAll().isEmpty()) {
			registrocalificacion.setIdRegistroCalificacion(1);
		} else {
			registrocalificacion.setIdRegistroCalificacion(regiscalifrepo.id().get(0).getIdRegistroCalificacion() + 1);
		}
		return regiscalifrepo.save(registrocalificacion);
	}
	
	public List<RegistroCalificacion>listadoRegistroCalificaciones(){
		return regiscalifrepo.findAll();
	}
}
