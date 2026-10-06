package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.FichaSocioeconomica;
import com.app.ista.service.FichaSocioeconomicaService;

@RestController
@RequestMapping("/fichaSocioeconomica")
public class FichaSocioeconomicaController {
	
	@Autowired
	FichaSocioeconomicaService fichaSocioeconomicaService;
	
	@PostMapping(value = "/addfichaSocioeconomica")
	public ResponseEntity<String> guardarFichaSocioeconomica(@RequestBody FichaSocioeconomica fichaSocioeconomica) {
		fichaSocioeconomicaService.guardarFichaSocioeconomica(fichaSocioeconomica);
        return ResponseEntity.ok("OK");
    }
	
	@GetMapping(path = "/listadoFichas", produces = "application/json")
	public List<FichaSocioeconomica>listarFichas(){
		return fichaSocioeconomicaService.listarFichas();
	}
	@GetMapping(path = "/bycedula/{cedula}", produces = "application/json")
	public FichaSocioeconomica porCedula(@PathVariable String cedula) {
		return fichaSocioeconomicaService.porCedula(cedula);
	}
	@PutMapping("/update-ficha")
	public FichaSocioeconomica actualizarFicha(@RequestBody FichaSocioeconomica fichaSocioeconomica){
		FichaSocioeconomica fichaSocial = fichaSocioeconomicaService.buscarPorId(fichaSocioeconomica.getIdFichaSocioeconomica());
		fichaSocial.setCedulaPersona(fichaSocioeconomica.getCedulaPersona());
		fichaSocial.setSituacionEconomica(fichaSocioeconomica.getSituacionEconomica());
		fichaSocial.setTipoVivienda(fichaSocioeconomica.getTipoVivienda());
		fichaSocial.setDescripcionVivienda(fichaSocioeconomica.getDescripcionVivienda());
		fichaSocial.setSeguro(fichaSocioeconomica.isSeguro());
		fichaSocial.setSalario(fichaSocioeconomica.getSalario());
		fichaSocial.setFechaRegistro(fichaSocioeconomica.getFechaRegistro());
		fichaSocial.setAdultoMayor(fichaSocioeconomica.isAdultoMayor());
		fichaSocial.setViveConOtros(fichaSocioeconomica.isViveConOtros());
		fichaSocial.setRecibebono(fichaSocioeconomica.isRecibebono());
		fichaSocial.setCantidadbono(fichaSocioeconomica.getCantidadbono());
		fichaSocial.setDiscapacidad(fichaSocioeconomica.isDiscapacidad());
		fichaSocial.setTipo_discapacidad(fichaSocioeconomica.getTipo_discapacidad());
		fichaSocial.setPorc_disc_mental(fichaSocioeconomica.getPorc_disc_mental());
		fichaSocial.setDescrip_disc_mental(fichaSocioeconomica.getDescrip_disc_mental());
		fichaSocial.setPorc_disc_fisica(fichaSocioeconomica.getPorc_disc_fisica());
		fichaSocial.setDescrip_disc_fisica(fichaSocioeconomica.getDescrip_disc_fisica());
		fichaSocial.setPareja(fichaSocioeconomica.isPareja());
		fichaSocial.setMadreSoltera(fichaSocioeconomica.isMadreSoltera());
		fichaSocial.setEnfermedades(fichaSocioeconomica.getEnfermedades());
		fichaSocioeconomicaService.guardar(fichaSocial);
		return fichaSocial;
	}
}
