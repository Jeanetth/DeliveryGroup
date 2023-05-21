/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;


/**
 * ANGEL ARISTIDES ALVARADO LANDAVERDE
 * AL20034
 *
 * @author AngelAlvarado
 */
public abstract class AbstractDataAccess<T> implements Serializable {
    
    protected final Class clase;

    public AbstractDataAccess (Class clase) {
        this.clase = clase;
    }

    abstract EntityManager getEntityManager ();
    
    public void crear (T entity) throws IllegalArgumentException, IllegalStateException {
        if (entity != null) {
            EntityManager em = this.getEntityManager();
            if (em != null) {
                em.persist(entity);
            } else {
                throw new IllegalStateException();
            } 
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void modificar (T entity) throws IllegalArgumentException, IllegalStateException {
        if(entity != null){
            EntityManager em = this.getEntityManager();
            if (em != null) {
                em.merge(entity);
            } else {
                throw new IllegalStateException();
            } 
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void eliminar(T entity) throws IllegalArgumentException, IllegalStateException {
       if(entity != null){
            EntityManager em = this.getEntityManager();
            if (em != null) {
                em.remove(em.merge(entity));
            } else {
                throw new IllegalStateException();
            }
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public Query generarConsultaBase (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(clase));
        Query q = em.createQuery(cq);
        return q;
     }

    public List<T> findAll () throws IllegalStateException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        if (em != null) {
            Query q = generarConsultaBase(em);
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }
    }
    
    
   public T findById( Object id) throws IllegalArgumentException, IllegalStateException {
        if (id != null) {
            EntityManager em = null;
            try {
                em = this.getEntityManager();
            } catch (Exception ex) {}
            if (em != null) {

                try {
                    return (T) em.find(clase, id);
                } catch (Exception ex) {
                }
            }
           // throw new IllegalStateException("Nose puede obtener un ambito de persistencia");
        }
        throw new IllegalArgumentException();
    }
    
    public List<T> findRange (int first, int pageSize) throws IllegalStateException {
        EntityManager em = null;
         try {
            em = this.getEntityManager();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        if (em != null) {
        	Query q = generarConsultaBase(em);
            q.setMaxResults(pageSize);
            q.setFirstResult(first);
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }
    }
    
    public long contar() throws IllegalStateException{
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        if (em != null) {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<T> rt = cq.from(clase);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return (Long)q.getSingleResult();
       } else {
            throw new IllegalStateException();
        }
    }
    
}