
import java.util.*;
import java.time.LocalDate;
import java.time.*;

class Book{
    String bName;
    String author;
    int availUnits;
    int borrowCount;
    double bPrice;
    String isbn;


    Book(String bName,String author,int availUnits,int borrowCount,double bPrice,String isbn){
        this.bName=bName;
        this.author=author;
        this.availUnits=availUnits;
        this.borrowCount=borrowCount;
        this.bPrice=bPrice;
        this.isbn=isbn;
    }
}
class Borrow{
    String bName;
    String isbn;
    String uID;
    LocalDate boDate;
    LocalDate  reDate;

    Borrow(String bName,String isbn,String uID,LocalDate boDate,LocalDate reDate){
        this.bName=bName;
        this.isbn=isbn;
        this.uID=uID;
        this.boDate=boDate;
        this.reDate=reDate;
    }
}
class User{
    String uName;
    String uPassword;
    double uWallet;
    String uID;
    ArrayList <Borrow> bookH;

    User(String uName, String uPassword, double uWallet, String uID,ArrayList <Borrow> bookH){
        this.uName=uName;
        this.uPassword=uPassword;
        this.uWallet = uWallet;
        this.uID=uID;
        this.bookH=bookH;
    }
}

public class Library {
    static ArrayList<Book> bookList = new ArrayList<Book>();
    static ArrayList<User> userList = new ArrayList<User>();
    static ArrayList<Borrow> borrowList = new ArrayList<Borrow>();
    static Scanner sc = new Scanner(System.in);


