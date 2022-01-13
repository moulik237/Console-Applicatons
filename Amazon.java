package zoho2;


import java.util.ArrayList;
import java.util.Scanner;

class Merchant {
    String businessName;
    int password;
    String status;
    String iD;

    Merchant(String businessName,int password,String status,String iD){
        this.businessName = businessName;
        this.password = password;
        this.status=status;
        this.iD=iD;
    }
}
class Approval{
    String businessName;
    int password;
    String status;


    Approval(String businessName,int password,String status){
        this.businessName = businessName;
        this.password = password;
        this.status=status;
    }
}
class User {
    String mailID;
    int password;
    int balance;
    String usId;

    User(String mailID,int password,int balance,String usId){
        this.mailID=mailID;
        this.password=password;
        this.balance=balance;
        this.usId=usId;
    }
}
class Orders {
    String userId;
    String productId;
    int noUnits;
    int price;

    Orders(String userId,String productId,int noUnits,int price){
        this.userId=userId;
        this.productId=productId;
        this.noUnits=noUnits;
        this.price=price;
    }
}
class Products {

    int productNo;
    String productName;
    String productId;
    int noOfUnits;
    int price;
    int disPrice;
    String merID;

    Products(int productNo,String productName,String productId,int noOfUnits,int price,int disPrice,String merID){
        this.productNo=productNo;
        this.productName = productName;
        this.productId = productId;
        this.noOfUnits = noOfUnits;
        this.price = price;
        this.disPrice = disPrice;
        this.merID = merID;
    }
}

public class Amazon {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Merchant> merchantList = new ArrayList<>();
    public static ArrayList<Approval> approvalList = new ArrayList<Approval>();
    public static ArrayList<Products> productList = new ArrayList<>();
    public static ArrayList<Orders> orderList = new ArrayList<>();


    public static boolean checkExistingUs(String name){
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).businessName.equals(name)){
                return false;
            }
        }
        return true;
    }
    public static boolean checkExistingInApproval(String name){
        for(int i=0;i<approvalList.size();i++){
            if(approvalList.get(i).businessName.equals(name)){
                return false;
            }
        }
        return true;
    }


    public static void merchantApproval() {
        for(int i=0;i<approvalList.size();i++){
            if(approvalList.get(i).status.equals("No")){
                System.out.println("Merchant Business Name : "+approvalList.get(i).businessName);
                //System.out.println("Merchant ID : "+approvalList.get(i).iD);
                System.out.println("1.Approve or 2.Reject");
                int approval = sc.nextInt();
                if(approval==1){
                    approvalList.get(i).status="Yes";
                    int usId = (merchantList.size()+1);
                    Merchant user = new Merchant(approvalList.get(i).businessName,approvalList.get(i).password, "Yes","MID"+usId);
                    merchantList.add(user);
                    System.out.println("Approved Successfully");
                }else if(approval==2){
                    approvalList.remove(i);
                }
                else System.out.println("Invalid Input");
            }
        }
        System.out.println("No more Pending Approvals");
    }
    public static void addMerchant() {
        System.out.println("Enter Business Name");
        sc.nextLine();
        String bName = sc.nextLine();
        boolean flag = checkExistingUs(bName);
        if (flag) {
            System.out.println("Enter Business Password");
            int usPassword = sc.nextInt();
            String usId = "MID" + (merchantList.size() + 1);

            Merchant user = new Merchant(bName, usPassword, "Yes", usId);
            merchantList.add(user);
            System.out.println("User Added Successfully ");
        } else {
            System.out.println("Business Name already Exist");
        }

    }
    public static void removeMerchant() {
        System.out.println("Enter Merchant Business Name to Remove : ");
        sc.nextLine();
        String merName = sc.next();
        boolean flag = checkExistingUs(merName);
        if(flag){
            for(int i=0;i<merchantList.size();i++) {
                if (merchantList.get(i).businessName == merName) {
                    merchantList.remove(i);
                    System.out.println("Merchant Removed Successfully");
                    break;
                }
            }
        }else{
            System.out.println("Merchant Business Name Not Found!");
        }
    }
    public static void listAllProducts() {
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).noOfUnits>0) {
                System.out.println(productList.get(i).productNo + " " + productList.get(i).productName
                        + " " + productList.get(i).productId + " " + productList.get(i).noOfUnits
                        + " " + productList.get(i).price + " " + productList.get(i).disPrice
                        + " " + productList.get(i).merID);
            }
        }
    }

    public static void becomeMerchant() {
        System.out.println("Enter Business Name");
        sc.nextLine();
        String bName = sc.nextLine();
        boolean flag = checkExistingUs(bName);
        boolean flag1 = checkExistingInApproval(bName);
        if(flag && flag1) {
            System.out.println("Enter Business Password");
            int usPassword = sc.nextInt();
//            int usId = (merchantList.size()+1);
            Approval user = new Approval(bName, usPassword, "No");
            approvalList.add(user);
            System.out.println("Waiting for Approval Your");
        } else {
            System.out.println("Business Name already Exist");
        }
    }
    public static void getMerchantId(){
        System.out.println("Enter Business Name to get ID: ");
        sc.nextLine();
        String name = sc.nextLine();
        int password;
        boolean flag = checkExistingUs(name);
        boolean flag1 = checkExistingInApproval(name);
        int k=0;
        if(flag==false) k=1;
        else if  (flag1==false) k=2;
        switch(k) {
            case 1:
                System.out.println("Enter Business Password : ");
                password = sc.nextInt();
                for (int i = 0; i < merchantList.size(); i++) {
                    if (merchantList.get(i).businessName.equals(name) && merchantList.get(i).password==password) {
                        System.out.println("Your Business ID is " + merchantList.get(i).iD);
                    }
                }
                break;
            case 2:
                System.out.println("Enter Business Password : ");
                password = sc.nextInt();
                for (int i = 0; i < approvalList.size(); i++) {
                    if (approvalList.get(i).businessName.equals(name) && approvalList.get(i).password==password) {
                        System.out.println(" Your Business Profile Waiting for Approval ");
                    }
                }
                break;
            default:
                System.out.println("Business Name Not Found !");
        }
    }
    public static void merchantLogin() {
        System.out.println("Enter Merchant ID and Password");
        String merchantId = sc.next();
        int merchantPassword = sc.nextInt();
//        boolean flag = checkExistingUs(merchantName);
//        int pro = 0;
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).iD.equals(merchantId) && merchantList.get(i).password==merchantPassword){
                merchantFunction(i);
//                pro++;
            }
        }
