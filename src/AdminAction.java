import javax.swing.plaf.PanelUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AdminAction {


    Scanner cin = new Scanner(System.in);

    public void adminMenu(UserFile userFile , RandomAccessFile user ,AdminAction adminAction , FlightFile flightFile) throws IOException {


        System.out.println("[ Admin menu option ] \n\n");
        System.out.println("[1] Add \n[2] Update \n[3] Remove \n[4] Flight schedules \n[0] Sing out");
        int optionAdmin;
        optionAdmin = cin.nextInt();

        switch (optionAdmin) {
            case 1: {

                addFlight(flightFile , userFile , adminAction , user);
                break;

            }
            case 2: {

                editFlight(flightFile ,userFile , adminAction ,  user);
                break;

            }


            case 3: {

                removeFlight(flightFile ,userFile , adminAction ,  user);
                break;

            }

            case 4: {

                printSchedules(flightFile);
                break;

            } case 0:{

                Login login = new Login();
                TicketFile ticketFile = new TicketFile();
                login.welcome( ticketFile ,userFile , user , adminAction , flightFile);
                break;
            }

            default:adminMenu(userFile , user , adminAction , flightFile);

        }

        adminMenu(userFile , user , adminAction , flightFile);

    }

//    =================================================================================================================>

//    [ ADD FLIGHT ]

    public void addFlight(FlightFile flightFile , UserFile userFile , AdminAction adminAction , RandomAccessFile user  ) throws IOException {

        printSchedules(flightFile);
        String flightId;
        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        System.out.println("[ Enter flightId ] \n[ If you want back enter X ]");

        flightId = cin.next();

        if (flightId.equals("X")){

            adminMenu(userFile , user , adminAction , flightFile);
        }

        else {

            if(flightFile.findFlight(flightId) == -1){

                flightFile.writeFlight(flightId);

                System.out.println("[ Enter origin ]");

                flightFile.writeFlight(cin.next());

                System.out.println(" [ Enter destination ]");

                flightFile.writeFlight(cin.next());

                System.out.println("[ Enter date ]");

                flightFile.writeFlight(cin.next());

                System.out.println(" [ Enter time ]");

                flightFile.writeFlight(cin.next());

                System.out.println("[ Enter price ]");

                flight.seek(flight.length());

                flight.writeInt(cin.nextInt());

                System.out.println("[ Enter seats ]");

                flight.seek(flight.length());

                flight.writeInt(cin.nextInt());

                Login.don();

                try{Thread.sleep(500);}catch(InterruptedException e) {};
            }else {
                System.out.println("[ This flightId does not exist ]");
                try{Thread.sleep(500);}catch(InterruptedException e) {};
                addFlight(flightFile , userFile , adminAction , user);
            }

        }

    }

//    =================================================================================================================>

//    [ EDIT FLIGHT ]

    public void editFlight(FlightFile flightFile , UserFile userFile , AdminAction adminAction , RandomAccessFile user) throws IOException {
        printSchedules(flightFile);
        String flightId;

        System.out.println("[ Which flights do you want to change ]\n[ Enter X if you want back ]");

        flightId = cin.next();

        if(flightId.equals("X")){
            adminMenu(userFile , user , adminAction , flightFile);
        }else {
            long pos = flightFile.findFlight(flightId);
            if (pos >= 0 && flightFile.fixToRead(pos).equals(flightId)){

                System.out.println("[ Which feature do you want to change ] \n\n [1] FlightId \n [2] Origin \n [3] Destination \n [4] Date \n [5] Time \n [6] Price \n [7] Seats \n [0] Back");
                int optionUpdate;
                optionUpdate = cin.nextInt();

                switch (optionUpdate) {
                    case 1: {
                        System.out.println("[ Enter new FlightId ]");
                        writeFlight(cin.next() , flightFile , pos);
                        break;
                    }

                    case 2: {
                        System.out.println("[ Enter new Origin ]");
                        writeFlight(cin.next() , flightFile , pos+30);
                        break;
                    }
                    case 3: {
                        System.out.println("[ Enter new Destination ]");
                        writeFlight(cin.next() , flightFile , pos+60);
                        break;
                    }
                    case 4: {
                        System.out.println("[ Enter new Date ]");
                        writeFlight(cin.next() , flightFile , pos+90);
                        break;
                    }
                    case 5: {
                        System.out.println("[ Enter new Time ]");
                        writeFlight(cin.next() , flightFile , pos+120);

                        break;
                    }
                    case 6: {
                        System.out.println("[ Enter new Price ]");
                        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");
                        flight.seek(pos+150);
                        flight.writeInt(cin.nextInt());
                        flight.close();
                        Login.don();

                        break;
                    }
                    case 7: {
                        System.out.println("[ Enter new Seats ]");
                        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");
                        flight.seek(pos+180);
                        flight.writeInt(cin.nextInt());
                        flight.close();
                        Login.don();
                        break;
                    }
                    case 0:{
                        adminMenu(userFile , user , adminAction , flightFile);
                        break;
                    }
                    default:editFlight(flightFile ,userFile , adminAction ,  user);
                }
            }else {

                System.out.println("[ FlightId not found ]");

                try{Thread.sleep(500);}catch(InterruptedException e) {};

                editFlight(flightFile ,userFile , adminAction ,  user);

            }

        }

    }
//    =================================================================================================================>

//     [ WRITE FILE : ّFLIGHT ]

    public void writeFlight(String info , FlightFile flightFile , long pos) throws IOException {

        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");
        flight.seek(pos);
        info = flightFile.fixToWrite(info);
        flight.writeChars(info);
        flight.close();
    }

//    =================================================================================================================>

//    [ REMOVE FLIGHT ]

    public void removeFlight(FlightFile flightFile , UserFile userFile , AdminAction adminAction , RandomAccessFile user) throws IOException {

        printSchedules(flightFile);
        System.out.println("[ Enter the flightId you want remove ]\n[ Enter X if you want back ]");
        String flightRemove;
        flightRemove = cin.next();

        if (flightRemove.equals("X")){
            adminMenu(userFile , user , adminAction , flightFile);
        }else {

            long pos;
            pos = flightFile.findFlight(flightRemove);

            if (pos >= 0 && flightFile.fixToRead(pos).equals(flightRemove)){

                RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");
                flight.seek(pos);
                writeFlightNew(flightRemove , flightFile);
                flight.close();
                Login.don();
                try{Thread.sleep(500);}catch(InterruptedException e) {};

            }else {
                System.out.println("[ This flightId does not exist ]");
                try{Thread.sleep(500);}catch(InterruptedException e) {};
                removeFlight(flightFile ,userFile , adminAction ,  user);
            }

        }

    }

//    =================================================================================================================>

//     [ WRITE FILE : ّFLIGHT ]

    public void writeFlightNew(String info , FlightFile flightFile) throws IOException {

        RandomAccessFile flightNew = new RandomAccessFile("flight.dat" , "rw");
        long pos;
        pos = flightFile.findFlight(info);
        flightNew.seek(pos);
        info = flightFile.fixToWrite(" ");
        flightNew.writeChars(info);
        flightNew.close();
    }

//    =================================================================================================================>

//    [ PRINT SCHEDULES ]

    public void printSchedules(FlightFile flightFile) throws IOException {

        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        flight.seek(0);

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");


        for (long i = 0; i < flight.length(); i= i + 158) {

            if (fixToReadPrint(i , flightFile) == true) {

                for (long j = i; j < i + 150; j = j + 30) {
                    System.out.printf("|%-15s", flightFile.fixToRead(j));
                }
                flight.seek(i + 150);
                System.out.printf("|%-15d", flight.readInt());
                flight.seek(i + 154);
                System.out.printf("|%-15d\n", flight.readInt());
            }
        }
        flight.close();
    }

//    =================================================================================================================>

//    [ FIND FLIGHT THAT REMOVED ]

    public boolean fixToReadPrint(long j ,FlightFile flightFile) throws IOException {
        boolean option = true;

        if (flightFile.fixToRead(j).equals("")){
            option = false;
        }

        return option;
    }
}
