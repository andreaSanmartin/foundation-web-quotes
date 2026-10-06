package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.RegistroFamliares;
import com.app.ista.service.RegistroFamiliaresService;

@RestController
@RequestMapping("/registroFamiliares")
public class RegistroFamiliaresController {
	
	@Autowired
	RegistroFamiliaresService regisfamser;
	
	@PostMapping("/addfamiliares")
    public RegistroFamliares guardarRegistroFamiliares(@RequestBody RegistroFamliares registrofamiliares) {
		RegistroFamliares nuevoRegistro = regisfamser.guardar(registrofamiliares);
        return nuevoRegistro;
    }
	
	@GetMapping(path = "/listadoRegistroFamiliares", produces = "application/json")
	public List<RegistroFamliares>listadoRegistroFamiliares(){
		return regisfamser.listadoRegistroFamiliares();
	}
	
	
	@GetMapping("/getByCedula")
	public List<RegistroFamliares> getByCedula(@RequestParam ("cedulaPersona") String cedulaPersona) {
		List<RegistroFamliares> familiares = regisfamser.porCedula(cedulaPersona);
		if(familiares.size() == 0) {
			return familiares;
		}
		return familiares;
	}
	
	/*	@PutMapping("/update-familiares")
	public RegistroFamliares actualizarFamiliares(@RequestBody RegistroFamliares registroFamliares){ 
		RegistroFamliares registroN = regisfamser.getFamiliaresById(registroFamliares.getIdRegistroFamiliares());
		if(registroN == null) {
			regisfamser.guardarRegistroFamiliares(registroN);
		} else {
		registroN.setCedulaFamiliar(registroFamliares.getCedulaFamiliar());
		registroN.setCedulaPersona(registroFamliares.getCedulaPersona());
		registroN.setParentesco(registroFamliares.getParentesco());
		registroN.setNombreF(registroFamliares.getNombreF());
		registroN.setApellidoF(registroFamliares.getApellidoF());
		registroN.setCelularF(registroFamliares.getCelularF());
		registroN.setCorreo(registroFamliares.getCorreo());
		regisfamser.guardar(registroN);
		}
		return registroN;
	} */
	
	@DeleteMapping("delete-familiar/{cedulaPersona}")
	 public ResponseEntity<String>eliminarRegistro(@PathVariable String cedulaPersona){
		regisfamser.eliminarFamiliar(cedulaPersona);	
      return new ResponseEntity<>(cedulaPersona, HttpStatus.OK);
	}
	
	/*@DeleteMapping("delete-familiarByiD/{idRegistroFamiliares}")
	 public ResponseEntity<Integer>eliminarRegistroById(@PathVariable int idRegistroFamiliares){
		regisfamser.eliminarFamiliarById(idRegistroFamiliares);	
      return new ResponseEntity<>(idRegistroFamiliares, HttpStatus.OK);
	}*/
	
	
}
