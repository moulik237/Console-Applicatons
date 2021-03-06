// Admin Name : admin,Password : 12345;
// User Name : Moulik,Password : 12345;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class vDetails{
    String vId;
    String CoName;
    String vehicleNo;
    double km;
    double totKm;
    String service;
    String vAvail;

    vDetails(String vId,String CoName,String vehicleNo,double km,double totKm,String service,String vAvail){
        this.vId=vId;
        this.CoName=CoName;
        this.vehicleNo=vehicleNo;
        this.km=km;
        this.totKm=totKm;
        this.service=service;
        this.vAvail=vAvail;
    }
}
class Vehicle{
    String modelName;
    ArrayList<vDetails> vDetail;
    String vType;

    Vehicle(String modelName,ArrayList<vDetails> vDetail,String vType){
        this.modelName=modelName;
        this.vDetail=vDetail;
        this.vType=vType;
    }
}
class Borrow{
    String modelName;
    String vehicleNo;
    String vNo;
    String vType;
    String uID;
    LocalDate boDate;
    String reState;

    Borrow(String modelName,String vehicleNo,String vNo,String vType,String uID,LocalDate boDate,String reStat){
        this.modelName=modelName;
        this.vehicleNo=vehicleNo;
        this.vNo=vNo;
        this.vType=vType;
        this.uID=uID;
        this.boDate=boDate;
        this.reState=reStat;
    }
}
class User {
    String uName;
    String uPassword;
    double uWallet;
    String uID;
    ArrayList<Borrow> borrowH;


    User(String uName, String uPassword, double uWallet, String uID, ArrayList<Borrow> borrowH) {
        this.uName = uName;
        this.uPassword = uPassword;
        this.uWallet = uWallet;
        this.uID = uID;
        this.borrowH = borrowH;
    }
}
class BorrowHistory{
    Borrow borrowH;
    double km;
    double bill;
    LocalDate reDate;

    BorrowHistory(Borrow borrowH, double km, double bill, LocalDate reDate){
        this.borrowH=borrowH;
        this.km=km;
        this.bill=bill;
        this.reDate=reDate;
    }
}

public class VehicleRental {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    static ArrayList<Borrow> borrowList = new ArrayList<>();
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<BorrowHistory> borrowHistories = new ArrayList<>();

