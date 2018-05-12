/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.SolicitudFacade;
import dto.ClienteDTO;
import dto.SolicitudDTO;
import entidades.Cliente;
import entidades.Solicitud;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class SolicitudBO {

    @EJB
    private SolicitudFacade solicitudFacade;

    public SolicitudDTO solicitudForDNI(String dni) {
        SolicitudDTO s = solicitudForDTO(solicitudFacade.SolicitudForDNI(dni));
        return s;
    }

    private SolicitudDTO solicitudForDTO(Solicitud e) {

        SolicitudDTO dto = new SolicitudDTO();
        if (e != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdcliente(e.getClienteIdcliente().getIdcliente());
            dto.setClienteIdcliente(clienteDTO);
            dto.setCorreo(e.getCorreo());
            dto.setDireccion(e.getDireccion());
            dto.setEstado(e.getEstado());
            dto.setIdsolicitud(e.getIdsolicitud());
            dto.setNombre(e.getNombre());
            dto.setTelefono(e.getTelefono());
        } else {
            return null;
        }
        return dto;
    }

    private List<SolicitudDTO> listSolicitudForDTO(List<Solicitud> solicitudList) {
        List<SolicitudDTO> ldto = new ArrayList<>();
        for (Solicitud e : solicitudList) {
            ldto.add(solicitudForDTO(e));
        }
        return ldto;
    }

    public void editarSolicitud(SolicitudDTO Solicitud) {
        Solicitud entity = DTOforSolicitud(Solicitud);
        solicitudFacade.edit(entity);
    }

    private Solicitud DTOforSolicitud(SolicitudDTO dto) {
        Solicitud s = new Solicitud();
        Cliente c = new Cliente();
        c.setIdcliente(dto.getClienteIdcliente().getIdcliente());
        c.setNombre(dto.getClienteIdcliente().getNombre());
        c.setDireccion(dto.getClienteIdcliente().getDireccion());
        c.setCorreo(dto.getClienteIdcliente().getCorreo());
        c.setTelefono(dto.getClienteIdcliente().getTelefono());
        c.setTipo(dto.getClienteIdcliente().getTipo());
        s.setClienteIdcliente(c);
        s.setCorreo(dto.getCorreo());
        s.setDireccion(dto.getDireccion());
        s.setEstado(dto.getEstado());
        s.setNombre(dto.getNombre());
        s.setTelefono(dto.getTelefono());
        if (dto.getIdsolicitud() != null) {
            s.setIdsolicitud(dto.getIdsolicitud());
        }
        return s;
    }
//no se usa

    private Cliente DTOforCliente(ClienteDTO dto) {
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

    public void nuevaSolicitud(SolicitudDTO dto) {
        Solicitud s = solicitudFacade.guardarSolicitud(DTOforSolicitud(dto));
        System.out.println(s.getCorreo());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
