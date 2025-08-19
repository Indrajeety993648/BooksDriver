import java.util.*;

public class BookService {
    private List<Book> books;

    public BookService(List<Book> books) {
        this.books = books;
    }

    public int getTotalBooksByAuthor(String author) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author))
                count++;
        }
        return count;
    }

    public Set<String> getAllAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    public List<String> getBooksByAuthor(String author) {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                titles.add(book.getTitle());
            }
        }
        return titles;
    }

    public List<Book> getBooksByRating(double rating) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getUserRating() == rating) {
                result.add(book);
            }
        }
        return result;
    }

    public Map<String, Integer> getBookPricesByAuthor(String author) {
        Map<String, Integer> bookPrices = new LinkedHashMap<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                bookPrices.put(book.getTitle(), book.getPrice());
            }
        }
        return bookPrices;
    }
}
