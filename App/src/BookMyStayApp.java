import java.util.Scanner;

public class BookMyStayApp {

    // Centralized Room Data
    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("===================================");

        displayRooms();

        System.out.print("\nEnter room type to search: ");
        String search = sc.nextLine();

        searchRoom(search);
    }

    // Display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");

        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }

    // Search logic
    public static void searchRoom(String search) {
        boolean found = false;

        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(search)) {
                found = true;

                if (availability[i] > 0) {
                    System.out.println("✅ Room available! Count: " + availability[i]);
                } else {
                    System.out.println("❌ Room not available");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("⚠️ Room type not found");
        }
    }
}