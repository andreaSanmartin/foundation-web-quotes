package com.app.ista.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "perregficdto")
public class PerRegFicDTO{
	
	@Id
	private int idPerRegFicDTO;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String celular;
	private String correo;
	private String genero;
	private String fechaNacimiento;
	private int edad;
	private String nacionalidad;
	private String estado_civil;  
	private int faltas;
	//registro familiares
	private List<RegistroFamliares> familiares;
	//ficha
	private String situacionEconomica;
	private String tipoVivienda;
	private String descripcionVivienda;
	private boolean seguro;
	private double salario;
	private String fechaRegistro;
	private boolean adultoMayor;
	private boolean viveConOtros;
	private boolean recibebono;
	private int cantidadbono;
	private boolean discapacidad;
	private String tipo_discapacidad;
	private int porc_disc_mental;
	private String descrip_disc_mental;
	private int porc_disc_fisica;
	private String descrip_disc_fisica;
	private boolean pareja;
	private boolean madreSoltera;
	private String[] enfermedades;
	//Asistencias
	private List<AsistenciaPersona> asistencias;
	
	public PerRegFicDTO() {}

	public int getIdPerRegFicDTO() {
		return idPerRegFicDTO;
	}

	public void setIdPerRegFicDTO(int idPerRegFicDTO) {
		this.idPerRegFicDTO = idPerRegFicDTO;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}

	public boolean isPareja() {
		return pareja;
	}

	public void setPareja(boolean pareja) {
		this.pareja = pareja;
	}

	public List<RegistroFamliares> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<RegistroFamliares> familiares) {
		this.familiares = familiares;
	}

	public String getSituacionEconomica() {
		return situacionEconomica;
	}

	public void setSituacionEconomica(String situacionEconomica) {
		this.situacionEconomica = situacionEconomica;
	}

	public String getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getDescripcionVivienda() {
		return descripcionVivienda;
	}

	public void setDescripcionVivienda(String descripcionVivienda) {
		this.descripcionVivienda = descripcionVivienda;
	}

	public boolean isSeguro() {
		return seguro;
	}

	public void setSeguro(boolean seguro) {
		this.seguro = seguro;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isAdultoMayor() {
		return adultoMayor;
	}

	public void setAdultoMayor(boolean adultoMayor) {
		this.adultoMayor = adultoMayor;
	}

	public boolean isViveConOtros() {
		return viveConOtros;
	}

	public void setViveConOtros(boolean viveConOtros) {
		this.viveConOtros = viveConOtros;
	}

	public boolean isRecibebono() {
		return recibebono;
	}

	public void setRecibebono(boolean recibebono) {
		this.recibebono = recibebono;
	}

	public int getCantidadbono() {
		return cantidadbono;
	}

	public void setCantidadbono(int cantidadbono) {
		this.cantidadbono = cantidadbono;
	}

	public boolean isDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getTipo_discapacidad() {
		return tipo_discapacidad;
	}

	public void setTipo_discapacidad(String tipo_discapacidad) {
		this.tipo_discapacidad = tipo_discapacidad;
	}

	public int getPorc_disc_mental() {
		return porc_disc_mental;
	}

	public void setPorc_disc_mental(int porc_disc_mental) {
		this.porc_disc_mental = porc_disc_mental;
	}
	
	public String getDescrip_disc_mental() {
		return descrip_disc_mental;
	}

	public void setDescrip_disc_mental(String descrip_disc_mental) {
		this.descrip_disc_mental = descrip_disc_mental;
	}

	public int getPorc_disc_fisica() {
		return porc_disc_fisica;
	}

	public void setPorc_disc_fisica(int porc_disc_fisica) {
		this.porc_disc_fisica = porc_disc_fisica;
	}

	public String getDescrip_disc_fisica() {
		return descrip_disc_fisica;
	}

	public void setDescrip_disc_fisica(String descrip_disc_fisica) {
		this.descrip_disc_fisica = descrip_disc_fisica;
	}

	public boolean isMadreSoltera() {
		return madreSoltera;
	}

	public void setMadreSoltera(boolean madreSoltera) {
		this.madreSoltera = madreSoltera;
	}

	public String[] getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(String[] enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<AsistenciaPersona> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<AsistenciaPersona> asistencias) {
		this.asistencias = asistencias;
	}
	
}
