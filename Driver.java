import java.util.*;

public class Driver {
    public static void main(String[] args) {
        DatasetReader reader = new DatasetReader("data.csv");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.println("Books by " + author + ": " + reader.getBooksByAuthor(author));
        System.out.println("Total books by " + author + ": " + reader.getTotalBooksByAuthor(author));
        System.out.println("Prices of books by " + author + ": " + reader.getBookPricesByAuthor(author));

        System.out.print("Enter rating: ");
        double rating = sc.nextDouble();
        System.out.println("Books with rating " + rating + ": ");
        for (Book book : reader.getBooksByRating(rating)) {
            book.printDetails();
        }

        System.out.println("\nAll authors in the dataset:");
        for (String a : reader.getAllAuthors()) {
            System.out.println(a);
        }
    }
}
