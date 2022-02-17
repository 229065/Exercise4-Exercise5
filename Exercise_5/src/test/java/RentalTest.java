import org.junit.Test;

import static org.junit.Assert.*;

public class RentalTest {
    private Rental rental;
    private Customer customer;
    private Vehicle vehicle;
    @org.junit.Test
    public void getCustomer() {
        customer=new Customer(4561,"Sandy");
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        rental =new Rental(customer,vehicle,"12/02/2022","12/02/2022");
        assertEquals(customer,rental.getCustomer());
    }

    @org.junit.Test
    public void getVehicle() {
        customer=new Customer(4561,"Sandy");
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        rental =new Rental(customer,vehicle,"12/02/2022","12/02/2022");
        assertEquals(vehicle,rental.getVehicle());
    }

    @org.junit.Test
    public void getRental_start_date() {
        customer=new Customer(4561,"Sandy");
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        rental =new Rental(customer,vehicle,"12/02/2022","12/03/2022");
        assertEquals("12/02/2022",rental.getRental_start_date());
    }

    @org.junit.Test
    public void getRental_end_date() {
        customer=new Customer(4561,"Sandy");
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        rental =new Rental(customer,vehicle,"12/02/2022","12/03/2022");
        assertEquals("12/03/2022",rental.getRental_end_date());
    }
}