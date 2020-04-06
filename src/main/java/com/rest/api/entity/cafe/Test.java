package com.rest.api.entity.cafe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Test")
public class Test {
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
