/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.daos;

import java.util.List;
import java.util.Map;

import com.grocerific.domain.Product;

public interface ProductsDao {

	Product getProduct(int productId);

	List<Product> getProductList();

	List<Product> getProductList(int offset, int size);

	List<Map<String, Object>> getProductsFields(String[] columns);
	
	Product addProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(int productId);

}