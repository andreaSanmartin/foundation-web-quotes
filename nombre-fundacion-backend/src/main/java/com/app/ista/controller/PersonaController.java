package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.AsistenciaPersona;
import com.app.ista.model.FichaSocioeconomica;
import com.app.ista.model.PerRegFicDTO;
import com.app.ista.model.Persona;
import com.app.ista.model.RegistroFamliares;
import com.app.ista.service.AsistenciaPersonaService;
import com.app.ista.service.FichaSocioeconomicaService;
import com.app.ista.service.PersonaService;
import com.app.ista.service.RegistroFamiliaresService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	PersonaService personaService;
	@Autowired
	RegistroFamiliaresService registrofamservice;
	@Autowired
	FichaSocioeconomicaService fichaservice;
	@Autowired
	AsistenciaPersonaService asistenciaservice;

	@PostMapping("/crearPersona")
	public Persona guardarPersona(@RequestBody Persona persona) {
		Persona validar = personaService.porCedula(persona.getCedula());
		if(validar == null) {
			Persona nuevaPersona = personaService.guardarPersona(persona);
			System.out.println(nuevaPersona.getCedula());
			return nuevaPersona;	
		} else {
			return null;
		}
	}

	@GetMapping("/listadoPersonas")
	public List<Persona> listarPersonas() {
		return personaService.listarPersonas();
	}

	@GetMapping(path = "/listarPorCorreo")
	public Persona recuperarPorCorreo(@RequestParam("correoPersona") String correoPersona ){
		Persona person = personaService.recuperarPorCorreo(correoPersona);
		if(person != null) {
			return person;
		}
		return new Persona();
	}
	
	@GetMapping("/getPersonaByCedula")
	public Persona porCedula(@RequestParam("cedula") String cedula) {
		Persona persona = personaService.porCedula(cedula);
		if (persona != null) {
			return persona;
		}
		return new Persona();
	}
	
	@DeleteMapping("delete-persona/{cedula}")
	public void eliminarPersona(@PathVariable String cedula){
		personaService.eliminarPersona(cedula);
	}
	
	@PutMapping("/update-persona")
	public Persona actualizarPersona(@RequestBody Persona persona) {
		Persona per= personaService.getPersonaById(persona.getIdPersona());
		per.setCedula(persona.getCedula());
		per.setNombres(persona.getNombres());
		per.setApellidos(persona.getApellidos());
		per.setDireccion(persona.getDireccion());
		per.setCelular(persona.getCelular());
		per.setCorreo(persona.getCorreo());
		per.setGenero(persona.getGenero());
		per.setFechaNacimiento(persona.getFechaNacimiento());
		per.setEdad(persona.getEdad());
		per.setNacionalidad(persona.getNacionalidad());
		per.setEstado_civil(persona.getEstado_civil());
		per.setBeneficiario(persona.isBeneficiario());
		per.setEstadoActivo(persona.isEstadoActivo());
		per.setFaltas(persona.getFaltas());
		personaService.guardar(per);
		return per;
	}
	
	@GetMapping("/listadoBeneficiariosActivos")
	public List<Persona> listarPorEstadoYTipo(@RequestParam ("estadoActivo") boolean estadoActivo, @RequestParam ("beneficiario") boolean beneficiario) {
		return personaService.findByEstadoAndBeneficiario(estadoActivo, beneficiario);
	}
	
	@GetMapping("/getAllInfo")
	public PerRegFicDTO getAllInfo(@RequestParam ("cedula") String cedula){
		Persona persona = personaService.porCedula(cedula);
		List<RegistroFamliares> familiares = registrofamservice.porCedula(cedula);
		FichaSocioeconomica ficha = fichaservice.porCedula(cedula);
		List<AsistenciaPersona> asistencias = asistenciaservice.buscarPorCedula(cedula);
		PerRegFicDTO respuesta = new PerRegFicDTO();
		respuesta.setIdPerRegFicDTO(persona.getIdPersona());
		respuesta.setCedula(persona.getCedula());
		respuesta.setNombres(persona.getNombres());
		respuesta.setApellidos(persona.getApellidos());
		respuesta.setDireccion(persona.getDireccion());
		respuesta.setCelular(persona.getCelular());
		respuesta.setCorreo(persona.getCorreo());
		respuesta.setGenero(persona.getGenero());
		respuesta.setFechaNacimiento(persona.getFechaNacimiento());
		respuesta.setEdad(persona.getEdad());
		respuesta.setNacionalidad(persona.getNacionalidad());
		respuesta.setEstado_civil(persona.getEstado_civil());
		respuesta.setFaltas(persona.getFaltas());
		respuesta.setFamiliares(familiares);
		respuesta.setSituacionEconomica(ficha.getSituacionEconomica());
		respuesta.setTipoVivienda(ficha.getTipoVivienda());
		respuesta.setDescripcionVivienda(ficha.getDescripcionVivienda());
		respuesta.setSeguro(ficha.isSeguro());
		respuesta.setSalario(ficha.getSalario());
		respuesta.setFechaRegistro(ficha.getFechaRegistro());
		respuesta.setAdultoMayor(ficha.isAdultoMayor());
		respuesta.setViveConOtros(ficha.isViveConOtros());
		respuesta.setRecibebono(ficha.isRecibebono());
		respuesta.setCantidadbono(ficha.getCantidadbono());
		respuesta.setDiscapacidad(ficha.isDiscapacidad());
		respuesta.setTipo_discapacidad(ficha.getTipo_discapacidad());
		respuesta.setPorc_disc_mental(ficha.getPorc_disc_mental());
		respuesta.setDescrip_disc_mental(ficha.getDescrip_disc_mental());
		respuesta.setPorc_disc_fisica(ficha.getPorc_disc_fisica());
		respuesta.setDescrip_disc_fisica(ficha.getDescrip_disc_fisica());
		respuesta.setPareja(ficha.isPareja());
		respuesta.setMadreSoltera(ficha.isMadreSoltera());
		respuesta.setEnfermedades(ficha.getEnfermedades());
		respuesta.setAsistencias(asistencias);
		return respuesta;
	}
	
}
