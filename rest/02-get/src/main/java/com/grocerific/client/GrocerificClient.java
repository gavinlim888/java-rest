/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.client;

import java.util.List;
import java.util.Map;

import com.grocerific.domain.Product;

public class GrocerificClient {

	public Product getProduct(int productId) {
		throw new UnsupportedOperationException();
	}

	public List<Product> getAllProducts() {
		throw new UnsupportedOperationException();
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