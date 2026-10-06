package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.RegistroFamliares;
import com.app.ista.repository.RegistroFamiliaresRepository;

@Service
public class RegistroFamiliaresService {

	@Autowired
	private RegistroFamiliaresRepository regisfamrepo;

/*	public RegistroFamliares guardarRegistroFamiliares(RegistroFamliares registrofamiliares) {
		if (regisfamrepo.findAll().isEmpty()) {
			registrofamiliares.setIdRegistroFamiliares(1);
		} else {
			registrofamiliares.setIdRegistroFamiliares(regisfamrepo.id().get(0).getIdRegistroFamiliares() + 1);
		}
		return regisfamrepo.save(registrofamiliares);
	} */
	
	public RegistroFamliares guardar(RegistroFamliares registroFamliares){
		return regisfamrepo.save(registroFamliares);
	}
	public List<RegistroFamliares>listadoRegistroFamiliares(){
		return regisfamrepo.findAll();
	}
	public List<RegistroFamliares> porCedula(String cedula){
		return regisfamrepo.findByCedulaPersona(cedula);
	}
	/*public RegistroFamliares getFamiliaresById(int id) {
		return regisfamrepo.findByIdRegistroFamiliares(id);
	}*/
	
	public String eliminarFamiliar(String cedulaPersona) {
        return regisfamrepo.deleteByCedulaPersona(cedulaPersona);
    }
	
/*	public Integer eliminarFamiliarById(int idRegistroFamiliares) {
        return regisfamrepo.deleteByIdRegistroFamiliares(idRegistroFamiliares);
    } */
	
}
