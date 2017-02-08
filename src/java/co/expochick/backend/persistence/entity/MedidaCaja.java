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
@Table(name = "medidascajas")
@NamedQueries({
    @NamedQuery(name = "MedidaCaja.findAll", query = "SELECT m FROM MedidaCaja m")
    , @NamedQuery(name = "MedidaCaja.findByIdMedidaCaja", query = "SELECT m FROM MedidaCaja m WHERE m.idMedidaCaja = :idMedidaCaja")
    , @NamedQuery(name = "MedidaCaja.findByMedidaCaja", query = "SELECT m FROM MedidaCaja m WHERE m.medidaCaja = :medidaCaja")})
public class MedidaCaja implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedidaCaja")
    private Integer idMedidaCaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "medidaCaja")
    private String medidaCaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedidaCaja", fetch = FetchType.LAZY)
    private List<TipoEmpaque> tipoEmpaqueList;

    public MedidaCaja() {
    }

    public MedidaCaja(Integer idMedidaCaja) {
        this.idMedidaCaja = idMedidaCaja;
    }

    public MedidaCaja(Integer idMedidaCaja, String medidaCaja) {
        this.idMedidaCaja = idMedidaCaja;
        this.medidaCaja = medidaCaja;
    }

    public Integer getIdMedidaCaja() {
        return idMedidaCaja;
    }

    public void setIdMedidaCaja(Integer idMedidaCaja) {
        this.idMedidaCaja = idMedidaCaja;
    }

    public String getMedidaCaja() {
        return medidaCaja;
    }

    public void setMedidaCaja(String medidaCaja) {
        this.medidaCaja = medidaCaja;
    }

    public List<TipoEmpaque> getTipoEmpaqueList() {
        return tipoEmpaqueList;
    }

    public void setTipoEmpaqueList(List<TipoEmpaque> tipoEmpaqueList) {
        this.tipoEmpaqueList = tipoEmpaqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedidaCaja != null ? idMedidaCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidaCaja)) {
            return false;
        }
        MedidaCaja other = (MedidaCaja) object;
        if ((this.idMedidaCaja == null && other.idMedidaCaja != null) || (this.idMedidaCaja != null && !this.idMedidaCaja.equals(other.idMedidaCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.expochick.backend.persistence.entity.MedidaCaja[ idMedidaCaja=" + idMedidaCaja + " ]";
    }

    @Override
    public String getId() {
        return idMedidaCaja.toString();
    }
    
}
