import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    private Customer c;
    @org.junit.Test
    public void getId() {
        c=new Customer(4561,"Sandy");
        assertEquals(4561,c.getId());
    }

    @org.junit.Test
    public void getCustomerName() {
        c=new Customer(4561,"Sandy");
        assertEquals("Sandy",c.getCustomerName());
    }
}