    //Admin actions
    private static void admin() {
        System.out.println("----- You Have Chosen ADMIN Login -----");
        System.out.println("Enter Admin Name : ");
        sc.nextLine();String aName = sc.nextLine();
        System.out.println("Enter Admin Password : ");
        String aPassword = sc.nextLine();
        if(aName.equals("admin")&&aPassword.equals("12345")) {
            int adCh = 0;
            do {
                System.out.println("----- Welcome ADMIN -----");
                System.out.println("1.View Vehicle");
                System.out.println("2.Search Vehicle");
                System.out.println("3.Borrower Details");
                System.out.println("4.User Details");
                System.out.println("5.Service vehicle");
                System.out.println("6.Add User");
                System.out.println("7.Add vehicle");
                System.out.println("8.Add New Model");
                System.out.println("9.Borrow History");
                System.out.println("10.Exit");
                System.out.println("Enter Your Choice");
                adCh=sc.nextInt();
                switch (adCh) {
                    case 1:
                        ViewVehicle();
                        break;
                    case 2:
                        SearchVehicle();
                        break;
                    case 3:
                        BorrowersDetails();
                        break;
                    case 4:
                        userDetails();
                        break;
                    case 5:
                        serviceVehicle();
                        break;
                    case 6:
                        addUser();
                        break;
                    case 7:
                        addVehicle();
                        break;
                    case 8:
                        addNewVehicle();
                        break;
                    case 9:
                        borrowHistory();
                        break;
                    default:
                        System.out.println("Invalid Input! Retry!!");
                        break;
                }
            }while (adCh != 10);
        }
    }
    //To view all vehicle by admin
    private static void ViewVehicle() {
        System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-6s|| %-8s||\n", "Model Name", "Vno", "Company", "TNno", "Km", "ser", "Avail");
        for (Vehicle vehicle : vehiclesList) {
            for (int i = 0; i < vehicle.vDetail.size(); i++) {
                System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-6s|| %-8s||\n", vehicle.modelName, vehicle.vDetail.get(i).vId, vehicle.vDetail.get(i).CoName, vehicle.vDetail.get(i).vehicleNo,vehicle.vDetail.get(i).km,vehicle.vDetail.get(i).service,vehicle.vDetail.get(i).vAvail);
            }
        }
    }
    //To search vehicle by its Name and vehicle Id
    private static void SearchVehicle() {
        System.out.print("Enter Model Name : ");
        sc.nextLine();
        String ve = sc.nextLine();
        boolean flag = true;
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.modelName.equals(ve)){
                for(int i =0;i<vehicle.vDetail.size();i++){
                    System.out.print("Enter Vehicle ID : ");
                    String veh = sc.nextLine();
                    if(vehicle.vDetail.get(i).vId.equals(veh)) {
                        System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-6s|| %-8s||\n", "Model Name", "Vno", "Company", "TNno", "Km", "ser", "Avail");
                        System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-6s|| %-8s||\n", vehicle.modelName, vehicle.vDetail.get(i).vId, vehicle.vDetail.get(i).CoName, vehicle.vDetail.get(i).vehicleNo,vehicle.vDetail.get(i).km,vehicle.vDetail.get(i).service,vehicle.vDetail.get(i).vAvail);
                        flag = false;
                        break;
                    }
                }
                break;
            }
        }
        if(flag) System.out.println("Enter correct Model Name");
    }
    //To display vehicle borrowers details
    private static void BorrowersDetails() {
        if(borrowList.size()==0) System.out.println("No vehicles Borrowed!");
        else {
            int i = 0;
            System.out.printf(" %-15s|| %-15s|| %-15s|| %-15s|| %-18s|| %-15s||\n", "Vehicle Mode", "User ID", "Vehicle ID", "Model Name", "Vehicle Number", "Borrow Date");
            while (i < borrowList.size()) {
                System.out.printf(" %-15s|| %-15s|| %-15s|| %-15s|| %-18s|| %-15s||\n", borrowList.get(i).vType, borrowList.get(i).uID, borrowList.get(i).vNo, borrowList.get(i).modelName, borrowList.get(i).vehicleNo, borrowList.get(i).boDate);
                i++;
                System.out.println();
            }
        }
    }
    //To view user details by their user ID
    private static void userDetails() {
        System.out.println("Enter User ID : ");
        sc.nextLine();
        String uId = sc.nextLine();
        for (User user : userList) {
            if (user.uID.equals(uId)) {
                System.out.println("User ID : "+user.uID+
                        "\nUser Name : "+user.uName+
                        "\nUser wallet : Rs."+user.uWallet);
                break;
            }
        }
    }
    //To view vehicle which are in service condition n service them
    private static void serviceVehicle() {
        int k=0;
        for (Vehicle vehicle : vehiclesList) {
            int j=0;
            while(j<vehicle.vDetail.size()) {
                if (vehicle.vDetail.get(j).km >= 3000) {
                    System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-8s|| %-6s|| %-8s||\n", "Model Name", "Vno", "Company", "TNno", "Km", "totKm", "ser", "Avail");
                    for (int i = 0; i < vehicle.vDetail.size(); i++) {
                        System.out.printf(" %-15s|| %-6s|| %-10s|| %-6s|| %-6s|| %-8s|| %-6s|| %-8s||\n", vehicle.modelName, vehicle.vDetail.get(i).vId, vehicle.vDetail.get(i).CoName, vehicle.vDetail.get(i).vehicleNo, vehicle.vDetail.get(i).km, vehicle.vDetail.get(i).totKm, vehicle.vDetail.get(i).service, vehicle.vDetail.get(i).vAvail);
                        System.out.print("Enter 'ok' to Service : ");
                        k++;
                        String s = sc.nextLine();
                        if(s.equals("ok")){
                            vehicle.vDetail.get(i).km=0;
                            System.out.println("Serviced Successfully!");
                            System.out.println("-------");
                        }
                    }
                }
                j++;
            }
        }
        if(k>0) {
            System.out.println("Enter Model Name :");
            sc.nextLine();
            String mName = sc.nextLine();
            System.out.println("Enter Vehicle to service : ");
            sc.nextLine();
            String vId = sc.nextLine();
            for (Vehicle vehicle : vehiclesList) {
                int j = 0;
                if (vehicle.modelName.equals(mName)) {
                    while (j < vehicle.vDetail.size()) {
                        if (vehicle.vDetail.get(j).vId.equals(vId)) {
                            vehicle.vDetail.get(j).km = 0;
                        }
                        j++;
                    }
                    break;
                }
            }
            System.out.println(mName + " Serviced Successfully!");
        }else System.out.println("No vehicle to Service!");
    }
    //User Sign in / add new user
    private static void addUser() {
        System.out.println("Enter User Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        boolean flag = checkExistingInUser(uName);
        if (flag) {
            System.out.println("Enter User Password : ");
            sc.nextLine();
            String usPassword = sc.nextLine();
            double usWallet;
            do {
                System.out.println("Security deposit amount should be above 3000.0 Rs");
                System.out.println("Enter Deposit Amount : ");
                usWallet = sc.nextDouble();
            } while (usWallet < 2999);
            ArrayList<Borrow> ub = new ArrayList<>();
            userList.add(new User(uName, usPassword, usWallet, "UID" + (userList.size() + 1),ub));
            System.out.println("Account Created Successfully!");
        } else System.out.println("User Name Already Exist!");
    }
    // To add new vehicle to the existing model
    private static void addVehicle() {
        System.out.print("Enter Vehicle Model Name : ");
        String modelName= sc.nextLine();
        for(Vehicle vehicle:vehiclesList){
            if(vehicle.modelName.equals(modelName)){
                int n = totNofVehicle();
                System.out.print("Enter Company Name : ");
                String cName = sc.nextLine();
                System.out.print("Enter Vehicle No : ");
                String vNo = sc.nextLine();
                vehicle.vDetail.add(new vDetails("VN" + n, cName, vNo, 0, 0, "Yes", "Yes"));
                System.out.println("Vehicle Added Successfully");
            }
        }
    }
    // To create new vehicle model and add vehicle
    private static void addNewVehicle() {
        ArrayList<vDetails> ve = new ArrayList<>();
        System.out.print("Enter Vehicle Model Name : ");
        String modelName= sc.nextLine();
        System.out.print("Enter Vehicle Type : ");
        String vType = sc.nextLine();
        System.out.print("Enter No of Vehicles : ");
        int k = sc.nextInt();
        int i=0;
        while(i<k) {
            int n = totNofVehicle();
            System.out.print("Enter Company Name : ");
            String cName = sc.nextLine();
            System.out.print("Enter Vehicle No : ");
            String vNo = sc.nextLine();
            ve.add(new vDetails("VN" + n, cName, vNo, 0, 0, "Yes", "Yes"));
            i++;
        }
        vehiclesList.add(new Vehicle(modelName, ve, vType));
    }
    //To view borrow history
    private static void borrowHistory() {
        if(borrowHistories.size()==0) System.out.println("No vehicles Borrowed!");
        else {
            int i = 0;
            System.out.printf(" %-15s|| %-12s|| %-14s|| %-15s|| %-10s|| %-15s|| %-15s|| %-5s|| %-6s|| \n",
                    "Vehicle Type", "Vehicle ID", "Model Name", "Vehicle Number", "User ID", "Borrow Date", "Return Date", "Km", "Bill");
            while (i < borrowHistories.size()) {
                System.out.printf(" %-15s|| %-12s|| %-14s|| %-15s|| %-10s|| %-15s|| %-15s|| %-5s|| %-6s|| \n",
                        borrowHistories.get(i).borrowH.vType, borrowHistories.get(i).borrowH.vNo,
                        borrowHistories.get(i).borrowH.modelName, borrowHistories.get(i).borrowH.vehicleNo,
                        borrowHistories.get(i).borrowH.uID, borrowHistories.get(i).borrowH.boDate,
                        borrowHistories.get(i).reDate, borrowHistories.get(i).km, borrowHistories.get(i).bill);
                i++;
                System.out.println();
            }
        }
    }


    //User
    static void user(){
        int usOp=0;
        do{
            System.out.println("----- You Have Chosen USER Login -----");
            System.out.println("1.User Signup");
            System.out.println("2.User Signing");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            usOp = sc.nextInt();
            switch (usOp){
                case 1:
                    addUser();
                    break;
                case 2:
                    userSigning();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input!,Retry!!");

            }
        }while(usOp!=3);
    }
    //User Login
    private static void userSigning() {
        System.out.println("Enter User Name : ");
        sc.nextLine();
        String uName = sc.nextLine();
        System.out.println("Enter User Password : ");
        String usPassword = sc.nextLine();
        boolean flag = true;
        for (User user : userList) {
            if (user.uName.equals(uName) && user.uPassword.equals(usPassword)) {
                UserFunction(user);
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Enter correct User Name and Password!,Retry!!");
        }
    }
    // User actions
    private static void UserFunction(User user) {
        int ch;
        do {
            System.out.println("----------- Welcome " + user.uName + " to Vehicle Rental Application -----------");
            System.out.println("1.View Vehicle");
            System.out.println("2.Borrow Vehicle");
            System.out.println("3.Borrow History");
            System.out.println("4.Return");
            System.out.println("5.Wallet");
            System.out.println("6.Exit");
            System.out.println("Enter your choice :");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    userView();
                    break;
                case 2:
                    borrowVehicle(user);
                    break;
                case 3:
                    uBorrowHistory(user);
                    break;
                case 4:
                    Return(user);
                    break;
                case 5:
                    walletBalance(user);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Input!,Retry!!");
                    break;
            }
        } while (ch != 6);
    }
    // To display all available vehicle
    private static void userView(){
        System.out.println("1.car");
        System.out.println("2.bike");
        System.out.println("3.Both");
        System.out.print("Enter your choice : ");
        String d = sc.next();
        boolean flag = true;
        for (Vehicle vehicle : vehiclesList) {
            if(vehicle.vType.equals(d)) {
                for (int i = 0; i < vehicle.vDetail.size(); i++) {
                    if (vehicle.vDetail.get(i).vAvail.equals("Yes")) {
                        System.out.println(++i + "." + vehicle.modelName+" "+vehicle.vType);
                        flag=false;
                        break;
                    }
                }
            }
        }
        if(flag){
            for (Vehicle vehicle : vehiclesList) {
                for (int i = 0; i < vehicle.vDetail.size(); i++) {
                    if (vehicle.vDetail.get(i).vAvail.equals("Yes")) {
                        System.out.println(++i + "." + vehicle.modelName+"  "+vehicle.vType);
                        break;
                    }
                }
            }
        }
    }
    //To borrow vehicle
    private static void borrowVehicle(User user) {
        System.out.print("Enter the number of bookings : ");
        int boNo = sc.nextInt();
        int k = len(user);
        if (k+boNo>2) {
            System.out.println("User Vehicle borrow limit is 2 ");
        } else{
            availVehicle();
            System.out.println("Enter Madel name : ");
            sc.nextLine();
            String mName=sc.nextLine();
            boolean flag = value(user,mName);
            if(flag) {
                for (Vehicle vehicle : vehiclesList) {
                    if (vehicle.modelName.equals(mName)) {
                        String vn = selectable(vehicle);
                        String vNo = getvNo(vehicle, vn);
                        System.out.print("Enter OK to Borrow '" + vehicle.modelName + "' " + vehicle.vType + " ");
                        String d = sc.next().toUpperCase(Locale.ROOT).trim();
                        if (d.equals("OK")) {
                            LocalDate boDate = LocalDate.now();
                            borrowList.add(new Borrow(mName, vNo, vn, vehicle.vType, user.uID, boDate,"Yet To Return"));
                            user.borrowH.add(new Borrow(mName, vNo, vn, vehicle.vType, user.uID, boDate,"Yet To Return"));
                            for (int i = 0; i < vehicle.vDetail.size(); i++) {
                                if (vehicle.vDetail.get(i).vId.equals(vn)) {
                                    vehicle.vDetail.get(i).vAvail = "No";
                                    break;
                                }
                            }
                            System.out.println(vehicle.modelName + " Booked successfully!");
                            veBorrDetails(user);
                        }
                    }
                }
            }else{
                System.out.println("Your wallet Balance is Too low!");
            }
        }
    }
    //To view user's borrow history
    private static void uBorrowHistory(User user){
        if(user.borrowH.size()==0){
            System.out.println("No vehicle borrowed");
        }else {
            int i = user.borrowH.size()-1;
            System.out.printf(" %-15s|| %-15s|| %-18s|| %-15s|| %-15s||\n", "Vehicle Mode", "Model Name", "Vehicle Number", "Borrow Date", "Status");

            while (i >=0) {
                System.out.printf(" %-15s|| %-15s|| %-18s|| %-15s|| %-15s||\n", user.borrowH.get(i).vType, user.borrowH.get(i).modelName, user.borrowH.get(i).vehicleNo, user.borrowH.get(i).boDate, user.borrowH.get(i).reState);
                i--;
                System.out.println();
            }
        }
    }
    //To return vehicle
    private static void Return(User user) {
        System.out.println("Enter Model name : ");
        sc.nextLine();
        String mName = sc.nextLine();
        System.out.print("Enter vehicle No : ");
        String vNo = sc.nextLine();
        int km=0;
        for (Borrow vehicle : borrowList) {
            if (vehicle.modelName.equals(mName) && vehicle.uID.equals(user.uID)){// && vehicle.vehicleNo.equals(vNo)) {
                System.out.println("Enter km Travelled : ");
                km = sc.nextInt();
                double bill = km * 10;
                Period diff;
                LocalDate reDate = LocalDate.now();
                diff = Period.between(vehicle.boDate, reDate);
                if(diff.getDays()>1) {
                    int n = diff.getDays();
                    int b=n*50;
                    bill+=b;
                }
                borrowHistories.add(new BorrowHistory(vehicle, km, bill, reDate));
                user.uWallet-=bill;
                System.out.println("Your Bill Rs."+bill+".");
                System.out.println("Returned Successfully!");
                borrowList.remove(vehicle);
                break;
            }
        }
        int i=0;
        while(i<user.borrowH.size()) {
            if (user.borrowH.get(i).vNo.equals(vNo)) {
                user.borrowH.get(i).reState = "Returned";
            }
            i++;
        }
        for (Vehicle ve : vehiclesList) {
            if (ve.modelName.equals(mName)) {
                for (int j = 0; j < ve.vDetail.size(); j++) {
                    if (ve.vDetail.get(j).vehicleNo.equals(vNo)) {
                        ve.vDetail.get(j).vAvail = "Yes";
                        ve.vDetail.get(j).totKm+=km;
                        ve.vDetail.get(j).km+=km;
                        break;
                    }
                }
                break;
            }
        }
    }
    //To view user wallet balance
    private static void walletBalance(User user) {
        System.out.println("Your wallet Balance : Rs"+user.uWallet);
    }


    //Return Vehicle No
    private static String getvNo(Vehicle vehicle,String vn) {
        String s="";
        for(int i=0;i<vehicle.vDetail.size();i++){
            if(vehicle.vDetail.get(i).vId.equals(vn)){
                s=vehicle.vDetail.get(i).vehicleNo;
                break;
            }
        }
        return s;
    }
    // Return borrowed vehicle detail
    private static void veBorrDetails(User user) {
        System.out.println("Vehicle Mode : "+user.borrowH.get(borrowList.size()-1).vType+
                "\nModel Name : "+user.borrowH.get(borrowList.size()-1).modelName+
                "\nVehicle Number : "+user.borrowH.get(borrowList.size()-1).vehicleNo+
                "\nBorrow Date : "+user.borrowH.get(borrowList.size()-1).boDate);
    }
    //return Vehicle ID which has low km
    private static String selectable(Vehicle vehicle) {
        double min = vehicle.vDetail.get(0).km;
        String vn = vehicle.vDetail.get(0).vId;
        for(int i=1;i<vehicle.vDetail.size();i++){
            if(vehicle.vDetail.get(i).vAvail.equals("Yes")) {
                if(vehicle.vDetail.get(i).km<=min && vehicle.vDetail.get(i).km<=3000){
                    min = vehicle.vDetail.get(i).km;
                    vn = vehicle.vDetail.get(i).vId;
                }
            }
        }
        return vn;
    }
    // To Check existing user name
    private static boolean checkExistingInUser(String uName) {
        for (User user : userList) {
            if (user.uName.equals(uName)) return false;
        }
        return true;
    }
    // Return user borrowed vehicle count
    private static int totNofVehicle() {
        int count=0;
        for (Vehicle vehicle : vehiclesList) {
            count += vehicle.vDetail.size();
        }
        return count;
    }
    //Return user borrowed vehicle count
    private static int len(User user){
        int c=0;
        for (Borrow borrow : borrowList) {
            if (borrow.uID.equals(user.uID)) {
                c++;
            }
        }
        return c;
    }
    //Return available vehicles model name
    private static void availVehicle() {
        int i=1;
        for (Vehicle vehicle : vehiclesList) {
            for (int j = 0; j < vehicle.vDetail.size(); j++) {
                if (vehicle.vDetail.get(i).vAvail.equals("Yes")) {
                    System.out.println(i +"."+vehicle.modelName);
                    i++;
                    break;
                }
            }
        }
    }
    //To check whether weather user wallet amount is greater than the require amount
    private static boolean value(User user,String mName){
        for(Vehicle ve : vehiclesList){
            if(ve.modelName.equals(mName)){
                if(ve.vType.equals("car")){
                    if(user.uWallet>=10000) return true;
                }
                else if(ve.vType.equals("bike")){
                    if(user.uWallet>=3000) return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        ArrayList<vDetails> ve = new ArrayList<>();
        vehiclesList.add(new Vehicle("Honda-i10",ve,"car"));
        vehiclesList.get(0).vDetail.add(new vDetails("VN1","Honda","TN06",0,0,"Yes","Yes"));
        vehiclesList.get(0).vDetail.add(new vDetails("VN2","Honda","TN01",0,0,"Yes","Yes"));
        //System.out.println(vehicles.get(0).vDetail.size());
        ArrayList<Borrow> ub = new ArrayList<>();
        userList.add(new User("12345","12345",10000,"UID1",ub));
        int ch=0;
        do{
            System.out.println("----- Welcome To Vehicle Rental Application -----");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            System.out.println("Enter Choice : ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    admin();
                    break;
                case 2:
                    user();
                    break;
                case 3:
                    System.out.println("Thanks for Using!");
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }while (ch!=3);
    }
}
