public class BookMyStayApp {

    // Centralized Room Data
    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};

    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("===================================");

        displayRooms();
    }

    // Method to display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");

        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }
}