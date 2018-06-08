/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Solicitud;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "fourcapital1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }
    public Solicitud SolicitudForDNI(String dni) {

        String jpa = "select s from Solicitud s "
                + "where s.estado = 'procesando' and s.clienteIdcliente.idcliente = '" + dni + "'";
        List<Solicitud> ls = em.createQuery(jpa, Solicitud.class).getResultList();
        if (0 < ls.size()) {
            return ls.get(0);
        }
        return null;
    }

    public void editarEstado(int id, String estado) {
        //este metodo no esta provado ni usado se queria reeemplazar en el BO en vez del edit
        String sql = "UPDATE Solicitud "
                + "set estado = 'finalizado' where idsolicitud='" + id + "'";
        em.createNativeQuery(sql).executeUpdate();
        //   UPDATE `bdfourcapital`.`solicitud` SET `telefono`='977489742' WHERE `idsolicitud`='2';
    }

    public Solicitud guardarSolicitud(Solicitud s) {
        em.persist(s);
        em.flush();
        return s;
    }
}
