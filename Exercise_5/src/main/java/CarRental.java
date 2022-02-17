import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//Store data of vehicle
class Vehicle
{
    private int vehicleId;
    private String brand;
    private String model;
    private int no_Of_Seats;
    private int licence_plate;
    private int flag;

    public Vehicle(int vehicleId, String brand, String model, int no_Of_Seats, int licence_plate,int flag) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.no_Of_Seats = no_Of_Seats;
        this.licence_plate = licence_plate;
        this.flag=flag;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getFlag() {
        return flag;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getNo_Of_Seats() {
        return no_Of_Seats;
    }

    public int getLicence_plate() {
        return licence_plate;
    }
}

//Rental Class Store Values of Rented Vehicle
class Rental
{
    private Customer customer;
    private Vehicle vehicle;
    private String rental_start_date;
    private String rental_end_date;

    public Rental(Customer customer, Vehicle vehicle, String rental_start_date, String rental_end_date) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rental_start_date = rental_start_date;
        this.rental_end_date = rental_end_date;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public String getRental_start_date() {
        return rental_start_date;
    }
    public String getRental_end_date() {
        return rental_end_date;
    }
}

//Store Customer Data
class Customer
{
    private int id;
    private String customerName;

    public Customer(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }
    public long getId() {
        return id;
    }
    public String getCustomerName() {
        return customerName;
    }
}

public class CarRental
{
    //Getting Vehicle Data From User and return Object of Vehicle
    public static Vehicle inputVehicleData(int flag)
    {
        Scanner sc =new Scanner(System.in);
        //Get Vehicle Data from user
        System.out.println("Enter Vehicle Id: ");
        int vehicleId=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Brand: ");
        String brand=sc.nextLine();

        System.out.println("Enter Model: ");
        String model=sc.nextLine();

        System.out.println("Enter No of Seats: ");
        int no_Of_Seats=sc.nextInt();

        System.out.println("Enter Licence Plate Number: ");
        int licence_plate=sc.nextInt();
        Vehicle v =new Vehicle(vehicleId,brand,model,no_Of_Seats,licence_plate,flag);

        return v;
    }

    //Search Id in Arraylist
    //return true if present is in list
    public static boolean searchId(ArrayList<String> s,String str)
    {
        for(String s1: s)
        {
            //Removing Double Quotes From String
            s1 = s1.replace("\"", "");
            if(s1.equals(str))
            {
                return true;
            }
        }
        return false;
    }

