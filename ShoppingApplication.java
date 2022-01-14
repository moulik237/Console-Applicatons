import java.util.ArrayList;
import java.util.Scanner;

class Merchant {
    String businessName;
    int password;
    String name;
    String status;
    String iD;

    Merchant(String businessName,int password,String name,String status,String iD){
        this.businessName = businessName;
        this.password = password;
        this.name=name;
        this.status=status;
        this.iD=iD;
    }
}
class Approval{
    String businessName;
    int password;
    String name;
    String status;

    Approval(String businessName,int password,String name,String status){
        this.businessName = businessName;
        this.password = password;
        this.name=name;
        this.status=status;
    }
}
class User {
    String mailID;
    int password;
    String name;
    int balance;
    String usId;

    User(String mailID,int password,String name,int balance,String usId){
        this.mailID=mailID;
        this.password=password;
        this.name=name;
        this.balance=balance;
        this.usId=usId;
    }
}
class Orders {
    String userId;
    String productId;
    String pName;
    int noUnits;
    int price;

    Orders(String userId,String productId,int noUnits,int price,String pName){
        this.userId=userId;
        this.productId=productId;
        this.noUnits=noUnits;
        this.price=price;
        this.pName=pName;
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

public class Shopping {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Merchant> merchantList = new ArrayList<>();
    public static ArrayList<Approval> approvalList = new ArrayList<>();
    public static ArrayList<Products> productList = new ArrayList<>();
    public static ArrayList<Orders> orderList = new ArrayList<>();
    static int userBill = 0;

    // To check Existing Business Name in merchantList,returns true if business name not found
    public static boolean checkExistingMe(String name){
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).businessName.equals(name)){
                return false;
            }
        }
        return true;
    }
    // To check Existing Business Name in approvalList,returns true if business name not found
    public static boolean checkExistingInUser(String mailId){
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).mailID.equals(mailId)){
                return false;
            }
        }
        return true;
    }
    // To check Existing Business Name in approvalList,returns true if business name not found
    public static boolean checkExistingInApproval(String name){
        for(int i=0;i<approvalList.size();i++){
            if(approvalList.get(i).businessName.equals(name)){
                return false;
            }
        }
        return true;
    }

    // To approve/reject merchant login request,approved details will be added to merchantList,rejected details will be deleted from approvalList
    public static void merchantApproval() {
        for(int i=0;i<approvalList.size();i++){
            if(approvalList.get(i).status.equals("No")){
                System.out.println("Merchant Business Name : "+approvalList.get(i).businessName);
                System.out.println("Merchant Name : "+approvalList.get(i).name);
                System.out.println("1.Approve or 2.Reject");
                int approval = sc.nextInt();
                if(approval==1){
                    approvalList.get(i).status="Yes";
                    int meId = (merchantList.size()+1);
                    Merchant user = new Merchant(approvalList.get(i).businessName,approvalList.get(i).password,approvalList.get(i).name,"Yes","MID"+meId);
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
    // To add merchant by admin,details will be added to merchantList
    public static void addMerchant() {
        System.out.println("Enter Business Name : ");
        sc.nextLine();String bName = sc.nextLine();
        boolean flag = checkExistingMe(bName);
        if (flag) {
            System.out.println("Enter Business Password : ");
            int mePassword = sc.nextInt();
            String meId = "MID" + (merchantList.size() + 1);
            System.out.println("Enter Your Name : ");
            String meName = sc.next();
            Merchant user = new Merchant(bName, mePassword,meName, "Yes", meId);
            merchantList.add(user);
            System.out.println("Merchant Added Successfully ");
        } else {
            System.out.println("Business Name already Exist");
        }

    }
    // To remove merchant by admin,details will be removed from merchantList
    public static void removeMerchant() {
        System.out.println("Enter Merchant Business Name to Remove : ");
        sc.nextLine();String merName = sc.nextLine();
        boolean flag = checkExistingMe(merName);
        //System.out.println(fla);
        if(flag==false){
            for(int i=0;i<merchantList.size();i++) {
                if (merchantList.get(i).businessName.equals(merName)) {
                    merchantList.remove(i);
                    System.out.println("Merchant Removed Successfully");
                    break;
                }
            }
        }else{
            System.out.println("Merchant Business Name Not Found!");
        }
    }
    // List all products in productList
    public static void listAllProducts() {
        System.out.println("S_No Product_Name Product_ID No_Of_Units Product_Price Discount_Price Merchant_ID");
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).noOfUnits>0) {
                System.out.println((i+1)+String.format("%-5s",".")+String.format("%-13s", productList.get(i).productName)
                        +String.format("%-11s", productList.get(i).productId) + String.format("%-12s", productList.get(i).noOfUnits)
                        +String.format("%-14s", productList.get(i).price)  +String.format("%-15s",productList.get(i).disPrice)
                        + String.format("%-10s", productList.get(i).merID));
            }
        }
    }

    // New merchant Sign in
    public static void becomeMerchant() {
        System.out.println("Enter Business Name : ");
        sc.nextLine();
        String bName = sc.nextLine();
        boolean flag = checkExistingMe(bName);
        boolean flag1 = checkExistingInApproval(bName);
        if(flag && flag1) {
            System.out.println("Enter Business Password : ");
            int mePassword = sc.nextInt();
            System.out.println("Enter Your Name : ");
            String meName = sc.next();
            Approval merchant = new Approval(bName,mePassword,meName,"No");
            approvalList.add(merchant);
            System.out.println("Waiting for Approval Your");
        } else {
            System.out.println("Business Name already Exist !");
        }
    }
    // To get merchant ID
    public static void getMerchantId(){
        System.out.println("Enter Business Name to get ID : ");
        sc.nextLine();
        String bName = sc.nextLine();
        int password;
        boolean flag = checkExistingMe(bName);
        boolean flag1 = checkExistingInApproval(bName);
        int k=0;
        if(flag==false) k=1;
        else if  (flag1==false) k=2;
        switch(k) {
            case 1:
                System.out.println("Enter Business Password : ");
                password = sc.nextInt();
                for (int i = 0; i < merchantList.size(); i++) {
                    if (merchantList.get(i).businessName.equals(bName) && merchantList.get(i).password==password) {
                        System.out.println("Your Business ID is " + merchantList.get(i).iD);
                    }
                }
                break;
            case 2:
                System.out.println("Enter Business Password : ");
                password = sc.nextInt();
                for (int i = 0; i < approvalList.size(); i++) {
                    if (approvalList.get(i).businessName.equals(bName) && approvalList.get(i).password==password) {
                        System.out.println(" Your Business Profile Waiting for Approval ");
                    }
                }
                break;
            default:
                System.out.println("Business ID Not Found !");
        }
    }
    // Merchant Login verification
    public static void merchantSigning() {
        System.out.println("----- You Have Chosen Merchant Login ----- ");
        System.out.println("Enter Merchant ID : ");
        String merchantId = sc.next();
        System.out.println("Enter User Password : ");
        int merchantPassword = sc.nextInt();
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).iD.equals(merchantId) && merchantList.get(i).password==merchantPassword){
                System.out.println("----- Hi! "+merchantList.get(i).name+" -----");
                merchantFunction(i);
            }
        }
    }

    // return no of product's the user is selling
    public static int noOfProduct(String k){
        int n=0;
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).merID.equals(k)){
                n++;
            }
        }
        return n;
    }
    // Merchant function
    public static void merchantFunction(int k){
        int meOp = 0;
        do {
            System.out.println("----- Welcome Back -----");
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
                    updateProduct(k);
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
    // To add products by merchant,details will be added to productList
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
    // To update product details by merchant,details will be updated in productList
    public static void updateProduct(int k){
        int n = noOfProduct(merchantList.get(k).iD);
        if(n>0) {
            System.out.println("Enter product ID : ");
            sc.nextLine();
            String pId = sc.nextLine();
            int up = 0;
            int g = 0;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).productId.equals(pId)) {
                    g = i;
                    break;
                }
            }
            do {
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
            } while (up != 5);
        }else System.out.println("No Product Found !");
    }
    // To remove products by merchant,details will be removed from productList
    public static void removeProducts(int k){
        System.out.println("Enter product ID : ");
        sc.nextLine();String pId = sc.nextLine();

        for(int i=0;i<productList.size();i++){
            if(productList.get(i).productId.equals(pId) && productList.get(i).merID.equals(merchantList.get(k).iD)){
                productList.remove(i);
                break;
            }
        }
        System.out.println("product Removed Successfully!");
    }
    // Will list all products sold by merchant from productList
    public static void listMyProduct(int k){
        int n = noOfProduct(merchantList.get(k).iD);
        if(n>0) {
        String a = merchantList.get(k).iD;
            System.out.println("Product_No Product_Name Product_ID No_Of_Units Product_Price Discount_Price Merchant_ID");
            for(int i=0;i<productList.size();i++){
                if(productList.get(i).merID.equals(a)){
                    System.out.println(String.format("%-12s",productList.get(i).productNo)+String.format("%-13s", productList.get(i).productName)
                            +String.format("%-11s", productList.get(i).productId) + String.format("%-12s", productList.get(i).noOfUnits)
                            +String.format("%-14s", productList.get(i).price)  +String.format("%-15s", productList.get(i).disPrice)
                            + String.format("%-10s", productList.get(i).merID));
                }
            }
        }else System.out.println("No Product Found!");
    }

    // Return product index No in productList
    public static int productId(String productId){
        int i;
        for(i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return i;
            }
        }
        return i;
    }
    // List All products and total cost in users cart from orderList
    public static void viewProduct(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).usId.equals(orderList.get(i).userId)){
                System.out.println("Product ID : "+orderList.get(i).productId+";  Product Name : "+orderList.get(i).pName+";  No of Units : "+orderList.get(i).noUnits);
                total+=orderList.get(i).price*orderList.get(i).noUnits;
            }
        }
        System.out.println("Total Amount : Rs."+total+".00");
    }
    // Remove products in users cart from orderList
    public static void removeProduct(int k){
        System.out.println("Enter Remove product ID or Enter 0 : ");
        sc.nextLine();
        String a = sc.nextLine();
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).usId.equals(orderList.get(i).userId) && orderList.get(i).productId.equals(a)){
                orderList.remove(i);
                break;
            }
        }
    }

    // View cart and actions
    public static void viewCart(int k) {
        int ch=0;
        do{
            System.out.println("1.Remove Products from cart");
            System.out.println("2.Check Out");
            System.out.println("3.Continue Shopping");
            ch=sc.nextInt();
            switch (ch) {
                case 1:
                    if(userBill>0) {
                        for (int i = 0; i < orderList.size(); i++) {
                            if (userList.get(k).usId.equals(orderList.get(i).userId)) {
                                System.out.println("Product ID : " + orderList.get(i).productId + ";  Product Name : " + orderList.get(i).pName + ";  No of Units : " + orderList.get(i).noUnits);
                            }
                        }
                        removeProduct(k);
                    }else System.out.println("No Product Found on Your Cart!\n Add Product To Your Cart");
                    break;
                case 2:

                    if(userBill>0) {
                        checkOut(k);
                    }else System.out.println("No Product Found on Your Cart!\n Add Product To Your Cart");
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }while (ch!=3);
    }
    // Return product price
    public static int productPrice(String productId){
        int price=0;
        for(int i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return productList.get(i).price;
            }
        }
        return price;
    }
    // Return Product Name
    public static String productName(String productId){
        String price="";
        for(int i=0;i<productList.size();i++){
            if(productId.equals(productList.get(i).productId)){
                return productList.get(i).productName;
            }
        }
        return price;
    }
    // To add products to cart
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
            String pName = productName(productId);
            Orders addToCart = new Orders(userList.get(k).usId,productId,noUnits,proPrice,pName);
            orderList.add(addToCart);
        }
        userBill = totalPrice(k);
    }
    // To Check Out
    public static void checkOut(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++) {
            if (userList.get(k).usId.equals(orderList.get(i).userId)) {
                int a = productId(orderList.get(i).productId);
                total+=orderList.get(i).price*orderList.get(i).noUnits;
                productList.get(a).noOfUnits-=orderList.get(i).noUnits;
                orderList.remove(i);
                userBill=total;
            }
        }
        userList.get(k).balance-=total;
        userBill=0;
        System.out.println("Thanks for Shopping!");
    }
    // Return User Bill Amount
    public static int totalPrice(int k){
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            if(userList.get(k).usId.equals(orderList.get(i).userId)){
                total+=orderList.get(i).price*orderList.get(i).noUnits;
            }
        }
        return total;
    }
    // User actions
    public static void UserFunction(int k){
        int usOp = 0;
        do {
            System.out.println("----- Welcome "+userList.get(k).name+" -----");
            System.out.println("1.View Product");
            System.out.println("2.View Cart");
            System.out.println("3.Check Balance");
            System.out.println("4.Exit");
            System.out.print("Enter Your Choice : ");
            usOp = sc.nextInt();
            switch (usOp) {
                case 1:
                    listAllProducts();
                    addToCart(k);
                    break;
                case 2:
                    if(userBill>0) {
                        viewProduct(k);
                        viewCart(k);
                    }else System.out.println("No Product Found on Your Cart!\n Add Product To Your Cart");
                    break;
                case 3:
                    System.out.println("Your Balance : "+userList.get(k).balance);
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (usOp != 4);
    }
    //User Sign in
    public static void userSignup() {
        System.out.println("Enter User Mail ID : ");
        sc.nextLine();String usMail = sc.nextLine();
        boolean flag = checkExistingInUser(usMail);
        if(flag) {
            System.out.println("Enter User Password : ");
            int usPassword = sc.nextInt();
            System.out.println("Enter User Name : ");
            sc.nextLine();
            String usName = sc.nextLine();
            System.out.println("Enter Deposit Amount : ");
            int usBalance = sc.nextInt();
            User user = new User(usMail, usPassword, usName, usBalance, "UID" + (userList.size() + 1));
            userList.add(user);
            System.out.println("Account Created Successfully!");
        }else System.out.println("User Mail ID Already Exist!");
    }
    //User Login
    public static void userSigning() {
        System.out.println("----- You Have Chosen User Login ----- ");
        System.out.println("Enter User Mail ID : ");
        String usMail = sc.next();
        System.out.println("Enter User Password : ");
        int usPassword = sc.nextInt();

        int pro=0;
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).mailID.equals(usMail) && userList.get(i).password==usPassword){
                UserFunction(i);
                pro++;
                break;
            }
        }
        if(pro==0) System.out.println("User ID and Password Mismatch! \nRetry!");
    }

    //Admin actions
    public static void admin() {
        System.out.println("----- You Have Chosen Admin Login -----");
        System.out.println("Enter Admin Name : ");
        String adminName = sc.next();
        System.out.println("Enter Password");
        int adminPassword = sc.nextInt();
        int adop = 0;
        if (adminName.equals("admin") && adminPassword == 12345) {
            do {
                System.out.println("-- Welcome Admin --");
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
    //Merchant actions
    public static void merchant(){
        int meOp=0;
        do{
        System.out.println("----- You Have Chosen Merchant Login -----");
        System.out.println("1. Become a Merchant");
        System.out.println("2. Get Merchant ID");
        System.out.println("3. Merchant Signing");
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
                    merchantSigning();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }while(meOp!=4);
    }
    //User actions
    public static void user() {
        int usOp=0;
        do{
        System.out.println("----- You Have Chosen User Login -----");
        System.out.println("1.User Signup");
        System.out.println("2.User Signing");
        System.out.println("3.Exit");
        System.out.println("Enter Choice : ");
        usOp = sc.nextInt();
            switch (usOp){
                case 1:
                    userSignup();
                    break;
                case 2:
                    userSigning();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input!");

            }
        }while(usOp!=3);
    }

    public static void main(String[] args) {
        Merchant u1 = new Merchant("business 1",12345,"Charan","Yes","MID1");
        Merchant u2 = new Merchant("business 2",12345,"Arun","Yes","MID2");
        merchantList.add(u1);
        merchantList.add(u2);
        Products p1 = new Products(101,"Mobile01","MID11",100,10000,100,"MID1");
        productList.add(p1);
        Products p2 = new Products(102,"TV01","MID12",250,10000,100,"MID1");
        productList.add(p2);
        User us1 = new User("user1@gmail.com",12345,"Ram",100000,"UID1");
        userList.add(us1);
        int ch = 0;
        do{
            System.out.println("----- Welcome To Shopping -----");
            System.out.println("1.Admin Login");
            System.out.println("2.Merchant Login");
            System.out.println("3.User Login");
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
                    System.out.println("Invalid Input Retry!");
                    break;
            }
        }while( ch!=4);
        System.out.println("Thanks for Using!");
    }
}
