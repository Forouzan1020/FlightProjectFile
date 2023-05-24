import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Login {

    Scanner cin = new Scanner(System.in);
    public static long pointer;
    public static String logIn;

//    =================================================================================================================>

//     [ START ]

    public void start() throws IOException {

        pointer = 0;


        UserFile userFile = new UserFile();
        AdminAction adminAction = new AdminAction();
        FlightFile flightFile = new FlightFile();
        TicketFile ticketFile = new TicketFile();
        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        welcome(ticketFile, userFile, user, adminAction, flightFile);
        user.close();
    }


//    =================================================================================================================>

//     [ WELCOME ]

    public void welcome(TicketFile ticketFile, UserFile userFile, RandomAccessFile user, AdminAction adminAction, FlightFile flightFile) throws IOException {

        UserAction userAction = new UserAction();

        System.out.println("[  Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up ");

        int option;

        option = cin.nextInt();

        switch (option) {

            case 1: {

                singIn(ticketFile, userFile, user, adminAction, flightFile, userAction);
                break;

            }

            case 2: {

                singUp(ticketFile, userFile, adminAction, flightFile, userAction);
                break;

            }

            default:
                welcome(ticketFile, userFile, user, adminAction, flightFile);


        }
        welcome(ticketFile, userFile, user, adminAction, flightFile);

    }

//    =================================================================================================================>

//     [ SING IN ]

    public void singIn(TicketFile ticketFile, UserFile userFile, RandomAccessFile user, AdminAction adminAction, FlightFile flightFile, UserAction userAction) throws IOException {


        String name, pass;


        System.out.println("[ Enter your name ] ");

        name = cin.next();

        if (findUserName(userFile, name, user) != -2) {
            pointer = findUserName(userFile, name, user);

            System.out.println("[ Enter your pass ] ");

            pass = cin.next();

            if (findUserPass(userFile, pass) == 1) {

                adminAction.adminMenu(userFile, user, adminAction, flightFile);

            } else if (findUserPass(userFile, pass) == -1) {

                System.out.println("[ your password is not correct ]");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                ;

            } else {
                logIn = pass;
                userAction.passengerMenu(ticketFile, userFile, user, adminAction, flightFile);

            }

        } else {
            System.out.println("[ This username not found ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;

        }

    }


//    =================================================================================================================>
//    [ FIND USER NAME ]

    public long findUserName(UserFile userFile, String info, RandomAccessFile user) throws IOException {

        user = new RandomAccessFile("User.dat", "rw");

        for (long i = 0; i < user.length(); i = i + 104) {

            if (info.equals(userFile.fixToRead(i))) {

                pointer = i;
                user.close();
                return i;

            } else if (info.equals("Admin")) {

                return -1;
            }
        }

        return -2;
    }

//    =================================================================================================================>
//    [ FIND PASSWORD OF USER ]

    public long findUserPass(UserFile userFile, String pass) throws IOException {


        if (userFile.fixToRead(pointer + 50).equals(pass)) {

            return 0;

        } else if (pass.equals("Admin")) {

            return 1;

        } else {

            return -1;

        }

    }

//    =================================================================================================================>
//    [ SING UP ]

    public void singUp(TicketFile ticketFile, UserFile userFile, AdminAction adminAction, FlightFile flightFile, UserAction userAction) throws IOException {
        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");
        String name, pass;
        long pos;
        int ticketId , count;
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat", "rw");
        ticket.seek(0);

        System.out.println("[ Enter your name ] ");

        name = cin.next();

        System.out.println("[ Enter your pass ] \n[ Or if you want back enter X ] ");

        pass = cin.next();

        if (pass.equals("X")) {

            welcome(ticketFile, userFile, user, adminAction, flightFile);
        } else if (findUserName(userFile, name, user) == -2 && !name.equals("Admin")) {

            userFile.writeUser(name);
            userFile.writeUser(pass);
            logIn = pass;
            user.seek(user.length());
            user.writeInt(0);
            pointer = user.length() - 104;
            user.close();
            pos = ticket.length();
            ticketFile.writeTicket(pass, pos);
            ticketId = ticketId();
            for (int i = 0; i < 5; i++) {
                count =5;
                for (long j = pos + 30; j < pos + 200; j = j + 34) {

                    ticketFile.writeTicket("null", j);

                    ticket.seek(j + 30);
                    ticket.writeInt(ticketId+count);
                    count++;
                }
            }
            don();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;

            userAction.passengerMenu(ticketFile, userFile, user, adminAction, flightFile);

        } else {
            System.out.println("[ This user already exist ]");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;

            welcome(ticketFile, userFile, user, adminAction, flightFile);
        }

    }

//    =================================================================================================================>
//    [ PRINT DONE ]

    public static void don() {

        System.out.println("[ don ] ");
    }

//    =================================================================================================================>
//    [ CREAT TICKET ID ]

    public int ticketId() throws IOException {

        int ticketBase;
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        if (Login.pointer != 0){

            ticket.seek(UserAction.posUInT+196);
            ticketBase = ticket.readInt()+1;

        }else {

            ticket.seek(UserAction.posUInT+60);
            ticketBase = 1000000;

        }
        ticket.close();

        return ticketBase;
    }
}
