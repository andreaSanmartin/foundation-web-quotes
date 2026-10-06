package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.TipoServicio;
import com.app.ista.repository.TipoSerActiRepository;

@Service
public class TipoSerActiService {
    
    @Autowired
    TipoSerActiRepository TpoActividadRepo;

    public TipoServicio guardadTipoAct(TipoServicio TipoAct){
        if (TpoActividadRepo.findAll().isEmpty()) {
			TipoAct.setIdTipoActividad(1);
        } else {
        	TipoAct.setIdTipoActividad(TpoActividadRepo.id().get(0).getIdTipoActividad()+ 1);
        }
		return TpoActividadRepo.save(TipoAct);
    }

    public void eliminarTipoAct(int idTipoActividad){
      if (TpoActividadRepo.findByIdTipoActividad(idTipoActividad)!= null) {
        this.TpoActividadRepo.deleteById(idTipoActividad);
      }
    } 

  public List<TipoServicio>listarAll(){
		return TpoActividadRepo.findAll();
	}

  public TipoServicio byNombreActi(String nombreActividad){
		return TpoActividadRepo.findByNombreActividad(nombreActividad);
	}
}
