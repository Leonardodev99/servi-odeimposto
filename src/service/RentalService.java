package service;

import entities.CarRental;
import entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	
	private AngolaTaxService taxService;

	public RentalService() {
		
	}

	public RentalService(Double pricePerHour, Double pricePerDay, AngolaTaxService taxService) {
		
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public AngolaTaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(AngolaTaxService taxService) {
		this.taxService = taxService;
	}
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2= carRental.getFinish().getTime();
		double duration = (double) (t2-t1)/1000/60/60;
		int x = (int) Math.ceil(duration);
		double basicPayment;
		if(x<=12) {
			basicPayment = pricePerHour*x;
		}
		else {
			basicPayment = pricePerDay*(x/24);
		}
		double tax = taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
	

}
