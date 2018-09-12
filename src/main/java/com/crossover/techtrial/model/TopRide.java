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
@Table(name = "top_ride")
public class TopRide implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2794458655374299462L;

	public TopRide() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="ride_count")
	Integer rideCount;

	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	Person person;

	@Column(name = "max_ride_duration")
	Double maxRideDuration;

	@Column(name = "total_ride_duration")
	Double totalRideDuration;

	@Column(name = "average_distance")
	Double averageDistance;

	

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

	public Double getMaxRideDuration() {
		return maxRideDuration;
	}

	public void setMaxRideDuration(Double maxRideDuration) {
		this.maxRideDuration = maxRideDuration;
	}

	public Double getTotalRideDuration() {
		return totalRideDuration;
	}

	public void setTotalRideDuration(Double totalRideDuration) {
		this.totalRideDuration = totalRideDuration;
	}

	public void setAverageDistance(Double averageDistance) {
		this.averageDistance = averageDistance;
	}

	

	public Integer getRideCount() {
		return rideCount;
	}

	public void setRideCount(Integer rideCount) {
		this.rideCount = rideCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((totalRideDuration == null) ? 0 : totalRideDuration.hashCode());
		result = prime * result + ((maxRideDuration == null) ? 0 : maxRideDuration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((averageDistance == null) ? 0 : averageDistance.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((rideCount == null) ? 0 : rideCount.hashCode());
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
		TopRide other = (TopRide) obj;
		if (totalRideDuration == null) {
			if (other.totalRideDuration != null)
				return false;
		} else if (!totalRideDuration.equals(other.totalRideDuration))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxRideDuration == null) {
			if (other.maxRideDuration != null)
				return false;
		} else if (!maxRideDuration.equals(other.maxRideDuration))
			return false;
		if (averageDistance == null) {
			if (other.averageDistance != null)
				return false;
		} else if (!averageDistance.equals(other.averageDistance))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (rideCount == null) {
			if (other.rideCount != null)
				return false;
		} else if (!rideCount.equals(other.rideCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TopRide [id=" + id + ", Person =" + person + ", totalRideDuration=" + totalRideDuration
				+ ", maxRideDuration=" + maxRideDuration + ", averageDistance=" + averageDistance + "]";
	}

}
