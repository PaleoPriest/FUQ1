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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByMail", query = "SELECT u FROM Users u WHERE u.mail = :mail"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByPay", query = "SELECT u FROM Users u WHERE u.pay = :pay")})
public class Users implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE")
    private Integer phone;
    @Size(max = 40)
    @Column(name = "MAIL")
    private String mail;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PAY")
    private Boolean pay;
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    @ManyToOne
    private Rooms room;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    
}
