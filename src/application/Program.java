package application;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Invoice;
import entities.Vehicle;
import service.AngolaTaxService;
import service.RentalService;

public class Program {
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:ss");
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter rental data");
			
			System.out.print("Car model: ");
			String carModel = sc.nextLine();
			System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
			Date start = sdf.parse(sc.nextLine());
			System.out.print("Return (dd/MM/yyyy hh:ss): ");
			Date finish = sdf.parse(sc.nextLine());
			
			CarRental car = new CarRental(start, finish, new Vehicle(carModel), new Invoice());
			
			System.out.print("Enter price per hour: ");
			double pricePerHour = sc.nextDouble();
			System.out.print("Enter price per day: ");
			double pricePerDay = sc.nextDouble();
			
			RentalService rs = new RentalService(pricePerHour, pricePerDay, new AngolaTaxService());
			
			rs.processInvoice(car);
			System.out.println();
			System.out.println("INVOICE");
			System.out.println("Basic payment: "+String.format("%.2f", car.getInvoice().getBasicPayment()));
			System.out.println("Tax: "+String.format("%.2f", car.getInvoice().getTax()));
			System.out.println("Total payment: "+String.format("%.2f", car.getInvoice().total()));
		}
		catch(ParseException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		sc.close();

	}

}
