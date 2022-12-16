package com.komsoft.shopmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "name")
	private String name;

	public Category() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category setId(long id) {
		this.id = id;
		return this;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
