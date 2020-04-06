package com.rest.api.entity.cafe;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author lsh
 *
 */
//@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Cafe")
public class Cafe {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (nullable = false, length = 50)
	private String name;

	@Column (nullable = false, length = 100)
	private String location;

	@Column (name = "phone_number")
	private String phoneNumber;

}
