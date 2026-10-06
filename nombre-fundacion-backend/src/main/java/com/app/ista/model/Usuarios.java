package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "usuarios")
public class Usuarios {

	@Id
	private int idUsuario;

	private String usuarioCedula;
	private String usuarioNombre;
	private String usuarioContrasenia;
	private int usuarioTipo;
	private boolean usuarioEstado;
	private String usuarioFechaCreacion;

	public Usuarios() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuarioCedula() {
		return usuarioCedula;
	}

	public void setUsuarioCedula(String usuarioCedula) {
		this.usuarioCedula = usuarioCedula;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public String getUsuarioContrasenia() {
		return usuarioContrasenia;
	}

	public void setUsuarioContrasenia(String usuarioContrasenia) {
		this.usuarioContrasenia = usuarioContrasenia;
	}

	public int getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(int usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public boolean isUsuarioEstado() {
		return usuarioEstado;
	}

	public void setUsuarioEstado(boolean usuarioEstado) {
		this.usuarioEstado = usuarioEstado;
	}

	public String getUsuarioFechaCreacion() {
		return usuarioFechaCreacion;
	}

	public void setUsuarioFechaCreacion(String usuarioFechaCreacion) {
		this.usuarioFechaCreacion = usuarioFechaCreacion;
	}
	
}