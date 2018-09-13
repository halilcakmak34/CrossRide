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
	private static final long serialVersionUID = -6552254944277097279L;

	public TopRide() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "ride_count")
	Integer rideCount;

	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	Person person;

	@Column(name = "max_ride_duration")
	Long maxRideDuration;

	@Column(name = "total_ride_duration")
	Long totalRideDuration;

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

	public Integer getRideCount() {
		return rideCount;
	}

	public void setRideCount(Integer rideCount) {
		this.rideCount = rideCount;
	}

	public Long getTotalRideDuration() {
		return totalRideDuration;
	}

	public void setTotalRideDuration(Long totalRideDuration) {
		this.totalRideDuration = totalRideDuration;
	}

	public Double getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(Double averageDistance) {
		this.averageDistance = averageDistance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getTotalRideDuration() == null) ? 0 : getTotalRideDuration().hashCode());
		result = prime * result + ((getMaxRideDuration() == null) ? 0 : getMaxRideDuration().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((getAverageDistance() == null) ? 0 : getAverageDistance().hashCode());
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
		if (getTotalRideDuration() == null) {
			if (other.getTotalRideDuration() != null)
				return false;
		} else if (!getTotalRideDuration().equals(other.getTotalRideDuration()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (getMaxRideDuration() == null) {
			if (other.getMaxRideDuration() != null)
				return false;
		} else if (!getMaxRideDuration().equals(other.getMaxRideDuration()))
			return false;
		if (getAverageDistance() == null) {
			if (other.getAverageDistance() != null)
				return false;
		} else if (!getAverageDistance().equals(other.getAverageDistance()))
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
		return "TopRide [id=" + id + ", Person =" + person + ", totalRideDuration=" + getTotalRideDuration()
				+ ", maxRideDuration=" + getMaxRideDuration() + ", averageDistance=" + getAverageDistance() + "]";
	}

	public Long getMaxRideDuration() {
		return maxRideDuration;
	}

	public void setMaxRideDuration(Long maxRideDuration) {
		this.maxRideDuration = maxRideDuration;
	}

}
