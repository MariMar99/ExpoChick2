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
@Table(name = "estadosclientes")
@NamedQueries({
    @NamedQuery(name = "EstadoCliente.findAll", query = "SELECT e FROM EstadoCliente e")
    , @NamedQuery(name = "EstadoCliente.findByIdEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.idEstadoCliente = :idEstadoCliente")
    , @NamedQuery(name = "EstadoCliente.findByNombreEstadoCliente", query = "SELECT e FROM EstadoCliente e WHERE e.nombreEstadoCliente = :nombreEstadoCliente")})
public class EstadoCliente implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoCliente")
    private Integer idEstadoCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreEstadoCliente")
    private String nombreEstadoCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCliente", fetch = FetchType.LAZY)
    private List<Cliente> clienteList;

    public EstadoCliente() {
    }

    public EstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public EstadoCliente(Integer idEstadoCliente, String nombreEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
        this.nombreEstadoCliente = nombreEstadoCliente;
    }

    public Integer getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public String getNombreEstadoCliente() {
        return nombreEstadoCliente;
    }

    public void setNombreEstadoCliente(String nombreEstadoCliente) {
        this.nombreEstadoCliente = nombreEstadoCliente;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCliente != null ? idEstadoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCliente)) {
            return false;
        }
        EstadoCliente other = (EstadoCliente) object;
        if ((this.idEstadoCliente == null && other.idEstadoCliente != null) || (this.idEstadoCliente != null && !this.idEstadoCliente.equals(other.idEstadoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.expochick.backend.persistence.entity.EstadoCliente[ idEstadoCliente=" + idEstadoCliente + " ]";
    }

    @Override
    public String getId() {
        return idEstadoCliente.toString();
    }
    
}
