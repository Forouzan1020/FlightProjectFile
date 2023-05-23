import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UserFile {

    RandomAccessFile user;

    static int SIZEu = 25;

    public UserFile() throws IOException {

        this.user = new RandomAccessFile("User.dat", "rw");
        user.close();

    }
//    =================================================================================================================>

//     [ WRITE FILE : USER ]

    public void writeUser(String info) throws IOException {

        info = fixToWrite(info);

        this.user = new RandomAccessFile("User.dat", "rw");

        user.seek(user.length());

        user.writeChars(info);

        user.close();

    }

//    =================================================================================================================>

//     [ FIX NAME AND PASSWORD FOR WRITE ]

    public String fixToWrite(String info) throws IOException {

        for (int i = info.length(); i < SIZEu; i++) {

            info += " ";

        }

        return info;
    }

//    =================================================================================================================>

//    [ Fix NAME AND PASSWORD FOR READ]

    public String fixToRead(long pointer) throws IOException {

        String info = "";

        this.user = new RandomAccessFile("User.dat", "rw");

        user.seek(pointer);

        for (int i = 0; i < SIZEu; i++) {

            info += user.readChar();

        }

        user.close();

        return info.trim();

    }




}
