package com.ict.internCollab.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	private String password;
		
	private boolean isActive;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date registrationDate;
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "userTypeId",referencedColumnName = "userTypeId") private
	 * UsersType userTypeId;
	 * 
	 */	
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "user_user_type", // Specify a join table name joinColumns
	 * = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name =
	 * "user_type_id")) private UsersType userTypeId;
	 * 
	 */
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_type_id")  // Define the join column
	private UsersType userTypeId;

	
	//generate constructors
	public Users() {
		super();
	}

	//generate constructor with all arguments

	public Users(int userId, String email, @NotEmpty String password, boolean isActive, Date registrationDate,
			UsersType userTypeId) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.registrationDate = registrationDate;
		this.userTypeId = (UsersType) userTypeId;
	}

	//getter and setter
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	
	public UsersType getUserTypeId() {
		return (UsersType) userTypeId;
	}

	
	public void setUserTypeId(UsersType userTypeId) {
		this.userTypeId = (UsersType) userTypeId;
	}

	//toString method
	@Override
	public String toString() {
		
		return "Users [userId=" + userId + ", email=" + email + ", password=" + password + ", isActive=" + isActive
				+ ", registrationDate=" + registrationDate + ", userTypeId=" + userTypeId + "]";
	
	
	}
	
	
}
