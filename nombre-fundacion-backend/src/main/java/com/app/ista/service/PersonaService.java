package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.Persona;
import com.app.ista.repository.PersonaRepository;

@Service
public class PersonaService {


	@Autowired
    private PersonaRepository personaRepository;
	
	public Persona guardarPersona(Persona persona) {
		if (personaRepository.findAll().isEmpty()) {
			persona.setIdPersona(1);
        } else {
        	persona.setIdPersona(personaRepository.id().get(0).getIdPersona()+ 1);
        }
        return personaRepository.save(persona);
	}
	
	public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }
	public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }
	
	public Persona recuperarPorCorreo(String correoPersona){
		return personaRepository.findByCorreo(correoPersona);
	}

	public Persona porCedula(String cedula){
		return personaRepository.findByCedula(cedula);
	}
	
	public Persona eliminarPersona(String cedula) {
		return personaRepository.deleteByCedula(cedula);
	}
	
	public List<Persona> findByEstadoAndBeneficiario(boolean estadoActivo, boolean beneficiario) {
		return personaRepository.findByEstadoActivoAndBeneficiario(estadoActivo, beneficiario);
	}

	public Persona getPersonaById(int idPersona) {
		return personaRepository.findByIdPersona(idPersona);
	}
	
	
}
