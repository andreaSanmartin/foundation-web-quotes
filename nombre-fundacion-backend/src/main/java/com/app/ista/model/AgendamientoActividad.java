package com.app.ista.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "agendamientoActividad")
public class AgendamientoActividad {

	@Id
	private int agendamientoActividad;
	private Date fechaRegistro;
	private Date fechaActividad;
	private String descripcionActividad;
	private Persona trabajadorEncargado;
	private Persona trabajadorAgendador;
	private String notas;
	
	public AgendamientoActividad(int agendamientoActividad, Date fechaRegistro, Date fechaActividad,
			String descripcionActividad, Persona trabajadorEncargado, Persona trabajadorAgendador, String notas) {
		this.agendamientoActividad = agendamientoActividad;
		this.fechaRegistro = fechaRegistro;
		this.fechaActividad = fechaActividad;
		this.descripcionActividad = descripcionActividad;
		this.trabajadorEncargado = trabajadorEncargado;
		this.trabajadorAgendador = trabajadorAgendador;
		this.notas = notas;
	}

	public int getAgendamientoActividad() {
		return agendamientoActividad;
	}

	public void setAgendamientoActividad(int agendamientoActividad) {
		this.agendamientoActividad = agendamientoActividad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public Persona getTrabajadorEncargado() {
		return trabajadorEncargado;
	}

	public void setTrabajadorEncargado(Persona trabajadorEncargado) {
		this.trabajadorEncargado = trabajadorEncargado;
	}

	public Persona getTrabajadorAgendador() {
		return trabajadorAgendador;
	}

	public void setTrabajadorAgendador(Persona trabajadorAgendador) {
		this.trabajadorAgendador = trabajadorAgendador;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	
	
	
}
