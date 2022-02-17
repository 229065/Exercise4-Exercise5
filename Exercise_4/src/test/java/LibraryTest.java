import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;

public class LibraryTest  {
    private Book b1,b2;

    @Test
    public void compare() {
        b1=new Book("To Kill a Mockingbird",2005,3.24);
        b2=new Book("Divergent",2003,1.24);
        ArrayList<Book> b = new ArrayList<>();
        b.add(b1);
        b.add(b2);
        Collections.sort(b,new Library());
        assertEquals(b2,b.get(0));//b.get(0) returns value of first element in array after sorting
    }
}