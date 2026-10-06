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

import com.app.ista.model.EntregaDonacion;
import com.app.ista.service.EntregaDonacionService;

@RestController
@RequestMapping("/entregaDonacion")
public class EntregaDonacionController {
	
	@Autowired
	EntregaDonacionService servicio;
	
	@PostMapping(path = "/", produces = "application/json")
	public EntregaDonacion postEntregaDonacion(@RequestBody EntregaDonacion entregaDonacion) {
		return servicio.guardarEntregaDonacion(entregaDonacion); 
	}
	
	@GetMapping(path = "/lista", produces = "application/json")
	public List<EntregaDonacion> listarEntregas(){
		return servicio.allEntregasDonaciones();
	}
	
	@GetMapping(path = "/byCedula/{cedulaBeneficiario}", produces = "application/json")
	public List<EntregaDonacion> porCedulaBeneficiario(@PathVariable (value = "cedulaBeneficiario") String cedulaBeneficiario){
		return servicio.listaEntregasByCedula(cedulaBeneficiario);
	}
	
	@GetMapping(path = "/{idEntregaDonacion}", produces = "application/json")
	public Optional<EntregaDonacion> leerPorId(@PathVariable (value = "idEntregaDonacion") Integer idEntregaDonacion){
		return servicio.porId(idEntregaDonacion);
	}
	
	@PutMapping(path = "/update/{idEntregaDonacion}", produces = "application/json")
	public EntregaDonacion updateEntregaDonacion(@PathVariable (value = "idEntregaDonacion") Integer idEntregaDonacion, @RequestBody EntregaDonacion entregaDonacion) {
		return servicio.actualizaEntregaDonacion(idEntregaDonacion, entregaDonacion);
	}
	
	@DeleteMapping(path = "/delete/{idEntregaDonacion}", produces = "application/json")
	public boolean eliminarEntregaDonacion(@PathVariable (value = "idEntregaDonacion") Integer idEntregaDonacion) {
		return servicio.eliminarEntregaDonacion(idEntregaDonacion);
	}

}
