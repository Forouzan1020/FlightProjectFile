import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws IOException {
//
//        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        Login login = new Login();
        UserFile userFile = new UserFile();
        FlightFile flightFile = new FlightFile();
        TicketFile ticketFile = new TicketFile();
        AdminAction adminAction = new AdminAction();
//        flightFile.flightDefault();
//        userFile.writeUser("name");
//        userFile.writeUser("pass");
//        user.seek(user.length());
//        user.writeInt(0);
//        adminAction.printSchedules(flightFile);
//        user.close();
//        ticketFile.print();

        login.start();


    }
}