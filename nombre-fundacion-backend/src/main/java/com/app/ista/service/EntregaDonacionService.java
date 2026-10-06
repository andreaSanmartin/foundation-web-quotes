package com.app.ista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.EntregaDonacion;
import com.app.ista.repository.EntregaDonacionRepository;

@Service
public class EntregaDonacionService {

	@Autowired
	private EntregaDonacionRepository repository;
	
	public EntregaDonacion guardarEntregaDonacion(EntregaDonacion entregaDonacion) {
		if (repository.findAll().isEmpty()) {
			entregaDonacion.setIdEntregaDonacion(1);
		}else {
			entregaDonacion.setIdEntregaDonacion(repository.id().get(0).getIdEntregaDonacion() +1);
		}
		
		return repository.save(entregaDonacion);
	}
	
	public List<EntregaDonacion> allEntregasDonaciones(){
		return repository.findAll();
	}
	
	public List<EntregaDonacion> listaEntregasByCedula(String cedulaBeneficiario){
		return repository.findByCedulaBeneficiario(cedulaBeneficiario);
	}
	
	public Optional<EntregaDonacion> porId(Integer idEntregaDonacion){
		return repository.findById(idEntregaDonacion);
	}
	
	public EntregaDonacion actualizaEntregaDonacion(Integer idEntregaDonacion, EntregaDonacion entregaDonacion) {
		if (repository.findById(idEntregaDonacion)!=null) {
			entregaDonacion.setIdEntregaDonacion(idEntregaDonacion);
			return repository.save(entregaDonacion);
		}else {
			return null;
		}
	}
	
	public boolean eliminarEntregaDonacion(Integer idEntregaDonacion) {
		if (repository.findById(idEntregaDonacion)!=null) {
			repository.deleteById(idEntregaDonacion);
			return true;
		}else {
			return false;
		}
	}
}
