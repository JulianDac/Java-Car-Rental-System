import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

public class ThriftyRentSystem {
    private ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
    int add_stat = 0;
    int rent_stat = 0;
    int return_stat = 0;
    private DateTime rent_date;
    private DateTime maintenance_date;
    private DateTime date_returned;
    private DateTime date_maintenance_completed;
    private static final String car_4_seater = "4";
    private static final String car_7_seater = "7";
    private static final String van_15 = "15";
    private int days_to_rent;
    private DateTime dateB;

    public void run() {
        //Generate data for 15 vehicles
        Vehicle alfa_romeo = new Car("C_AlfaRomeo", "2019", "Alfa Romeo", "Spider", "4");
        Vehicle bmw = new Car("C_BMW", "2019", "BMW", "i8", "4");
        Vehicle chrsyler = new Car("C_Chrysler", "2019", "Chrysler", "300", "4");
        Vehicle ferrari = new Car("C_Ferrari", "2019", "Ferrari", "488 Spider", "4");
        Vehicle honda = new Car("C_Honda", "2019", "Honda", "Accord Euro", "4");
        Vehicle jaguar = new Car("C_Jaguar", "2019", "Jaguar", "Mark 1", "4");
        Vehicle lamborghini = new Car("C_Lamborghini", "2019", "Lamborghini", "Aventador", "4");
        Vehicle lotus = new Car("C_Lotus", "2019", "Lotus", "Elise", "4");
        Vehicle mercedes = new Car("C_Mercedes", "2019", "Mercedes", "AMG GT63", "4");
        Vehicle mustang = new Car("C_Mustang", "2019", "Mustang", "GT500 Shelby", "4");
        Vehicle maserati = new Van("V_Maserati", "2019", "Maserati", "Alfieri", "15");
        Vehicle opel = new Van("V_Opel", "2019", "Opel", "Corsa Sedan", "15");
        Vehicle nissan = new Van("V_Nissan", "2019", "Nissan", "Skyline", "15");
        Vehicle porsche = new Van("V_Porsche", "2019", "Porsche", "911", "15");
        Vehicle tata = new Van("V_Tata", "2019", "Tata", "Harrier", "15");
        allVehicles.add(alfa_romeo);
        allVehicles.add(bmw);
        allVehicles.add(chrsyler);
        allVehicles.add(ferrari);
        allVehicles.add(honda);
        allVehicles.add(jaguar);
        allVehicles.add(lamborghini);
        allVehicles.add(lotus);
        allVehicles.add(mercedes);
        allVehicles.add(mustang);
        allVehicles.add(maserati);
        allVehicles.add(opel);
        allVehicles.add(nissan);
        allVehicles.add(porsche);
        allVehicles.add(tata);

        //Generate data for rental records
        RentalRecord alfa_romeo_1 = new RentalRecord("C_AlfaRomeo", "Tyrion", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord alfa_romeo_2 = new RentalRecord("C_AlfaRomeo", "Sansa", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord bmw_1 = new RentalRecord("C_BMW", "Jon", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord bmw_2 = new RentalRecord("C_BMW", "Tywin", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord chrysler_1 = new RentalRecord("C_Chrysler", "Dani", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord chrysler_2 = new RentalRecord("C_Chrysler", "Aegon", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord ferrari_1 = new RentalRecord("C_Ferrari", "Drogo", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord ferrari_2 = new RentalRecord("C_Ferrari", "GreyWorm", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord honda_1 = new RentalRecord("C_Honda", "Jamie", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord honda_2 = new RentalRecord("C_Honda", "Cercei", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord jaguar_1 = new RentalRecord("C_Jaguar", "Robert", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord jaguar_2 = new RentalRecord("C_Jaguar", "Ned", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord lamborghini_1 = new RentalRecord("C_Lamborghini", "Catherine", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord lamborghini_2 = new RentalRecord("C_Lamborghini", "Theon", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord lotus_1 = new RentalRecord("C_Lotus", "Bran", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord lotus_2 = new RentalRecord("C_Lotus", "Arya", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord mercedes_1 = new RentalRecord("C_Mercedes", "Glegane", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord mercedes_2 = new RentalRecord("C_Mercedes", "Joffrey", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord mustang_1 = new RentalRecord("C_Mustang", "Davos", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord mustang_2 = new RentalRecord("C_Mustang", "Brienne", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord maserati_1 = new RentalRecord("V_Maserati", "Varis", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord maserati_2 = new RentalRecord("V_Maserati", "Finger", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord opel_1 = new RentalRecord("V_Opel", "Edd", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord opel_2 = new RentalRecord("V_Opel", "Sam", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord nissan_1 = new RentalRecord("V_Nissan", "Tormund", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord nissan_2 = new RentalRecord("V_Nissan", "Margaery", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord porsche_1 = new RentalRecord("V_Porsche", "Renly", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord porsche_2 = new RentalRecord("V_Porsche", "Olenna ", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);
        RentalRecord tata_1 = new RentalRecord("V_Tata", "Melisandre", convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), convertStrToDateTime("11/11/11"), 100, 120);
        RentalRecord tata_2 = new RentalRecord("V_Tata", "Bob", convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), convertStrToDateTime("12/12/12"), 100, 120);

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

    private void displaySummary() {
        System.out.println("****** Rental Company ******");
        System.out.println("Add Property:    " + add_stat);
        System.out.println("Rent Property:   " + rent_stat);
        System.out.println("Return Property: " + return_stat);
        System.out.println();
        System.out.println();
        System.out.println();
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

    private boolean isValidVehicleType(String userInput) {
        if(userInput.equals("C") || userInput.equals("V")) {
            return true;
        } else {
            System.out.println("Invalid input. Input C or V only.");
            return false;
        }
    }

    private DateTime convertStrToDateTime(String date_input) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
            Date dateA = format.parse(date_input); //Create object dateA to store user input as a Date class object
            Calendar cal = Calendar.getInstance(); //Create object cal to obtain day/mo/year from dateA
            cal.setTime(dateA); //setTime(Date date) is to set this calendar's time with dateA's date
            int dateA_day = cal.get(Calendar.DAY_OF_MONTH); //Obtain day in terms of Integer
            int dateA_month = cal.get(Calendar.MONTH) + 1; //Obtain month in terms of Integer
            int dateA_year = cal.get(Calendar.YEAR); //Obtain year in terms of Integer
            int dateA_dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //Obtain day of week in terms of Integer
            dateB = new DateTime(dateA_day, dateA_month, dateA_year); //Construct new DateTime object using 3 Integers
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateB;
    }

    private boolean isValidSeatsInputForCar(String userInput) {
        if (userInput.equals("4") || userInput.equals("7")) {
            return true;
        } else {
            System.out.println("A car can only have either 4 or 7 passenger seats");
            return false;
        }
    }

    private void checkValidDaysRentalForCar(DateTime rent_date, int days_rented) {
        Scanner scanner = new Scanner(System.in);
        boolean validDayCar = false;
        while (!validDayCar) {
            if (days_rented <= 14) {
                if (rent_date.getNameOfDay().equals("Friday") || rent_date.getNameOfDay().equals("Saturday")) {
                    if (days_rented < 3) {
                        System.out.println("Rental period must be at least 3 days for Fri and Sat");
                        validDayCar = false;
                    } else {
                        validDayCar = true;
                    }
                } else {
                    if (days_rented < 2) {
                        System.out.println("Rental period must be at least 2 days");
                        validDayCar = false;
                    } else {
                        validDayCar = true;
                    }
                }
            } else {
                System.out.println("Rental period cannot exceed 14 days ");
                validDayCar = false;
            }
        }
    }

    private void checkValidDaysRentalForVan(Vehicle found_vehicle, DateTime rent_date, int days_rented) {
        DateTime daysSinceLastMaintenance = new DateTime();
        Scanner scanner = new Scanner(System.in);
        int daysSinceLastMaintenanceInt;
        while (found_vehicle.getLastMaintenanceDate() != null) {
            daysSinceLastMaintenanceInt = daysSinceLastMaintenance.diffDays(rent_date, found_vehicle.getLastMaintenanceDate());
            if (daysSinceLastMaintenanceInt >= 12) {
                System.out.println("This Van was last maintained on " + found_vehicle.getLastMaintenanceDate().getFormattedDate() + "." + "\n"
                        + "It can't be rented out now and will be sent for maintenance.");
                try {
                    found_vehicle.performMaintenance();
                } catch (RentException e) {
                    System.out.println("Tough luck. Someone else has rented it.");
                } catch (MaintenanceException e) {
                    System.out.println("Already is under maintenance.");
                }
            }
            boolean validDayVan = false;
            while (!validDayVan) {
                if (days_rented < 1) {
                    System.out.println("Rental period is at least 1 day. This Van is now rented for 1 day.");
                    validDayVan = false;
                } else if (daysSinceLastMaintenanceInt + days_rented > 12) {
                    System.out.println("This Van was last maintained on " + found_vehicle.getLastMaintenanceDate().getFormattedDate() + "." + "\n"
                            + "We will allow this vehicle to be rented for only: " + (12 - daysSinceLastMaintenanceInt) + " days.");
                    validDayVan = false;
                } else {
                    validDayVan = true;
                }
            }
        }
    }

    private void addVehicle() {
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

    private void rentVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            System.out.println("Vehicle with this ID is not found");
        } else {
            System.out.print("Customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter Rent Date (dd/mm/yy): ");
            String date_input = scanner.nextLine();
            rent_date = convertStrToDateTime(date_input);
            System.out.println("Rent date is " + rent_date.getFormattedDate());
            System.out.print("How many days to rent out: ");
            int days_rented = scanner.nextInt();
            char vehicleType = found_vehicle.getID().charAt(0);
            switch (vehicleType) {
                case 'C':
                    checkValidDaysRentalForCar(rent_date, days_rented);
                    break;
                case 'V':
                    checkValidDaysRentalForVan(found_vehicle, rent_date, days_rented);
                    break;
            }
            try {
                found_vehicle.rent(customerId, rent_date, days_rented);
                System.out.println("Vehicle " + id + " is now rented by customer " + customerId);
                DateTime estReturnDate = new DateTime(rent_date, days_rented);
                System.out.println("Expected return date " + estReturnDate.getFormattedDate());
                rent_stat++;
            } catch (RentException e) {
                System.out.println("Tough luck. Someone else has rented it.");
            } catch (MaintenanceException e) {
                System.out.println("Tough luck. It has not returned from maintenance.");
            }
        }
    }

    private void returnVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID (starts with C or V): ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            System.out.println("Vehicle with this ID is not found");
        } else if (found_vehicle.getLatestRentalRecord() == null){
            System.out.println("No rental record found. This vehicle was never rented out.");
        } else {
            System.out.println("Enter Return Date (dd/mm/yy): ");
            String date_input = scanner.nextLine();
            date_returned = convertStrToDateTime(date_input);
            try {
                checkPositiveRentalPeriod(date_returned, found_vehicle);
                System.out.println("Return date is " + date_returned.getFormattedDate());
                found_vehicle.returnVehicle(date_returned);
                return_stat++;
                System.out.print("Vehicle " + found_vehicle.getID() + " is now returned.\n");
                found_vehicle.getLatestRentalRecord().setActReturnDate(date_returned);
                checkLateReturn(found_vehicle, date_returned);
            } catch (ReturnException e) {
                System.out.println("Can't return");
            }
        }
    }

    private void checkPositiveRentalPeriod(DateTime input, Vehicle vehicle) throws ReturnException {
        int positiveDaysCheckInt;
        DateTime temp_date = new DateTime();
        positiveDaysCheckInt = temp_date.diffDays(input, vehicle.getLatestRentalRecord().getRentDate());
        if (positiveDaysCheckInt < 0) {
            throw new ReturnException("Oof");
        }
    }

    private void checkLateReturn(Vehicle found_vehicle, DateTime date_returned) {
        int daysRentedToPayForInt;
        int daysLateToPayForInt;
        DateTime daysRentedToPayFor = new DateTime();
        DateTime daysLateToPayFor = new DateTime();
        daysRentedToPayForInt = daysRentedToPayFor.diffDays(found_vehicle.getLatestRentalRecord().getEstReturnDate(), found_vehicle.getLatestRentalRecord().getRentDate());
        daysLateToPayForInt = daysLateToPayFor.diffDays(found_vehicle.getLatestRentalRecord().getActReturnDate(), found_vehicle.getLatestRentalRecord().getEstReturnDate());
        if(daysRentedToPayFor.diffDays(date_returned, found_vehicle.getLatestRentalRecord().getEstReturnDate()) > 0) {
            System.out.println("This is a late return");
            System.out.println("Days rented: " + daysRentedToPayForInt);
            System.out.println("Late days: " + daysLateToPayForInt);
            if (found_vehicle.getID().charAt(0) == 'C') {
                System.out.println("Fee payable: " + rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
                System.out.println("Late fee payable: " + lateFeeToPay(found_vehicle.getID(), daysLateToPayForInt));
                found_vehicle.getLatestRentalRecord().setRentalFee(rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
                found_vehicle.getLatestRentalRecord().setLateFee(lateFeeToPay(found_vehicle.getID(), daysLateToPayForInt));
            } else if (found_vehicle.getID().charAt(0) == 'V') {
                daysLateToPayForInt = daysRentedToPayForInt + daysLateToPayForInt;
                System.out.println("Late fee payable: " + lateFeeToPay(found_vehicle.getID(), daysLateToPayForInt));
                found_vehicle.getLatestRentalRecord().setRentalFee(0);
                found_vehicle.getLatestRentalRecord().setLateFee(lateFeeToPay(found_vehicle.getID(), daysLateToPayForInt));
            }
        } else {
            System.out.println("Days rented: " + daysRentedToPayForInt);
            System.out.println("Fee payable: " + rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
            found_vehicle.getLatestRentalRecord().setRentalFee(rentalFeeToPay(found_vehicle.getID(), daysRentedToPayForInt));
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

    private void vehicleMaintenance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            System.out.println("Vehicle with this ID is not found.");
        } else if (found_vehicle.getID().charAt(0) != 'V'){
            System.out.println("Invalid ID. Only vans need to be maintained.");
        } else {
            try {
                found_vehicle.performMaintenance();
                System.out.print("Enter Maintenance Date (dd/mm/yy): ");
                String date_input = scanner.nextLine();
                maintenance_date = convertStrToDateTime(date_input);
                System.out.println("Maintenance date is " + maintenance_date.getFormattedDate());
                System.out.print("Vehicle " + found_vehicle.getID() + " is now under maintenance.\n");
            } catch (RentException e) {
                System.out.println("Being Rented");
            } catch (MaintenanceException e) {
                System.out.println("Already has been sent out for maintenance");
            }
//            boolean isSuccessfulPerformanceMaintenance = found_vehicle.performMaintenance();
//            if (isSuccessfulPerformanceMaintenance) {
//                System.out.print("Vehicle " + found_vehicle.getID() + " is now under maintenance.\n");
//            } else {
//                System.out.println("Failed to perform maintenance on vehicle " + found_vehicle.getID());
//            }
        }
    }

    private void completeMaintenance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vehicle ID: ");
        String id = scanner.nextLine();
        Vehicle found_vehicle = getVehicleObjFromID(id);
        if (found_vehicle == null) {
            System.out.println("Vehicle with this ID is not found");
        } else if (found_vehicle.getID().charAt(0) != 'V'){
            System.out.println("Invalid ID");
        } else {
            try {
                found_vehicle.completeMaintenance(date_maintenance_completed);
                System.out.println("Enter Completion Date (dd/mm/yy): ");
                String date_input = scanner.nextLine();
                date_maintenance_completed = convertStrToDateTime(date_input);
                System.out.println("Maintenance completion date is " + date_maintenance_completed.getFormattedDate());
                System.out.print("Vehicle " + found_vehicle.getID() + " has all maintenance completed and ready for rent.\n");
            } catch (RentException e) {
                System.out.println("Being Rented");
            } catch (MaintenanceException e) {
                System.out.println("This vehicle is not currently under maintenance.");
            }
        }
    }

    private void displayAllVehicles() {
        for (Vehicle vehicle : allVehicles) {
            vehicle.printDetails();
            vehicle.printAllRentalRecordDetails();
        }
    }

}
