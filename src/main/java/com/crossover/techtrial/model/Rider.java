/**
 * 
 */
package com.crossover.techtrial.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author crossover
 *
 */
@Entity
@Table(name = "rider")
public class Rider implements Serializable{

  private static final long serialVersionUID = 7401548380514451401L;
  
  public Rider() {}
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  Person person;
  
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

@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Rider other = (Rider) obj;
    if (person == null) {
      if (other.person != null)
        return false;
    } else if (!person.equals(other.person))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", person=" + person + "]";
  }
  
  


}
