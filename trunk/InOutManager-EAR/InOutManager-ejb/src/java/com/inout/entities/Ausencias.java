/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.entities;

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
@Table(name = "AUSENCIAS")
@NamedQueries({
    @NamedQuery(name = "Ausencias.findAll", query = "SELECT a FROM Ausencias a"),
    @NamedQuery(name = "Ausencias.findById", query = "SELECT a FROM Ausencias a WHERE a.id = :id"),
    @NamedQuery(name = "Ausencias.findByFecha", query = "SELECT a FROM Ausencias a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Ausencias.findByObservacion", query = "SELECT a FROM Ausencias a WHERE a.observacion = :observacion")})
public class Ausencias implements Serializable {
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
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "DOCUMENTO")
    @ManyToOne
    private Persona persona;
    @JoinColumn(name = "ID_MOTIVO", referencedColumnName = "ID")
    @ManyToOne
    private MotivoAusencia motivoAusencia;
    @JoinColumn(name = "ID_HORARIO", referencedColumnName = "ID")
    @ManyToOne
    private HorarioSemana horarioSemana;

    public Ausencias() {
    }

    public Ausencias(Long id) {
        this.id = id;
    }

    public Ausencias(Long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public MotivoAusencia getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(MotivoAusencia motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    public HorarioSemana getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(HorarioSemana horarioSemana) {
        this.horarioSemana = horarioSemana;
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
        if (!(object instanceof Ausencias)) {
            return false;
        }
        Ausencias other = (Ausencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inout.entities.Ausencias[id=" + id + "]";
    }

}
