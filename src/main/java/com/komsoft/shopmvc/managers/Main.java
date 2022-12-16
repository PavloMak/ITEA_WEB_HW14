package com.komsoft.shopmvc.managers;

import java.util.List;

import com.komsoft.shopmvc.model.Product;

public class Main {

	public static void main(String[] args) {
		ProductEntityManager manager = new ProductEntityManager();
		List<Product> productsList = manager.getAll("1");

		for (Product product : productsList) {
			System.out.println(product);
		}

	}

}
