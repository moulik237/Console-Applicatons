import java.util.Scanner;

class userdata {
    String name;
    int pin;
    int balance;
    String acId;
    userdata(String name, int pin, int balance, String acId){
        this.name=name;
        this.pin=pin;
        this.balance=balance;
        this.acId=acId;
    }
}

class ATM {
    static Scanner sc = new Scanner(System.in);
    static int[] noOfCurrency = {1, 2, 1, 1};
    static int[] currency = {100, 200, 500, 2000};
    static int total = 3000;
    static userdata u[] = new userdata[4];

    // <---------------- ADMIN --------------->
    // To load amount in ATM machine
    static void addAmount() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter No of " + currency[i] + ": ");
            noOfCurrency[i] += sc.nextInt();
        }
    }
    // To check ATM machine amount
    static void atmBalance() {
        int tot = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println("No of " + currency[i] + "->" + noOfCurrency[i]);
            tot += currency[i] * noOfCurrency[i];
        }
        total = tot;
        System.out.println(total);
    }

    // <---------------- USER --------------->
    // return remainder amount value
    static int remainder(int withDrawAm) {
        for (int j = 3; j >= 0; j--) {
            int n = noOfCurrency[j];
            int k = currency[j];
            while (withDrawAm >= k && n > 0) {
                withDrawAm -= k;
                n--;
            }
        }
        return withDrawAm;
    }
    // To withdraw amount from the ATM machine
    static void withdraw(int k) {
        System.out.println("Enter Withdraw Amount ");
        System.out.println("Multiples Of ");
        for (int i = 0; i < 4; i++) {
            if (noOfCurrency[i] > 0)
                System.out.print(currency[i] + "s ");
        }
        int withDrawAmt = sc.nextInt();
        if (withDrawAmt > total && u[k].balance<withDrawAmt){
            System.out.println("Enter Minimum Amount ");
        } else {
            int res = remainder(withDrawAmt);
            if (res == 0) {
                total -= withDrawAmt;
                u[k].balance -= withDrawAmt;
                for (int j = 3; j >= 0; j--) {
                    while (withDrawAmt >= currency[j] && noOfCurrency[j] > 0) {
                        withDrawAmt -= currency[j];
                        noOfCurrency[j]--;
                        noOfNote++;
                    }
                }
                System.out.println("Amount Withdraw Successfully!");
                System.out.println("Collect Your Amount");
            } else {
                System.out.println("Retry");
            }
        }
    }
    // To Check User account Balance
    static void checkBalance(int k) {
        System.out.println("Balance : " + u[k].balance);
    }
    // To Change User  Account Pin
    static void pinChange(int k) {
        System.out.println("Enter Your Old Pin : ");
        int n = sc.nextInt();
        if(n==u[k].pin)
            u[k].pin=sc.nextInt();
        System.out.println("Your Pin Has Been changed Successfully!");
    }
    // To deposit amount
    static void deposit(int k) {

        for (int i = 0; i < 4; i++) {
            System.out.println("Enter No of " + currency[i] + "->");
            int n = sc.nextInt();
            noOfCurrency[i] += n;
            total += currency[i] * n;
            u[k].balance+=currency[i]*n;
        }
        System.out.println("Amount Deposited Successfully!");
    }
    // To transfer amount from one account to another account
    static void amountTransfer(int k) {
        System.out.println("Enter Amount Transfer Account Number : ");
        String transferAc = sc.next();
        int pro=0;
        for (int i = 0; i < u.length; i++) {
            if (u[i].acId.equals(transferAc)){
                System.out.println("Enter Transfer Amount");
                int amt = sc.nextInt();
                if (u[k].balance <= amt){
                    System.out.println("Enter Minimum Amount");
                } else {
                    u[k].balance-=amt;
                    u[i].balance+=amt;
                    pro++;
                    System.out.println("Amount Transferred Successfully");
                }
            }
        } if(pro==0) System.out.println("Check Transfer Account Number");
    }
    // admin verification and action
    static void admin() {
        System.out.println("----- You Have Chosen Admin Login -----");
        System.out.println("Enter Admin Name and Password");
        String adminName = sc.next();
        int adminPassword = sc.nextInt();
        int adop = 0;
        if (adminName.equals("admin") && adminPassword == 12345) {
            do {
                System.out.println("-- Welcome Admin --");
                System.out.println("1.Load");
                System.out.println("2.Check Balance");
                System.out.println("3.Exit");
                System.out.println("Enter Your Choice");
                adop = sc.nextInt();
                switch (adop) {
                    case 1:
                        System.out.println("Enter amount");
                        addAmount();
                        break;
                    case 2:
                        System.out.println("ATM Balance");
                        atmBalance();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } while (adop != 3);
        } else {
            System.out.println("check Admin Name and Password");
            admin();
        }
    }
    // User actions
    static void userFun(int i){
        int usop = 0;
        do {
            System.out.println("----- Welcome "+u[i].name+" -----");
            System.out.println("1.Withdraw Amount");
            System.out.println("2.Check Balance");
            System.out.println("3.Pin change");
            System.out.println("4.Direct Deposit");
            System.out.println("5.Amount Transfer");
            System.out.println("6.Exit");
            System.out.println("Enter Your Choice");
            usop = sc.nextInt();
            switch (usop) {
                case 1:
                    withdraw(i);
                    break;
                case 2:
                    checkBalance(i);
                    break;
                case 3:
                    pinChange(i);
                    break;
                case 4:
                    deposit(i);
                    break;
                case 5:
                    amountTransfer(i);
                    break;
                case 6:
                    break;
                default:
                    userFun(i);
                    System.out.println("Invalid Input");
            }
        }while(usop!=6);
    }
    // user verification
    static void user() {
        System.out.println("----- You Have Chosen User Login -----");
        System.out.println("Enter User Name and Password");

        String userName = sc.next();
        int userPassword = sc.nextInt();
        int pro=0;
        for (int i = 0; i < u.length; i++) {
            if (u[i].name.equals(userName) && u[i].pin == userPassword) {
                userFun(i);
                pro++;
            }
        }
        if(pro==0) user();
    }

    public static void main(String[] args) {
        u[0] = new userdata("Ram",14,2300,"U1014");
        u[1] = new userdata("Arun",24,72300,"U1024");
        u[2] = new userdata("Kumar",77,62300,"U1077");
        u[3] = new userdata("Moulik",92,42300,"U1092");
        int ch = 0;
        do {
            System.out.println("----- Welcome To ATM -----");
            System.out.println("ATM Application");
            System.out.println("1.Admin Login");
            System.out.println("2.User Login");
            System.out.println("3.Exit");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    admin();
                    break;
                case 2:
                    user();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input Retry");
                    break;
            }
        } while (ch != 3);
        System.out.println("Thanks for Using!");
    }
}
