/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import static tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.boundary.Constantes.NOMBRE_PU;
import tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity.ComercioTipoComercio;
import java.io.Serializable;

/**
 *
 * @author moimo98
 */
@Stateless
@LocalBean
public class ComercioTipoComercioBean extends AbstractDataAccess<ComercioTipoComercio> implements Serializable {
    
    
    @PersistenceContext(unitName = NOMBRE_PU)
    EntityManager em;
    
    @Override
    EntityManager getEntityManager() {
        return em;
    }

    public ComercioTipoComercioBean() {
        super(ComercioTipoComercio.class);
    }
}