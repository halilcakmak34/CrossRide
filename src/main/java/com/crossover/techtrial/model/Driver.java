/**
 * 
 */
package com.crossover.techtrial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author halilcakmak
 *
 */
@Entity
@Table(name = "driver")
public class Driver implements Serializable{

  
  /**
	 * 
	 */
	private static final long serialVersionUID = 3982792711600655077L;

public Driver() {}
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  Person person;
  
  @ManyToOne
  @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
  Vehicle vehicle;
  
  @Column(name="tax_id")
  Tax taxInfo;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}

public Vehicle getVehicle() {
	return vehicle;
}

public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
}

public Tax getTaxInfo() {
	return taxInfo;
}

public void setTaxInfo(Tax taxInfo) {
	this.taxInfo = taxInfo;
}

@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
    result = prime * result + ((taxInfo == null) ? 0 : taxInfo.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Driver other = (Driver) obj;
    if (taxInfo == null) {
      if (other.taxInfo != null)
        return false;
    } else if (!taxInfo.equals(other.taxInfo))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (vehicle == null) {
      if (other.vehicle != null)
        return false;
    } else if (!vehicle.equals(other.vehicle))
      return false;
    if (person == null) {
      if (other.person != null)
        return false;
    } else if (!person.equals(other.person))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", vehicle=" + vehicle + ", person=" + person + ", taxInfo=" + taxInfo + "]";
  }
  
  


}
