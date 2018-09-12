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
import javax.persistence.Table;

/**
 * @author halilcakmak
 *
 */
@Entity
@Table(name = "vehicle_type")
public class VehicleType implements Serializable {

	private static final long serialVersionUID = -4214663625021747102L;

	public VehicleType() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "vehicle_type")
	String vehicleType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getVehicleType() == null) ? 0 : getVehicleType().hashCode());
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
		VehicleType other = (VehicleType) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "VehicleType [id=" + id + ", vehicleType=" + getVehicleType() + "]";
	}

}
