package team.asd.entities;

import team.asd.constants.ProductState;

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
