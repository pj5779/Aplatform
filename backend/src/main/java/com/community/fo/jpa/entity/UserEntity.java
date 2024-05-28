package com.community.fo.jpa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(length = 100, name = "name", nullable = false) 
    private String name;
    
    @Column(length = 200, name = "email", nullable = true)
    private String email;
    
    @Column(length = 100,name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "del")
    private Boolean del;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

}
