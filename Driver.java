import java.util.*;

// BookService handles all queries
public class Driver {
    public static void main(String[] args) {
        DatasetReader reader = new DatasetReader("data.csv");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        BookService service = new BookService(reader.getBooks());
        System.out.println("Books by " + author + ": " + service.getBooksByAuthor(author));
        System.out.println("Total books by " + author + ": " + service.getTotalBooksByAuthor(author));
        System.out.println("Prices of books by " + author + ": " + service.getBookPricesByAuthor(author));

        System.out.print("Enter rating: ");
        double rating = sc.nextDouble();
        System.out.println("Books with rating " + rating + ": ");
        for (Book book : service.getBooksByRating(rating)) {
            book.printDetails();
        }

        System.out.println("\nAll authors in the dataset:");
        for (String a : service.getAllAuthors()) {
            System.out.println(a);
        }
        sc.close();
    }
}
