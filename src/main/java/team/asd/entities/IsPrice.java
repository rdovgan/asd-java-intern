package team.asd.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IsPrice {

	BigDecimal getPrice();

	LocalDate getFromDate();

	LocalDate getToDate();

	String getCurrency();

}
