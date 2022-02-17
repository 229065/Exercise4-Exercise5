import static org.junit.Assert.*;

public class VehicleTest {
    private Vehicle vehicle;
    @org.junit.Test
    public void getVehicleId() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals(1,vehicle.getVehicleId());
    }

    @org.junit.Test
    public void getFlag() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals(0,vehicle.getFlag());
    }

    @org.junit.Test
    public void getBrand() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals("Audi",vehicle.getBrand());
    }

    @org.junit.Test
    public void getModel() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals("Q7",vehicle.getModel());
    }

    @org.junit.Test
    public void getNo_Of_Seats() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals(6,vehicle.getNo_Of_Seats());
    }

    @org.junit.Test
    public void getLicence_plate() {
        vehicle = new Vehicle(1,"Audi","Q7",6,12345,0);
        assertEquals(12345,vehicle.getLicence_plate());
    }
}