package team.asd.tutorials.entities;

import java.time.LocalDate;

public interface IsTax {

	Integer getId();

	Integer getAccountId();

	Integer getPartyId();

	Integer getProductId();

	String getName();

	String getState();

	String getType();

	String getCurrency();

	String getNote();

	Double getAmount();

	LocalDate getDate();

}
