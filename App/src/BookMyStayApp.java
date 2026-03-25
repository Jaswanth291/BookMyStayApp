import java.util.ArrayList;
import java.util.Scanner;

public class BookMyStayApp {

    // Room Data
    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};
    static int[] roomNumbers = {101, 201, 301, 401};

    // Add-on services
    static String[] services = {"Breakfast", "WiFi", "Parking"};

    // Booking History
    static ArrayList<String> bookingHistory = new ArrayList<>();

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

        int roomIndex = bookRoom(name, room);

        if (roomIndex != -1) {
            String selectedServices = selectServices(sc);
            saveBooking(name, roomIndex, selectedServices);
        }

        showHistory();
    }

    // Display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");

        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }

    // Booking logic
    public static int bookRoom(String name, String room) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(room)) {

                if (availability[i] > 0) {

                    int allocatedRoom = roomNumbers[i] + (5 - availability[i]);
                    availability[i]--;

                    System.out.println("\n✅ Booking Confirmed!");
                    System.out.println("Guest Name: " + name);
                    System.out.println("Room Type: " + roomTypes[i]);
                    System.out.println("Room Number: " + allocatedRoom);

                    return i;
                } else {
                    System.out.println("❌ Room not available");
                    return -1;
                }
            }
        }

        System.out.println("⚠️ Invalid room type");
        return -1;
    }

    // Add-on services
    public static String selectServices(Scanner sc) {

        System.out.println("\nSelect Add-On Services (y/n):");

        String selected = "";

        for (int i = 0; i < services.length; i++) {
            System.out.print(services[i] + "? (y/n): ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                selected += services[i] + " ";
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