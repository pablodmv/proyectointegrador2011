/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.entities;

import com.inout.util.converters;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "MARCA")
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findById", query = "SELECT m FROM Marca m WHERE m.id = :id"),
    @NamedQuery(name = "Marca.findByFecha", query = "SELECT m FROM Marca m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Marca.findByFechaYPersona", query = "SELECT m FROM Marca m WHERE m.fecha = :fecha AND m.persona = :persona"),
    @NamedQuery(name = "Marca.findByHora", query = "SELECT m FROM Marca m WHERE m.hora = :hora"),
    @NamedQuery(name = "Marca.findByIdDispositivo", query = "SELECT m FROM Marca m WHERE m.idDispositivo = :idDispositivo"),
    @NamedQuery(name = "Marca.findByDispositivo", query = "SELECT m FROM Marca m WHERE m.dispositivo = :dispositivo"),
    @NamedQuery(name = "Marca.findByIdPareja", query = "SELECT m FROM Marca m WHERE m.idPareja = :idPareja"),
    @NamedQuery(name = "Marca.findByCorreccionFecha", query = "SELECT m FROM Marca m WHERE m.correccionFecha = :correccionFecha"),
    @NamedQuery(name = "Marca.findByCorreccionHora", query = "SELECT m FROM Marca m WHERE m.correccionHora = :correccionHora")})
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "HORA")
    private String hora;
    @Basic(optional = false)
    @Column(name = "ID_DISPOSITIVO")
    private String idDispositivo;
    @Basic(optional = false)
    @Column(name = "DISPOSITIVO")
    private String dispositivo;
    @Basic(optional = false)
    @Column(name = "ID_PAREJA")
    private long idPareja;
    @Column(name = "CORRECCION_FECHA")
    @Temporal(TemporalType.DATE)
    private Date correccionFecha;
    @Column(name = "CORRECCION_HORA")
    private String correccionHora;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "DOCUMENTO")
    @ManyToOne(optional = false)
    private Persona persona;

    public Marca() {
    }

    public Marca(Long id) {
        this.id = id;
    }

    public Marca(Long id, Date fecha, String hora, String idDispositivo, String dispositivo, long idPareja) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idDispositivo = idDispositivo;
        this.dispositivo = dispositivo;
        this.idPareja = idPareja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public long getIdPareja() {
        return idPareja;
    }

    public void setIdPareja(long idPareja) {
        this.idPareja = idPareja;
    }

    public Date getCorreccionFecha() {
        return correccionFecha;
    }

    public void setCorreccionFecha(Date correccionFecha) {
        this.correccionFecha = correccionFecha;
    }

    public String getCorreccionHora() {
        return correccionHora;
    }

    public void setCorreccionHora(String correccionHora) {
        this.correccionHora = correccionHora;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inout.entities.Marca[id=" + id + "]";
    }

}
