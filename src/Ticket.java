import java.io.RandomAccessFile;

public class Ticket {


    protected RandomAccessFile fileTicket;

    private int SIZEt = 10;

    public  static int ticketGeneratorCounter ;
    private int ticketId;
    private String flightId;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
