package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "actividadPersona")
public class ActividadPersona {

	@Id
	private int idActividadPersona;
	private Persona cedulaPersona;
	private String fechaActividad;
	private String horaInicio;
	private String horaFin;
	private String descripcionActividad;
	private TipoServicio tipoActividad;
	private boolean asistencia;

	public ActividadPersona() {
	}


	public ActividadPersona(int idActividadPersona, Persona cedulaPersona, String fechaActividad, String horaInicio, String horaFin, String descripcionActividad, TipoServicio tipoActividad, boolean asistencia) {
		this.idActividadPersona = idActividadPersona;
		this.cedulaPersona = cedulaPersona;
		this.fechaActividad = fechaActividad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcionActividad = descripcionActividad;
		this.tipoActividad = tipoActividad;
		this.asistencia = asistencia;
	}

	public String getFechaActividad() {
		return this.fechaActividad;
	}

	public void setFechaActividad(String fechaActividad) {
		this.fechaActividad = fechaActividad;
	}


	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Persona getCedulaPersona() {
		return this.cedulaPersona;
	}

	public void setCedulaPersona(Persona cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}


	public int getIdActividadPersona() {
		return idActividadPersona;
	}

	public void setIdActividadPersona(int idActividadPersona) {
		this.idActividadPersona = idActividadPersona;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}


	public TipoServicio getTipoActividad() {
		return this.tipoActividad;
	}

	public void setTipoActividad(TipoServicio tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public boolean isAsistencia() {
		return asistencia;
	}

	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}
	
}
