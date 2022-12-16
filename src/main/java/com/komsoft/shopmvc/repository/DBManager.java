package com.komsoft.shopmvc.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.komsoft.shopmvc.model.Category;
import com.komsoft.shopmvc.model.Product;
import com.komsoft.shopmvc.model.User;

import java.sql.PreparedStatement;

public class DBManager {

	private DBConnector conection = new DBConnector();

	private static final String INSERT_USER = "INSERT INTO users (login, password, full_name, region, gender, comment) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String GET_FULL_NAME = "SELECT full_name FROM users WHERE login = ? AND password= ?;";
	private static final String GET_ALL_PRODUCT = "SELECT products.id AS p_id, products.name AS p_name, products.description, products.price, categories.id AS c_id, categories.name AS c_name FROM products JOIN categories ON products.category_id=categories.id";
	private static final String GET_ALL_PRODUCT_BY_Category = "SELECT products.id AS p_id, products.name AS p_name, products.description, products.price, categories.id AS c_id, categories.name AS c_name FROM products JOIN categories ON products.category_id=categories.id WHERE categories.id=?";
	private static final String GET_ONE_PRODUCT = "SELECT products.id AS p_id, products.name AS p_name, products.description, products.price, categories.id AS c_id, categories.name AS c_name FROM products JOIN categories ON products.category_id=categories.id WHERE products.id=?";
	private User user = null;

	public void createBD() {
		Connection conn = null;
		conn = conection.getConnection();
		if (conn != null) {
			Statement st = null;
			try {
				st = conn.createStatement();

				st.execute(
						"CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, login VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(100) NOT NULL, full_name VARCHAR(50) NOT NULL,"
								+ " region VARCHAR(30) NOT NULL, gender VARCHAR(10) NOT NULL, comment VARCHAR(200));");

				st.execute(
						"CREATE TABLE IF NOT EXISTS categories (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL UNIQUE);");

				st.execute(
						"CREATE TABLE IF NOT EXISTS products (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL UNIQUE, description VARCHAR(500), price DOUBLE NOT NULL,"
								+ " category_id INT NOT NULL, FOREIGN KEY (category_id) REFERENCES categories(id));");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}
	}

	public void addUserToBD(String login, String password, String fullName, String region, String gender,
			String comment) {
		Connection conn = null;
		conn = conection.getConnection();
		if (conn != null) {
			PreparedStatement st = null;
			try {

				st = conn.prepareStatement(INSERT_USER);
				st.setString(1, login);
				st.setString(2, password);
				st.setString(3, fullName);
				st.setString(4, region);
				st.setString(5, gender);
				st.setString(6, comment);
				st.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}
	}

	public boolean getFullNameByLoginAndPassword(String login, String password) {

		Connection conn = null;
		conn = conection.getConnection();

		String fullName = null;

		if (conn != null && login != null && password != null) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {

				st = conn.prepareStatement(GET_FULL_NAME);
				st.setString(1, login);
				st.setString(2, password);
				rs = st.executeQuery();

				if (rs.next()) {
					fullName = rs.getString("full_name");
					user = new User();
					user.setLogin(login);
					user.setName(fullName);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}

		return (user == null) ? false : true;
	}

	public User getUser() {
		return user;
	}

	public List<Product> getAllProduct(String category) {

		Connection conn = null;
		conn = conection.getConnection();

		List<Product> result = new ArrayList<>();

		if (conn != null) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {

				PreparedStatement statement = conn
						.prepareStatement(category != null ? GET_ALL_PRODUCT_BY_Category : GET_ALL_PRODUCT);
				if (category != null) {
					statement.setInt(1, Integer.parseInt(category));
				}
				ResultSet products = statement.executeQuery();
				while (products.next()) {
					result.add(new Product().setId(products.getLong("p_id")).setName(products.getString("p_name"))
							.setDescription(products.getString("description")).setPrice(products.getDouble("price"))
							.setCategory(new Category().setId(products.getLong("c_id"))
									.setName(products.getString("c_name"))));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}

		return result;
	}

	public Product getOneProduct(String productId) {

		Connection conn = null;
		conn = conection.getConnection();

		Product result = null;

		if (conn != null) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {

				PreparedStatement statement = conn.prepareStatement(GET_ONE_PRODUCT);
				if (productId != null) {
					statement.setInt(1, Integer.parseInt(productId));
				}
				ResultSet products = statement.executeQuery();
				while (products.next()) {
					result = new Product().setId(products.getLong("p_id")).setName(products.getString("p_name"))
							.setDescription(products.getString("description")).setPrice(products.getDouble("price"))
							.setCategory(new Category().setId(products.getLong("c_id"))
									.setName(products.getString("c_name")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("DBManager returned null conection.");
		}

		return result;
	}

}
