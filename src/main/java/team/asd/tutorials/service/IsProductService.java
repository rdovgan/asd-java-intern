package team.asd.tutorials.service;

import lombok.NonNull;
import team.asd.tutorials.constants.ProductState;
import team.asd.tutorials.entities.IsProduct;
import team.asd.tutorials.exceptions.WrongProductException;

import java.util.List;
import java.util.Map;

public interface IsProductService {

	/**
	 * Defines list of names from provided list of Products.
	 *
	 * @param productList provided list of products
	 * @return a list of product names
	 * @throws WrongProductException in case when wrong product was provided
	 */
	@NonNull
	List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException;

	/**
	 * Filters a list of product items and return a list of Created ones.
	 *
	 * @param productList list of products
	 * @return list of products with `Created` state
	 */
	List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList);

	/**
	 * Calculates count of products by each state.
	 *
	 * @param productList list of product items
	 * @return map of product count count by state
	 * @throws WrongProductException in case when wrong product entity was provided
	 * @see ProductState
	 */
	@NonNull
	Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException;

	/**
	 * Filters a list of product items by product name and state that provided in a separate object.
	 * If any of fields is null, do not need to filter by this field.
	 *
	 * @param productList list of products
	 * @param product     provided object
	 * @return list of products that equals to provided product
	 * @throws WrongProductException is provided object was null
	 */
	@NonNull
	List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException;

}
