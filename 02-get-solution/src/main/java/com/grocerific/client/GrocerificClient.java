/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.grocerific.domain.Product;

public class GrocerificClient {
	private final String PRODUCTS_URL = "http://localhost:8080/02-get/products/";
	
	public Product getProduct(int productId) {
		Client client =  ClientBuilder.newClient();
		WebTarget target = client.target(PRODUCTS_URL + String.valueOf(productId));
		Product product = target.request().get(Product.class);
		
		return product;
		
	}

	public List<Product> getAllProducts() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PRODUCTS_URL);
		List<Product> products = target.request().get(new GenericType<ArrayList<Product>>(){});
		
		return products;
	}

	public List<Product> getProductsPage(int offset, int size) {
		throw new UnsupportedOperationException();
	}

	public List<Map<String, Object>> getProductsFields(String[] fields) {
		throw new UnsupportedOperationException();
	}

	public Product addProduct(Product product) {
		throw new UnsupportedOperationException();
	}

	public void updateProduct(Product product) {
		throw new UnsupportedOperationException();
	}

	public void deleteProduct(int productId) {
		throw new UnsupportedOperationException();
	}
}