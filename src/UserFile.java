import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UserFile {

    protected RandomAccessFile fileUser;

    private int SIZEu = 20;

    public UserFile() throws FileNotFoundException {

        this.fileUser = new  RandomAccessFile("User" , "rw");

    }
//    =================================================================================================================>

//     [ WRITE FILE OF USER ]

    public void writeUser(String name , String password) throws IOException {

        fixUserInfoToWrite(name);
        fixUserInfoToWrite(password);

        fileUser.writeChars(name);
        fileUser.writeChars(password);

    }

//    =================================================================================================================>

//     [ FIX NAME AND PASSWORD FOR WRITE ]

    public String fixUserInfoToWrite(String info ){


        for (int i = 0; i < 20; i++) {

            if (info.length() < SIZEu){

                info += " ";



            }

        }

        return info.substring(0 , SIZEu) ;


    }

//    =================================================================================================================>

//     [ READ NAME AND PASSWORD ]

    public  void readFileUser() throws IOException {

        String name , pass;

        name = fixUserInfoToRead();
        pass = fixUserInfoToRead();

    }

//    =================================================================================================================>

//     [ Fix NAME AND PASSWORD FOR READ ]

    public  String fixUserInfoToRead() throws IOException {

        String info;

        info = "";

        for (int i = 0; i < SIZEu; i++) {

            info += fileUser.readChar();

        }

        return info.trim();

    }

}
