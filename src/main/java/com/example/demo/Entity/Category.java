package com.example.demo.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="Category")
@Setter
@Getter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "cid")
	 private int id;
	@Column(name="cname")
	private String name;
	
	
	@OneToMany(fetch=FetchType.LAZY,targetEntity=Product.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cid",referencedColumnName = "cid")
	
	 private Set<Product>productList;
	
	
	
	
	 
}




