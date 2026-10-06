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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.ObservacionesPersona;
import com.app.ista.service.ObservacionesPersonaService;

@RestController
@RequestMapping("/observacionesPersonas")
public class ObservacionesPersonaController {
    @Autowired
    ObservacionesPersonaService observacionesPersonaService;

    @PostMapping(value = "/addobservacionesPersonas")
    public ObservacionesPersona guardarObservacionesPersonas(@RequestBody ObservacionesPersona observacionesPersona){
    	ObservacionesPersona observacionCreada = observacionesPersonaService.guardarObservacionesPersonas(observacionesPersona);
        return observacionCreada;
    }
    
    @GetMapping("/getObservaciones")
    public List<ObservacionesPersona> getObservaciones(){
    	return observacionesPersonaService.listarObservaciones();
    }
    
    @GetMapping("/byCedulaObservaciones/{cedulaPersona}")
    public List<ObservacionesPersona> byCedulaObservaciones(@PathVariable String cedulaPersona){
        return observacionesPersonaService.listByCedula(cedulaPersona);
    }

    @PutMapping("/update-Observaciones")
    public ObservacionesPersona actualizarObservaciones(@RequestBody ObservacionesPersona observacionesPersona){
        ObservacionesPersona observacionesPersona2 = observacionesPersonaService.getporId(observacionesPersona.getIdObservacionesPersona());
        observacionesPersona2.setDescripcionobservacion(observacionesPersona.getDescripcionobservacion());
        observacionesPersonaService.guardar(observacionesPersona2);
        return observacionesPersona2;
    }
    
    @DeleteMapping("/deleteObser/{idObservacionesPersona}")
    public ResponseEntity<Integer>eliminarEmpleado(@PathVariable int idObservacionesPersona){		
    	observacionesPersonaService.eliminarObservacion(idObservacionesPersona);	
    	return new ResponseEntity<>(idObservacionesPersona, HttpStatus.OK);
	}
}

