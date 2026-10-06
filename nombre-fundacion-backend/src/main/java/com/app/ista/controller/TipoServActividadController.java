package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.TipoServicio;
import com.app.ista.service.TipoSerActiService;

@RestController
@RequestMapping("/tipoactividad")
public class TipoServActividadController {
    
    @Autowired
    TipoSerActiService TipoActiService;


    @PostMapping
    public TipoServicio guardarTipoActividadPersona(@RequestBody TipoServicio tipoActividad){
        TipoActiService.guardadTipoAct(tipoActividad);
        return tipoActividad;
    }

    @GetMapping(path = "/listadoTipoAct", produces = "application/json")
	public List<TipoServicio> listarActividadesPersona() {
		return TipoActiService.listarAll();
	}

    @GetMapping(path = "/bynombre/{nombreActividad}", produces = "application/json")
	public TipoServicio porNombre(@PathVariable String nombreActividad) {
		return TipoActiService.byNombreActi(nombreActividad);
	}

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        this.TipoActiService.eliminarTipoAct(id);
    }
}
