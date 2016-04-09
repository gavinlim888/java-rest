/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.domain;

/**
 * A product
 * 
 * @author Gavin Lim
 */
public class Product {
	private int id;
	private String description;
	private String size;
	private float price;

	public Product() {
	}

	public Product(int id, String description, String size, float price) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
