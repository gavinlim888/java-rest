/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */
package com.grocerific.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.grocerific.daos.JdbcProductsDao;
import com.grocerific.daos.ProductsDao;
import com.grocerific.domain.Product;

@Path("products")
public class ProductsResource {
	ProductsDao dao;
	
	public ProductsResource(){
		this.dao = new JdbcProductsDao();
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Product get(@PathParam("id") int productId) {
		return dao.getProduct(productId);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> getAll() {
		return dao.getProductList();
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