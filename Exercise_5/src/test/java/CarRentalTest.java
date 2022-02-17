import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarRentalTest {
    private CarRental cr;

    //return true if Id is present in List
    @org.junit.Test
    public void searchId()
    {
        ArrayList<String> s=new ArrayList<>();
        s.add("1");
        s.add("2");
        boolean actualResult = cr.searchId(s,"1");
        assertTrue(actualResult);
    }

    @org.junit.Test
    public void checkDate() throws ParseException {

        String date1="12/02/2022";
        String date2="12/03/2022";

        boolean actualResult =cr.checkDate(date1,date2);
        //Return False
        //Date 1 occurs before Date 2
        assertFalse(actualResult);
    }
}