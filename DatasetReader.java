import java.io.*;
// Import Book class
// Book class is in the default package, no import needed
import java.util.*;

public class DatasetReader {
    private List<Book> books;

    public DatasetReader(String csvFilePath) {
        books = new ArrayList<>();
        readCSV(csvFilePath);
    }

    private void readCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();
            if (line == null)
                return; // Empty file
            // Read header and map column names to indices
            String[] headers = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            Map<String, Integer> colIndex = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                colIndex.put(headers[i].trim().replace("\"", "").toLowerCase(), i);
            }

            // Required columns
            String[] required = { "name", "author", "user rating", "reviews", "price", "year", "genre" };
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (fields.length != headers.length)
                    continue; // Skip malformed lines
                try {
                    String title = fields[colIndex.get("name")].replace("\"", "");
                    String author = fields[colIndex.get("author")].replace("\"", "");
                    double userRating = Double.parseDouble(fields[colIndex.get("user rating")]);
                    int reviews = Integer.parseInt(fields[colIndex.get("reviews")]);
                    int price = Integer.parseInt(fields[colIndex.get("price")]);
                    int year = Integer.parseInt(fields[colIndex.get("year")]);
                    String genre = fields[colIndex.get("genre")].replace("\"", "");
                    books.add(new Book(title, author, userRating, reviews, price, year, genre));
                } catch (Exception e) {
                    // Skip malformed lines
                    continue;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    // 1. Total number of books by an author
    public int getTotalBooksByAuthor(String author) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author))
                count++;
        }
        return count;
    }

    // 2. All authors in the dataset
    public Set<String> getAllAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    // 3. Names of all books by an author
    public List<String> getBooksByAuthor(String author) {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                titles.add(book.getTitle());
            }
        }
        return titles;
    }

    // 4. Classify with a user rating
    public List<Book> getBooksByRating(double rating) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getUserRating() == rating) {
                result.add(book);
            }
        }
        return result;
    }

    // 5. Price of all books by an author
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
