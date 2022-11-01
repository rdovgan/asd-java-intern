package team.asd.tutorials.entities;

import team.asd.tutorials.constants.ProductState;
import team.asd.tutorials.entities.IsProduct;

public class TestProduct implements IsProduct {

	private String name;

	private ProductState state;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}
}
