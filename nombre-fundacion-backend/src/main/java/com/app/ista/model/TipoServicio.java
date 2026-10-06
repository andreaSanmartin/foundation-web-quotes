package com.app.ista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TipoActividad")
public class TipoServicio {
    //Esta colleccion es para el tipo de actividad en las actividades 
    @Id
    private int idTipoActividad;
    private String nombreActividad;
    private String descripcionActividad;

    public TipoServicio(int idTipoActividad, String nombreActividad, String descripcionActividad) {
        this.idTipoActividad = idTipoActividad;
        this.nombreActividad = nombreActividad;
        this.descripcionActividad = descripcionActividad;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }
    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }
    public String getNombreActividad() {
        return nombreActividad;
    }
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    public String getDescripcionActividad() {
        return descripcionActividad;
    }
    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }    
}
