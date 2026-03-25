import java.util.Scanner;

public class BookMyStayApp {

    // Centralized Room Data
    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};

    // Room number tracking
    static int[] roomNumbers = {101, 201, 301, 401};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("===================================");

        displayRooms();

        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room type to book: ");
        String room = sc.nextLine();

        bookRoom(name, room);

        System.out.println("\nUpdated Availability:");
        displayRooms();
    }

    // Display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");

        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }

    // Booking + Allocation
    public static void bookRoom(String name, String room) {
        boolean found = false;

        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(room)) {
                found = true;

                if (availability[i] > 0) {

                    int allocatedRoom = roomNumbers[i] + (5 - availability[i]);
                    availability[i]--;

                    System.out.println("\n✅ Booking Confirmed!");
                    System.out.println("Guest Name: " + name);
                    System.out.println("Room Type: " + roomTypes[i]);
                    System.out.println("Room Number: " + allocatedRoom);

                } else {
                    System.out.println("❌ Room not available");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("⚠️ Invalid room type");
        }
    }
}