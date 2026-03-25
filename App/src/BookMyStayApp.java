import java.util.ArrayList;
import java.util.Scanner;

public class BookMyStayApp {

    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};
    static int[] roomNumbers = {101, 201, 301, 401};
    static String[] services = {"Breakfast", "WiFi", "Parking"};

    static ArrayList<String> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("===================================");
            System.out.println("   Welcome to Book My Stay App");
            System.out.println("===================================");

            displayRooms();

            // Name validation
            String name;
            while (true) {
                System.out.print("\nEnter your name: ");
                name = sc.nextLine().trim();

                if (!name.isEmpty()) break;
                System.out.println("⚠️ Name cannot be empty!");
            }

            // Room validation
            int roomIndex = -1;
            while (roomIndex == -1) {
                System.out.print("Enter room type to book: ");
                String room = sc.nextLine();
                roomIndex = getRoomIndex(room);

                if (roomIndex == -1) {
                    System.out.println("⚠️ Invalid room type. Try again.");
                }
            }

            if (availability[roomIndex] > 0) {

                int allocatedRoom = roomNumbers[roomIndex] + (5 - availability[roomIndex]);
                availability[roomIndex]--;

                System.out.println("\n✅ Booking Confirmed!");
                System.out.println("Guest Name: " + name);
                System.out.println("Room Type: " + roomTypes[roomIndex]);
                System.out.println("Room Number: " + allocatedRoom);

                String selectedServices = selectServices(sc);

                saveBooking(name, roomIndex, selectedServices);

            } else {
                System.out.println("❌ Room not available");
            }

            showHistory();

        } catch (Exception e) {
            System.out.println("⚠️ Unexpected error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // Display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");
        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }

    // Get room index safely
    public static int getRoomIndex(String room) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(room)) {
                return i;
            }
        }
        return -1;
    }

    // Service selection with validation
    public static String selectServices(Scanner sc) {

        System.out.println("\nSelect Add-On Services (y/n):");

        String selected = "";

        for (int i = 0; i < services.length; i++) {

            while (true) {
                System.out.print(services[i] + "? (y/n): ");
                String choice = sc.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    selected += services[i] + " ";
                    break;
                } else if (choice.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("⚠️ Please enter only y or n");
                }
            }
        }

        return selected.isEmpty() ? "None" : selected;
    }

    // Save booking
    public static void saveBooking(String name, int roomIndex, String services) {

        String record = "Name: " + name +
                " | Room: " + roomTypes[roomIndex] +
                " | Services: " + services;

        bookingHistory.add(record);
    }

    // Show history
    public static void showHistory() {

        System.out.println("\n📊 Booking History:");

        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        for (int i = 0; i < bookingHistory.size(); i++) {
            System.out.println((i + 1) + ". " + bookingHistory.get(i));
        }
    }
}