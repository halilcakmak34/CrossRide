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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author halilcakmak
 *
 */
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 224140553229345515L;

	public Vehicle() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	Driver owner;

	@OneToOne
	@JoinColumn(name = "vehicle_type_id", referencedColumnName = "id")
	VehicleType vehicleType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getOwner() {
		return owner;
	}

	public void setOwner(Driver owner) {
		this.owner = owner;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((getVehicleType() == null) ? 0 : getVehicleType().hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (getVehicleType() == null) {
			if (other.getVehicleType() != null)
				return false;
		} else if (!getVehicleType().equals(other.getVehicleType()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (getOwner() == null) {
			if (other.getOwner() != null)
				return false;
		} else if (!getOwner().equals(other.getOwner()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", owner=" + getOwner() + ", vehicleType=" + getVehicleType() + "]";
	}

}
