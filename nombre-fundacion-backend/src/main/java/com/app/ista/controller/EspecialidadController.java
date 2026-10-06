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
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.Especialidad;
import com.app.ista.service.EspecialidadService;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
	@Autowired
	EspecialidadService espeser;
	
	@PostMapping(path = "/guardar")
    public Especialidad guardarEspecialidad(@RequestBody Especialidad especialidad) {
		espeser.guardarEspecialidad(especialidad);
        return especialidad;
    }
	
	@GetMapping(path = "/listado", produces = "application/json")
	public List<Especialidad>listarEspecialidad(){
		return espeser.listarEspecialidad();
	}


	@DeleteMapping(path = "/eliminar/{especialidadId}")
	public void deleteByIdEspecialidad(@PathVariable (name = "especialidadId") Long especialidadId){
		espeser.eliminarEspecialidad(especialidadId);
	}

	@PutMapping(path = "/editar/{especialidadId}")
	public Especialidad modificarEspecialidad(@RequestBody Especialidad especialidadG,
	@PathVariable(name = "especialidadId") Long especialidadId) {
		Especialidad esp = espeser.listarEspecialidadId(especialidadId).get();
		esp.setNombreEspecialidad(especialidadG.getNombreEspecialidad());
		esp.setCentroMedico(especialidadG.getCentroMedico());
		espeser.editarEspecialidad(especialidadId, esp);
		return esp;
    }
}
