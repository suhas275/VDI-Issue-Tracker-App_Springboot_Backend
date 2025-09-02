package com.vdi.authentication.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User") 
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer associateId;
    private String firstName; 
    private String lastName;
    private String email;
    private String contactNo;
    private String roles;
    private String userName;
    private String password;

    @PrePersist
    void onCreate() {    
    	if (this.roles == null) {         
    		this.roles = "USER_ROLES"; }
    	}
}
