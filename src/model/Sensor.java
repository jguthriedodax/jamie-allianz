package model;

import java.util.SortedSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import model.location.District;
import serializer.SensorSerializer;

@Getter @Setter
@Entity
@Table
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonSerialize(using = SensorSerializer.class)
public class Sensor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String name;

	private double latitude;

	private double longitude;

	@OneToMany(mappedBy="sensor")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@SortNatural
	private SortedSet<Reading> readings;

	@ManyToOne(optional=false)
	@JoinColumn(name="district_id", nullable=false)
	private District district;
}
