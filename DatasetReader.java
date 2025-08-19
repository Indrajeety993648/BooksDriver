import java.io.*;
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
                return;
            String[] headers = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            Map<String, Integer> colIndex = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                colIndex.put(headers[i].trim().replace("\"", "").toLowerCase(), i);
            }
            String[] required = { "name", "author", "user rating", "reviews", "price", "year", "genre" };
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (fields.length != headers.length)
                    continue;
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
}
