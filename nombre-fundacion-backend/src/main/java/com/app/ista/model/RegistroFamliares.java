package com.app.ista.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registroFamiliares")
public class RegistroFamliares {
	/*@Id
	private int idRegistroFamiliares;*/
	
	private String cedulaFamiliar;
	private String cedulaPersona;
	private String parentesco;
	private String nombreF;
	private String apellidoF;
	private String celularF;
	private String correo;
	private int numHijos;
	
	public RegistroFamliares() {
	
	}

/*	public int getIdRegistroFamiliares() {
		return idRegistroFamiliares;
	}

	public void setIdRegistroFamiliares(int idRegistroFamiliares) {
		this.idRegistroFamiliares = idRegistroFamiliares;
	} */

	public String getCedulaFamiliar() {
		return cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getNombreF() {
		return nombreF;
	}

	public void setNombreF(String nombreF) {
		this.nombreF = nombreF;
	}

	public String getApellidoF() {
		return apellidoF;
	}

	public void setApellidoF(String apellidoF) {
		this.apellidoF = apellidoF;
	}

	public String getCelularF() {
		return celularF;
	}

	public void setCelularF(String celularF) {
		this.celularF = celularF;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getNumHijos() {
		return numHijos;
	}

	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}
			
}
