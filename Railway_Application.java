// Admin Name : admin,Password : 12345;
// User Mobile No : 123456789,User Password : 12345;

import java.util.*;
import java.util.stream.IntStream;

class User{
    String name;
    String password;
    int phoneNo;
    User(String name,String password,int phoneNo){
        this.name=name;
        this.password=password;
        this.phoneNo=phoneNo;
    }
}
class ApprovalUser{
    String name;
    String password;
    int phoneNo;

    ApprovalUser(String name,String password,int phoneNo){
        this.name=name;
        this.password=password;
        this.phoneNo=phoneNo;
    }
}
class Train{
    String TrainName;
    String startPoint;
    String endPoint;
    int noOfStation;
    int noOfSeat;
    List<String> station;
    int noOfSeatAlotted;
    int[][] seatAlotted;

    Train(String TrainName,String startPoint,String endPoint,int noOfStation,int noOfSeat,List<String> station,int noOfSeatAlotted,int[][] seatAlotted){
        this.TrainName=TrainName;
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.noOfStation=noOfStation;
        this.noOfSeat=noOfSeat;
        this.station=station;
        this.noOfSeatAlotted=noOfSeatAlotted;
        this.seatAlotted=seatAlotted;
    }
}
class waitingList{
    Train train;
    int noOfPassengers;
    int st;
    int en;
    
    waitingList(Train train, int noOfPassengers, int st, int en){
        this.train=train;
        this.noOfPassengers=noOfPassengers;
        this.st=st;
        this.en=en;
        //this.availableTicket=availableTicket;
    }
}
public class Railway {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<User> user = new ArrayList<>();
    static ArrayList<ApprovalUser> appUser = new ArrayList<>();
    static ArrayList<waitingList> waitingLis = new ArrayList<>();

    // <---------------- ADMIN --------------->
    // To approve/reject user login request,approved details will be added to userList,rejected details will be deleted from waitingLList
    static void approveUser(){
        int i=0;
        while (true) {
            if (i >= appUser.size()) break;
            System.out.println("User Name : "+appUser.get(i).name);
                System.out.println("1.Approve or 2.Reject");
                int approval = sc.nextInt();
            if (approval == 1) {
                User us2 = new User(appUser.get(i).name,appUser.get(i).password,appUser.get(i).phoneNo);
                user.add(us2);
                System.out.println("Approved Successfully");
            } else {
                if(approval==2){
                    appUser.remove(i);
                }
                else System.out.println("Invalid Input");
            }
            i++;
        }
        System.out.println("No more Pending Approvals");
        }
    // To add Train
    static void addTrain() {
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        System.out.println("Enter Train Boarding Station Name : ");
        sc.nextLine();String bName = sc.nextLine();
        System.out.println("Enter Train Destination Station Name : ");
        sc.nextLine();String dName = sc.nextLine();
        System.out.println("Enter No Of Station : ");
        int noOfStation = sc.nextInt();
        System.out.println("Enter No Of Seat Available : ");
        int noOfSeat = sc.nextInt();
        int[][] seatAlotted = new int[noOfSeat][noOfStation];
        List<String> st = new ArrayList<>();
        System.out.println("Enter Stations Names : ");
        int i = 0;
        while (i < noOfStation) {
            System.out.println("Enter "+(i+1)+"  Station : ");
            String sn=sc.nextLine();
            st.add(sn);
            i++;
        }
        Train newTrain = new Train(tName,bName,dName,noOfStation,noOfSeat,st,0,seatAlotted);
        trains.add(newTrain);
    }
    // To declare Seat availability
    static void decSeat() {
        System.out.println("Train Name");
        int i = 0;
        while (i<trains.size()) {
            System.out.println(i+1+"."+trains.get(i).TrainName);
            i++;
        }
        System.out.println("Enter Choice : ");
        int n = sc.nextInt();
        if(n<=trains.size()){
            System.out.println("No of Seat Allotted : "+trains.get(n-1).noOfSeatAlotted);
            System.out.println("Enter No Of Available Seats");
            int noOfS = sc.nextInt();
            trains.get(n - 1).noOfSeat = noOfS;
            trains.get(n-1).seatAlotted=new int[noOfS][trains.get(n-1).noOfStation];
        }else System.out.println("Invalid Input!");
    }
    // To Display train details
    static void trainDetails(){
        viewTrains();
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        for (Train train : trains) {
            if (train.TrainName.equals(tName)) {
                System.out.println(String.format("Train Name : %s\nTrain Boarding Station Name : %s\n" +
                                "Train Destination Station Name : %s\n" +
                                "No Of Seat : %s\nNo Of Seat Allotted : %s\n",
                        train.TrainName, train.startPoint, train.endPoint,
                        train.noOfSeat, train.noOfSeatAlotted));

            }
            System.out.println("Seat Allotment : ");
            for (int i = 0; i < train.noOfSeat; i++) {
                for (int j = 0; j < train.noOfStation; j++) {
                    System.out.print(train.seatAlotted[i][j]);
                }
                System.out.println();
            }
            break;
        }
    }

