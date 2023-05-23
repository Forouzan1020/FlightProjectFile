import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TicketFile {

    RandomAccessFile ticket;

    public static int SIZEt = 15;

    public TicketFile() throws IOException {

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        ticket.close();
    }

//    =================================================================================================================>

//     [ WRITE FILE : TICKET ]

    public void writeTicket(String flightId , long pos) throws IOException {

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");

        ticket.seek(pos);

        flightId = fixToWrite(flightId);

        ticket.writeChars(flightId);

        ticket.close();

    }

//    =================================================================================================================>

//     [ FIX INFORMATION FOR WRITE ]

    public String fixToWrite(String info){

        for (int i = info.length(); i < SIZEt ; i++) {
            info += " ";
        }
        return info;

    }

//    =================================================================================================================>

//    [ Fix INFORMATION FOR READ]

    public String fixToRead(long option) throws IOException {

        String info ="";

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");

            ticket.seek(option);
            for (int i = 0; i < SIZEt; i++) {
                info += ticket.readChar();
            }

        ticket.close();
        return info.trim();
    }

//    =================================================================================================================>

//     [ FIND USER IN THICKET FILE ]

    public long findUserT() throws IOException {

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");

        ticket.seek(0);

        for (long i = 0; i <ticket.length() ; i=i+200) {

            if (Login.logIn.equals(fixToRead(i))){

                return i;
            }

        }
        ticket.close();
        return -1;
    }
//    =================================================================================================================>

//     [ FIND EMPTY IN THICKET FILE ]

    public long posForBuy(long posUser) throws IOException {

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");

        ticket.seek(0);

        for (long i = posUser + 30; i < ticket.length(); i=i+34) {

            if (fixToRead(i).equals("null")){
                ticket.close();
                return i;
            }

        }
        ticket.close();
        return -1;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void print() throws IOException {

        RandomAccessFile ticket = new RandomAccessFile("ticket.dat" , "rw");
        ticket.seek(0);


        System.out.println(ticket.length());

        System.out.println(fixToRead(UserAction.posUInT));
        for (long i = UserAction.posUInT+30; i <ticket.length(); i=i+34) {
                System.out.println(fixToRead(i));
                ticket.seek(i+30);
                System.out.printf("%d\n", ticket.readInt());
        }

        ticket.close();
    }

}
