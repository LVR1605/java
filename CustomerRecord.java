import java.util.ArrayList;
import java.io.*;

public class CustomerRecord {

    public static void main(String[] args) throws Exception {

        Customers superClass = new Customers();

        ArrayList<Customers> customers = new ArrayList<>();

        customers = superClass.giveRecords();

        customers = MainCall(customers);
    }

    public static ArrayList<Customers> MainCall(ArrayList<Customers> customers) throws Exception {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);

        boolean flag = true;

        do {
            System.out.printf("\nMENU ITEMS:\n\t1.) ADD NEW RECORD\t2.) DELETE RECORD\t3.) VIEW RECORD LIST\t4.) VIEW A RECORD \t5.) EXIT\nResponse: ");
            String resp = input.readLine();

            switch (resp.toUpperCase()) {
                case "1":
                    CreateClass create = new CreateClass(customers);
                    break;
                case "2":
                    DeleteRecords delete = new DeleteRecords(customers);
                    break;
                case "3":
                    int count = 0;
                    if (customers.size() <= 0) {
                        System.out.println("\n\tThere are no customer records. Please add a new record by pressing 1 in the menu. Press enter to return to the menu...");
                        resp = input.readLine();
                    } else {
                        System.out.print("\n\nRecord List:");
                    }
                    for (Customers p : customers) {
                        System.out.printf("\n\nCustomer [%d]:\n%-7S%-10S%-10S%-7S\n", (count + 1), ("\tCustomer number: " + p.getcustomerNumber()), ("\tCustomer Name: " + p.getcustomerName()), ("\tCustomer Address: " + p.getcustomerAddress()), ("\tCustomer contact number: " + p.getcustomerContactNumber()));
                        count++;
                    }
                    break;
                case "4":
                    ViewRecord view = new ViewRecord(customers);
                    break;
                case "5":
                    System.out.println("Terminating Program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("The value doesn't match any options. Please try again...");
            }
        } while (flag);

        return customers;
    }
}

class ViewRecord {

    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(reader);

    int ifound = 0;
    int count = 0;
    String resp = null;

    ViewRecord(ArrayList<Customers> customers) throws Exception {

        if (customers.size() <= 0) {
            System.out.println("\n\tThere are no customer records. Please add a record by pressing 1 in the menu.\n\t\tPress enter to return to the menu...");
            resp = input.readLine();
            MainCall(customers);
        }
        do {
            System.out.printf("\nEnter customer number to view:\nResponse: ");
            resp = input.readLine();

            for (Customers p : customers) {
                if (p.getcustomerNumber().equals(resp)) {
                    System.out.printf("\nCustomer Records:\nCustomer Number: %S\nCustomer Name: %S\nCustomer Address: %S\nCustomer Contact Number: %S\n\nDo you want to modify the existing record? [Yes|No]:\nResponse: ", p.getcustomerNumber(), p.getcustomerAddress(), p.getcustomerName(), p.getcustomerContactNumber());
                    ifound = 1;
                    resp = input.readLine();

                    if (ifound == 1 && (resp.equalsIgnoreCase("YES") || resp.equalsIgnoreCase("Y"))) {
                        System.out.printf("\nEnter new name:\nResponse: ");
                        String newName = input.readLine();
                        p.setcustomerName(newName);

                        System.out.printf("\nEnter new address:\nResponse: ");
                        String newCustomerAddress = input.readLine();
                        p.setcustomerAddress(newCustomerAddress);

                        System.out.printf("\nEnter new contactNumber:\nResponse: ");
                        String newContactNumber = input.readLine();
                        p.setcustomerContactNumber(newContactNumber);

                        System.out.printf("\n\t\tModification Success! Press enter to return to the menu or No to continue.\nResponse: ");
                        resp = input.readLine();
                    }

                    customers.get(count).setcustomerName(p.getcustomerName());
                    customers.get(count).setcustomerAddress(p.getcustomerAddress());
                    customers.get(count).setcustomerContactNumber(p.getcustomerContactNumber());

                    Customers superClass = new Customers();
                    superClass.addRecords(customers);

                    MainCall(superClass.giveRecords());

                    break;
                }
                count++;
            }

            if (!(ifound == 1)) {
                System.out.printf("\n\tCustomer Records don't exist! Press enter to return to the menu or No to continue.\nResponse: ");
                resp = input.readLine();
                ifound = 0;
                continue;
            }

        } while (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"));
    }
}

class DeleteRecords {

