import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws IOException {
//
//        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        Login login = new Login();
        UserFile userFile = new UserFile();
//        userFile.writeUser("name");
//        userFile.writeUser("pass");
//        user.seek(user.length());
//        user.writeInt(0);
        userFile.print();
//        user.close();
//        login.start();


    }
}