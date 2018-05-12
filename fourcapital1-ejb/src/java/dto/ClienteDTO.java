/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class ClienteDTO {

   
 
    private Integer idcliente;
   
    private String nombre;
   
    private String direccion;
   
    private String correo;
    
    private String telefono;
   
    private String tipo;
   
    private List<DeudaDTO> deudaList;
   
    private List<SolicitudDTO> solicitudList;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<DeudaDTO> getDeudaList() {
        return deudaList;
    }

    public void setDeudaList(List<DeudaDTO> deudaList) {
        this.deudaList = deudaList;
    }

    public List<SolicitudDTO> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<SolicitudDTO> solicitudList) {
        this.solicitudList = solicitudList;
    }

    
    
}
