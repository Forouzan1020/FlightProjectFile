import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws IOException {


        Login login = new Login();
        FlightFile flightFile = new FlightFile();

        login.start();
        flightFile.flightDefault();

//        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");
//        UserFile userFile = new UserFile();
//        TicketFile ticketFile = new TicketFile();
//        AdminAction adminAction = new AdminAction();
//        userFile.writeUser("name");
//        userFile.writeUser("pass");
//        user.seek(user.length());
//        user.writeInt(0);
//        adminAction.printSchedules(flightFile);
//        user.close();
//        ticketFile.print();
//        System.out.println(user.length());

    }
}