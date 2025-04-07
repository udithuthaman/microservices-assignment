package com.example.studentservice.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;																																																																				


	@Column(nullable = false)
	@JsonProperty("firstName")
	private String firstName;
	
	@Column(nullable = false)
	@JsonProperty("lastName")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@JsonProperty("email")

	private String email;


    private Long courseId;
}
