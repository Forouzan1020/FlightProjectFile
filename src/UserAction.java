import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Stack;

public class UserAction {



    Scanner cin = new Scanner(System.in);
    public static long posUInT;


    public void passengerMenu(TicketFile ticketFile, UserFile userFile, RandomAccessFile user, AdminAction adminAction, FlightFile flightFile) throws IOException {

        Login login = new Login();

        int userOption;

        System.out.println("[ Passenger menu option ] \n\n[1] Change password \n[2] Search flight tickets \n[3] Booking tickets\n[4] Ticket cancellation \n[5] Booked tickets \n[6] Add charge \n[0] sing out ");

        userOption = cin.nextInt();

        switch (userOption) {

            case 1: {
                changePassword(ticketFile,userFile, user, adminAction, flightFile, login);
                break;
            }

            case 2: {

                searchFlight(ticketFile,userFile, user, adminAction, flightFile);
                break;
            }

            case 3: {

                bookingTicket(ticketFile,userFile, adminAction, flightFile);
                break;
            }

            case 4: {
                ticketCancellation(ticketFile,adminAction,flightFile,userFile);
                break;
            }

            case 5: {

                bookedTicket(ticketFile,flightFile,adminAction,userFile);
                break;
            }

            case 6: {
                addCharge(ticketFile,userFile, adminAction, flightFile);
                break;
            }

            case 0: {
                login.welcome(ticketFile,userFile, user, adminAction, flightFile);
                break;
            }

            default:
                passengerMenu(ticketFile,userFile, user, adminAction, flightFile);

        }
        passengerMenu(ticketFile,userFile, user, adminAction, flightFile);

    }


//    =================================================================================================================>

//     [ CHANGE PASSWORD ]

    private void changePassword(TicketFile ticketFile,UserFile userFile, RandomAccessFile user, AdminAction adminAction, FlightFile flightFile, Login login) throws IOException {

        System.out.println("[ Enter new password ]\n[ Enter X if you want back ]");

        String newPass;
        newPass = cin.next();
        long pos;

        if (newPass.equals("X")) {

            passengerMenu(ticketFile,userFile, user, adminAction, flightFile);

        }
        pos = Login.pointer + 50;
        writeUserPos(newPass, pos, userFile);

        login.don();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        ;


    }
//    =================================================================================================================>

//     [ WRITE FILE : USER ]

    public void writeUserPos(String info, long pos, UserFile userFile) throws IOException {

        info = userFile.fixToWrite(info);

        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        user.seek(pos);

        user.writeChars(info);

        user.close();

    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT ]

    private void searchFlight(TicketFile ticketFile,UserFile userFile, RandomAccessFile user, AdminAction adminAction, FlightFile flightFile) throws IOException {
        adminAction.printSchedules(flightFile);
        System.out.println(" [ Choose how the tickets will be sorted ]\n\n [1] FlightId\n [2] Origin\n [3] Destination\n [4] Date\n [5] Time\n [6] Price\n [7] Seats\n [0] Bake ");
        switch (cin.nextInt()) {
            case 1: {

                printFlightIdS(ticketFile,flightFile , userFile ,user ,adminAction);
                break;

            }
            case 2: {

                printOriginS(ticketFile, adminAction ,flightFile ,userFile , user);
                break;

            }
            case 3: {

               printDestinationS(ticketFile,adminAction ,flightFile ,userFile , user);
                break;
            }
            case 4: {

                printDateS(ticketFile,adminAction ,flightFile ,userFile , user);
                break;

            }
            case 5: {

                printTimeS(ticketFile,adminAction ,flightFile ,userFile , user);
                break;

            }
            case 6: {

                printPriceS(ticketFile,adminAction ,flightFile ,userFile , user);
                break;
            }
            case 7: {

                printSeatsS(ticketFile,adminAction ,flightFile ,userFile , user);
                break;
            }
            case 0: {

                passengerMenu(ticketFile,userFile, user, adminAction, flightFile);
                break;

            }
            default:
                searchFlight(ticketFile,userFile, user, adminAction, flightFile);
        }
    }


//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT ID SORT ]

