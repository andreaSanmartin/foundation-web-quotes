package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.RegistroCalificacion;
import com.app.ista.service.RegistroCalificacionService;

@RestController
@RequestMapping("/registroCalificacion")
public class RegistroCalificacionController {
	
	@Autowired
	RegistroCalificacionService registrocalificacionservice;
	
	@PostMapping
    public ResponseEntity<String> guardarRegistroCalificacion(@RequestBody RegistroCalificacion registrocalificacion) {
		registrocalificacionservice.guardarRegistroCalificacion(registrocalificacion);
        return ResponseEntity.ok("OK");
    }
	
	@GetMapping(path = "/listadoRegistroCalificaciones", produces = "application/json")
	public List<RegistroCalificacion>listadoRegistroCalificaciones(){
		return registrocalificacionservice.listadoRegistroCalificaciones();
	}

}
