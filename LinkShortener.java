import java.util.*;

public class LinkShortener {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> urlMap;
    private Map<String, String> reverseMap;
    private Random random;

    public LinkShortener() {
        urlMap = new HashMap<>();
        reverseMap = new HashMap<>();
        random = new Random();
    }

    // Generate a short URL
    public String shortenURL(String longURL) {
        if (reverseMap.containsKey(longURL)) {
            return reverseMap.get(longURL);
        }

        String shortURL;
        do {
            shortURL = generateShortURL();
        } while (urlMap.containsKey(shortURL));

        urlMap.put(shortURL, longURL);
        reverseMap.put(longURL, shortURL);
        return shortURL;
    }

    // Expand a short URL
    public String expandURL(String shortURL) {
        return urlMap.getOrDefault(shortURL, "Invalid short URL");
    }

    // Generate a random short URL
    private String generateShortURL() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter long URL: ");
                    String longURL = scanner.nextLine();
                    String shortURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.print("Enter short URL: ");
                    String shortInput = scanner.nextLine();
                    String expandedURL = linkShortener.expandURL(shortInput);
                    System.out.println("Original URL: " + expandedURL);
                    break;
                case 3:
                    System.out.println("....");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
