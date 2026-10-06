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

import com.app.ista.model.Donaciones;
import com.app.ista.service.DonacionesService;

@RestController
@RequestMapping("/donaciones")
public class DonacionesController {
	
	@Autowired
	DonacionesService donacserv;
	
	@PostMapping(path = "/", produces = "application/json")
    public Donaciones guardarDonacion(@RequestBody Donaciones donaciones){
		return donacserv.guardarDonacion(donaciones);
    }

	@GetMapping(path = "/listadoDonaciones", produces = "application/json")
	public List<Donaciones>listadoDonaciones(){
		return donacserv.listadoDonaciones();
	}
	
	@GetMapping(path = "/listaByNombre/{nombreDonacion}", produces = "application/json")
	public List<Donaciones> listaPorNombre(@PathVariable (value = "nombreDonacion") String nombreDonacion){
		return donacserv.listaDoncionesByNombre(nombreDonacion);
	}
	
	@GetMapping(path = "/listaByCategoria/{categoria}", produces = "application/json")
	public List<Donaciones> listaPorCategoria(@PathVariable (value = "categoria") String categoria){
		return donacserv.listaDonacionesByCategoria(categoria);
	}
	
	@GetMapping(path = "/{idDonacion}", produces = "application/json")
	public Optional<Donaciones> leePorId(@PathVariable (value = "idDonacion") Long idDonacion) {
		return donacserv.porId(idDonacion);
	}
	
	@PutMapping(path = "/update/{idDonacion}", produces = "application/json")
	public Donaciones updateDonacion(@PathVariable (value = "idDonacion") Long idDonacion, @RequestBody Donaciones donaciones) {
		return donacserv.actualizaDonacion(idDonacion, donaciones);
	}
	
	@DeleteMapping(path = "/delete/{idDonacion}", produces = "application/json")
	public boolean eliminarDonacion(@PathVariable (value = "idDonacion") Long idDonacion) {
		return donacserv.eliminarDonacion(idDonacion);
	}
}
