package com.komsoft.shopmvc.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.komsoft.shopmvc.model.Product;

public class ProductEntityManager {
	EntityManager manager = Persistence.createEntityManagerFactory("products").createEntityManager();

	public Product add(Product product) {

		manager.getTransaction().begin();
		Product dbProduct = manager.merge(product);
		manager.getTransaction().commit();
		return dbProduct;

	}

	public Product getPudgeById(int id) {
		return manager.find(Product.class, id);
	}

	public List<Product> getAll() {
		TypedQuery<Product> namedQuery = manager.createNamedQuery("Product.getAll", Product.class);
		return namedQuery.getResultList();
	}

	public List<Product> getAll(String id) {
		TypedQuery<Product> namedQuery = null;
		if (id.equals(null)) {
			namedQuery = manager.createNamedQuery("Product.getAll", Product.class);
		} else {
			namedQuery = manager.createNamedQuery("Product.getAllByCategory", Product.class);
			namedQuery.setParameter("catId", id);
		}
		return namedQuery.getResultList();
	}

}