    // <---------------- USER --------------->
    // Return available ticket Count
    static int availTic(Train train, int noOfPassengers, int st, int en){
        int availableTicket = 0;
        for (int k = 0; k < noOfPassengers; k++) {
            int seat = 0;
            if(k<train.noOfSeat) {
                for (int i = 0; i < train.noOfSeat; i++) {
                    for (int i1 = st; i1 < en; i1++) {
                        if (train.seatAlotted[i][i1] != 0 ) {
                            break;
                        } else seat++;
                    }
                    if (seat == en - st) {
                        ++availableTicket;
                        for (int i1 = st - 1; i1 < en; i1++) {
                            train.seatAlotted[i][i1] = 99;
                        }
                        break;
                    }
                }
            }else break;
        }
        for (int i = 0; i < train.noOfSeat; i++) {
            for (int j = 0; j < train.noOfStation; j++) {
                if(train.seatAlotted[i][j]==99) train.seatAlotted[i][j]=0;
            }
        }
        return availableTicket;
    }
    // allot ticket
    static void allotTicket(Train train, int noOfPassengers, int st, int en, int availableTicket){
        int allotted = train.noOfSeatAlotted;
        if(allotted==0) allotted++;
        if(availableTicket>0) {
            int k = 0;
            while (k < noOfPassengers) {
                int seat = 0;
                for (int i = 0; i < train.noOfSeat; i++) {
                    for (int i1 = st; i1 < en; i1++) {
                        if (train.seatAlotted[i][i1] != 0) {
                            break;
                        } else seat++;
                    }
                    if (seat == en - st) {

                        for (int i1 = st - 1; i1 < en; i1++) {
                            train.seatAlotted[i][i1] = allotted;
                        }
                        System.out.println("Your Seat No is : " + (i + 1) + "\nYour Ticket No : " + allotted++);
                        break;
                    }
                }
                k++;
            }
            train.noOfSeatAlotted = allotted;
            for (int i = 0; i < train.noOfSeat; i++) {
                for (int j = 0; j < train.noOfStation; j++) {
                    System.out.print(train.seatAlotted[i][j]);
                }
                System.out.println();
            }
        }
        if(noOfPassengers-availableTicket>0){
            waitingList wl = new waitingList(train,(noOfPassengers-availableTicket),st,en);
            waitingLis.add(wl);
        }
    }
    // allot ticket for waiting list passengers
    static void WaitingLisAllot(Train train, int noOfPassengers, int st, int en, int availableTicket){
        int allotted = train.noOfSeatAlotted;
        if(availableTicket>0) {
            for (int k = 0; k < noOfPassengers; k++) {
                int seat = 0;
                for (int i = 0; i < train.noOfSeat; i++) {
                    for (int i1 = st; i1 < en; i1++) {
                        if (train.seatAlotted[i][i1] != 99) {
                            break;
                        } else seat++;
                    }
                    if (seat == en - st) {
                        ++allotted;
                        for (int i1 = st - 1; i1 < en; i1++) {
                            train.seatAlotted[i][i1] = allotted;
                        }
                        break;
                    }
                }
            }
            train.noOfSeatAlotted = allotted;
        }

        if(noOfPassengers!=availableTicket){
            waitingList wl = new waitingList(train,(noOfPassengers-availableTicket),st,en);
            waitingLis.add(wl);
        }
    }
    //User Sign up
    static void userSignup() {
        System.out.println("Enter Your Mobile No : ");
        int uPhoneNo = sc.nextInt();
        boolean flag=true;
        int i = 0;
        while (true) {
            if (i >= user.size()) break;
            if(uPhoneNo==user.get(i).phoneNo){
                flag = false;
                break;
            }
            i++;
        }
        if(flag) {
            System.out.println("Enter Your Name : ");
            String uName = sc.nextLine();
            System.out.println("Enter Your Password : ");
            String uPassword = sc.nextLine();

            ApprovalUser aU = new ApprovalUser(uName, uPassword, uPhoneNo);
            appUser.add(aU);
            System.out.println("Account Created Successfully! \nWaiting for Approval! ");
        }else System.out.println("User Mobile No is Already Exist!");
    }
    //User Sign in
    public static void userSigning() {
        System.out.println("----- You Have Chosen User Login ----- ");
        System.out.println("Enter User Mobile No : ");
        int usMobileNo = sc.nextInt();
        System.out.println("Enter User Password : ");
        sc.nextLine();String usPassword = sc.nextLine();
        int pro=0;
        for(int i=0;i<user.size();i++){
            if(user.get(i).phoneNo==usMobileNo && user.get(i).password.equals(usPassword)){
                UserFunction(i);
                pro++;
                break;
            }
        }
        if(pro==0) System.out.println("User ID and Password Mismatch! \nRetry!");
    }
    // User actions
    static void UserFunction(int u){
        int usCh=0;
        do{
            System.out.println("1.View Trains and Availability");
            System.out.println("2.Book Tickets");
            System.out.println("3.Ticket Cancellation");
            System.out.println("4.Exit");
            System.out.println("Enter Choice : ");
            usCh = sc.nextInt();
            if(usCh == 1) {
                viewTrains();
            } else if (usCh == 2) {
                viewTrains();
                bookTicket();
            } else if (usCh == 3) {
                viewTrains();
                ticketCancel();
            } else if (usCh==4){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }while (usCh!=4);
    }
    // To view available train's
    static void viewTrains() {
        System.out.println("SNo  Train_Name          Boarding_Point     Destination_Point");
        IntStream.range(0, trains.size()).mapToObj(i -> String.format("%-4s %-20s %-18s %-19s", (i + 1), trains.get(i).TrainName, trains.get(i).startPoint, trains.get(i).endPoint)).forEach(System.out::println);
    }
    // To book train
    static void bookTicket(){
        System.out.println("----- Ticket Booking -----");
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        for (Train train : trains) {
            if (train.TrainName.equals(tName)) {
                System.out.println("Enter No Of Passengers : ");
                int noOfPassengers = sc.nextInt();
                for (int s = 0; s < train.station.size(); s++) {
                    System.out.println(train.station.get(s));
                }
                System.out.println("Enter Boarding Station No : ");
                int st = sc.nextInt();
                System.out.println("Enter Destination Station No : ");
                int en = sc.nextInt();


                int availableTicket = availTic(train, noOfPassengers, st, en);
                if (noOfPassengers != availableTicket) {
                    if (availableTicket>0) System.out.println(availableTicket +" : Seats are Available "+
                            (noOfPassengers-availableTicket)+" : Seat Will be in WaitingList!");
                    else if(availableTicket==0) System.out.println("Ticket's are Not Available");
                } else {
                    System.out.println(availableTicket+" : Seats are Available ");
                }

                System.out.println(" 1.Continue  2.Exit\nEnter Your choice : ");
                if (sc.nextInt() == 1) {
                    allotTicket(train, noOfPassengers, st, en, availableTicket);
                }
                break;
            }
        }
    }
    // To cancel train
    static void ticketCancel() {
        System.out.println("Enter Train Name : ");
        sc.nextLine();String tName = sc.nextLine();
        for (Train train : trains) {
            if (train.TrainName.equals(tName)) {
                System.out.println("Enter Ticket No : ");
                int ticNo = sc.nextInt();
                System.out.println("Enter Seat No : ");
                int seatNo = sc.nextInt();
                for (int j = 0; j < train.noOfStation; j++) {
                    if (train.seatAlotted[seatNo - 1][j] == ticNo) {
                        train.seatAlotted[seatNo - 1][j] = 0;
                    }
                }
                break;
            }
        }
        System.out.println("Ticket Canceled Successfully!");
        waitingAllot();
    }
    // waiting list function
    static void waitingAllot(){
        if(waitingLis.size()>0) {
            for (waitingList waitingLi : waitingLis) {
                int n = availTic(waitingLi.train, waitingLi.noOfPassengers, waitingLi.st, waitingLi.en);
                if (n > 0) {
                    WaitingLisAllot(waitingLi.train, waitingLi.noOfPassengers, waitingLi.st, waitingLi.en, n);
                    waitingLis.remove(waitingLi);
                }
            }
        }
    }

    //Admin actions
    static void admin(){
        System.out.println("Enter Your Name : ");
        sc.nextLine();String aName = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String aPassword = sc.nextLine();
        if(aName.equals("admin")&&aPassword.equals("12345")) {
            int adCh = 0;
            do {
                System.out.println("1.Username authentication");
                System.out.println("2.Add Trains, Routes and Stations");
                System.out.println("3.Declare Seats Availability");
                System.out.println("4.View Train Details");
                System.out.println("5.Exit");
                System.out.println("Enter Choice : ");
                adCh = sc.nextInt();
                if (adCh == 1) {
                    approveUser();
                } else if (adCh == 2) {
                    addTrain();
                } else if (adCh == 3) {
                    decSeat();
                } else if (adCh == 4) {
                    trainDetails();
                } else if (adCh == 5) { 
                    break;
                } else {
                    System.out.println("Invalid Input");
                }
            } while (adCh != 5);
        }else System.out.println("Incorrect Admin Name and Password!");
    }
    //User actions
    static void user(){
        int usOp=0;
        do {
            System.out.println("----- You Have Chosen User Login -----");
            System.out.println("1.User Signup");
            System.out.println("2.User Signing");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            usOp = sc.nextInt();
            if (usOp == 1) {
                userSignup();
            } else if (usOp == 2) {
                userSigning();
            } else if (usOp == 3) {
            } else {
                System.out.println("Invalid Input!");
            }
        }while(usOp!=3);
    }

    public static void main(String[] args) {
        User u1 = new User("Moulik","12345",123456789);
        user.add(u1);
        int[][] seat=new int[5][5];
        ArrayList<String> s = new ArrayList<String>();
        s.add("1.Kovai");s.add("2.Tanjur");s.add("3.Kabul");s.add("4.Sikim");s.add("5.Chennai");
        Train t1 = new Train("Kovai Express","Kovai","Chennai",5,5,s,0,seat);
        trains.add(t1);
        int ch=0;
        do{
            System.out.println("----- Welcome To Railway Reservation System -----");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            ch = sc.nextInt();
            if (ch == 1) {
                admin();
            } else if (ch == 2) {
                user();
            } else if (ch == 3) {
                System.out.println("Thanks for Using!");
            } else {
                System.out.println("Invalid Input");
            }
        }while (ch!=3);
    }
}
