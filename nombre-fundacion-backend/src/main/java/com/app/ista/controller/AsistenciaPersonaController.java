package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.AsistenciaPersona;
import com.app.ista.service.AsistenciaPersonaService;

@RestController
public class AsistenciaPersonaController {

	@Autowired
	AsistenciaPersonaService asistenciaservice;
	
	@GetMapping("/getAll-asistencia")
	public List<AsistenciaPersona> listarAsistencias() { 
		return asistenciaservice.listarAsistencias();
	}
	
	@GetMapping("/get-asistenciabycedula")
	public List<AsistenciaPersona> listarAsistenciasPorCedula(@RequestParam("cedulaPersona") String cedulaPersona) {
		List<AsistenciaPersona> asistencias = asistenciaservice.buscarPorCedula(cedulaPersona);
		return asistencias;
	}
	
	@PostMapping("/post-asistencia")
	public AsistenciaPersona guardarAsistencia(@RequestBody AsistenciaPersona asistencia) {
		AsistenciaPersona asistenciacreada = asistenciaservice.guardarAsistenciaPersona(asistencia);
		return asistenciacreada;
	}
	
	@PutMapping("/update-asistencia")
	public AsistenciaPersona actualizarAsistencia(@RequestBody AsistenciaPersona asistencia) {
		AsistenciaPersona asistenciaAct = asistenciaservice.buscarPorIdAsistencia(asistencia.getIdAsistencia());
		asistenciaAct.setCedulaPersona(asistencia.getCedulaPersona());
		asistenciaAct.setActividad(asistencia.getActividad());
		asistenciaAct.setDescripcion(asistencia.getDescripcion());
		asistenciaAct.setFecha(asistencia.getFecha());
		asistenciaservice.guardar(asistenciaAct);
		return asistenciaAct;
	}
	
	@DeleteMapping("delete-asistencia/{idAsistencia}")
	 public ResponseEntity<Integer>eliminarAsistencia(@PathVariable int idAsistencia){
		asistenciaservice.eliminarAsistencia(idAsistencia);	
      return new ResponseEntity<>(idAsistencia, HttpStatus.OK);
	}
	
}
	
