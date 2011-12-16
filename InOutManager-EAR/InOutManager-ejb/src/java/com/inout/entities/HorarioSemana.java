/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "HORARIO_SEMANA")
@NamedQueries({
    @NamedQuery(name = "HorarioSemana.findAll", query = "SELECT h FROM HorarioSemana h"),
    @NamedQuery(name = "HorarioSemana.findById", query = "SELECT h FROM HorarioSemana h WHERE h.id = :id"),
    @NamedQuery(name = "HorarioSemana.findByDiaSemana", query = "SELECT h FROM HorarioSemana h WHERE h.diaSemana = :diaSemana"),
    @NamedQuery(name = "HorarioSemana.findByInicio", query = "SELECT h FROM HorarioSemana h WHERE h.inicio = :inicio"),
    @NamedQuery(name = "HorarioSemana.findByFin", query = "SELECT h FROM HorarioSemana h WHERE h.fin = :fin"),
    @NamedQuery(name = "HorarioSemana.findDiasSemana", query = "SELECT DISTINCT h.diaSemana FROM HorarioSemana h WHERE h.persona = :persona"),
    @NamedQuery(name = "HorarioSemana.findBySalon", query = "SELECT h FROM HorarioSemana h WHERE h.salon = :salon"),
    @NamedQuery(name = "HorarioSemana.findByPersona", query = "SELECT h FROM HorarioSemana h WHERE h.persona = :persona"),
    @NamedQuery(name = "HorarioSemana.findByObservaciones", query = "SELECT h FROM HorarioSemana h WHERE h.observaciones = :observaciones")})

public class HorarioSemana implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "DIA_SEMANA")
    private short diaSemana;
    @Basic(optional = false)
    @Column(name = "INICIO")
    private String inicio;
    @Basic(optional = false)
    @Column(name = "FIN")
    private String fin;
    @Column(name = "SALON")
    private String salon;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "DOCUMENTO")
    @ManyToOne(optional = false)
    private Persona persona;
    @OneToMany(mappedBy = "horarioSemana")
    private Collection<Ausencias> ausenciasCollection;

    public HorarioSemana() {
    }

    public HorarioSemana(Long id) {
        this.id = id;
    }

    public HorarioSemana(Long id, short diaSemana, String inicio, String fin) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(short diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Collection<Ausencias> getAusenciasCollection() {
        return ausenciasCollection;
    }

    public void setAusenciasCollection(Collection<Ausencias> ausenciasCollection) {
        this.ausenciasCollection = ausenciasCollection;
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
        if (!(object instanceof HorarioSemana)) {
            return false;
        }
        HorarioSemana other = (HorarioSemana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inout.entities.HorarioSemana[id=" + id + "]";
    }

}
