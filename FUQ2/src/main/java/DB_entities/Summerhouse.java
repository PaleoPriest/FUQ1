/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_entities;

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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rugile
 */
@Entity
@Table(name = "SUMMERHOUSE")
@NamedQueries({
    @NamedQuery(name = "Summerhouse.findAll", query = "SELECT s FROM Summerhouse s"),
    @NamedQuery(name = "Summerhouse.findById", query = "SELECT s FROM Summerhouse s WHERE s.id = :id"),
    @NamedQuery(name = "Summerhouse.findByName", query = "SELECT s FROM Summerhouse s WHERE s.name = :name"),
    @NamedQuery(name = "Summerhouse.findByDescription", query = "SELECT s FROM Summerhouse s WHERE s.description = :description"),
    @NamedQuery(name = "Summerhouse.findByMaxRooms", query = "SELECT s FROM Summerhouse s WHERE s.maxRooms = :maxRooms")})
public class Summerhouse implements Serializable {

    

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "MAX_ROOMS")
    private Integer maxRooms;
    @Column(name = "USER_ID")
    private Integer userId;
    
    @Column(name = "RESERVSTIONSTART")
    @Temporal(TemporalType.DATE)
    private Date reservationStart;
    
    @Column(name = "RESERVATIONTIME")
    private Integer reservationTime;
    @Column(name = "MAXROOMS")
    private Integer maxrooms;
    @Column(name = "RESERVATIONSTART")
    @Temporal(TemporalType.DATE)
    private Date reservationstart;
    @Column(name = "ADDITIONMONTH")
    private Integer additionmonth;
    @Column(name = "ADDITIONDAY")
    private Integer additionday;
    @Column(name = "USERID")
    private Integer userid;
    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;
    
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    @ManyToOne
    private Rooms room;

    public Summerhouse() {
    }

    public Summerhouse(Integer id) {
        this.id = id;
    }

    public Summerhouse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Summerhouse(Integer id, String name, String description, Integer maxRooms, Integer reservationTime)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxRooms = maxRooms;
        this.reservationTime = reservationTime;
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

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
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
        if (!(object instanceof Summerhouse)) {
            return false;
        }
        Summerhouse other = (Summerhouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_entities.Summerhouse[ id=" + id + " ]";
    }

    /**
     * @return the reservationTime
     */
    public Integer getReservationTime() {
        return reservationTime;
    }

    /**
     * @param reservationTime the reservationTime to set
     */
    public void setReservationTime(Integer reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * @return the reservationStart
     */
    public Date getReservationStart() {
        return reservationStart;
    }

    /**
     * @param reservationStart the reservationStart to set
     */
    public void setReservationStart(Date reservationStart) {
        this.reservationStart = reservationStart;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMaxrooms() {
        return maxrooms;
    }

    public void setMaxrooms(Integer maxrooms) {
        this.maxrooms = maxrooms;
    }

    public Date getReservationstart() {
        return reservationstart;
    }

    public void setReservationstart(Date reservationstart) {
        this.reservationstart = reservationstart;
    }

    public Integer getAdditionmonth() {
        return additionmonth;
    }

    public void setAdditionmonth(Integer additionmonth) {
        this.additionmonth = additionmonth;
    }

    public Integer getAdditionday() {
        return additionday;
    }

    public void setAdditionday(Integer additionday) {
        this.additionday = additionday;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOptLockVersion() {
        return optLockVersion;
    }

    public void setOptLockVersion(Integer optLockVersion) {
        this.optLockVersion = optLockVersion;
    }
}
