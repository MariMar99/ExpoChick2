/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.expochick.backend.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mariana
 */
@Entity
@Table(name = "estadosantinarcoticos")
@NamedQueries({
    @NamedQuery(name = "EstadoAntinarcoticos.findAll", query = "SELECT e FROM EstadoAntinarcoticos e")
    , @NamedQuery(name = "EstadoAntinarcoticos.findByIdEstadoAntinarcoticos", query = "SELECT e FROM EstadoAntinarcoticos e WHERE e.idEstadoAntinarcoticos = :idEstadoAntinarcoticos")
    , @NamedQuery(name = "EstadoAntinarcoticos.findByNombreEstadoAntinarcoticos", query = "SELECT e FROM EstadoAntinarcoticos e WHERE e.nombreEstadoAntinarcoticos = :nombreEstadoAntinarcoticos")})
public class EstadoAntinarcoticos implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoAntinarcoticos")
    private Integer idEstadoAntinarcoticos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreEstadoAntinarcoticos")
    private String nombreEstadoAntinarcoticos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoAnti", fetch = FetchType.LAZY)
    private List<Antinarcoticos> antinarcoticosList;

    public EstadoAntinarcoticos() {
    }

    public EstadoAntinarcoticos(Integer idEstadoAntinarcoticos) {
        this.idEstadoAntinarcoticos = idEstadoAntinarcoticos;
    }

    public EstadoAntinarcoticos(Integer idEstadoAntinarcoticos, String nombreEstadoAntinarcoticos) {
        this.idEstadoAntinarcoticos = idEstadoAntinarcoticos;
        this.nombreEstadoAntinarcoticos = nombreEstadoAntinarcoticos;
    }

    public Integer getIdEstadoAntinarcoticos() {
        return idEstadoAntinarcoticos;
    }

    public void setIdEstadoAntinarcoticos(Integer idEstadoAntinarcoticos) {
        this.idEstadoAntinarcoticos = idEstadoAntinarcoticos;
    }

    public String getNombreEstadoAntinarcoticos() {
        return nombreEstadoAntinarcoticos;
    }

    public void setNombreEstadoAntinarcoticos(String nombreEstadoAntinarcoticos) {
        this.nombreEstadoAntinarcoticos = nombreEstadoAntinarcoticos;
    }

    public List<Antinarcoticos> getAntinarcoticosList() {
        return antinarcoticosList;
    }

    public void setAntinarcoticosList(List<Antinarcoticos> antinarcoticosList) {
        this.antinarcoticosList = antinarcoticosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoAntinarcoticos != null ? idEstadoAntinarcoticos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoAntinarcoticos)) {
            return false;
        }
        EstadoAntinarcoticos other = (EstadoAntinarcoticos) object;
        if ((this.idEstadoAntinarcoticos == null && other.idEstadoAntinarcoticos != null) || (this.idEstadoAntinarcoticos != null && !this.idEstadoAntinarcoticos.equals(other.idEstadoAntinarcoticos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.expochick.backend.persistence.entity.EstadoAntinarcoticos[ idEstadoAntinarcoticos=" + idEstadoAntinarcoticos + " ]";
    }

    @Override
    public String getId() {
        return idEstadoAntinarcoticos.toString();
    }
    
}
