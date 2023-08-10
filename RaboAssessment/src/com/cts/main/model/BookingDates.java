package com.cts.main.model;

import java.io.Serializable;

public class BookingDates implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkin;
	
	private String checkout;

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
}
