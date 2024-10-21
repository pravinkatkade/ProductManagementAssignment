package com.example.demo.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;
	@Column(name = "pname")
	private String name;
	
	@Column(name = "price")
	private String price;
//	@ManyToOne
//    @JoinColumn(name = "cid") // Foreign key referencing Category
//    private Category category;
	@Column(name="cid")
	 private int cid;
	
	
	
}