    static void ViewBook() {
        System.out.printf(" %-23s|| %-15s|| %-11s|| %-5s|| %-13s||\n", "Book Title", "Author Name", "Book Price", "ISBN", "Availability");
        String avail = "No";
        for (Book book : bookList) {
            if (book.availUnits > 0) avail = "Yes";
            System.out.printf(" %-23s|| %-15s|| %-11s|| %-5s|| %-13s||\n", book.bName, book.author, book.bPrice, book.isbn, avail);
        }
        System.out.println("<-------------------------------------------------------------------------------->");
//        System.out.println("Press Enter to continue...");
//        sc.nextLine();
//        String d=sc.nextLine();
    }
    private static void SearchBook() {
        System.out.println("Enter Book Title/ISBN no ");
        sc.nextLine();
        String bK = sc.nextLine();
        boolean flag = true;
        for (Book book : bookList) {
            if (book.isbn.equals(bK) || book.bName.equals(bK)) {
                System.out.printf(" %-23s|| %-15s|| %-11s|| %-5s|| %-13s||\n", "Book Title", "Author Name", "Book Price", "ISBN", "Availability");
                String avail = "No";
                if (book.availUnits > 0) avail = "Yes";
                System.out.printf(" %-23s|| %-15s|| %-11s|| %-5s|| %-13s||\n", book.bName, book.author, book.bPrice, book.isbn, avail);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Enter correct Book Name/ISBN no");
        }
    }
    private static void AddBook() {
        System.out.println("Enter Book Title : ");
        String bName = sc.nextLine().trim();
        System.out.println("Enter Author Name : ");
        String author = sc.nextLine().trim();
        System.out.println("Enter Book Availability : ");
        int availUnit = sc.nextInt();
        System.out.println("Enter Book Price : ");
        double bPrice = sc.nextInt();
        String isbn = "BK" + (bookList.size() + 1);
        bookList.add(new Book(bName, author, availUnit, 0, bPrice, isbn));
        System.out.println("Book Added Successfully!!");
    }
    private static void DeleteBook() {
        System.out.println("Enter Book Title/ISBN no to Delete");
        sc.nextLine();
        String bK = sc.nextLine();
        boolean flag = true;
        if (flag) {
            for (Book book : bookList) {
                if (book.isbn.equals(bK) || book.bName.equals(bK)) {
                    bookList.remove(book);
                    System.out.println("Book Deleted Successfully!");
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println("Enter correct Book Name/ISBN no,Retry!!");
        }
    }
    private static void ModifyBookDetails() {
        System.out.println("Enter Book Title/ISBN no ");
        sc.nextLine();
        String bK = sc.nextLine();
        boolean flag = true;
        if (flag) {
            for (Book book : bookList) {
                if (book.isbn.equals(bK) || book.bName.equals(bK)) {
                    System.out.println("1.Update Book Title\n2.Update Book Author Name\n3.Update Book Availability\n" +
                            "4.Update Book Price\n5.Change Book ISBN no\nEnter Your Choice");
                    int n = sc.nextInt();
                    switch (n) {
                        case 1:
                            System.out.println("Enter new book Title : ");
                            sc.nextLine();
                            book.bName = sc.nextLine().trim();
                            System.out.println("Book Title Changed Successfully");
                            break;
                        case 2:
                            System.out.println("Enter new Author Name : ");
                            sc.nextLine();
                            book.author = sc.nextLine().trim();
                            System.out.println("Book Author Changed Successfully");
                            break;
                        case 3:
                            System.out.println("Enter Book Availability : ");
                            book.availUnits = sc.nextInt();
                            System.out.println("Book Availability Updated Successfully");
                            break;
                        case 4:
                            System.out.println("Enter new Book Price : ");
                            book.bPrice = sc.nextInt();
                            System.out.println("Book Price Changed Successfully");
                            break;
                        case 5:
                            System.out.println("Enter new Book ISBN no : ");
                            sc.nextLine();
                            book.isbn = sc.nextLine().trim();
                            System.out.println("Book ISBN Changed Successfully");
                            break;
                        default:
                            System.out.println("Invalid Input! Retry!!");
                            ModifyBookDetails();
                    }
                    break;
                }
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Enter correct Book Name/ISBN no,Retry!!");
        }
    }
    private static void BorrowersDetails() {
        System.out.printf(" %-23s|| %-5s|| %-10s|| %-13s|| %-13s||\n", "Book Title", "ISBN", "User ID", "Borrow Date", "Ex-ReDate");
        for (Borrow borrow : borrowList) {
            System.out.printf(" %-23s|| %-5s|| %-10s|| %-13s|| %-13s||\n", borrow.bName, borrow.isbn, borrow.uID, borrow.boDate, borrow.reDate);
        }
    }
    private static void RemoveUs() {
        System.out.println("Enter User ID : ");
        sc.nextLine();
        String uId = sc.nextLine();
        boolean flag = true;
        for(User user : userList){
            if(user.uID.equals(uId)){
                System.out.print("Enter OK to Borrow '" + user.uID + "' Book ");
                String d = sc.next().toUpperCase(Locale.ROOT).trim();
                if (d.equals("OK")) {
                    userList.remove(user);
                    System.out.println(uId + " Removed Successfully!");
                    flag=false;
                    break;
                }
            }
        }
        if (flag) {
            System.out.println("User ID Not Found!");
        }
    }
    

    static boolean checkExistingInUser(String name) {
        for (User user : userList) {
            if (user.uName.equals(name)) return false;
        }
        return true;
    }
    private static void userSignup() {
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
                System.out.println("Security deposit amount should be above 1500.0 Rs");
                System.out.println("Enter Deposit Amount : ");
                usWallet = sc.nextDouble();
            } while (usWallet < 1499);
            ArrayList<Borrow> book = new ArrayList<>();
            userList.add(new User(uName, usPassword, usWallet, "UID" + (userList.size() + 1), book));
            System.out.println("Account Created Successfully!");
        } else System.out.println("User Name Already Exist!");
    }
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
    private static void UserFunction(User user) {
        int ch;
        do {
            System.out.println("----------- Welcome " + user.uName + " to Library -----------");
            System.out.println("1.View Book");
            System.out.println("2.Borrow Book");
            System.out.println("3.Borrow History");
            System.out.println("4.Return");
            System.out.println("5.Wallet");
            System.out.println("6.Exit");
            System.out.println("Enter your choice :");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    ViewBook();
                    break;
                case 2:
                    ViewBook();
                    borrowBook(user);
                    break;
                case 3:
                    borrowHistory(user);
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
    private static int len(User user){
        int c=0;
        for (int i = 0; i < borrowList.size(); i++) {
            if(borrowList.get(i).uID.equals(user.uID)){
                c++;
            }
        }
        return c;
    }
    private static void borrowBook(User user) {
        System.out.print("Enter the number of bookings : ");
        int boNo = sc.nextInt();
        int k = len(user);
        if (k+boNo>3) {
            System.out.println("User Book borrow limit is 3 ");
        } else {
            System.out.println("Enter Book ISBN no ");
            String[] bISBN = new String[boNo];
            for (int i = 0; i < boNo; i++) {
                System.out.print((i + 1) + ". ");
                bISBN[i] = sc.next();
            }
            for (String bK : bISBN) {
                for (Book book : bookList) {
                    if (book.isbn.equals(bK)) {
                        boolean fag = checkBk(user,bK);
                        if(fag){
                            if (user.uWallet > 500 && user.uWallet > book.bPrice) {
                                System.out.print("Enter OK to Borrow '" + book.bName + "' Book ");
                                String d = sc.next().toUpperCase(Locale.ROOT).trim();
                                if (d.equals("OK")) {
                                    Period diff;
                                    boolean flag = true;
                                    do {
                                        System.out.println("Enter expected Return Date(yyyy-mm-dd) : ");
                                        sc.nextLine();
                                        LocalDate reDate = LocalDate.parse(sc.next());
                                        LocalDate boDate = LocalDate.now();
                                        diff = Period.between(boDate, reDate);
                                        if (diff.getDays() > 1) {
                                            borrowList.add(new Borrow(book.bName, book.isbn, user.uID, boDate, reDate));
                                            user.bookH.add(new Borrow(book.bName, book.isbn, user.uID, boDate, reDate));
                                            System.out.println("'"+book.bName + "' Book borrowed Successfully!");
                                            book.borrowCount++;
                                            book.availUnits--;
                                            flag = false;
                                        } else System.out.println("Invalid return Date!!");
                                    } while (flag);
                                }
                            } else {
                                System.out.println("Your Wallet balance is too low! To purchase your Order!!");
                            }
                        }else {
                            System.out.println("'" + bK + "' Book Already Borrowed yet to return");
                        }
                    }
                }
            }
        }
    }
    private static boolean checkBk(User user,String bK) {

        for (int i = 0; i < borrowList.size(); i++) {
            if (borrowList.get(i).isbn.equals(bK) && borrowList.get(i).uID.equals(user.uID) ) {
                return false;
            }
        }
        return true;
    }
    private static void borrowHistory(User user) {
        System.out.printf(" %-23s|| %-5s|| %-13s|| %-13s|| %-16s||\n", "Book Title", "ISBN", "Borrow Date", "ReDate", "Status");
        for (int i = 0; i < user.bookH.size(); i++) {
            String s="Book Returned";
            for (Borrow borrow : borrowList) {
                if (user.bookH.get(i).isbn.equals(borrow.isbn)) {
                    s = "Yet to Return";
                    break;
                }
            }
            System.out.printf(" %-23s|| %-5s|| %-13s|| %-13s|| %-16s||\n", user.bookH.get(i).bName, user.bookH.get(i).isbn, user.bookH.get(i).boDate, user.bookH.get(i).reDate,s);
        }
    }
    private static void Return(User user) {
        int ch=0;
        do{
            System.out.println("1.Return Book");
            System.out.println("2.Book Miss");
            System.out.println("Enter Your Choice : ");
            ch = sc.nextInt();
            switch (ch){
                case 1:
                    bookReturn(user);
                    break;
                case 2:
                    bookMiss(user);
                default:
                    System.out.println("Invalid Input!,Retry!!");
            }

        }while(ch>2);
    }
    private static void bookReturn(zoho4.User user) {
        System.out.println("----- Book Return -----");
        System.out.print("Enter the number of return book : ");
        int boNo = sc.nextInt();
        System.out.println("Enter Book ISBN no ");
        String[] bISBN = new String[boNo];
        for (int i = 0; i < boNo; i++) {
            System.out.print((i + 1) + ". ");
            bISBN[i] = sc.next();
        }
        for (String bK : bISBN) {
            for (zoho4.Book book : bookList) {
                if (book.isbn.equals(bK)) {
                    int j = 1000;
                    for (int i = 0; i < user.bookH.size(); i++) {
                        if (user.bookH.get(i).isbn.equals(bK)) {
                            j = i;
                            break;
                        }
                    }
                    if (j == 1000) {
                        System.out.println(bK + " Book is not Borrowed please Try again");
                        break;
                    } else {
                        System.out.print("Enter OK to Return '" + book.bName + "' Book ");
                        String d = sc.next().toUpperCase(Locale.ROOT).trim();
                        if (d.equals("OK")) {
                            Period diff;
                            LocalDate Date = LocalDate.now();
                            diff = Period.between(user.bookH.get(j).boDate, Date);
                            if (diff.getDays() > 15) {
                                int n = diff.getDays() - 15;
                                double nAmt = n * 2;
                                if(nAmt<book.bPrice*0.5) {
                                    user.uWallet += book.bPrice - nAmt;
                                    System.out.println("Your Fined Amount Rs." + nAmt + " due to exceed of returning date.");
                                }
                                else {
                                    user.uWallet -=book.bPrice*0.5;
                                    System.out.println("You have been fined : "+book.bPrice*0.5);
                                }
                            }
                            for (int i = 0; i < borrowList.size(); i++) {
                                if (borrowList.get(i).isbn.equals(bK) && borrowList.get(i).uID.equals(user.uID) ) {
                                    borrowList.remove(i);
                                    break;
                                }
                            }
                            user.bookH.get(j).reDate = Date;
                            book.availUnits++;
                            System.out.println("Book Returned Successfully ");
                        }
                    }
                }
            }
        }
    }
    private static void bookMiss(User user) {
        System.out.print("Enter the number of Missed book : ");
        int boNo = sc.nextInt();
        System.out.println("Enter Book ISBN no ");
        String[] bISBN = new String[boNo];
        for (int i = 0; i < boNo; i++) {
            System.out.print((i + 1) + ". ");
            bISBN[i] = sc.next();
        }
        for (String bK : bISBN) {
            for (Book book : bookList) {
                if (book.isbn.equals(bK)) {
                    int j = 1000;
                    for (int i = 0; i < user.bookH.size(); i++) {
                        if (user.bookH.get(i).isbn.equals(bK)) {
                            j = i;
                            break;
                        }
                    }
                    if (j == 1000) {
                        System.out.println(bK + " Book is not Borrowed please Try again");
                        break;
                    } else {
                        user.uWallet -=book.bPrice*0.5;
                        System.out.println("You have been fined : "+book.bPrice*0.5);
                    }
                }
            }
        }
    }
    private static void walletBalance(User user) {
        System.out.println("User Name : "+user.uName+"\nUser wallet Balance : "+user.uWallet);
    }

    static void admin(){
        System.out.println("----- You Have Chosen ADMIN Login -----");
        System.out.println("Enter Admin Name : ");
        sc.nextLine();String aName = sc.nextLine();
        System.out.println("Enter Admin Password : ");
        String aPassword = sc.nextLine();
        if(aName.equals("admin")&&aPassword.equals("12345")) {
            int adCh = 0;
            do {
                System.out.println("---------- Welcome Admin ----------");
                System.out.println("1.View Available Books");
                System.out.println("2.Search Book");
                System.out.println("3.Add a Book");
                System.out.println("4.Delete a Book");
                System.out.println("5.Modify Book Details");
                System.out.println("6.Borrow Details");
                System.out.println("7.Remove User");
                System.out.println("8.Exit");
                System.out.println("Enter Choice : ");
                adCh = sc.nextInt();
                switch (adCh) {
                    case 1:
                        ViewBook();
                        break;
                    case 2:
                        SearchBook();
                        break;
                    case 3:
                        AddBook();
                        break;
                    case 4:
                        DeleteBook();
                        break;
                    case 5:
                        ModifyBookDetails();
                        break;
                    case 6:
                        BorrowersDetails();
                        break;
                    case 7:
                        RemoveUs();
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Invalid Input! Retry!!");
                        break;
                }
            } while (adCh != 8);
        }else {
            System.out.println("Incorrect Admin Name and Password! Retry!!");
            admin();
        }
    }
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
                    userSignup();
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
    public static void main(String[] args) {
        bookList.add(new Book("Da Vinci Code","Brown",10,0,100,"BK1"));
        bookList.add(new Book("Harry Potter","Rowling",10,0,200,"BK2"));
        bookList.add(new Book("The Deathly Hallows","J.K",10,0,300,"BK3"));
        ArrayList<Borrow> book = new ArrayList<>();
        userList.add(new User("Moulik","12345",2000,"UID1",book));
        int ch=0;
        do{
            System.out.println("----- Welcome To Library -----");
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
