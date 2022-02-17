//@author Vinod Muley
import java.io.*;
import java.util.*;

class Author
{
    private String name;
    private ArrayList<Book> book;

    public Author(String name,ArrayList<Book> book)
    {
        this.name=name;
        this.book=book;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Book> getBook()// Return ArrayList of Books
    {
        return book;
    }
}
class Book
{
    private String title;
    private int year;
    private double averageRating;

    public Book()
    {
        this.title="";
        this.year=0;
        this.averageRating=0;
    }

    public Book(String title,int year,double averageRating)
    {
        this.title=title;
        this.year=year;
        this.averageRating=averageRating;
    }

    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public double getAverageRating() {
        return averageRating;
    }
}

//Comparing Object of Books Class to Sort by Year
class SortByYear implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getYear() - b2.getYear();
    }
}

//Comparing Object of Books Class to Sort by Ratings
class SortByRatings implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        if (b1.getAverageRating() < b2.getAverageRating())
            return -1;
        if (b1.getAverageRating() > b2.getAverageRating())
            return 1;
        return 0;
    }
}
public class Library implements Comparator<Book>
{
    //Remove Duplicate From Map<Author, ArrayList<Book>>
    private static void removeDuplicates(Map<Author, ArrayList<Book>> sample) {
        Collection<ArrayList<Book>> list = sample.values();
        for(Iterator<ArrayList<Book>> itr = list.iterator(); itr.hasNext();) {
            if(Collections.frequency(list, itr.next())>1) {
                itr.remove();
            }
        }
    }

    //Reading file from Resource Section
    public static BufferedReader readFile(String fileName) throws FileNotFoundException {

        String path ="src/main/resources/"+fileName;
        BufferedReader br = new BufferedReader(new FileReader(path));
        return br;
    }

