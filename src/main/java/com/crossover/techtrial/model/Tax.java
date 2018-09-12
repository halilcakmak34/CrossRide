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
 * @author halilcakmak
 *
 */
@Entity
@Table(name = "tax")
public class Tax implements Serializable {

	private static final long serialVersionUID = 224140553229345515L;

	public Tax() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne
	@JoinColumn(name = "amenable_id", referencedColumnName = "id")
	Driver amenable;
	
	@OneToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	Vehicle vehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getAmenable() {
		return amenable;
	}

	public void setAmenable(Driver amenable) {
		this.amenable = amenable;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAmenable() == null) ? 0 : getAmenable().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((getVehicle() == null) ? 0 : getVehicle().hashCode());
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
		Tax other = (Tax) obj;
		if (getVehicle() == null) {
			if (other.getVehicle() != null)
				return false;
		} else if (!getVehicle().equals(other.getVehicle()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (getAmenable() == null) {
			if (other.getAmenable() != null)
				return false;
		} else if (!getAmenable().equals(other.getAmenable()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tax [id=" + id + ", amenable=" + getAmenable() + ", vehicle = " + getVehicle() + "]";
	}

}
