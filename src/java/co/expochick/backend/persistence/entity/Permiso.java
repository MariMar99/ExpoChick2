/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.backend.persistence.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mariana
 */
@Entity
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
    , @NamedQuery(name = "Permiso.findByIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permiso.findByNombrePermiso", query = "SELECT p FROM Permiso p WHERE p.nombrePermiso = :nombrePermiso")
    , @NamedQuery(name = "Permiso.findByDescripcionPermiso", query = "SELECT p FROM Permiso p WHERE p.descripcionPermiso = :descripcionPermiso")
    , @NamedQuery(name = "Permiso.findByEstado", query = "SELECT p FROM Permiso p WHERE p.estado = :estado")
    , @NamedQuery(name = "Permiso.findByUrl", query = "SELECT p FROM Permiso p WHERE p.url = :url")})
public class Permiso implements Serializable {

    @JoinTable(name = "permisosroles", joinColumns = {
        @JoinColumn(name = "idPermiso", referencedColumnName = "idPermiso")}, inverseJoinColumns = {
        @JoinColumn(name = "idRol", referencedColumnName = "idRol")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Rol> rolCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPermiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombrePermiso")
    private String nombrePermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcionPermiso")
    private String descripcionPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @OneToMany(mappedBy = "permisoPadre")
    private Collection<Permiso> permisoCollection;
    @ManyToMany(mappedBy = "permisoList", fetch = FetchType.LAZY)
    private List<Rol> rolList;
    @JoinColumn(name = "permisoPadre", referencedColumnName = "idPermiso")
    @ManyToOne
    private Permiso permisoPadre;

    public Permiso() {
    }

    public Permiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permiso(Integer idPermiso, String nombrePermiso, String descripcionPermiso, boolean estado, String url) {
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
        this.descripcionPermiso = descripcionPermiso;
        this.estado = estado;
        this.url = url;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public Collection<Permiso> getPermisoCollection() {
        return permisoCollection;
    }

    public void setPermisoCollection(Collection<Permiso> permisoCollection) {
        this.permisoCollection = permisoCollection;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }
    

    public Permiso getPermisoPadre() {
        return permisoPadre;
    }

    public void setPermisoPadre(Permiso permisoPadre) {
        this.permisoPadre = permisoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.expochick.backend.persistence.entity.Permiso[ idPermiso=" + idPermiso + " ]";
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }
    
}
