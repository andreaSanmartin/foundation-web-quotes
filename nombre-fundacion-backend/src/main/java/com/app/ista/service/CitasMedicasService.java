package com.app.ista.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.CitasMedicas;
import com.app.ista.repository.CitasMedicasRepository;

@Service
public class CitasMedicasService {
	
	@Autowired
	private CitasMedicasRepository citasMedicasRepository;
	
	public CitasMedicas guardarCita(CitasMedicas cita) {
		if (citasMedicasRepository.findAll().isEmpty()) {
			cita.setIdCitasMedicas(1);
		} else {
			cita.setIdCitasMedicas(citasMedicasRepository.id().get(0).getIdCitasMedicas()+ 1);
		}
		return citasMedicasRepository.save(cita);
	}
	
	public List<CitasMedicas>listarCita(){
		return citasMedicasRepository.findAll();
	}

	public Optional<CitasMedicas> listarCitasId( Long id){
		return citasMedicasRepository.findById(id);
	}

	public void eliminarCita(Long idcita) {
		
		citasMedicasRepository.deleteById(idcita);
        }

   	public CitasMedicas editarCitas(Long idCita, CitasMedicas citas) {
	   return citasMedicasRepository.save(citas);
   }

   	public List<CitasMedicas>listarFecha(Date fecha){
		return citasMedicasRepository.findByFechaCitaMedica(fecha);
	}
        
        public CitasMedicas listarCentroMedicoPorNombre(String nombreCentroMedico) {
		
        return citasMedicasRepository.findByCentroMedico(nombreCentroMedico);
	}
        
        public CitasMedicas listarPorPaciente(String paciente){
		return citasMedicasRepository.findByPaciente(paciente);
	}
        
        public CitasMedicas listarPorAcompa(String cedulaacom){
		return citasMedicasRepository.findByAcompaniante(cedulaacom);
	}
        
        public CitasMedicas listarPorTrabaja(String cedulatra){
		return citasMedicasRepository.findByTrabajadorFundacion(cedulatra);
	}

}
