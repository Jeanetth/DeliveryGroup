/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moimo98
 */
@Entity
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByIdOrden", query = "SELECT o FROM Orden o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Orden.findByFechaOrden", query = "SELECT o FROM Orden o WHERE o.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "Orden.findByObservaciones", query = "SELECT o FROM Orden o WHERE o.observaciones = :observaciones"),
    @NamedQuery(name = "Orden.findByIdDireccion", query = "SELECT o FROM Orden o WHERE o.idDireccion = :idDireccion")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden")
    private Long idOrden;
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrden;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "id_direccion")
    private BigInteger idDireccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private Collection<OrdenDetalle> ordenDetalleCollection;
    @OneToMany(mappedBy = "idOrden")
    private Collection<Factura> facturaCollection;
    @OneToMany(mappedBy = "idOrden")
    private Collection<OrdenEstado> ordenEstadoCollection;
    @OneToMany(mappedBy = "idOrden")
    private Collection<Entrega> entregaCollection;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne
    private Sucursal idSucursal;

    public Orden() {
    }

    public Orden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigInteger getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(BigInteger idDireccion) {
        this.idDireccion = idDireccion;
    }

    @XmlTransient
    public Collection<OrdenDetalle> getOrdenDetalleCollection() {
        return ordenDetalleCollection;
    }

    public void setOrdenDetalleCollection(Collection<OrdenDetalle> ordenDetalleCollection) {
        this.ordenDetalleCollection = ordenDetalleCollection;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @XmlTransient
    public Collection<OrdenEstado> getOrdenEstadoCollection() {
        return ordenEstadoCollection;
    }

    public void setOrdenEstadoCollection(Collection<OrdenEstado> ordenEstadoCollection) {
        this.ordenEstadoCollection = ordenEstadoCollection;
    }

    @XmlTransient
    public Collection<Entrega> getEntregaCollection() {
        return entregaCollection;
    }

    public void setEntregaCollection(Collection<Entrega> entregaCollection) {
        this.entregaCollection = entregaCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.entity.Orden[ idOrden=" + idOrden + " ]";
    }
    
}
