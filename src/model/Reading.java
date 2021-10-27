package model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import serializer.ReadingSerializer;

@Getter @Setter
@Entity
@Table
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonSerialize(using = ReadingSerializer.class)
public class Reading implements Comparable<Reading> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private int reading;

	private Instant atTime;

	@ManyToOne(optional=false)
	@JoinColumn(name="sensor_id", nullable=false)
	private Sensor sensor;

	@Override
	public int compareTo(Reading o) {
		// Multiply by -1 so NaturalSort starts with most recent time
		return this.getAtTime().compareTo(o.getAtTime()) * -1;
	}
}
