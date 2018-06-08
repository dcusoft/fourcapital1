/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class DeudaDTO implements Serializable {

    protected DeudaPKDTO deudaPK;
    private String agencia;
    private String monto;
    private String producto;
    private String estadoCarta;
    private ClienteDTO cliente;
    private String nuemroCarta;
    private String origen;
    private Date fecha;
    //variables extra
    private boolean estadoBoton;
    private String estado;
    
    
    public DeudaDTO() {
    }

    public DeudaDTO(DeudaPKDTO deudaPK) {
        this.deudaPK = deudaPK;
    }

    public DeudaDTO(int iddeuda, int clienteIdcliente) {
        this.deudaPK = new DeudaPKDTO(iddeuda, clienteIdcliente);
    }

    public DeudaPKDTO getDeudaPK() {
        return deudaPK;
    }

    public void setDeudaPK(DeudaPKDTO deudaPK) {
        this.deudaPK = deudaPK;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deudaPK != null ? deudaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeudaDTO)) {
            return false;
        }
        DeudaDTO other = (DeudaDTO) object;
        if ((this.deudaPK == null && other.deudaPK != null) || (this.deudaPK != null && !this.deudaPK.equals(other.deudaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Deuda[ deudaPK=" + deudaPK + " ]";
    }

    public boolean isEstadoBoton() {
        if(monto.equals("0")||monto.equals("")||monto.isEmpty()||monto==null){
        estadoBoton=false;
        }else{
        estadoBoton=true;
        }
        return estadoBoton;
    }

    public void setEstadoBoton(boolean estadoBoton) {
        this.estadoBoton = estadoBoton;
    }

    public String getEstadoCarta() {
        return estadoCarta;
    }

    public void setEstadoCarta(String estadoCarta) {
        this.estadoCarta = estadoCarta;
    }

    public String getEstado() {
        if(isEstadoBoton()){
            estado="pendiente";
        }else{
            estado="cancelado";
        }
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNuemroCarta() {
        return nuemroCarta;
    }

    public void setNuemroCarta(String nuemroCarta) {
        this.nuemroCarta = nuemroCarta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
