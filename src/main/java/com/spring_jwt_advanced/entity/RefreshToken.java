package com.spring_jwt_advanced.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "refresh_token")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "expiry_date")
	private Timestamp expiryDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	

}
