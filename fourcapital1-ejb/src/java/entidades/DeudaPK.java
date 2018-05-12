/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class DeudaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "iddeuda")
    private int iddeuda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente_idcliente")
    private int clienteIdcliente;

    public DeudaPK() {
    }

    public DeudaPK(int iddeuda, int clienteIdcliente) {
        this.iddeuda = iddeuda;
        this.clienteIdcliente = clienteIdcliente;
    }

    public int getIddeuda() {
        return iddeuda;
    }

    public void setIddeuda(int iddeuda) {
        this.iddeuda = iddeuda;
    }

    public int getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(int clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddeuda;
        hash += (int) clienteIdcliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeudaPK)) {
            return false;
        }
        DeudaPK other = (DeudaPK) object;
        if (this.iddeuda != other.iddeuda) {
            return false;
        }
        if (this.clienteIdcliente != other.clienteIdcliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DeudaPK[ iddeuda=" + iddeuda + ", clienteIdcliente=" + clienteIdcliente + " ]";
    }
    
}
