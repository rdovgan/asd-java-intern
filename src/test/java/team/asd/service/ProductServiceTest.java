package team.asd.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.constants.ProductState;
import team.asd.data.ProductData;
import team.asd.engine.ServicesScannerUtils;
import team.asd.entities.IsProduct;
import team.asd.entities.TestProduct;
import team.asd.exceptions.WrongProductException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for IsProductService implementations")
public class ProductServiceTest {

	private static Stream<IsProductService> defineProductServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsProductService>> classes = reflections.getSubTypesOf(IsProductService.class);
		ServicesScannerUtils<IsProductService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	//List<String> defineProductNames(List<IsProduct> productList);

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductNames(IsProductService productService) throws WrongProductException {
		final int productCount = 19;
		assertEquals(productCount, productService.defineProductNames(ProductData.defineProductList(productCount)).size(), "Wrong product's name list size");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductNamesWithOneNullElement(IsProductService productService) {
		final int productCount = 2;
		List<IsProduct> productList = ProductData.defineProductList(productCount);
		productList.add(null);
		assertThrows(WrongProductException.class, () -> productService.defineProductNames(productList),
				"Wrong product's name list size. Null objects should be ignored");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductNamesWithNullParameter(IsProductService productService) throws WrongProductException {
		assertEquals(0, productService.defineProductNames(null).size(), "Wrong product's name list size. Null objects should be ignored");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductNamesWithEmptyListParameter(IsProductService productService) throws WrongProductException {
		assertEquals(0, productService.defineProductNames(new ArrayList<>()).size(), "Wrong product's name list size. Null objects should be ignored");
	}

	//List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList);

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductsWithCreatedState(IsProductService productService) {
		final int createdProductSize = 5;
		List<IsProduct> productList = ProductData.defineProductList(createdProductSize - 1);
		IsProduct finalProduct = ProductData.defineProduct("Final product", ProductState.Final);
		IsProduct suspendedProduct = ProductData.defineProduct("Suspended product", ProductState.Suspended);
		IsProduct createdProduct = ProductData.defineProduct("Created product", ProductState.Created);
		productList.addAll(Arrays.asList(finalProduct, suspendedProduct, createdProduct, null));
		assertEquals(createdProductSize, productService.defineProductsWithCreatedState(productList).size(), "Wrong product's list size.");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testDefineProductsWithCreatedStateWithNullParameter(IsProductService productService) {
		assertEquals(0, productService.defineProductsWithCreatedState(null).size(), "Wrong product's list size.");
	}

	//Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException;

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testCalculateProductCountByStateWithNullParameter(IsProductService productService) throws WrongProductException {
		final int productStateCount = ProductState.values().length;
		assertEquals(productStateCount, productService.calculateProductCountByState(null).size(),
				"Should be map with each TestProduct State as a key and zero values");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testCalculateProductCountByState(IsProductService productService) throws WrongProductException {
		final int productStateCount = ProductState.values().length;
		final int productCountWithCreatedState = 5;
		final int productCountWithFinalState = 2;
		final int productCountWithDeprecatedState = 8;
		final int totalProductsCount = productCountWithCreatedState + productCountWithFinalState + productCountWithDeprecatedState;

		List<IsProduct> createdProducts = ProductData.defineProductList(productCountWithCreatedState);
		List<IsProduct> finalProducts = IntStream.range(0, productCountWithFinalState)
				.mapToObj(number -> ProductData.defineProduct("Final " + number, ProductState.Final)).collect(Collectors.toList());
		List<IsProduct> deprecatedProducts = IntStream.range(0, productCountWithDeprecatedState)
				.mapToObj(number -> ProductData.defineProduct("Deprecated " + number, ProductState.Deprecated)).collect(Collectors.toList());

		Map<ProductState, Integer> resultMap = productService.calculateProductCountByState(
				Stream.of(createdProducts, finalProducts, deprecatedProducts).flatMap(Collection::stream).collect(Collectors.toList()));
		assertEquals(productCountWithCreatedState, resultMap.get(ProductState.Created), "Incorrect count of Created products");
		assertEquals(totalProductsCount, resultMap.values().stream().reduce(0, Integer::sum), "Incorrect count of product in map");
		assertEquals(productCountWithFinalState, resultMap.get(ProductState.Final), "Incorrect count of Final products");
		assertEquals(0, resultMap.get(ProductState.Suspended), "Incorrect count of Suspended products");
	}

	//List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException;

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testFilterProductsByProvidedObjectWithNullParameter(IsProductService productService) throws WrongProductException {
		assertThrows(WrongProductException.class, () -> productService.filterProductsByProvidedObject(ProductData.defineProductList(2), null),
				"Exception should be thrown when null product was provided");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testFilterProductsByProvidedObjectWithNullProductsList(IsProductService productService) throws WrongProductException {
		assertEquals(0, productService.filterProductsByProvidedObject(null, new TestProduct()).size(),
				"Empty list should be return if null product list was provided");
		assertEquals(0, productService.filterProductsByProvidedObject(null, null).size(), "Empty list should be return if null product list was provided");
	}

	@ParameterizedTest
	@MethodSource("defineProductServices")
	public void testFilterProductsByProvidedObject(IsProductService productService) throws WrongProductException {
		int productStateCount = ProductState.values().length;

		final int productCountWithCreatedState = 5;
		final int productCountWithFinalState = 9;
		final int productCountWithDeprecatedState = 3;
		final int productCountWithSuspendedState = 2;

		final int totalProductsCount =
				productCountWithCreatedState + productCountWithFinalState + productCountWithDeprecatedState + productCountWithSuspendedState;

		final String defaultNameForDeprecatedProducts = "DEPRECATED. DON'T USE IT";

		List<IsProduct> createdProducts = ProductData.defineProductList(productCountWithCreatedState);
		List<IsProduct> finalProducts = IntStream.range(0, productCountWithFinalState)
				.mapToObj(number -> ProductData.defineProduct("Final " + number, ProductState.Final)).collect(Collectors.toList());
		List<IsProduct> deprecatedProducts = IntStream.range(0, productCountWithDeprecatedState)
				.mapToObj(number -> ProductData.defineProduct(defaultNameForDeprecatedProducts, ProductState.Deprecated)).collect(Collectors.toList());
		List<IsProduct> suspendedProducts = IntStream.range(0, productCountWithSuspendedState)
				.mapToObj(number -> ProductData.defineProduct(defaultNameForDeprecatedProducts, ProductState.Suspended)).collect(Collectors.toList());

		List<IsProduct> mergedProductList = Stream.of(createdProducts, finalProducts, deprecatedProducts, suspendedProducts).flatMap(Collection::stream)
				.collect(Collectors.toList());

		assertEquals(totalProductsCount, productService.filterProductsByProvidedObject(mergedProductList, new TestProduct()).size(),
				"Should be map with each TestProduct State as a key and zero values");

		IsProduct productWithStandardNameForDeprecatedItems = ProductData.defineProduct(defaultNameForDeprecatedProducts, null);
		assertEquals(productCountWithDeprecatedState + productCountWithSuspendedState,
				productService.filterProductsByProvidedObject(mergedProductList, productWithStandardNameForDeprecatedItems).size(),
				"Incorrect products count on name filtering");

		IsProduct productWithCreatedState = ProductData.defineProduct(null, ProductState.Created);
		assertEquals(productCountWithCreatedState, productService.filterProductsByProvidedObject(mergedProductList, productWithCreatedState).size(),
				"Incorrect product count on state filtering");

		IsProduct firstFinalProduct = ProductData.defineProduct("Final 1", ProductState.Final);
		assertEquals(1, productService.filterProductsByProvidedObject(mergedProductList, firstFinalProduct).size(),
				"Incorrect product count on name and state filtering");
	}

}
