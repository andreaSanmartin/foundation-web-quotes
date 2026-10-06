package com.app.ista.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entregaDonacion")
public class EntregaDonacion {

	@Id
	private int idEntregaDonacion;
	private String cedulaBeneficiario;
	private String productoEntregado;
	private String descripcionProducto;
	private int cantidadEntregada;
	private Date fechaEntrega;
	
	public EntregaDonacion(int idEntregaDonacion, String cedulaBeneficiario, String productoEntregado,
			String descripcionProducto, int cantidadEntregada, Date fechaEntrega) {
		
		this.idEntregaDonacion = idEntregaDonacion;
		this.cedulaBeneficiario = cedulaBeneficiario;
		this.productoEntregado = productoEntregado;
		this.descripcionProducto = descripcionProducto;
		this.cantidadEntregada = cantidadEntregada;
		this.fechaEntrega = fechaEntrega;
	}

	public int getIdEntregaDonacion() {
		return idEntregaDonacion;
	}

	public void setIdEntregaDonacion(int idEntregaDonacion) {
		this.idEntregaDonacion = idEntregaDonacion;
	}

	public String getCedulaBeneficiario() {
		return cedulaBeneficiario;
	}

	public void setCedulaBeneficiario(String cedulaBeneficiario) {
		this.cedulaBeneficiario = cedulaBeneficiario;
	}

	public String getProductoEntregado() {
		return productoEntregado;
	}

	public void setProductoEntregado(String productoEntregado) {
		this.productoEntregado = productoEntregado;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public int getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
}