    public void printSort(long[] posStart , int posMore, FlightFile flightFile , AdminAction adminAction) throws IOException {

        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int k = 0; k < 30 ; k++) {

            if (posStart[k] != 0) {

                for (long i = posStart[0]; i < flight.length(); i = i + 158) {

                    if (adminAction.fixToReadPrint(i, flightFile) == true) {

                        for (long j = i; j < i + 150; j = j + 30) {
                            System.out.printf("|%-15s", flightFile.fixToRead(j));
                        }
                        flight.seek(i + 150);
                        System.out.printf("|%-15d", flight.readInt());
                        flight.seek(i + 154);
                        System.out.printf("|%-15d\n", flight.readInt());

                    }
                }
            }
        }
        flight.close();
}

//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT ID SORT ]

    private void printFlightIdS(TicketFile ticketFile , FlightFile flightFile , UserFile userFile , RandomAccessFile user , AdminAction adminAction ) throws IOException {

        adminAction.printSchedules(flightFile);

        String flightId ;
        long[] reWrite = new long[1];
        System.out.println("[ Enter the flightId ]\n[ Enter X if you want back ]");
        flightId = cin.next();


        if (flightId.equals("X")){
            searchFlight(ticketFile,userFile, user, adminAction, flightFile);
        }

        if ( flightFile.findFlight(flightId) >= 0) {

            reWrite[0]=flightFile.findFlight(flightId);
            printSort(ticketFile,flightFile,1,reWrite ,adminAction ,userFile, user);

        }else {
            System.out.println("This flightId not exist");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            printFlightIdS(ticketFile,flightFile,userFile,user,adminAction);
        }
    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT ORIGIN SORT ]

    public void printOriginS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the origin ]\n[ Enter X if you want back ]");
        String origin;
        int count = 0;
        long[] reWrite = new long[30];
        origin = cin.next();


        if (origin.equals("X")){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        for (long i = 30; i < flight.length(); i = i+158 ) {

            if (origin.equals(flightFile.fixToRead(i))) {

                reWrite[count] = i - 30;
                count++;

            }
        }
        if (count == 0){

                System.out.println("[ This flight not exist ]");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                ;
                printOriginS(ticketFile,adminAction, flightFile, userFile, user);

            }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();
    }

//    =================================================================================================================>

//     [ PRINT AFTER SORT ]
    public void printSort(TicketFile ticketFile,FlightFile flightFile ,int count , long[] reWrite , AdminAction adminAction  , UserFile userFile ,RandomAccessFile user) throws IOException {

        long j;

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          \n");

        for (int k = 0; k < count ; k++) {
            j = reWrite[k];
            System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s", flightFile.fixToRead(j),flightFile.fixToRead(j+30),flightFile.fixToRead(j+60),flightFile.fixToRead(j+90),flightFile.fixToRead(j+120));
            flight.seek(j + 150);
            System.out.printf("|%-15d", flight.readInt());
            flight.seek(j + 154);
            System.out.printf("|%-15d\n", flight.readInt());
        }
        flight.close();

        System.out.println("\n[ Enter X if you want back ]");
        if (cin.next().equals("X")){
            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT DESTINATION SORT ]

    public void printDestinationS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the Destination ]\n[ Enter X if you want back ]");
        String Destination;
        int count = 0;
        long[] reWrite = new long[30];
        Destination = cin.next();


        if (Destination.equals("X")){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        for (long i = 60; i < flight.length(); i = i+158 ) {

            if (Destination.equals(flightFile.fixToRead(i))) {

                reWrite[count] = i - 60;
                count++;

            }
        }
        if (count == 0){

            System.out.println("[ This flight not exist ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            printDestinationS(ticketFile,adminAction, flightFile, userFile, user);

        }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();

    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT DATE SORT ]

    public void printDateS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the Date ]\n[ Enter X if you want back ]");
        String Date;
        int count = 0;
        long[] reWrite = new long[30];
        Date = cin.next();


        if (Date.equals("X")){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        for (long i = 90; i < flight.length(); i = i+158 ) {

            if (Date.equals(flightFile.fixToRead(i))) {

                reWrite[count] = i - 90;
                count++;

            }
        }
        if (count == 0){

            System.out.println("[ This flight not exist ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            printDateS(ticketFile,adminAction, flightFile, userFile, user);

        }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();

    }

//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT TIME SORT ]

    public void printTimeS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the Time ]\n[ Enter X if you want back ]");
        String Time;
        int count = 0;
        long[] reWrite = new long[30];
        Time = cin.next();


        if (Time.equals("X")){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        for (long i = 120; i < flight.length(); i = i+158 ) {

            if (Time.equals(flightFile.fixToRead(i))) {

                reWrite[count] = i - 120;
                count++;

            }
        }
        if (count == 0){

            System.out.println("[ This flight not exist ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            printTimeS(ticketFile,adminAction, flightFile, userFile, user);

        }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();

    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT DESTINATION SORT ]

    public void printPriceS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the max price ]\n[ Enter 0 if you want back ]");
        int priceMax , priceMin;
        int count = 0 , price;
        long[] reWrite = new long[30];
        priceMax = cin.nextInt();


        if (priceMax == 0){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);
        System.out.println("[ Enter the min price ]");
        priceMin = cin.nextInt();

        for (long i = 150; i < flight.length(); i = i+158 ) {

            flight.seek(i);
            price = flight.readInt();

            if (price <= priceMax && price >= priceMin) {

                reWrite[count] = i - 150;
                count++;

            }
        }
        if (count == 0){

            System.out.println("[ This flight not exist ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            printPriceS(ticketFile,adminAction, flightFile, userFile, user);

        }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();

    }