//        if(pro==0){
//            System.out.println("Check Merchant ID and Password");
//            break;
//        }
    }

    public static int noOfProduct(String k){
        int n=0;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).merID.equals(k)){
                n++;
            }
        }
        return n;
    }
    public static void merchantFunction(int k){
        int meOp = 0;
        do {
            System.out.println("--Welcome Merchant--");
            System.out.println("1.Add Product");
            System.out.println("2.Update Product");
            System.out.println("3.Remove Product");
            System.out.println("4.List My Product");
            System.out.println("5.Exit");
            System.out.print("Enter Your Choice : ");
            meOp = sc.nextInt();
            String s = merchantList.get(k).iD;
            int no = noOfProduct(s);
            switch (meOp) {
                case 1:
                    addProducts(k,no);
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProducts(k);
                    break;
                case 4:
                    listMyProduct(k);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (meOp != 5);
    }
    public static void addProducts(int k,int n){
        int pNo = n+101;
        System.out.println("Enter Product Name : ");
        sc.nextLine();String pName = sc.nextLine();
        String pId = merchantList.get(k).iD+(n+1);
        System.out.println("Enter Available Units : ");
        int aUnit = sc.nextInt();
        System.out.println("Enter Product Price : ");
        int pPrice = sc.nextInt();
        System.out.println("Enter Discount Price : ");
        int dPrice = sc.nextInt();
        Products addProduct = new Products(pNo,pName,pId,aUnit,pPrice,dPrice,merchantList.get(k).iD);
        productList.add(addProduct);
    }
    public static void updateProduct(){
        System.out.println("Enter product ID : ");
        sc.nextLine();String pId = sc.nextLine();
        int up=0;int g=0;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).productId.equals(pId)){
                g=i;
                break;
            }
        }
        do{
            System.out.println("1.Update Product Name");
            System.out.println("2.Update No Units");
            System.out.println("3.Update Product Price");
            System.out.println("4.Update Product Discount Price");
            System.out.println("5.Exit");
            up = sc.nextInt();
            switch (up) {
                case 1:
                    System.out.println("Enter Product Name : ");
                    sc.nextLine();
                    String pName = sc.nextLine();
                    productList.get(g).productName = pName;
                    break;
                case 2:
                    System.out.println("Enter Available Units : ");
                    int aUnit = sc.nextInt();
                    productList.get(g).noOfUnits = aUnit;
                    break;
                case 3:
                    System.out.println("Enter Product Price : ");
                    int pPrice = sc.nextInt();
                    productList.get(g).price = pPrice;
                    break;
                case 4:
                    System.out.println("Enter Discount Price : ");
                    int dPrice = sc.nextInt();
                    productList.get(g).disPrice = dPrice;
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while(up!=5);
    }
    public static void removeProducts(int k){
        System.out.println("Enter product ID : ");
        sc.nextLine();String pId = sc.nextLine();
//        System.out.println("Enter Product Name : ");
//        sc.nextLine();String pName = sc.nextLine();
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).productId.equals(pId) && productList.get(i).merID.equals(merchantList.get(k).iD)){
                productList.remove(i);
                break;
            }
        }
        System.out.println("product Removed Successfully");
    }
    public static void listMyProduct(int k){
        String a = merchantList.get(k).iD;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).merID.equals(a)){
                System.out.println(productList.get(i).productNo+" "+productList.get(i).productName
                        +" "+productList.get(i).productId+" "+productList.get(i).noOfUnits
                        +" "+productList.get(i).price+" "+productList.get(i).disPrice
                        +" " +productList.get(i).merID);
            }
        }
    }

    public static int productId(String productId){
        int i;
        for(i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return i;
            }
        }
        return i;
    }
    public static void viewProduct(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).usId.equals(orderList.get(i).userId)){
                System.out.println("Product ID : "+orderList.get(i).productId+" No of Units : "+orderList.get(i).noUnits);
                total+=orderList.get(i).price*orderList.get(i).noUnits;
            }
        }
        System.out.println("Total Amount : Rs."+total+".00");
    }
    public static void removeProduct(int k){
        System.out.println("Enter Remove product ID");
        sc.nextLine();
        String a = sc.nextLine();
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).usId.equals(orderList.get(i).userId) && orderList.get(i).productId.equals(a)){
                orderList.remove(i);
                break;
            }
        }
    }
    public static void viewCart(int k) {
        int ch=0;
        do{
            System.out.println("1.View Products");
            System.out.println("2.Remove Products from cart");
            System.out.println("3.Check Out");
            System.out.println("4.Continue Shopping");
            ch=sc.nextInt();
            switch (ch){
                case 1:
                    viewProduct(k);
                    break;
                case 2:
                    for(int i=0;i<orderList.size();i++){
                        if(userList.get(k).usId.equals(orderList.get(i).userId)){
                            System.out.println("Product ID : "+orderList.get(i).productId+"; No of Units : "+orderList.get(i).noUnits);
                        }
                    }
                    removeProduct(k);
                    break;
                case 3:
                    checkOut(k);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while (ch!=4);
    }
    public static int productPrice(String productId){
        int price=0;
        for(int i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return productList.get(i).price;
            }
        }
        return price;
    }
    public static void addToCart(int k){
        int no=0;
        System.out.println("Enter no of Items : ");
        no = sc.nextInt();
        for(int i=0;i<no;i++){
            System.out.println("Enter no of Product ID : ");
            sc.nextLine();String productId=sc.nextLine();
            System.out.println("Enter no of Units : ");
            int noUnits = sc.nextInt();
            int proPrice = productPrice(productId);
            Orders addToCart = new Orders(userList.get(k).usId,productId,noUnits,proPrice);
            orderList.add(addToCart);
        }
    }
    public static void checkOut(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++) {
            if (userList.get(k).usId.equals(orderList.get(i).userId)) {
                int a = productId(orderList.get(i).productId);
                total+=orderList.get(i).price*orderList.get(i).noUnits;
                productList.get(a).noOfUnits-=orderList.get(i).noUnits;
            }
        }
        userList.get(k).balance-=total;
        System.out.println("Thanks for Shopping !");
    }
    public static void UserFunction(int k){
        int usOp = 0;
        do {
            System.out.println("--Welcome User--");
            System.out.println("1.View Product");
            System.out.println("2.View Cart");
            System.out.println("3.Check Balance");
            System.out.println("4.Check Out");
            System.out.println("5.Exit");
            System.out.print("Enter Your Choice : ");
            usOp = sc.nextInt();
            switch (usOp) {
                case 1:
                    listAllProducts();
                    addToCart(k);
                    break;
                case 2:
                    viewCart(k);
                    break;
                case 3:
                    System.out.println("Your Balance : "+userList.get(k).balance);
                    break;
                case 4:
                    checkOut(k);
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (usOp != 5);
    }
    public static void userSignup() {
        System.out.println("Enter User Mail ID : ");
        sc.nextLine();String usMail = sc.nextLine();
        System.out.println("Enter User Password : ");
        int usPassword = sc.nextInt();
        System.out.println("Enter Deposit Amount : ");
        int usBalance = sc.nextInt();
        User user = new User(usMail,usPassword,usBalance,"UID"+(userList.size()+1));
        userList.add(user);
    }
    public static void userLogin() {
        System.out.println("Enter User Mail ID : ");
        sc.nextLine();String usMail = sc.nextLine();
        System.out.println("Enter User Password : ");
        int usPassword = sc.nextInt();
        //int d = (int)userList.get(i).password
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).mailID.equals(usMail) && userList.get(i).password==usPassword){
                UserFunction(i);
                break;
            }
        }
    }

    public static void admin() {
        System.out.println("You Have Chosen Admin Login");
        System.out.println("Enter Admin Name and Password");
        String adminName = sc.next();
        int adminPassword = sc.nextInt();
        int adop = 0;
        if (adminName.equals("admin") && adminPassword == 12345) {
            do {
                System.out.println("--Welcome Admin--");
                System.out.println("1.Merchant Approval List");
                System.out.println("2.Add Merchant");
                System.out.println("3.Remove Merchant");
                System.out.println("4.List All Products");
                System.out.println("5.Exit");
                System.out.print("Enter Your Choice : ");
                adop = sc.nextInt();
                switch (adop) {
                    case 1:
                        merchantApproval();
                        break;
                    case 2:
                        addMerchant();
                        break;
                    case 3:
                        removeMerchant();
                        break;
                    case 4:
                        listAllProducts();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } while (adop != 5);
        } else {
            System.out.println("check Admin Name and Password");
        }
    }
    public static void merchant(){
        int meOp=0;
        do{
        System.out.println("You Have Chosen User Login");
        System.out.println("1. Become a Merchant");
        System.out.println("2. Get Merchant ID");
        System.out.println("3. Merchant Login");
        System.out.println("4. Exit");
        System.out.println("Enter Choice : ");
        meOp = sc.nextInt();
            switch (meOp){
                case 1:
                    becomeMerchant();
                    break;
                case 2:
                    getMerchantId();
                    break;
                case 3:
                    merchantLogin();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while(meOp!=4);
    }
    public static void user() {
        int usOp=0;
        do{
        System.out.println("You Have Chosen User Login");
        System.out.println("1.User Signup");
        System.out.println("2.User Login");
        System.out.println("3.Exit");
        System.out.println("Enter Choice : ");
        usOp = sc.nextInt();
            switch (usOp){
                case 1:
                    userSignup();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input");

            }
        }while(usOp!=3);
    }

    public static void main(String[] args) {
        Merchant u1 = new Merchant("business 1",12345,"Yes","MID1");
        Merchant u2 = new Merchant("business 2",12345,"Yes","MID2");
        merchantList.add(u1);
        merchantList.add(u2);
        Products p1 = new Products(101,"Mobile01","MID11",100,10000,100,"MID1");
        productList.add(p1);
        User us1 = new User("user1@gmail.com",12345,100000,"UID1");
        userList.add(us1);
        int ch = 0;
        do{
            System.out.println("Welcome To Amazon");
            System.out.println("1.Admin Login");
            System.out.println("2.Merchant Login");
            System.out.println("3.User");
            System.out.println("4.Exit");
            System.out.print("Enter Choice : ");


            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    admin();
                    break;
                case 2:
                    merchant();
                    break;
                case 3:
                    user();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input Retry");
                    break;
            }
        }while( ch!=4);
        System.out.println("Thanks for Using");
    }
}