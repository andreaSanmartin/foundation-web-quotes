package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Spring create a collection in MongoDB 
@Document(collection = "citasMedicas")
public class CitasMedicas {
	@Id
	private int idCitasMedicas;
	private String descripcionCitaMedica;
	private String fechaRegistro;
	private String fechaCitaMedica;
	private String paciente;
	private String acompaniante;
	private String mensaje;
	private String trabajadorFundacion;
    private String centroMedico;
	private String especialidad;
	private String nota;
	private boolean asistencia;

	public CitasMedicas() {
		// TODO Auto-generated constructor stub
	}
	
    public int getIdCitasMedicas() {
        return idCitasMedicas;
    }

    public void setIdCitasMedicas(int idCitasMedicas) {
        this.idCitasMedicas = idCitasMedicas;
    }

    public String getDescripcionCitaMedica() {
        return descripcionCitaMedica;
    }

    public void setDescripcionCitaMedica(String descripcionCitaMedica) {
        this.descripcionCitaMedica = descripcionCitaMedica;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaCitaMedica() {
        return fechaCitaMedica;
    }

    public void setFechaCitaMedica(String fechaCitaMedica) {
        this.fechaCitaMedica = fechaCitaMedica;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getAcompaniante() {
        return acompaniante;
    }

    public void setAcompaniante(String acompaniante) {
        this.acompaniante = acompaniante;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTrabajadorFundacion() {
        return trabajadorFundacion;
    }

    public void setTrabajadorFundacion(String trabajadorFundacion) {
        this.trabajadorFundacion = trabajadorFundacion;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(String centroMedico) {
        this.centroMedico = centroMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

	public boolean isAsistencia() {
		return asistencia;
	}

	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}
	
}