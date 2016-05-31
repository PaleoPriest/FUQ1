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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rugile
 */
@Entity
@Table(name = "REMEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remember.findAll", query = "SELECT r FROM Remember r")})
public class Remember implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 38)
    @Column(name = "ID")
    private String id;
    @JoinColumn(name = "LOGGEDINUSER", referencedColumnName = "ID")
    @ManyToOne
    private Users loggedinuser;
    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    public Remember() {
    }

    public Remember(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getLoggedinuser() {
        return loggedinuser;
    }

    public void setLoggedinuser(Users loggedinuser) {
        this.loggedinuser = loggedinuser;
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
        if (!(object instanceof Remember)) {
            return false;
        }
        Remember other = (Remember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_entities.Remember[ id=" + id + " ]";
    }

    public Integer getOptLockVersion() {
        return optLockVersion;
    }

    public void setOptLockVersion(Integer optLockVersion) {
        this.optLockVersion = optLockVersion;
    }
    
}
