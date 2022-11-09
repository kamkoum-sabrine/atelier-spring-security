package com.example.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class User implements Serializable, UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String username;
	private String password;
	
	
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(Integer userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	public Integer getUserId() {
	 return userId;
	 }
	public void setUserId(Integer userId) {
	 this.userId = userId;
	 }
	public String getUsername() {
	 return username;
	 }
	@Override
	public boolean isAccountNonExpired() {
	 return false;
	 }
	@Override
	public boolean isAccountNonLocked() {
	 return false;
	 }
	@Override
	public boolean isCredentialsNonExpired() {
	 return false;
	 }
	@Override
	public boolean isEnabled() {
	 return false;
	 }
	public void setUsername(String username) {
	 this.username = username;
	 }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	 return null;
	 }
	public String getPassword() {
	 return password;
	 } 
	public void setPassword(String password) {
		 this.password = password;
		 }
} 