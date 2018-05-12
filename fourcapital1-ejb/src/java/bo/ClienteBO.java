/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ClienteFacade;
import dto.ClienteDTO;
import entidades.Cliente;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class ClienteBO {

    @EJB
    private ClienteFacade clienteFacade;
    
    
public void editar(ClienteDTO cliente) {

        clienteFacade.edit(dtoForCliente(cliente));

    }

    public Cliente dtoForCliente(ClienteDTO dto){
         Cliente c = new Cliente();
        c.setCorreo(dto.getCorreo());
        //c.setDeudaList(listDeudaForDTO(entidad.getDeudaList()));
        c.setDireccion(dto.getDireccion());
        c.setIdcliente(dto.getIdcliente());
        c.setNombre(dto.getNombre());
        c.setTelefono(dto.getTelefono());
        c.setTipo(dto.getTipo());
        return c;
         
     }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
