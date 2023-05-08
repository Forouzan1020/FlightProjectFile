import javax.swing.plaf.PanelUI;
import java.util.Scanner;

public class Login {

    Scanner cin = new Scanner(System.in);
    public static int loggedInIndex;
    public static String done = "done";

//    =================================================================================================================>

//     [ START ]

    public void start(){

        Flight flight = new Flight();
        User user = new User();
        Ticket ticket = new Ticket();


    }


//    =================================================================================================================>

//     [ WELCOME ]

    public void welcome(){

        System.out.println("[  Welcome to airline reservation system ] \n\n [ Menu option ] \n\n [1] Sing in \n [2] Sing up ");

        int option;
        option = cin.nextInt();

        switch (option){

            case 1:{

            }

            case 2:{

            }

            default: welcome();


        }
        welcome();

    }

//    =================================================================================================================>

//     [ SING IN ]
//
//    public void singIn(){
//
//        String name, pass;
//
//
//
//        System.out.println("[ Enter your name ] ");
//
//        name = cin.next();
//
//        if (checkName(name, users) == 0 || checkName(name, users) == 1)
//        {
//
//            System.out.println("[ Enter your pass ] ");
//
//            pass = cin.next();
//
//
//
//            if (checkSing(name, pass, users) == -1) {
//
//                admin.adminMenu(users ,flights ,admin , userAction,flightAction);
//            }
//
//            else if (checkSing(name, pass, users) == -2)
//            {
//
//                System.out.println("[ your password is not correct ]");
//                try{Thread.sleep(500);}catch(InterruptedException e) {};
//                welcomeMenu(admin ,  userAction ,  flightAction ,  users ,  flights);
//
//            }
//
//            else
//            {
//                loggedInIndex=checkSing(name, pass, users);
//                userAction.passengerMenu(flights ,users ,admin , userAction,flightAction);
//
//            }
//
//        } else
//        {
//            System.out.println("[ This username not found ]");
//            try{Thread.sleep(500);}catch(InterruptedException e) {};
//            welcomeMenu(admin ,  userAction ,  flightAction ,  users ,  flights);
//        }
//
//    }

}
