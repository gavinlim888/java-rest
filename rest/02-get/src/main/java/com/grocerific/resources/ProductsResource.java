/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */
package com.grocerific.resources;

import java.util.List;

import javax.ws.rs.core.Response;

import com.grocerific.domain.Product;

public class ProductsResource {
	public Product get(int productId) {
		return null;
	}

	public List<Product> getAll() {
		return null;
	}

	public List<Product> getPage(int offset, int size) {
		return null;
	}

	public String getFieldsAsJson(List<String> fields) {
		return null;
	}

	public Response add(Product product) {
		return null;
	}

	public Response update(Product product, int id) {
		return null;
	}

	public Response delete(int productId) {
		return null;
	}
}