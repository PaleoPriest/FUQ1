/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Paulina
 */
@Entity
@Table(name = "RENT")
@NamedQueries({
    @NamedQuery(name = "Rent.findAll", query = "SELECT r FROM Rent r"),
    @NamedQuery(name = "Rent.findById", query = "SELECT r FROM Rent r WHERE r.id = :id"),
    @NamedQuery(name = "Rent.findByName", query = "SELECT r FROM Rent r WHERE r.name = :name"),
    @NamedQuery(name = "Rent.findByDescription", query = "SELECT r FROM Rent r WHERE r.description = :description"),
    @NamedQuery(name = "Rent.findByFreePlace", query = "SELECT r FROM Rent r WHERE r.freePlace = :freePlace")})
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "FREE_PLACE")
    private Integer freePlace;

    public Rent() {
    }

    public Rent(Integer id) {
        this.id = id;
    }

    public Rent(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(Integer freePlace) {
        this.freePlace = freePlace;
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
        if (!(object instanceof Rent)) {
            return false;
        }
        Rent other = (Rent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_entities.Rent[ id=" + id + " ]";
    }
    
}
