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

import com.app.ista.model.ActividadPersona;
import com.app.ista.service.ActividadPersonaService;

@RestController
@RequestMapping("/actividadPersona")
public class ActividadPersonaController {

	@Autowired
	ActividadPersonaService actividadPersonaService;

	@PostMapping
	public ActividadPersona guardarActividadPersona(@RequestBody ActividadPersona actividadPersona) {
		actividadPersonaService.guardarActividadPersona(actividadPersona);
		return actividadPersona;
	}

	@GetMapping(path = "/listadoActividadesPersona", produces = "application/json")
	public List<ActividadPersona> listarActividadesPersona() {
		return actividadPersonaService.listarActividadesPersona();
	}
	
	@DeleteMapping("/eliminar/{idActividadPersona}")
    public void eliminar(@PathVariable int idActividadPersona) {
        this.actividadPersonaService.eliminaActividad(idActividadPersona);
    }

	@GetMapping(path = "/busquedaDiaria/{fechaActividad}", produces = "application/json")
	public List<ActividadPersona> getActiviDiria(@PathVariable String fechaActividad) {
		return actividadPersonaService.busquedaActDiaria(fechaActividad);
	}

	@PutMapping("/update-acti/{IdActividadPersona}")
	public ActividadPersona actualizarActividad(@RequestBody ActividadPersona acti, @PathVariable (name="IdActividadPersona") Integer id) {
		ActividadPersona Act = actividadPersonaService.listarporId(id).get();
		Act.setCedulaPersona(acti.getCedulaPersona());
		Act.setFechaActividad(acti.getFechaActividad());
		Act.setHoraInicio(acti.getHoraInicio());
		Act.setHoraFin(acti.getHoraFin());
		Act.setDescripcionActividad(acti.getDescripcionActividad());
		Act.setTipoActividad(acti.getTipoActividad());
		Act.setAsistencia(acti.isAsistencia());
		actividadPersonaService.guardar(Act);
		return Act;
	}	
}
	