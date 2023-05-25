/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moimo98
 */
@Entity
@Table(name = "comercio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comercio.findAll", query = "SELECT c FROM Comercio c"),
    @NamedQuery(name = "Comercio.findByIdComercio", query = "SELECT c FROM Comercio c WHERE c.idComercio = :idComercio"),
    @NamedQuery(name = "Comercio.findByNombre", query = "SELECT c FROM Comercio c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comercio.findByActivo", query = "SELECT c FROM Comercio c WHERE c.activo = :activo"),
    @NamedQuery(name = "Comercio.findByDescripcion", query = "SELECT c FROM Comercio c WHERE c.descripcion = :descripcion")})
public class Comercio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comercio")
    private Long idComercio;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comercio")
    
    @JsonbTransient
    private Collection<ComercioTipoComercio> comercioTipoComercioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comercio")
     
    @JsonbTransient
    private Collection<ProductoComercio> productoComercioCollection;
    @OneToMany(mappedBy = "idComercio")
    
    @JsonbTransient
    private Collection<Sucursal> sucursalCollection;

    public Comercio() {
    }

    public Comercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public Long getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  @XmlTransient
    public Collection<ComercioTipoComercio> getComercioTipoComercioCollection() {
        return comercioTipoComercioCollection;
    }
    

    public void setComercioTipoComercioCollection(Collection<ComercioTipoComercio> comercioTipoComercioCollection) {
        this.comercioTipoComercioCollection = comercioTipoComercioCollection;
    }

    @XmlTransient
    public Collection<ProductoComercio> getProductoComercioCollection() {
        return productoComercioCollection;
    }
    

    public void setProductoComercioCollection(Collection<ProductoComercio> productoComercioCollection) {
        this.productoComercioCollection = productoComercioCollection;
    }

   @XmlTransient
    public Collection<Sucursal> getSucursalCollection() {
        return sucursalCollection;
    }

    public void setSucursalCollection(Collection<Sucursal> sucursalCollection) {
        this.sucursalCollection = sucursalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComercio != null ? idComercio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comercio)) {
            return false;
        }
        Comercio other = (Comercio) object;
        if ((this.idComercio == null && other.idComercio != null) || (this.idComercio != null && !this.idComercio.equals(other.idComercio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.control.entity.Comercio[ idComercio=" + idComercio + " ]";
    }
    
}
