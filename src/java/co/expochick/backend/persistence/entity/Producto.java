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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombreProducto", query = "SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria = :categoria")
    , @NamedQuery(name = "Producto.findByCalibre", query = "SELECT p FROM Producto p WHERE p.calibre = :calibre")
    , @NamedQuery(name = "Producto.findByGradoMadurez", query = "SELECT p FROM Producto p WHERE p.gradoMadurez = :gradoMadurez")})
public class Producto implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "categoria")
    private int categoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calibre")
    private int calibre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gradoMadurez")
    private int gradoMadurez;
    @JoinColumn(name = "idEstadoProducto", referencedColumnName = "idEstadoProducto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoProducto idEstadoProducto;
    @JoinColumn(name = "idPrecioCantidad", referencedColumnName = "idPrecioCantidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PrecioCantidad idPrecioCantidad;
    @JoinColumn(name = "idPresentacion", referencedColumnName = "idPresentacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Presentacion idPresentacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Trazabilidad> trazabilidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<DetallePedido> detallePedidoList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombreProducto, String descripcion, int categoria, int calibre, int gradoMadurez) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.calibre = calibre;
        this.gradoMadurez = gradoMadurez;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public int getGradoMadurez() {
        return gradoMadurez;
    }

    public void setGradoMadurez(int gradoMadurez) {
        this.gradoMadurez = gradoMadurez;
    }

    public EstadoProducto getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(EstadoProducto idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public PrecioCantidad getIdPrecioCantidad() {
        return idPrecioCantidad;
    }

    public void setIdPrecioCantidad(PrecioCantidad idPrecioCantidad) {
        this.idPrecioCantidad = idPrecioCantidad;
    }

    public Presentacion getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Presentacion idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public List<Trazabilidad> getTrazabilidadList() {
        return trazabilidadList;
    }

    public void setTrazabilidadList(List<Trazabilidad> trazabilidadList) {
        this.trazabilidadList = trazabilidadList;
    }

    public List<DetallePedido> getDetallePedidoList() {
        return detallePedidoList;
    }

    public void setDetallePedidoList(List<DetallePedido> detallePedidoList) {
        this.detallePedidoList = detallePedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.expochick.backend.persistence.entity.Producto[ idProducto=" + idProducto + " ]";
    }

    @Override
    public String getId() {
        return idProducto.toString();
    }
    
}
