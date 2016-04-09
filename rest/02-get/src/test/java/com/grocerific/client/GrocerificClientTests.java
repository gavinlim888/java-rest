/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.grocerific.domain.Product;
import com.grocerific.exceptions.DataNotFoundException;

public class GrocerificClientTests {

	GrocerificClient client;
	final int EXISTING_PRODUCT_ID = 1;
	final int NON_EXISTENT_PRODUCT_ID = 99999;

	@Before
	public void setUp() {
		client = new GrocerificClient();
	}

	@Test
	public void testGetProductThatExists() {
		Product product = client.getProduct(EXISTING_PRODUCT_ID);		
		assertNotNull(product);
		printProduct(product);
	}

	@Test
	public void testGetProductThatDoesNotExist() {
		assertNull(client.getProduct(NON_EXISTENT_PRODUCT_ID));
	}

	@Test
	public void testGetAllProducts() {
		List<Product> products = client.getAllProducts();

		assertNotNull(products);
		
		for (Product product : products) {
			System.out.println(product.getDescription());
		}
	}

	@Test
	public void testGetProductsPage() {
		int offset = 0;
		int size = 5;
		List<Product> products = client.getProductsPage(offset, size);
		
		assertNotNull(products);
		assertTrue("Results should not be more than page size", products.size() <= size);
		
		for (Product product : products) {
			System.out.println(product.getDescription());
		}
	}

	@Test
	public void testAddProduct() {
		Product product = new Product();
		product.setDescription("Kirkland Multi-Vitmains");
		product.setSize("1000 mg");
		product.setPrice(750f);
		product = client.addProduct(product);
		assertNotNull(product);

		// product Id should already have been assigned
		assertTrue(product.getId() != 0);
		
		printProduct(product);
	}

	@Test
	public void testUpdateProduct() {
		float newPrice = 30f;
		
		Product product = new Product();
		product.setId(1);
		product.setDescription("Coke Can");
		product.setSize("330ml");
		product.setPrice(newPrice);
		client.updateProduct(product);
		
		Product updatedProduct = client.getProduct(1);
		assertTrue(updatedProduct.getPrice() == product.getPrice());
		
		printProduct(updatedProduct);
	}

	@Test
	public void testDeleteProduct() {
		int productIdToBeDeleted = 20;
		client.deleteProduct(productIdToBeDeleted);	
		assertNull(client.getProduct(productIdToBeDeleted));
	}
	
	@Test(expected=DataNotFoundException.class)
	public void testDeleteProductThatDoesNotExist() {
		client.deleteProduct(NON_EXISTENT_PRODUCT_ID);	
	}
	
	@Test	
	public void testGetProductsFields() {
		String[] fields = {"description", "size"};
		List<Map<String, Object>> data = client.getProductsFields(fields);
				
		assertNotNull("No data was retrieved.", data);

		for (Map<String, Object> map : data) {
			System.out.println(map);
		}		
	}
	
	void printProduct(Product product) {
		System.out.println("id: " + product.getId());
		System.out.println("description: " + product.getDescription());
		System.out.println("price: " + product.getPrice());
		System.out.println("size: " + product.getSize());
	}
}