    ArrayList<Customers> customersClone = new ArrayList<>();

    public DeleteRecords(ArrayList<Customers> customers) throws Exception {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);

        String resp = null;

        do {
            if (customers.size() <= 0) {
                System.out.println("\n\tThere are no Customer records. Please add a new record by pressing 1 in the menu. Press enter to return to the menu...");
                resp = input.readLine();
                break;
            }
            System.out.printf("\nEnter Customer number to delete:\nResponse: ");
            resp = input.readLine();

            int ifound = 0;

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getcustomerNumber().equals(resp)) {
                    ifound = 1;

                    System.out.printf("\nCustomer Records:\nCustomer number: %S\nCustomer Name: %S\nCustomer address: %S\nCustomer contact number: %S", customers.get(i).getcustomerNumber(), customers.get(i).getcustomerAddress(), customers.get(i).getcustomerName(), customers.get(i).getcustomerContactNumber());

                    customers.remove(i);
                    System.out.printf("\n\n\tDeletion complete! Please press enter to return to the menu or No to continue.\nResponse:");
                    resp = input.readLine();
                    break;
                }
            }

            if (!(ifound == 1)) {
                ifound = 0;
                System.out.println("\tCustomer Record doesn't exist! Please press enter to return to menu or No to continue.\nResponse:");
                resp = input.readLine();
            }
        } while (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"));
    }
}

class CreateClass {

    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(reader);

    int ifound = 0;

    public CreateClass(ArrayList<Customers> customers) throws Exception {

        String resp = null;

        int count = 0;

        do {
            System.out.printf("\nEnter number : ");
            String number = input.readLine();

            for (Customers p : customers) {
                if (p.getcustomerNumber().equals(number)) {
                    ifound = 1;
                    break;
                }
            }

            if (!(ifound == 0)) {
                ifound = 0;
                System.out.println("\tThis customer number already exists! Please try a different customer number");
                CreateClass create = new CreateClass(customers);
            }

            System.out.printf("\nEnter name : ");
            String name = input.readLine();

            System.out.printf("\nEnter address : ");
            String address = input.readLine();

            System.out.printf("\nEnter contactNumber : ");
            String contactNumber = input.readLine();

            System.out.printf("\n\nRecord List:\n\ncustomer [%d]:\n%-7S%-10S%-10S%-7S\n\nDo you want to save? [Yes|No]\nResponse: ", (count + 1), ("\tcustomer number: " + number), ("\tcustomer Name: " + name), ("\tcustomer address: " + address), ("\tcustomer number: " + contactNumber));
            resp = input.readLine();
            count = 0;

            if (resp.equalsIgnoreCase("YES") || resp.equalsIgnoreCase("Y")) {
                customers.add(new Customers(number, name, address, contactNumber));
                Customers superClass = new Customers();
                superClass.addRecords(customers);
                System.out.printf("\n\tThe record has been saved!\n\t\tPlease press enter to return to the menu or No to continue.\nResponse: ");
                resp = input.readLine();
            } else {
                System.out.printf("\n\tThe record hasn't been saved!\n\t\tPress Enter to return to the menu or No to continue.\nResponse: ");
                resp = input.readLine();
            }
        } while (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"));
        MainCall(customers);
    }
}

class Customers {

    private String customerNumber;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;

    private ArrayList<Customers> customers = new ArrayList<>();

    public Customers(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        super();
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
    }

    public Customers() {
    }

    public String toString() {
        return "\nCustomer customerNumber=" + customerNumber + ", customerName=" + customerName + ", customerAddress=" + customerAddress + ", customerContactNumber=" + customerContactNumber + "\n";
    }

    public void setcustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setcustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setcustomerContactNumber(String customerContactNumber) {
        this.customerContactNumber = customerContactNumber;
    }

    public String getcustomerNumber() {
        return customerNumber;
    }

    public String getcustomerName() {
        return customerName;
    }

    public String getcustomerAddress() {
        return customerAddress;
    }

    public String getcustomerContactNumber() {
        return customerContactNumber;
    }

    public void addRecords(ArrayList<Customers> customers) {
        this.customers = customers;
    }

    public ArrayList<Customers> giveRecords() {
        return customers;
    }
}
