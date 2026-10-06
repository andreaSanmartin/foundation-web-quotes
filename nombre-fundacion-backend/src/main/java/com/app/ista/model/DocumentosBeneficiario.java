package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documentosFamiliares")
public class DocumentosBeneficiario {
	
	@Id
	private int idDocumentos;
	private String cedulaPersona;
	private String tipoDocumento;
	private String nombreArchivo;

	public DocumentosBeneficiario() {
	}

	public int getIdDocumentos() {
		return idDocumentos;
	}

	public void setIdDocumentos(int idDocumentos) {
		this.idDocumentos = idDocumentos;
	}

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	
}
