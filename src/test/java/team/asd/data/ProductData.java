package team.asd.data;


import team.asd.constants.ProductState;
import team.asd.entities.IsProduct;
import team.asd.entities.TestProduct;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductData {

	private static final Integer PRODUCT_NUMBER_LIMIT = 1000000;

	private static Random random = new Random();

	public static IsProduct defineProduct(String name, ProductState state) {
		TestProduct product = new TestProduct();
		product.setName(name);
		product.setState(state);
		return product;
	}

	public static IsProduct defineDefaultProduct() {
		TestProduct product = new TestProduct();
		product.setName("TestProduct #" + (random.nextInt(PRODUCT_NUMBER_LIMIT - PRODUCT_NUMBER_LIMIT / 10) + PRODUCT_NUMBER_LIMIT / 10));
		product.setState(ProductState.Created);
		return product;
	}

	public static List<IsProduct> defineProductList(Integer limit) {
		if (limit == null || limit < 1) {
			limit = 1;
		}
		return Stream.generate(ProductData::defineDefaultProduct).limit(limit).collect(Collectors.toList());
	}
}
