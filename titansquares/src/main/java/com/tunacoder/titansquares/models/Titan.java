package com.tunacoder.titansquares.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity 
public class Titan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	@NotEmpty(message = "Course name field can't be empty")
	private String name;

	@Column(name = "RACE")
	@NotEmpty(message = "Titan race field can't be empty")
	private String race;

	@Column(name = "AGE")
	@Min(value = 2)
	@Max(value = 52)
	private int age;

	@Column(name = "DESCRIPTION")
	@NotEmpty(message = "Description field can't be empty")
	private String description;

	public Titan() {}

	public Titan(Long id, String name, String race, int age, String description) {
		this.id = id;
		this.name = name;
		this.race = race;
		this.age = age;
		this.description = description;
	}
 
	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", name='" + name + '\'' +
				", race='" + race + '\'' +
				", age=" + age +
				", description='" + description + '\'' +
				'}';
	}
}