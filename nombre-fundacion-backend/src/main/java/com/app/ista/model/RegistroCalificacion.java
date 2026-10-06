package com.app.ista.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "registroCalificacion")
public class RegistroCalificacion {

	@Id
	private int idRegistroCalificacion;
	private String calificacionEntrevistado;
	private String recomiendaAyuda;
	private String notas;
	private Date fechaEntrega;
	private FichaSocioeconomica fichaSocioEconomica;
	
	public RegistroCalificacion(int idRegistroCalificacion, String calificacionEntrevistado, String recomiendaAyuda,
			String notas, Date fechaEntrega, FichaSocioeconomica fichaSocioEconomica) {
		this.idRegistroCalificacion = idRegistroCalificacion;
		this.calificacionEntrevistado = calificacionEntrevistado;
		this.recomiendaAyuda = recomiendaAyuda;
		this.notas = notas;
		this.fechaEntrega = fechaEntrega;
		this.fichaSocioEconomica = fichaSocioEconomica;
	}

	public int getIdRegistroCalificacion() {
		return idRegistroCalificacion;
	}

	public void setIdRegistroCalificacion(int idRegistroCalificacion) {
		this.idRegistroCalificacion = idRegistroCalificacion;
	}

	public String getCalificacionEntrevistado() {
		return calificacionEntrevistado;
	}

	public void setCalificacionEntrevistado(String calificacionEntrevistado) {
		this.calificacionEntrevistado = calificacionEntrevistado;
	}

	public String getRecomiendaAyuda() {
		return recomiendaAyuda;
	}

	public void setRecomiendaAyuda(String recomiendaAyuda) {
		this.recomiendaAyuda = recomiendaAyuda;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public FichaSocioeconomica getFichaSocioEconomica() {
		return fichaSocioEconomica;
	}

	public void setFichaSocioEconomica(FichaSocioeconomica fichaSocioEconomica) {
		this.fichaSocioEconomica = fichaSocioEconomica;
	}

	
}
