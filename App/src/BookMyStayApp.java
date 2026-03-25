class BookingSystem {

    static String[] roomTypes = {"Single Room", "Double Room"};
    static int[] availability = {1, 1}; // only 1 room each to test concurrency

    // synchronized method (thread-safe)
    public synchronized static void bookRoom(String user, int roomIndex) {

        System.out.println(user + " trying to book " + roomTypes[roomIndex]);

        if (availability[roomIndex] > 0) {

            // simulate delay
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            availability[roomIndex]--;

            System.out.println("✅ " + user + " successfully booked " + roomTypes[roomIndex]);

        } else {
            System.out.println("❌ " + user + " failed! No rooms available");
        }
    }
}

// Thread class
class UserThread extends Thread {

    String userName;
    int roomIndex;

    UserThread(String userName, int roomIndex) {
        this.userName = userName;
        this.roomIndex = roomIndex;
    }

    public void run() {
        BookingSystem.bookRoom(userName, roomIndex);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Simulate multiple users booking same room
        Thread t1 = new UserThread("User1", 0);
        Thread t2 = new UserThread("User2", 0);

        t1.start();
        t2.start();
    }
}