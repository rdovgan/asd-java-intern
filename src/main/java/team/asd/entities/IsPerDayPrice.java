package team.asd.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IsPerDayPrice {

	BigDecimal getPrice();

	LocalDate getDate();

	String getCurrency();

}
