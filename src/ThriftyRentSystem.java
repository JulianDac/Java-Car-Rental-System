import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

public class ThriftyRentSystem {
    ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
    int add_stat = 0;
    int rent_stat = 0;
    int return_stat = 0;
    private DateTime rent_date;
    private DateTime date_returned;
    private DateTime date_maintenance_completed;
    private static final String car_4_seater = "4";
    private static final String car_7_seater = "7";
    private static final String van_15 = "15";
    private int days_to_rent;

    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("**** ThriftyRent SYSTEM MENU ****\n" +
                    "Add vehicle:            1\n" +
                    "Rent vehicle:           2\n" +
                    "Return vehicle:         3\n" +
                    "Vehicle Maintenance:    4\n" +
                    "Complete Maintenance:   5\n" +
                    "Display All Vehicles:   6\n" +
                    "Exit Program:           7\n");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addVehicle();
                    break;

                case 2:
                    rentVehicle();
                    break;

                case 3:
                    returnVehicle();
                    break;

                case 4:
                    vehicleMaintenance();
                    break;

                case 5:
                    completeMaintenance();
                    break;

                case 6:
                    displayAllVehicles();
                    break;

                case 7:
                    System.out.println("Bye");
                    exit(0);
                    break;
                default:
                    System.out.println("This is not a valid menu option.\n\n");
                    break;
            }
            displaySummary();
        }
    }

    private Vehicle getVehicleObjFromID(String id) {
        for (int i = 0; i < allVehicles.size(); i++) {
            Vehicle vehicle = allVehicles.get(i);
            if (vehicle.getID().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public void displaySummary() {
        System.out.println("****** Rental Company ******");
        System.out.println("Add Property:    " + add_stat);
        System.out.println("Rent Property:   " + rent_stat);
        System.out.println("Return Property: " + return_stat);
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private boolean isValidVehicleType(String userInput) {
        if(userInput.equals("C") || userInput.equals("V")) {
            return true;
        } else {
            System.out.println("Invalid input. Input C or V only.");
            return false;
        }
    }

    private boolean isValidSeatsInputForCar(String userInput) {
        if (userInput.equals("4") || userInput.equals("7")) {
            return true;
        } else {
            System.out.println("A car can only have either 4 or 7 passenger seats");
            return false;
        }
    }


    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);
        String vehicleType;
        //Check if invalid input by using isValidVehicleType method above
        do {
            System.out.println("Is this a Car(C) or a Van(V): ");
            vehicleType = scanner.nextLine();
        } while (!isValidVehicleType(vehicleType));
        //Start collecting details for vehicle
        boolean isCar = true;
        switch (vehicleType) {
            case "C":
                isCar = true;
                break;
            case "V":
                isCar = false;
                break;
        }
        System.out.println("Enter Vehicle ID: ");
        String vehicleId;
        if (isCar) {
            vehicleId = "C" + scanner.nextLine();
        } else {
            vehicleId = "V" + scanner.nextLine();
        }
        System.out.println("Enter vehicle year: ");
        String yearInput = scanner.nextLine();
        System.out.println("Enter vehicle make: ");
        String makeInput = scanner.nextLine();
        System.out.println("Enter vehicle model: ");
        String modelInput = scanner.nextLine();
        String seatsInput;
        //Check if invalid input by using isValidSeatsInputForCar
        if (isCar) {
            do {
                System.out.println("Enter vehicle seats: ");
                seatsInput = scanner.nextLine();
            } while (!isValidSeatsInputForCar(seatsInput));
        } else {
            System.out.println("This van will have 15 seats");
            seatsInput = "15";
        }
        Vehicle vehicleToAdd;
            if (isCar) {
                vehicleToAdd = new Car(vehicleId, yearInput, makeInput, modelInput, seatsInput);
            } else {
                vehicleToAdd = new Van(vehicleId, yearInput, makeInput, modelInput, seatsInput);
            }
            allVehicles.add(vehicleToAdd);
            add_stat++;
            System.out.print("Added " + vehicleToAdd.getID() + " successfully.\n\n");
        }

    public void rentVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            // TODO
            System.out.println("Vehicle with this ID is not found");
        } else {
            System.out.print("Customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter Rent Date (dd/mm/yy): ");
            try {
                String date_input = scanner.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
                Date dateA = format.parse(date_input); //Create object dateA to store user input as a Date class object
                Calendar cal = Calendar.getInstance(); //Create object cal to obtain day/mo/year from dateA
                cal.setTime(dateA); //setTime(Date date) is to set this calendar's time with dateA's date
                int dateA_day = cal.get(Calendar.DAY_OF_MONTH); //Obtain day in terms of Integer
                int dateA_month = cal.get(Calendar.MONTH) + 1; //Obtain month in terms of Integer
                int dateA_year = cal.get(Calendar.YEAR); //Obtain year in terms of Integer
                int dateA_dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //Obtain day of week in terms of Integer
                DateTime dateB = new DateTime(dateA_day, dateA_month, dateA_year); //Construct new DateTime object using 3 Integers
                this.rent_date = dateB;
                System.out.println("Rent date is " + rent_date.getFormattedDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean validDay = false;
            while (!validDay) {
                System.out.print("How many days to rent out: ");
                int days_rented = scanner.nextInt();
                if (found_vehicle.getID().charAt(0) == 'C') {
                    if (days_rented <= 14) {
                        if (rent_date.getNameOfDay().equals("Friday") || rent_date.getNameOfDay().equals("Saturday")) {
                            if (days_rented < 3) {
                                System.out.println("Rental period must be at least 3 days for Fri and Sat");
                            } else {
                                validDay = true;
                            }
                        } else {
                            if (days_rented < 2) {
                                System.out.println("Rental period must be at least 2 days");
                            } else {
                                validDay = true;
                            }
                        }
                    } else {
                        System.out.println("Rental period cannot exceed 14 days ");
                    }
                } else if (found_vehicle.getID().charAt(0) == 'V') {
                    if (days_rented < 1) {
                        System.out.println("Rental period is at least 1 day. This Van is now rented for 1 day.");
                        this.days_to_rent = 1; //minimum rental period for van = 1
                    } else {
                        this.days_to_rent = days_rented;
                    }
                }
            }

            // TODO other parameters
            try {
                found_vehicle.rent(customerId, rent_date, days_to_rent);
                rent_stat++;
            } catch (Exception e) {
                System.out.println("Vehicle could not be rented");
            }
        }
    }

    public void returnVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID (starts with C or V): ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            System.out.println("Vehicle with this ID is not found");
        } else {
            System.out.println("Enter Return Date (dd/mm/yy): ");
            try {
                String date_input = scanner.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yy");
                Date dateA = format.parse(date_input); //Create object dateA to store user input as a Date class object
                Calendar cal = Calendar.getInstance(); //Create object cal to obtain day/mo/year from dateA
                cal.setTime(dateA); //setTime(Date date) is to set this calendar's time with dateA's date
                int dateA_day = cal.get(Calendar.DAY_OF_MONTH); //Obtain day in terms of Integer
                int dateA_month = cal.get(Calendar.MONTH); //Obtain month in terms of Integer
                int dateA_year = cal.get(Calendar.YEAR); //Obtain year in terms of Integer
                DateTime dateB = new DateTime(dateA_day, dateA_month, dateA_year); //Construct new DateTime object using 3 Integers
                this.date_returned = dateB;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (found_vehicle.returnVehicle(date_returned)) {
                return_stat++;
                System.out.print("Vehicle " + found_vehicle.getID() + " is now returned.\n");

                int daysRentedToPayForInt;
                int daysLateToPayForInt;
                DateTime daysRentedToPayFor = new DateTime();
                DateTime daysLateToPayFor = new DateTime();
                //TODO
                daysRentedToPayForInt = daysRentedToPayFor.diffDays(found_vehicle.getLatestRentalRecord().getEstReturnDate(), found_vehicle.getLatestRentalRecord().getRentDate());
                daysLateToPayForInt = daysLateToPayFor.diffDays(found_vehicle.getLatestRentalRecord().getActReturnDate(), found_vehicle.getLatestRentalRecord().getEstReturnDate());

                if(daysRentedToPayFor.diffDays(date_returned, found_vehicle.getLatestRentalRecord().getEstReturnDate()) > 0) {
                    System.out.println("This is a late return");
                    System.out.println("Days rented: " + daysRentedToPayForInt);
                    System.out.println("Late days: " + daysLateToPayForInt);
                    System.out.println("Fee payable: " + rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
                    System.out.println("Late fee payable: " + lateFeeToPay(found_vehicle.getID(), daysLateToPayForInt));
                } else {
                    System.out.println("Days rented: " + daysRentedToPayForInt);
                    System.out.println("Fee payable: " + rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
                }
                //TODO LATE FEE

            } else {
                // TODO
                System.out.println("Vehicle could not be returned.");
            }
        }
    }

    private double rentalFeeToPay(String vehicleID , int daysRentedToPayForInt) {
        double fee = 0.0;
        if (vehicleID.charAt(0) == 'C') {
            if (getVehicleObjFromID(vehicleID).getSeats().equals("4")) {
                fee = 78 * daysRentedToPayForInt;
            } else {
                fee = 113 * daysRentedToPayForInt;
            }
        }
        else if (vehicleID.charAt(0) == 'V') {
            fee = 235 * daysRentedToPayForInt;
        }
        return fee;
    }

    private double lateFeeToPay(String vehicleID, int daysLateToPayForInt) {
        double late_fee = 0.0;
        if (vehicleID.charAt(0) == 'C') {
            if (getVehicleObjFromID(vehicleID).getSeats().equals("4")) {
                late_fee = 1.25 * 78 * daysLateToPayForInt;
            } else {
                late_fee = 1.25 * 113 * daysLateToPayForInt;
            }
        }
        else if (vehicleID.charAt(0) == 'V') {
            late_fee = 299 * daysLateToPayForInt;
        }
        return late_fee;
    }

    public void vehicleMaintenance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            // TODO
            System.out.println("Vehicle with this ID is not found");
        } else {
            boolean isSuccessfulPerformanceMaintenance = found_vehicle.performMaintenance();
            if (isSuccessfulPerformanceMaintenance) {
                System.out.print("Vehicle " + found_vehicle.getID() + " is now under maintenance.\n");
            } else {
                System.out.println("Failed to perform maintenance on vehicle " + found_vehicle.getID());
            }
        }

    }

    public void completeMaintenance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            // TODO
            System.out.println("Vehicle with this ID is not found");
        } else {
            // TODO input completionDate
            System.out.println("Enter Completion Date (dd/mm/yy): ");
            try {
                String date_input = scanner.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yy");
                Date dateA = format.parse(date_input); //Create object dateA to store user input as a Date class object
                Calendar cal = Calendar.getInstance(); //Create object cal to obtain day/mo/year from dateA
                cal.setTime(dateA); //setTime(Date date) is to set this calendar's time with dateA's date
                int dateA_day = cal.get(Calendar.DAY_OF_MONTH); //Obtain day in terms of Integer
                int dateA_month = cal.get(Calendar.MONTH); //Obtain month in terms of Integer
                int dateA_year = cal.get(Calendar.YEAR); //Obtain year in terms of Integer
                DateTime dateB = new DateTime(dateA_day, dateA_month, dateA_year); //Construct new DateTime object using 3 Integers
                this.date_maintenance_completed = dateB;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DateTime completionDate = new DateTime();
            if (found_vehicle.completeMaintenance(date_maintenance_completed)) {
                System.out.print("Vehicle " + found_vehicle.getID()
                                 + " has all maintenance completed and ready for rent.\n");
            } else {
                // TODO
            }
        }
    }

    public void displayAllVehicles() {
        for (Vehicle vehicle : allVehicles) {
            vehicle.printDetails();
            vehicle.printAllRentalRecordDetails();
            // TODO details about up to 10 most recent rental records of each vehicle
        }
    }

}
