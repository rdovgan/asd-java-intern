package team.asd.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestPerDayPrice implements IsPerDayPrice {

	private BigDecimal price;

	private LocalDate date;

	private String currency;

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
