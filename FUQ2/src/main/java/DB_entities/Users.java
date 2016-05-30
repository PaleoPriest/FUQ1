/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rugile
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname"),
    @NamedQuery(name = "Users.findByNickname", query = "SELECT u FROM Users u WHERE u.nickname = :nickname"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByMail", query = "SELECT u FROM Users u WHERE u.mail = :mail"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByPay", query = "SELECT u FROM Users u WHERE u.pay = :pay")})
public class Users implements Serializable {

    @Column(name = "THISYEARHOLIDAYS")
    private Integer thisyearholidays;
    @Column(name = "LASTYEARHOLIDAYS")
    private Integer lastyearholidays;
    @Column(name = "RESERVATIONGROUP")
    private Integer reservationgroup;
    @Column(name = "FORYEAR")
    private Integer foryear;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;
    @Size(max = 15)
    @Column(name = "NAME")
    private String name;
    @Size(max = 45)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 20)
    @Column(name = "NICKNAME")
    private String nickname;
    @Size(max = 40)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 40)
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PHONE")
    private Integer phone;
    @Size(max = 10)
    @Column(name = "SEX")
    private String sex;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PAY")
    private Boolean pay;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 20)
    @Column(name = "FBID")
    private String fbid;
    @Column(name = "POINTS")
    private Integer points;
    @Column(name = "ISADMIN")
    private Boolean isAdmin;

    @Transient private boolean editable;

    
    
    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

       public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }
    
    public Integer getPoints() {

        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;

    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_entities.Users[ id=" + id + " ]";
    }


    @Transient
    public boolean isEditable() {
        return editable;
    }

    @Transient
    public void setEditable(boolean editable) {
        this.editable = editable;

    }

    public Integer getThisyearholidays() {
        return thisyearholidays;
    }

    public void setThisyearholidays(Integer thisyearholidays) {
        this.thisyearholidays = thisyearholidays;
    }

    public Integer getLastyearholidays() {
        return lastyearholidays;
    }

    public void setLastyearholidays(Integer lastyearholidays) {
        this.lastyearholidays = lastyearholidays;
    }

    public Integer getReservationgroup() {
        return reservationgroup;
    }

    public void setReservationgroup(Integer reservationgroup) {
        this.reservationgroup = reservationgroup;
    }

    public Integer getForyear() {
        return foryear;
    }

    public void setForyear(Integer foryear) {
        this.foryear = foryear;
    }
}
