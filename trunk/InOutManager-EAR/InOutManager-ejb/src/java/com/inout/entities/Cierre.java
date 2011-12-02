/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "CIERRE")
@NamedQueries({
    @NamedQuery(name = "Cierre.findAll", query = "SELECT c FROM Cierre c"),
    @NamedQuery(name = "Cierre.findById", query = "SELECT c FROM Cierre c WHERE c.id = :id"),
    @NamedQuery(name = "Cierre.findByMes", query = "SELECT c FROM Cierre c WHERE c.mes = :mes"),
    @NamedQuery(name = "Cierre.findByAno", query = "SELECT c FROM Cierre c WHERE c.ano = :ano")})
public class Cierre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "MES")
    private String mes;
    @Basic(optional = false)
    @Column(name = "ANO")
    private short ano;

    public Cierre() {
    }

    public Cierre(Long id) {
        this.id = id;
    }

    public Cierre(Long id, String mes, short ano) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
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
        if (!(object instanceof Cierre)) {
            return false;
        }
        Cierre other = (Cierre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inout.entities.Cierre[id=" + id + "]";
    }

}
