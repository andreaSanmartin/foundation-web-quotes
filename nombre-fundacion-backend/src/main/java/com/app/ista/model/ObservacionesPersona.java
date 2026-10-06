package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "observacionespersona")
public class ObservacionesPersona {
    @Id
    private int idObservacionesPersona;
    private String cedulaPersona;
    private String fechaRegistro;
    private String descripcionobservacion;
    
    public ObservacionesPersona() {
    }
    
    public int getIdObservacionesPersona() {
        return idObservacionesPersona;
    }
    public void setIdObservacionesPersona(int idObservacionesPersona) {
        this.idObservacionesPersona = idObservacionesPersona;
    }
    public String getCedulaPersona() {
        return cedulaPersona;
    }
    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }
    public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getDescripcionobservacion() {
        return descripcionobservacion;
    }
    public void setDescripcionobservacion(String descripcionobservacion) {
        this.descripcionobservacion = descripcionobservacion;
    }    
    
}
