package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "centroMedico")
public class CentroMedico {

	@Id
	private int idCentroMedico;
	private String nombreCentroMedico;
	private String direccionCentroMedico;
	private String telefonoCentroMedico;

	public CentroMedico(int idCentroMedico, String nombreCentroMedico, String direccionCentroMedico,
			String telefonoCentroMedico) {
		this.idCentroMedico = idCentroMedico;
		this.nombreCentroMedico = nombreCentroMedico;
		this.direccionCentroMedico = direccionCentroMedico;
		this.telefonoCentroMedico = telefonoCentroMedico;
	}

	public int getIdCentroMedico() {
		return idCentroMedico;
	}

	public void setIdCentroMedico(int idCentroMedico) {
		this.idCentroMedico = idCentroMedico;
	}

	public String getNombreCentroMedico() {
		return nombreCentroMedico;
	}

	public void setNombreCentroMedico(String nombreCentroMedico) {
		this.nombreCentroMedico = nombreCentroMedico;
	}

	public String getDireccionCentroMedico() {
		return direccionCentroMedico;
	}

	public void setDireccionCentroMedico(String direccionCentroMedico) {
		this.direccionCentroMedico = direccionCentroMedico;
	}

	public String getTelefonoCentroMedico() {
		return telefonoCentroMedico;
	}

	public void setTelefonoCentroMedico(String telefonoCentroMedico) {
		this.telefonoCentroMedico = telefonoCentroMedico;
	}

}
