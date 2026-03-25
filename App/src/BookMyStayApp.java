import java.util.ArrayList;
import java.util.Scanner;

public class BookMyStayApp {

    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};
    static int[] roomNumbers = {101, 201, 301, 401};
    static String[] services = {"Breakfast", "WiFi", "Parking"};

    static ArrayList<String> bookingHistory = new ArrayList<>();
    static ArrayList<Integer> bookedRoomIndex = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("===================================");

        displayRooms();

        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();

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

            availability[roomIndex]--;

            System.out.println("\n✅ Booking Confirmed!");

            String selectedServices = selectServices(sc);

            saveBooking(name, roomIndex, selectedServices);

        } else {
            System.out.println("❌ Room not available");
        }

        showHistory();

        // Cancellation option
        System.out.print("\nDo you want to cancel any booking? (y/n): ");
        String cancelChoice = sc.nextLine();

        if (cancelChoice.equalsIgnoreCase("y")) {
            cancelBooking(sc);
        }

        System.out.println("\nFinal Room Availability:");
        displayRooms();

        sc.close();
    }

    // Display rooms
    public static void displayRooms() {
        System.out.println("\nAvailable Room Types:\n");
        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println((i + 1) + ". " + roomTypes[i] +
                    " - Available: " + availability[i]);
        }
    }

    // Get room index
    public static int getRoomIndex(String room) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(room)) {
                return i;
            }
        }
        return -1;
    }

    // Select services
    public static String selectServices(Scanner sc) {
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
        bookedRoomIndex.add(roomIndex);
    }

    // Show history
    public static void showHistory() {
        System.out.println("\n📊 Booking History:");
        for (int i = 0; i < bookingHistory.size(); i++) {
            System.out.println((i + 1) + ". " + bookingHistory.get(i));
        }
    }

    // Cancel booking + rollback
    public static void cancelBooking(Scanner sc) {

        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }

        showHistory();

        System.out.print("Enter booking number to cancel: ");
        int index = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (index < 1 || index > bookingHistory.size()) {
            System.out.println("⚠️ Invalid selection");
            return;
        }

        int roomIndex = bookedRoomIndex.get(index - 1);

        // rollback availability
        availability[roomIndex]++;

        bookingHistory.remove(index - 1);
        bookedRoomIndex.remove(index - 1);

        System.out.println("✅ Booking cancelled successfully!");
    }
}