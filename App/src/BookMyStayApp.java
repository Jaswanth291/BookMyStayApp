import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMyStayApp {

    static String[] roomTypes = {"Single Room", "Double Room", "Deluxe Room", "Suite Room"};
    static int[] availability = {5, 3, 2, 1};
    static String[] services = {"Breakfast", "WiFi", "Parking"};

    static ArrayList<String> bookingHistory = new ArrayList<>();

    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Load previous data
        loadBookings();

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App");
        System.out.println("===================================");

        displayRooms();

        System.out.print("\nEnter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room type to book: ");
        String room = sc.nextLine();

        int roomIndex = getRoomIndex(room);

        if (roomIndex != -1 && availability[roomIndex] > 0) {

            availability[roomIndex]--;

            String selectedServices = selectServices(sc);

            saveBooking(name, roomIndex, selectedServices);

            saveToFile(); // persist data

            System.out.println("✅ Booking saved successfully!");

        } else {
            System.out.println("❌ Booking failed!");
        }

        showHistory();

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
        for (String service : services) {
            System.out.print(service + "? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                selected += service + " ";
            }
        }
        return selected.isEmpty() ? "None" : selected;
    }

    // Save booking to memory
    public static void saveBooking(String name, int roomIndex, String services) {
        String record = name + "," + roomTypes[roomIndex] + "," + services;
        bookingHistory.add(record);
    }

    // Save to file
    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String record : bookingHistory) {
                writer.write(record);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving data");
        }
    }

    // Load from file
    public static void loadBookings() {
        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                bookingHistory.add(line);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading data");
        }
    }

    // Show history
    public static void showHistory() {
        System.out.println("\n📊 Booking History:");
        for (String record : bookingHistory) {
            System.out.println(record);
        }
    }
}