import java.util.ArrayList;

import static org.junit.Assert.*;

public class AuthorTest {
    private Author a;
    private Book b;
    @org.junit.Test
    public void getName()
    {
        ArrayList<Book> b= new ArrayList<>();
        b.add(new Book("The Hunger Games",2008,3.24));
        a=new Author("Suzanne Collins",b);
        assertEquals("Suzanne Collins",a.getName());
    }

    @org.junit.Test
    public void getBook() {
        ArrayList<Book> b= new ArrayList<>();
        b.add(new Book("The Hunger Games",2008,3.24));
        a=new Author("Suzanne Collins",b);
        assertEquals(b,a.getBook());// getBook() return ArrayList of Books
    }
}