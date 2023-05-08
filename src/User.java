import java.io.RandomAccessFile;

public class User {



    private String name;
    private String pass;
    private long budget;

    public Ticket [] tickets = new  Ticket[15];




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

//    =================================================================================================================>

//     [ WRITE FILE OF USER ]

    public void writeToFile(){



    }


//    =================================================================================================================>



}
