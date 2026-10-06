package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.AgendamientoActividad;
import com.app.ista.service.AgendamientoActividadService;

@RestController
@RequestMapping("/agendamientoActividad")
public class AgendamientoActividadController {
	
	@Autowired
	AgendamientoActividadService agendamientoActividadService;
	
	@PostMapping
    public ResponseEntity<String> guardarAgendamientoActividad(@RequestBody AgendamientoActividad agendamientoActividad) {
		agendamientoActividadService.guardarActividadPersona(agendamientoActividad);
        return ResponseEntity.ok("OK");
    }
	
	@GetMapping(path = "/listadoAgendamientoActividad", produces = "application/json")
	public List<AgendamientoActividad>listadoAgendamientoActividad(){
		return agendamientoActividadService.listarAgendamientoActividad();
	}

}