    //Compare start_date and end_date
    public static boolean checkDate(String start_date,String end_date) throws ParseException {

        SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
        Date date1 = s.parse(start_date);
        Date date2 = s.parse(end_date);

        if(date1.compareTo(date2) > 0) //Date 1 occurs after Date 2
        {
            return true;
        }
        else if(date1.compareTo(date2) < 0)//Date 1 occurs before Date 2
        {
            return false;
        } else if(date1.compareTo(date2) == 0) //Both dates are equal
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc =new Scanner(System.in);

        //ArrayList of Vehicles Class Objects
        ArrayList<Vehicle> listOfRentedVehicle=new ArrayList<>();
        ArrayList<Vehicle> listOfNotRentedVehicle=new ArrayList<>();
        ArrayList<Vehicle> listOfVehicle=new ArrayList<>();

        //ArrayList of Customer Class Objects
        ArrayList<Customer> listOfCustomer=new ArrayList<>();
        //ArrayList of Rental Class Objects
        ArrayList<Rental> listofRentalData=new ArrayList<>();

        int temp=0;
        while(temp!=8)
        {
            System.out.println("" +
                    "1. Rent Car \n" +
                    "2. List  of Vehicle that are rented \n" +
                    "3. Adding Vehicle To Car Rental Fleet \n" +
                    "4. List of  Vehicle belonging to the  Car Rental Fleet \n" +
                    "5. List  of Vehicle that are not rented \n" +
                    "6. Cancellation of a vehicle from the Car Rental Fleet. \n" +
                    "7. Export All Vehicles In CSV File \n" +
                    "8. The list of all vehicles available for rental in a specified period");
            int option=sc.nextInt();
            switch (option)
            {
                case 1:
                    //Get Customer Data from user
                    System.out.println("Enter Mobile Number: ");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Name: ");
                    String name=sc.nextLine();

                    Customer c =new Customer(id,name);
                    //Addding Customer Objects in listOfCustomer
                    listOfCustomer.add(c);

                    //calling inputVehicleData() function to get vehicle data from user
                    Vehicle v=inputVehicleData(1);//Flag =1 indicates Vehicle is  rented

                    listOfRentedVehicle.add(v);

                    listOfVehicle.add(v);

                    System.out.println("Enter Rental Start Date: ");
                    String rental_start_date=sc.nextLine();

                    System.out.println("Enter Rental End Date: ");
                    String rental_end_date=sc.nextLine();

                    //Addding Rental Objects in listofRentalData
                    Rental r =new Rental(c,v,rental_start_date,rental_end_date);
                    listofRentalData.add(r);

                    //Instantiating the CSVWriter class
                    CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/output.csv"));
                    writer.flush();
                    //Writing data to a csv file
                    String columnName[] = {"Customer_id", "Customer_name",
                            "vehicleId", "brand", "model", "no_Of_Seats","licence_plate" ,
                            "rental_start_date","rental_end_date"};
                    writer.writeNext(columnName);
                    for(Rental o :listofRentalData)
                    {
                        String tempArray[]={String.valueOf(o.getCustomer().getId()),o.getCustomer().getCustomerName(),
                                String.valueOf(o.getVehicle().getVehicleId()),o.getVehicle().getBrand(),o.getVehicle().getModel(), String.valueOf(o.getVehicle().getNo_Of_Seats()), String.valueOf(o.getVehicle().getLicence_plate()),
                                o.getRental_start_date(),o.getRental_end_date()
                        };

                        writer.writeNext(tempArray);
                    }
                    writer.flush();
                    System.out.println("Data entered");
                    writer.close();
                    break;

                case 2:
                    //2. List  of Vehicle that are rented  (flag=1)
                    for(Vehicle o:listOfRentedVehicle)
                    {
                        System.out.println(o.getVehicleId()+" "+o.getBrand()+" "+o.getModel()+" "+o.getNo_Of_Seats()+" "+o.getLicence_plate());
                    }
                    break;
                case 3:
                    //3. Adding Vehicle To Car Rental Fleet

                    //Store id of Vehicle in CSV File
                    ArrayList<String> listOfId=new ArrayList<>();
                    String line = "";

                    try
                    {
                        //Parsing a output.csv file into BufferedReader class constructor
                        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/output.csv"));
                        int t=0;
                        while ((line = br.readLine()) != null)   //returns a Boolean value
                        {
                            if(t!=0) //To Skip Header of CSV
                            {
                                String[] data = line.split(",");    // use "," as separator
                                listOfId.add(data[2]);
                            }
                            t++;
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("Enter Vehicle Id: ");
                    String vehicleId=sc.next();
                    //Check if Vehicle already added or not
                    if(searchId(listOfId,vehicleId))
                    {
                        System.out.println("Vehicle is already Added");
                    }
                    else {
                        System.out.println("Enter Brand: ");
                        String brand = sc.nextLine();

                        System.out.println("Enter Model: ");
                        String model = sc.nextLine();

                        System.out.println("Enter No of Seats: ");
                        int no_Of_Seats = sc.nextInt();

                        System.out.println("Enter Licence Plate Number: ");
                        int licence_plate = sc.nextInt();

                        //Flag =0 indicates Vehicle is not rented
                        Vehicle vo = new Vehicle(Integer.parseInt(vehicleId), brand, model, no_Of_Seats, licence_plate, 0);

                        listOfNotRentedVehicle.add(vo);
                        listOfVehicle.add(vo);
                    }
                    break;
                case 4:
                    //4. List of  Vehicle belonging to the  Car Rental Fleet

                    //Printing List of All Vehicle in Car Rental Fleet
                    for(Vehicle o : listOfVehicle)
                    {
                        System.out.println(o.getVehicleId()+" "+o.getBrand()+" "+o.getModel()+" "+o.getNo_Of_Seats()+" "+o.getLicence_plate());
                    }
                    break;
                case 5:
                    //5. List  of Vehicle that are not rented
                    for(Vehicle o : listOfNotRentedVehicle)
                    {
                        System.out.println(o.getVehicleId()+" "+o.getBrand()+" "+o.getModel()+" "+o.getNo_Of_Seats()+" "+o.getLicence_plate());
                    }
                    break;
                case 6:

                    //Taking Vehicle Id as Input From user
                    System.out.println("Enter Vehicle Id:");
                    int vid=sc.nextInt();

                    int i=0;
                    for(Vehicle ov : listOfVehicle)
                    {
                        if(ov.getVehicleId()==vid)
                        {
                            break;
                        }
                        i++;
                    }

                    //Remove Vehicle Form Car Rental Fleet
                    listOfVehicle.remove(i);
                    System.out.println("Vehicle is Removed");
                    break;
                case 7:
                    //7. Export All Vehicles In CSV File
                    //Instantiating the CSVWriter class
                    CSVWriter vehicleWriter = new CSVWriter(new FileWriter("src/main/resources/vehicles.csv"));

                    //Clear vehicles.csv before writing in vehicles.csv
                    vehicleWriter.flush();

                    //Writing data to a csv file
                    String vehiclesColumnName[] = {"vehicleId", "brand", "model", "no_Of_Seats","licence_plate"};
                    vehicleWriter.writeNext(vehiclesColumnName);
                    for(Vehicle o :listOfVehicle)
                    {

                        String tempArray[]={String.valueOf(o.getVehicleId()),
                                o.getBrand(),o.getModel(), String.valueOf(o.getNo_Of_Seats()),
                                String.valueOf(o.getLicence_plate())};
                        //Adding an String array to vehicles.csv
                        vehicleWriter.writeNext(tempArray);
                    }
                    vehicleWriter.flush();
                    System.out.println("Export Successfully ");
                    vehicleWriter.close();
                    break;
                case 8:
                    //8. The list of all vehicles available for rental in a specified period

                    //Initializing ArrayList  to store vehicles available for rental in a specified period
                    ArrayList<Vehicle> vehicleAvailablePeriod=new ArrayList<>();

                    Scanner sc1=new Scanner(System.in);
                    String start_date="";
                    String end_date="";

                    //Getting Start Date and End Date From User
                    System.out.println("Enter Start Date (dd/mm/yyyy): ");
                    start_date=sc1.nextLine();

                    System.out.println("Enter End Date (dd/mm/yyyy): ");
                    end_date=sc1.nextLine();

                    //Printing Vehicle that are not rented
                    for(Vehicle ov : listOfNotRentedVehicle)
                    {
                        System.out.println(ov.getVehicleId()+" "+ov.getBrand()+" "+ov.getModel()+" "+ov.getNo_Of_Seats()+" "+ov.getLicence_plate());
                    }


                    //Printing Vehicle that  rented by checking condition
                    //Comparing Date with list of Rental Data
                    for(Rental rdata : listofRentalData) {
                        if (checkDate(start_date, rdata.getRental_end_date()))
                        {
                            vehicleAvailablePeriod.add(rdata.getVehicle());
                        }else if (checkDate(rdata.getRental_start_date(),end_date))
                        {
                            vehicleAvailablePeriod.add(rdata.getVehicle());
                        }else
                        {
                            System.out.println("Sorry.. Vehicles are not available.");
                        }
                    }

                    //Printing Vehicle Data Available for rental in a specified period
                    for(Vehicle vdata : vehicleAvailablePeriod)
                    {
                        System.out.println(vdata.getVehicleId()+" "+vdata.getBrand()+" "+vdata.getModel()+" "+vdata.getNo_Of_Seats()+" "+vdata.getLicence_plate());
                    }
                    temp=8;
                    break;
                default:
                        System.out.println("Try Another Options.");
                    break;
            }
        }
    }
}