    //Comparing Object of Books Class to Sort by Title
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }

    public static void main(String[] args) {
        BufferedReader booksFile = null;
        try {
            booksFile = readFile("Books.csv");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        ArrayList<Book> b = new ArrayList<>();

        ArrayList<String> authorName = new ArrayList<>();

        Author author;
        Book book;

        //Parsing Data From Books.csv
        String line;
        int k = 0;
        try {
            while ((line = booksFile.readLine()) != null) {
                if (k != 0) {
                    String details[] = line.split(";");
                    //Spliting Author Name by ","
                    if (details[6].contains(",")) {
                        String repetedName[] = details[6].split(",");
                        for (int i = 0; i < repetedName.length; i++) {
                            authorName.add(repetedName[i]);
                            book = new Book(details[8], (int) Double.parseDouble(details[7]), Double.parseDouble(details[10]));
                            b.add(book);
                        }
                    } else {
                        authorName.add(details[6]);
                        book = new Book(details[8], (int) Double.parseDouble(details[7]), Double.parseDouble(details[10]));
                        b.add(book);
                    }
                }
                k++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //HashMap Store Author And List of Books
        Map<Author, ArrayList<Book>> authorBooksMap = new HashMap<>();

        ArrayList<Book> ab = null;//Initializing ArrayList
        for (int i = 0; i < authorName.size(); i++) {
            String tempAuthorName = authorName.get(i);

            ab = new ArrayList<>();//store list of book by author
            for (int j = 0; j < authorName.size(); j++) {

                if (tempAuthorName.equals(authorName.get(j))) {
                    ab.add(b.get(j));
                }
            }
            author = new Author(tempAuthorName, ab);//Creating Author Class Object
            authorBooksMap.put(author, ab);//store auhtor name and list of books in map
        }

        //Removing Duplicate Values From List
        removeDuplicates(authorBooksMap);

        Scanner sc = new Scanner(System.in);
        int temp=0;
        while (temp!=8) {
            System.out.println("" +
                    "1. Sorted By Book Title \n" +
                    "2. Sorted By Publication Date \n" +
                    "3. Sorted By Rating \n" +
                    "4. Given a book, know who is the author \n" +
                    "5. How many books has an author written \n" +
                    "6. Given a Date, what are the books written in that year \n" +
                    "7. who is the most prolific author? \n" +
                    "8. Exit"
            );
            //Take Option input from user
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    //Sorted By Book Title
                    System.out.println("Sorted By Book Title: ");
                    for(Author author1 : authorBooksMap.keySet())
                    {
                        //Calling compare Function in Library Class
                        Collections.sort(author1.getBook(), new Library());
                        System.out.println("Author : "+author1.getName());
                        System.out.println("Books : ");
                        for(int i=0;i<author1.getBook().size();i++)
                        {
                            System.out.println("  "+(i+1)+" "+author1.getBook().get(i).getTitle());
                        }
                        System.out.println("---------------------------------");
                    }
                    break;
                case 2:
                    //Sorted By Publication Date:
                    System.out.println("Sorted By Publication Year: ");
                    for(Author author1 : authorBooksMap.keySet())
                    {
                        //Calling compare Function in sortByYear Class
                        Collections.sort(author1.getBook(), new SortByYear());
                        System.out.println("Author : "+author1.getName());
                        System.out.println("Books : Publication Year");
                        for(int i=0;i<author1.getBook().size();i++)
                        {
                            System.out.println("  "+(i+1)+" "+author1.getBook().get(i).getTitle()+" : "+author1.getBook().get(i).getYear());
                        }
                        System.out.println("---------------------------------");
                    }
                    break;
                case 3:
                    //Sorted By Rating:
                    System.out.println("Sorted By Rating: ");
                    for(Author author1 : authorBooksMap.keySet())
                    {
                        //Calling compare Function in SortByRatings Class
                        Collections.sort(author1.getBook(), new SortByRatings());
                        System.out.println("Author : "+author1.getName());
                        System.out.println("Books : Ratings");
                        for(int i=0;i<author1.getBook().size();i++)
                        {
                            System.out.println("  "+(i+1)+" "+author1.getBook().get(i).getTitle()+" : "+author1.getBook().get(i).getAverageRating());
                        }
                        System.out.println("---------------------------------");
                    }
                    break;
                case 4:
                    //4. Given a book, know who is the author
                    //Initializing Scanner Class
                    Scanner sc1 = new Scanner(System.in);

                    //Input Name Of Book
                    System.out.println("Enter Name Of Book: ");
                    String bookName = sc1.nextLine();//Store Input

                    //Initializing authorNameList
                    ArrayList<String> authorNameList =new ArrayList<>();
                    for (Author author1 : authorBooksMap.keySet()) {
                            for (int i = 0; i < author1.getBook().size(); i++) {
                                if (author1.getBook().get(i).getTitle().toLowerCase().trim().equals(bookName.toLowerCase().trim())) { //Comparing Book name in list to User Input
                                    authorNameList.add(author1.getName());
                                }
                            }
                    }
                    //Printing Author of Book
                    System.out.println("Authors : ");
                    int c=1;
                    for(String aName : authorNameList)
                    {
                        System.out.println(" "+c+" "+aName);
                        c++;
                    }
                    System.out.println("---------------------------------");
                    break;
                case 5:
                    //5. How many books has an author written
                    for(Author author1 : authorBooksMap.keySet())
                    {
                        System.out.println("Author : "+author1.getName()+" | Book Count : "+author1.getBook().size());
                        System.out.println("---------------------------------");
                    }
                    break;
                case 6:
                    //6. Given a Date, what are the books written in that year
                    System.out.println("Enter Year: ");
                    sc = new Scanner(System.in);

                    //Input Year
                    int year = sc.nextInt();

                    //Initializing authorNameList
                    ArrayList<String> booksNameList =new ArrayList<>();
                    for (Author author1 : authorBooksMap.keySet()) {
                        for (int i = 0; i < author1.getBook().size(); i++) {
                            if (author1.getBook().get(i).getYear()==year) { //Comparing Book name in list to User Input
                                booksNameList.add(author1.getBook().get(i).getTitle());
                            }
                        }
                    }
                    //Printing booksNameList
                    System.out.println("Books : ");
                    int l=1;
                    for(String bName : booksNameList)
                    {
                        System.out.println(" "+l+" "+bName);
                        l++;
                    }
                    System.out.println("---------------------------------");
                    break;
                case 7:
                    //7. who is the most prolific author?

                    //Initializing ArrayList
                    ArrayList<String> aNameList =new ArrayList<>();

                    //Mapping Authorname and Book Count
                    Map<String, Integer> bookCount = new HashMap<>();
                    for(Author author1 : authorBooksMap.keySet())
                    {
                        bookCount.put(author1.getName(),author1.getBook().size());
                    }
                    //Sorting Map in Descending Order by value
                    Map<String, Integer> result = new LinkedHashMap<>();
                    bookCount.entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

                    int highestCount=0;
                    for(String anl : result.keySet())
                    {
                        if(highestCount==0) {
                            highestCount = result.get(anl);
                        }
                        if(result.get(anl)==highestCount)
                        {
                            aNameList.add(anl);
                        }
                    }
                    System.out.println("Highest Book Count: "+highestCount);
                    System.out.println("Author : ");
                    //Printing aNameList
                    for(String name : aNameList)
                    {
                        System.out.println(name);
                    }
                    break;
                case 8:
                    System.out.println("Thank you for Visiting Library...!");
                    temp=8;
                    break;
                default:
                    System.out.println("Try Again");
                    break;
            }
        }
    }
}



