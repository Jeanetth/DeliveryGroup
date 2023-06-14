/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import jakarta.persistence.PersistenceContext;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.Constantes.NOMBRE_PU;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.Comercio;

/**
 *
 * @author Angel
 */
@Stateless
@LocalBean
public class ComercioBean extends AbstractDataAccess<Comercio> implements Serializable {
    
    
    @PersistenceContext(unitName = NOMBRE_PU)
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }

    public ComercioBean() {
        super(Comercio.class);
    }
    /*
    public List<Comercio> findByIdComercio(Long idPersona, int first, int pageSize) {
        if (idPersona != null && em != null) {
            Query q = em.createNamedQuery("Comercio.findByIdComercio");
            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            q.setParameter("id_comercio", idPersona);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }
    
   /* public List<Comercio> findAllByIdPersona(Long idPersona) {
        if (idPersona != null && em != null) {
            Query q = em.createNamedQuery("Comercio.findByIdComercio");
            q.setParameter("id_comercio", idPersona);
            return q.getResultList();
        }
        return Collections.EMPTY_LIST;
    }*/

}
