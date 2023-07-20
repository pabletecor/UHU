/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "EXPERT", catalog = "", schema = "DDSI_057")
@NamedQueries({
    @NamedQuery(name = "Expert.findAll", query = "SELECT e FROM Expert e")
    , @NamedQuery(name = "Expert.findByCodexpert", query = "SELECT e FROM Expert e WHERE e.codexpert = :codexpert")
    , @NamedQuery(name = "Expert.findByName", query = "SELECT e FROM Expert e WHERE e.name = :name")
    , @NamedQuery(name = "Expert.findByCountry", query = "SELECT e FROM Expert e WHERE e.country = :country")
    , @NamedQuery(name = "Expert.findBySex", query = "SELECT e FROM Expert e WHERE e.sex = :sex")
    , @NamedQuery(name = "Expert.findBySpecialism", query = "SELECT e FROM Expert e WHERE e.specialism = :specialism")})
public class Expert implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODEXPERT")
    private String codexpert;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "SEX")
    private Character sex;
    @Basic(optional = false)
    @Column(name = "SPECIALISM")
    private String specialism;

    public Expert() {
    }

    public Expert(String codexpert) {
        this.codexpert = codexpert;
    }

    public Expert(String codexpert, String name, String country, String specialism) {
        this.codexpert = codexpert;
        this.name = name;
        this.country = country;
        this.specialism = specialism;
    }

    public String getCodexpert() {
        return codexpert;
    }

    public void setCodexpert(String codexpert) {
        String oldCodexpert = this.codexpert;
        this.codexpert = codexpert;
        changeSupport.firePropertyChange("codexpert", oldCodexpert, codexpert);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        String oldCountry = this.country;
        this.country = country;
        changeSupport.firePropertyChange("country", oldCountry, country);
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        Character oldSex = this.sex;
        this.sex = sex;
        changeSupport.firePropertyChange("sex", oldSex, sex);
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        String oldSpecialism = this.specialism;
        this.specialism = specialism;
        changeSupport.firePropertyChange("specialism", oldSpecialism, specialism);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codexpert != null ? codexpert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expert)) {
            return false;
        }
        Expert other = (Expert) object;
        if ((this.codexpert == null && other.codexpert != null) || (this.codexpert != null && !this.codexpert.equals(other.codexpert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Business.Expert[ codexpert=" + codexpert + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
