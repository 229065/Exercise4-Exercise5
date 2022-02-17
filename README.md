# Exercise4-Exercise5

### Exercise 4  
   This project is about the Library System 
   
   #### Features :
  1. Sorted By Book Title 
  2. Sorted By Publication Date 
  3. Sorted By Rating 
  4. Given a book, know who is the author 
  5. How many books has an author written 
  6. Given a Date, what are the books written in that year
  7. who is the most prolific author? 
   
 This Exercise includes 5 Classes :  
 - `Author` :
         Define the  Name of the Author and ArrayList Books written by the Author.
 - `Book` :
         Define Title, Year, averageRating of Book
 - `SortByYear` :
         SortByYear class implement Comparator interface to sort ArrayList<Book> of the book by Year
 - `SortByRatings` :
         SortByYear class implement Comparator interface to sort ArrayList<Book> of the book by Ratings
 - `Library` :
   Library class implement Comparator interface to sort ArrayList<Book> of the book by Title
      
   Methods :
   
      - `removeDuplicates()` : 
               This method takes HashMap as a Parameter and removes duplicate data from Map.
      - `readFile()` :
               This method takes Filename as parameter and return Object of BufferedReader class;
      - `compare()` :
               This method Override from Comparator interface to sort ArrayList<> of the book by Title

### Exercise 5
   There are 3 classes in Shapes.java `Rectangle`, `Circle`, `Square` which includes the following functions :
   - `perimeter()` :
                  Calculate Perimeter of Circle, Rectangle and Square.                 
## Instructions

### IntelliJ

1. Open IntelliJ IDEA and select File > Open....
2. Choose the Exercise1ToExercise3 directory and click OK.
3. Select Exercise from Project Menu
4. Select `File` > Settings... > `Build ,Execution, Deployment` > Build Tools
5. Inside `Build Tools` Select `Maven` > `Runner`
6. Check `Delegate IDE build/run actions to Maven checkbox`
7. Click on Apply and then Ok
8. Go to Main Project 
9. Select Build > `Build Project`
   

## Requirements
- [Java version: 14.0.1](https://www.javatpoint.com/javafx-how-to-install-java)
- [Junit 4.13.1](https://www.jetbrains.com/help/idea/junit.html)
- [Apache Maven 3.8.4](https://www.javatpoint.com/how-to-install-maven)

## Resources
- [Java Comparator Interface](https://www.javatpoint.com/Comparator-interface-in-collection-framework)
- [JUnit](https://www.javatpoint.com/junit-tutorial)
- [CSVWriter class](https://www.tutorialspoint.com/how-to-write-data-to-csv-file-in-java)

## Issues
   If you find a bug, Please let me know by submitting an issue.
   
## Author
- @Vinod Muley