//    =================================================================================================================>

//     [ SEARCH FLIGHT : FLIGHT SEATS SORT ]

    public void printSeatsS(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ,RandomAccessFile user) throws IOException {

        adminAction.printSchedules(flightFile);
        System.out.println("[ Enter the max seat ]\n[ Enter 0 if you want back ]");
        int seatMax ,seatMin;
        int count = 0  , seat;
        long[] reWrite = new long[30];
        seatMax = cin.nextInt();


        if (seatMax == 0){

            searchFlight(ticketFile,userFile ,user ,adminAction ,flightFile );
        }

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");

        flight.seek(0);

        System.out.println("[ Enter the min seat ]");
        seatMin = cin.nextInt();

        for (long i = 154; i < flight.length(); i = i+158 ) {


                flight.seek(i);
                seat = flight.readInt();

                if ( seat >= seatMin && seat <= seatMax ) {

                    reWrite[count] = i - 154;
                    count++;

                }

        }
        if (count == 0){

            System.out.println("[ This flight not exist ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            printSeatsS(ticketFile,adminAction, flightFile, userFile, user);

        }



        printSort(ticketFile,flightFile,count,reWrite ,adminAction ,userFile, user );
        flight.close();

    }
//    =================================================================================================================>

//     [ BOOKING THICKET ]

    public  void bookingTicket(TicketFile ticketFile ,UserFile userFile, AdminAction adminAction, FlightFile flightFile) throws IOException {

        adminAction.printSchedules(flightFile);

        String flightId ;
        long flightPos , writePos , userPos;

        int price , money , seat;

        System.out.println("[ Enter the flightId ]\n[ Enter X if you want back ]");

        flightId = cin.next();

        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        if (flightId.equals("X")){

            passengerMenu(ticketFile,userFile, user, adminAction, flightFile);
        }



        if (flightFile.findFlight(flightId) != -1){

            flightPos = flightFile.findFlight(flightId);

            user.seek(Login.pointer + 100);
            flight.seek(flightPos + 150 );

            if (user.readInt() < flight.readInt()){

                System.out.println("[ You do not have enough money ]");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                ;

                bookingTicket(ticketFile,userFile, adminAction, flightFile);

            } else if (flight.readInt() < 0) {
                System.out.println("[ There are not enough seats ]");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                ;

                bookingTicket(ticketFile,userFile, adminAction, flightFile);
                
            }else {

                userPos = Login.pointer;
                writePos = ticketFile.posForBuy(ticketFile.findUserT());

                if (userPos >= 0 && writePos >= 0) {

                    ticketFile.writeTicket(flightId, writePos);

                    user.seek(userPos + 100);
                    money = user.readInt();

                    flight.seek(flightPos + 150);
                    price = flight.readInt();

                    flight.seek(flightPos + 154);
                    seat = flight.readInt();

                    flight.seek(flightPos + 154);
                    flight.writeInt(seat - 1);

                    user.seek(userPos + 100);
                    user.writeInt(money - price);


                    Login.don();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    ;
                }
            }


        } else if (flightFile.findFlight(flightId) == -1) {
            System.out.println("[ Flight not found ]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            ;
            bookingTicket(ticketFile,userFile, adminAction, flightFile);

        }

        user.close();
        ticket.close();
        flight.close();

    }

//    =================================================================================================================>

//     [ ADD CHARGE ]

    public void addCharge(TicketFile ticketFile,UserFile userFile, AdminAction adminAction, FlightFile flightFile) throws IOException {

        int money;

        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");
        user.seek(Login.pointer + 100);
        money = user.readInt();
        System.out.printf("[ Your budget : %d ]\n[ Enter the amount you want to charge ]\n", money);
        user.seek(Login.pointer + 100);
        user.writeInt(money + cin.nextInt());
        Login.don();
        user.seek(Login.pointer + 100);
        money = user.readInt();
        System.out.printf("[ Your budget : %d ]", money);
        System.out.println("\n[ Enter X if you want back ]");
        if (cin.next().equals("X")) {

            passengerMenu(ticketFile, userFile, user, adminAction, flightFile);

            user.close();
        }
    }

//    =================================================================================================================>

//     [ PRINT TICKETS ]

    public void bookedTicket( TicketFile ticketFile , FlightFile flightFile, AdminAction adminAction, UserFile userFile) throws IOException {

        long[] reWriteF;
        long[] reWriteT;
        int count=0;

        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        posUInT = ticketFile.findUserT();

        reWriteF = new long[5];
        reWriteT = new long[5];

        user.seek(0);
        ticket.seek(0);
        flight.seek(0);




        for (long i = posUInT+30; i < posUInT+200; i= i+34) {

            for (long j = 0; j < flight.length() ; j = j + 158) {

                if (!ticketFile.fixToRead(i).equals("null") && ticketFile.fixToRead(i).equals(flightFile.fixToRead(j)) ){


                    reWriteF[count] = j;
                    reWriteT[count] = i+30;
                    count++;

                }

            }

        }

        flight.close();
        ticket.close();
        user.close();

        printTickets(flightFile,adminAction,ticketFile,userFile,user,count,reWriteF,reWriteT);

    }


    public void printTickets(FlightFile flightFile , AdminAction adminAction,TicketFile ticketFile, UserFile userFile, RandomAccessFile user,int count, long[] reWriteF , long[] reWriteT) throws IOException {


        long  j;

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");

        flight.seek(0);
        ticket.seek(0);

        System.out.println("|FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats          |TicketId       \n");


        for (int k = 0; k < count; k++) {
            j = reWriteF[k];

            System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s", flightFile.fixToRead(j),flightFile.fixToRead(j+30),flightFile.fixToRead(j+60),flightFile.fixToRead(j+90),flightFile.fixToRead(j+120));
            flight.seek(j + 150);
            System.out.printf("|%-15d", flight.readInt());
            flight.seek(j + 154);
            System.out.printf("|%-15d", flight.readInt());
            ticket.seek(reWriteT[k]);
            System.out.printf("|%-15d\n", ticket.readInt());
        }

        flight.close();
        ticket.close();

        System.out.println("\n[ Enter X if you want back ]");
        if (cin.next().equals("X")) {
            passengerMenu(ticketFile, userFile, user, adminAction, flightFile);
        }
    }
//    =================================================================================================================>

//     [ TICKETS CANCELLATION ]

    public void ticketCancellation(TicketFile ticketFile,AdminAction adminAction , FlightFile flightFile , UserFile userFile ) throws IOException {

        System.out.println("[ Enter the ID of the ticket you want to cancel ]\n[ Enter 0 if you want back ]");

        long pos;
        int ticketId , ticketId1;
        String flightId;
        ticketId = cin.nextInt();

        RandomAccessFile flight = new RandomAccessFile("Flight.dat", "rw");
        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        if (ticketId == 0){

            passengerMenu(ticketFile, userFile, user, adminAction, flightFile);
        }


        flight.seek(0);
        ticket.seek(0);

        for (long i = posUInT+30; i < posUInT+196; i=i+34) {

            ticket.seek(i+30);
            ticketId1 = ticket.readInt();
            if (ticketId1 == ticketId){

                flightId = ticketFile.fixToRead(i);
                pos = flightFile.findFlight(flightId);
                flight.seek(pos+154);
                flight.writeInt(flight.readInt()+1);
                user.seek(Login.pointer+100);
                flight.seek(pos+150);
                user.writeInt(user.readInt()+flight.readInt());
                ticketFile.writeTicket("null" ,i);

                Login.don();
                try{Thread.sleep(500);}catch(InterruptedException e) {};
                passengerMenu(ticketFile, userFile, user, adminAction, flightFile);

            }

        }

            System.out.println("[ Tis ticketId not exist ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            ticketCancellation(ticketFile,adminAction,flightFile,userFile);




    }
}
