import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Login {

    Scanner cin = new Scanner(System.in);
    public static long pointer;


//    =================================================================================================================>

//     [ START ]

    public void start() throws IOException {

        pointer = 0;

        UserFile userFile = new UserFile();

        RandomAccessFile user = new RandomAccessFile("User.dat", "rw");

        welcome(userFile , user);

        user.close();
    }


//    =================================================================================================================>

//     [ WELCOME ]

    public void welcome(UserFile userFile , RandomAccessFile user) throws IOException {

        System.out.println("[  Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up ");

        int option ;

        option = cin.nextInt();

        switch (option){

            case 1:{

                singIn(userFile , user);
                break;

            }

            case 2:{

                singUp(userFile , user);
                break;

            }

            default: welcome(userFile , user);


        }
        welcome(userFile , user);

    }

//    =================================================================================================================>

//     [ SING IN ]

    public void singIn(UserFile userFile , RandomAccessFile user) throws IOException {


        String name, pass;


        System.out.println("[ Enter your name ] ");

        name = cin.next();

        if (findUserName(userFile,name , user) != -2 )
        {

            pointer =findUserName(userFile,name,user);

            System.out.println("[ Enter your pass ] ");

            pass = cin.next();


            if (findUserPass(userFile , pass ) == 1) {

                System.out.println("admin");
            }

            else if (findUserPass(userFile , pass ) == -1)
            {

                System.out.println("[ your password is not correct ]");
                try{Thread.sleep(500);}catch(InterruptedException e) {};

            }

            else
            {
                System.out.println("passenger");
            }

        } else
        {
            System.out.println("[ This username not found ]");
            try{Thread.sleep(500);}catch(InterruptedException e) {};

        }

    }


//    =================================================================================================================>
//    [ FIND USER NAME ]

    public long findUserName(UserFile userFile , String info , RandomAccessFile user ) throws IOException {


        for (long i = 0 ; i < user.length() ; i = i + 104) {

            if ( info.equals(userFile.fixToRead(i)) ){

                pointer = i;
                return i;

            } else if (info.equals("Admin")) {

                return -1;
            }
        }

        return -2;
    }

//    =================================================================================================================>
//    [ FIND PASSWORD OF USER ]

    public long findUserPass(UserFile userFile , String pass  ) throws IOException {

        long point;
        point = pointer + 50;

        if (userFile.fixToRead(point).equals(pass)){

            return 0;

        } else if (pass.equals("Admin")) {

            return 1;

        } else {

            return -1;

        }

    }

//    =================================================================================================================>
//    [ SING UP ]

    public void singUp(UserFile userFile , RandomAccessFile user) throws IOException {

        String name , pass;

        System.out.println("[ Enter your name ] ");

        name = cin.next();

        System.out.println("[ Enter your pass ] \n[ Or if you want back enter X ] ");

        pass = cin.next();

        if ( pass.equals("X") ){

            welcome(userFile , user);
        }

        else if ( findUserName(userFile , name , user) == -2 )
        {

            userFile.writeUser(name);
            userFile.writeUser(pass);
            user.writeInt(0);
            don();

            try{Thread.sleep(500);}catch(InterruptedException e) {};

            System.out.println("passenger");

        }
        else
        {
            System.out.println("[ This user already exist ]");

            try{Thread.sleep(500);}catch(InterruptedException e) {};

            welcome(userFile , user);
        }

    }

//    =================================================================================================================>
//    [ PRINT DONE ]

    public static void don(){

        System.out.println("[ don ] ");
    }
}
