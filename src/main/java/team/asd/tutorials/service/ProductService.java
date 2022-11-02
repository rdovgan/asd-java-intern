package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.tutorials.constants.ProductState;
import team.asd.tutorials.entities.IsProduct;
import team.asd.tutorials.exceptions.WrongProductException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductService implements IsProductService {
	@Override
	public @NonNull List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		if (productList.stream()
				.anyMatch(p -> p == null || StringUtils.isEmpty(p.getName()))) {
			throw new WrongProductException("Wrong product in the list");
		}
		return productList.stream()
				.map(IsProduct::getName)
				.collect(Collectors.toList());
	}

	@Override
	public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		return productList.stream()
				.filter(p -> p != null && p.getState() == ProductState.Created)
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException {
		Map<ProductState, Integer> productByState = Stream.of(ProductState.values())
				.collect(Collectors.toMap(Function.identity(), c -> 0));

		if (CollectionUtils.isEmpty(productList)) {
			return productByState;
		}
		if (productList.stream()
				.anyMatch(Objects::isNull)) {
			throw new WrongProductException("Wrong product in the list");
		}

		productByState.putAll(productList.stream()
				.collect(Collectors.groupingBy(IsProduct::getState, Collectors.collectingAndThen(Collectors.counting(), Long::intValue))));
		return productByState;
	}

	@Override
	public @NonNull List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		if (product == null) {
			throw new WrongProductException("Null product was provided");
		}
		return productList.stream()
				.filter(p -> product.getName() == null || product.getName()
						.equals(p.getName()))
				.filter(p -> product.getState() == null || product.getState() == p.getState())
				.collect(Collectors.toList());
	}
}
