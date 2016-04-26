/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Paulina
 */
@Entity
@Table(name = "ROOMS")
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findById", query = "SELECT r FROM Rooms r WHERE r.id = :id"),
    @NamedQuery(name = "Rooms.findByMaxBeds", query = "SELECT r FROM Rooms r WHERE r.maxBeds = :maxBeds"),
    @NamedQuery(name = "Rooms.findByStartDate", query = "SELECT r FROM Rooms r WHERE r.startDate = :startDate"),
    @NamedQuery(name = "Rooms.findByDuration", query = "SELECT r FROM Rooms r WHERE r.duration = :duration"),
    @NamedQuery(name = "Rooms.findByPrice", query = "SELECT r FROM Rooms r WHERE r.price = :price")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MAX_BEDS")
    private Integer maxBeds;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "DURATION")
    private Integer duration;
    @Column(name = "PRICE")
    private Integer price;
    @OneToMany(mappedBy = "room")
    private List<Users> usersList;
    @OneToMany(mappedBy = "room")
    private List<Summerhouse> summerhouseList;

    public Rooms() {
    }

    public Rooms(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxBeds() {
        return maxBeds;
    }

    public void setMaxBeds(Integer maxBeds) {
        this.maxBeds = maxBeds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Summerhouse> getSummerhouseList() {
        return summerhouseList;
    }

    public void setSummerhouseList(List<Summerhouse> summerhouseList) {
        this.summerhouseList = summerhouseList;
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
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_entities.Rooms[ id=" + id + " ]";
    }
    
}
