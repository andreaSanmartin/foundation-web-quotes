package com.app.ista.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.CentroMedico;
import com.app.ista.service.CentroMedicoService;

//cambios
@RestController
@RequestMapping("/centroMedico")
public class CentroMedicoController {
	
	@Autowired
	CentroMedicoService centroMedicoService;
	
	@PostMapping(path = "/guardar")
	public CentroMedico guardarCentroMedico(@RequestBody CentroMedico centroMed) {
		centroMedicoService.guardarCentroMedico(centroMed);
        return centroMed;
    }

	boolean transaccion;

	@GetMapping(path = "/listado", produces = "application/json")
	public List<CentroMedico>listarCentroMedico(){
		return centroMedicoService.listarCentroMedico();
	}

	@GetMapping(path = "/listadoId/{centroMedicoId}", produces = "application/json")
	public Optional<CentroMedico>listarCentroMedicoId(Long idCentro){
		return centroMedicoService.listarCentroId(idCentro);
	}	


	@DeleteMapping(path = "/eliminar/{centroMedicoId}")
	public void deleteByIdCentroMe(@PathVariable (name = "centroMedicoId") Long centroMedicoId){
		centroMedicoService.eliminarCentroMedico(centroMedicoId);
	}

	@PutMapping(path = "/editar/{centroMedicoId}")
	public CentroMedico modificarCentroMedico(@RequestBody CentroMedico centroMedico,
	@PathVariable(name = "centroMedicoId") Long centroMedicoId) {
		CentroMedico centro = centroMedicoService.listarCentroId(centroMedicoId).get();
		centro.setDireccionCentroMedico(centroMedico.getDireccionCentroMedico());
		centro.setNombreCentroMedico(centroMedico.getNombreCentroMedico());
		centro.setTelefonoCentroMedico(centroMedico.getTelefonoCentroMedico());
		centroMedicoService.editarCentroMedico(centroMedicoId, centro);
		return centro;
    }

}
