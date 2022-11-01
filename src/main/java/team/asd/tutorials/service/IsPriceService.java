package team.asd.tutorials.service;


import lombok.NonNull;
import team.asd.tutorials.entities.IsPerDayPrice;
import team.asd.tutorials.entities.IsPrice;
import team.asd.tutorials.exceptions.WrongPriceException;

import java.math.BigDecimal;
import java.util.List;

public interface IsPriceService {

	/**
	 * Defines an average value of provided per-day price list.
	 *
	 * @param prices list of prices
	 * @return an average price
	 * @throws WrongPriceException in case when date of two prices are equals or wrong price item was provided.
	 */
	@NonNull
	BigDecimal defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException;

	/**
	 * Defines an average value of provided price list.
	 * Amount will be calculated based on price duration.
	 * * For example, average amount for two prices (value=100, duration=3) and (value=200, duration=1) will be (100+100+100+200) / 4 = 125
	 *
	 * @param prices list of prices
	 * @return an average price per days
	 * @throws WrongPriceException in case when price dates are collides or wrong price item was provided.
	 */
	@NonNull
	BigDecimal defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException;

}
