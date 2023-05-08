import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        UserFile userFile = new  UserFile();

        userFile.writeUser("gffhfhh" , "5555");
        userFile.readFileUser();


    }
}