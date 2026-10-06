package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.AgendamientoActividad;
import com.app.ista.repository.AgendamientoActividadRepository;

@Service
public class AgendamientoActividadService {

	@Autowired
	private AgendamientoActividadRepository agendaactivirepo;
	
	public AgendamientoActividad guardarActividadPersona(AgendamientoActividad agendamientoactividad) {
		if (agendaactivirepo.findAll().isEmpty()) {
			agendamientoactividad.setAgendamientoActividad(1);
        } else {
        	agendamientoactividad.setAgendamientoActividad(agendaactivirepo.id().get(0).getAgendamientoActividad() + 1);
        }
		return agendaactivirepo.save(agendamientoactividad);
	}
	
	public List<AgendamientoActividad> listarAgendamientoActividad(){
		return agendaactivirepo.findAll();
	}
}
