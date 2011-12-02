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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "MOTIVO_AUSENCIA")
@NamedQueries({
    @NamedQuery(name = "MotivoAusencia.findAll", query = "SELECT m FROM MotivoAusencia m"),
    @NamedQuery(name = "MotivoAusencia.findById", query = "SELECT m FROM MotivoAusencia m WHERE m.id = :id"),
    @NamedQuery(name = "MotivoAusencia.findByMotivo", query = "SELECT m FROM MotivoAusencia m WHERE m.motivo = :motivo")})
public class MotivoAusencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "MOTIVO")
    private String motivo;
    @OneToMany(mappedBy = "motivoAusencia")
    private Collection<Ausencias> ausenciasCollection;

    public MotivoAusencia() {
    }

    public MotivoAusencia(Long id) {
        this.id = id;
    }

    public MotivoAusencia(Long id, String motivo) {
        this.id = id;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
        if (!(object instanceof MotivoAusencia)) {
            return false;
        }
        MotivoAusencia other = (MotivoAusencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inout.entities.MotivoAusencia[id=" + id + "]";
    }

}
