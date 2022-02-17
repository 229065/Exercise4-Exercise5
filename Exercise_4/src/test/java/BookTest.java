import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    private Book b;
    @Test
    public void getTitle()
    {
        b=new Book("To Kill a Mockingbird",2005,3.24);
        assertEquals("To Kill a Mockingbird",b.getTitle());
    }

    @Test
    public void getYear()
    {
        b=new Book("The Fault in Our Stars",1925,4.24);
        assertEquals(1925,b.getYear());
    }

    @Test
    public void getAverageRating()
    {
        b=new Book("The Fault in Our Stars",1925,3.20);
        assertEquals(3.20,b.getAverageRating(),0);
    }
}