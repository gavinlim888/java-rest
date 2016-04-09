/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.grocerific.domain.Product;
import com.grocerific.exceptions.DataNotFoundException;

/**
 * ProductsDao implementation that uses Spring's JdbcTemplate
 * 
 * @author Gavin Lim
 */
@Repository("productsDao")
public class JdbcProductsDao implements ProductsDao {
	private JdbcTemplate template;

	public JdbcProductsDao() {
		ApplicationContext spring = new ClassPathXmlApplicationContext("data-context.xml");
		DataSource dataSource = (DataSource) spring.getBean("dataSource");
		template = new JdbcTemplate(dataSource);
	}

	/**
	 * Maps the result set to a Product object
	 */
	private class ProductRowMapper implements ParameterizedRowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setDescription(rs.getString("description"));
			product.setSize(rs.getString("size"));
			product.setPrice(rs.getFloat("price"));
			return product;
		}
	}

	@Override
	public Product getProduct(int productId) {
		try {
			String sql = "SELECT * FROM products WHERE id=?";
			return this.template.queryForObject(sql, new ProductRowMapper(), productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Product> getProductList() {
		String sql = "SELECT * FROM products";
		List<Product> products = this.template.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Product.class));
		if (products.size() == 0)
			return null;
		return products;
	}

	@Override
	public List<Map<String, Object>> getProductsFields(String[] columns) {
		StringJoiner sj = new StringJoiner(",");
		for (String col : columns) {
			sj.add(col);
		}
		String projection = sj.toString();
		String sql = "SELECT " + projection + " FROM products";
		List<Map<String, Object>> products = this.template.queryForList(sql);
		if (products.size() == 0)
			return null;
		return products;
	}

	@Override
	public List<Product> getProductList(int offset, int size)  {
		String sql = "SELECT * FROM products LIMIT ?, ?";
		List<Product> products = this.template.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Product.class), offset, size);
		if (products.size() == 0)
			return null;
		return products; 
	}

	@Override
	public Product addProduct(final Product product) {
		// holder will be used to store the auto-generated ID
		KeyHolder holder = new GeneratedKeyHolder();

		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO products (description, size, price) VALUES (?, ?, ?)";
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, product.getDescription());
				ps.setString(2, product.getSize());
				ps.setFloat(3, product.getPrice());
				return ps;
			}
		}, holder);

		int newProductId = holder.getKey().intValue();
		product.setId(newProductId);
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		String sql = "UPDATE products SET description=?, size=?, price=? WHERE id=?";
		int updateCount = template.update(sql, product.getDescription(), product.getSize(), product.getPrice(),
				product.getId());
		if (updateCount == 0) {
			throw new DataNotFoundException("The product you want to update does not exist.");
		}
	}

	@Override
	public void deleteProduct(int productId) {
		String sql = "DELETE FROM products WHERE id=?";
		int deleteCount = template.update(sql, productId);
		if (deleteCount == 0) {
			throw new DataNotFoundException("The product you want to delete does not exist.");
		}
	}
}