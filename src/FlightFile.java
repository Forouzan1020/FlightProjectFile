import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;

public class FlightFile {

    RandomAccessFile flight;

    static int SIZEf = 15;

//    |FlightId       |Origin         |Destination    |Date           |Time           |Price          |Seats


    public FlightFile() throws IOException {
        this.flight = new RandomAccessFile("Flight.dat" , "rw");
        flight.close();

    }

//    =================================================================================================================>

//     [ WRITE FILE : ّFLIGHT ]

    public void writeFlight(String info) throws IOException {

        this.flight = new RandomAccessFile("Flight.dat" , "rw");
        flight.seek(flight.length());

        info = fixToWrite(info);

        flight.writeChars(info);

        flight.close();
    }



//    =================================================================================================================>

//     [ FIX INFORMATION FOR WRITE ]

    public String fixToWrite(String info){


        for (int i = info.length(); i < SIZEf; i++) {

            info += " ";

        }
        return info;

    }


//    =================================================================================================================>

//    [ Fix INFORMATION FOR READ]

    public String fixToRead(long option) throws IOException {

        this.flight = new RandomAccessFile("Flight.dat" , "rw");
        flight.seek(option);

        String info ="";

        for (int i = 0; i < SIZEf; i++) {

            info += flight.readChar();
        }
        flight.close();
        return info.trim();

    }

//    =================================================================================================================>

//    [ FIND FLIGHT ]

    public long findFlight(String flightId) throws IOException {

        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        flight.seek(0);

        for (long i = 0; i < flight.length() ; i = i+158) {

            if (fixToRead(i).equals(flightId)){

                flight.close();
                return i;

            }
        }
        flight.close();
        return -1;

    }

//    =================================================================================================================>

//  [ DEFAULT THREE FLIGHT ]

    public void flightDefault() throws IOException {

        long option ;
        RandomAccessFile flight = new RandomAccessFile("Flight.dat" , "rw");

        writeFlight("WX-12");
        writeFlight("Yazd");
        writeFlight("Tehran");
        writeFlight("1401/12/10");
        writeFlight("12:30");
        option = findFlight("WX-12")+150;
        flight.seek(option);
        flight.writeInt(700000);
        option = option +4;
        flight.seek(option);
        flight.writeInt(51);

        writeFlight("WZ-15");
        writeFlight("Mashhad");
        writeFlight("Ahvaz");
        writeFlight("1401/12/11");
        writeFlight("08:00");
        option = findFlight("WZ-15")+150;
        flight.seek(option);
        flight.writeInt(900000);
        option = option+4;
        flight.seek(option);
        flight.writeInt(254);

        writeFlight("BG-22");
        writeFlight("Shiraz");
        writeFlight("Tabriz");
        writeFlight("1401/12/12");
        writeFlight("22:30");
        option = findFlight("BG-22")+150;
        flight.seek(option);
        flight.writeInt(1100000);
        option = option +4;
        flight.seek(option);
        flight.writeInt(12);

        flight.close();

    }


}
