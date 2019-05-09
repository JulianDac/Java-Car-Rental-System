import java.util.ArrayList;

public class Vehicle {
    protected String id;
    protected String year;
    protected String make;
    protected String model;
    protected String seats;
    protected boolean beingRented = false;
    protected boolean beingMaintained = false;
    protected String rentedStatus;
    protected DateTime lastMaintenanceDate;

    protected ArrayList<RentalRecord> allRentalRecords = new ArrayList<RentalRecord>();
    protected ArrayList<MaintenanceRecord> allMaintenanceRecords = new ArrayList<MaintenanceRecord>();

    public static final int MAX_RENTAL_RECORDS = 10;


    public Vehicle(String id , String year, String make, String model, String seats) {
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.seats = seats;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public boolean isBeingRented() {
        return beingRented;
    }

    public void setBeingRented(boolean beingRented) {
        this.beingRented = beingRented;
    }

    public boolean isBeingMaintained() {
        return beingMaintained;
    }

    public void setBeingMaintained(boolean beingMaintained) {
        this.beingMaintained = beingMaintained;
    }

    public DateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public ArrayList<RentalRecord> getAllRentalRecords() {
        return allRentalRecords;
    }

    public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        if (beingRented) {
            assert !this.beingMaintained;
            System.out.println("This vehicle is being rented by someone else.");
            return false;
        } else if (beingMaintained) {
            System.out.println("This vehicle is under maintenance");
            return false;
        } else {
            DateTime estReturnDate = new DateTime(rentDate, numOfRentDay);
            if (this.allRentalRecords.size() == Vehicle.MAX_RENTAL_RECORDS) {
                this.allRentalRecords.remove(0);
            }
            // updating the vehicle status
            this.beingRented = true;
            // creating a new rental record
            RentalRecord new_rental_record = new RentalRecord(this.id, customerId, rentDate, estReturnDate);
            // updating the rental record collection of the vehicle
            allRentalRecords.add(new_rental_record);
            // TODO any other operations you consider necessary
            return true;
        }
    }

    public boolean returnVehicle(DateTime returnDate) {
        if (!this.beingRented) {
            System.out.println("This vehicle was never rented out in the first place.");
            return false;
        } else {
            assert !this.allRentalRecords.isEmpty();
            this.beingRented = false;
        }
        RentalRecord latest_rental_record = allRentalRecords.get(allRentalRecords.size()-1);
        latest_rental_record.setActReturnDate(returnDate);
        latest_rental_record.setActReturnDate(returnDate);
        latest_rental_record.setRentalFee(0.0);
        latest_rental_record.setLateFee(0.0);
        this.beingRented = false;
        return true;
    }

    public boolean performMaintenance() {
        if (this.beingRented) {
            System.out.println("This vehicle is currently rented out.");
            return false;
        } else if (this.beingMaintained) {
            System.out.println("This vehicle is already under maintenance.");
            return false;
        } else {
            System.out.println("We are sending this vehicle away for maintenance.");
            beingMaintained = true;
            return true;
        }
    }

    public boolean completeMaintenance(DateTime completionDate) {
        if (this.beingRented) {
            System.out.println("This vehicle is currently rented out and not under maintenance.");
            return false;
        } else if (!this.beingMaintained) {
            System.out.println("This vehicle is not under maintenance.");
            return false;
        } else {
            MaintenanceRecord new_maintenance_record = new MaintenanceRecord(this.id, completionDate);
            System.out.println("Maintenance is finished.");
            allMaintenanceRecords.add(new_maintenance_record);
            this.lastMaintenanceDate = completionDate;
            this.beingMaintained = false;
            return true;
        }
    }

    public String toString() {
        if (beingRented = true) {
            rentedStatus = "Available";
        } else {
            rentedStatus = "Not Available";
        }
        return String.join(":", id, year, make, model, seats, rentedStatus);
    }

    public String getDetails() {
        String vehicle_details =
                "Vehicle ID:" + id + "\n"
                        + "Year:" + year + "\n"
                        + "Make: " + make + "\n"
                        + "Model: " + model + "\n"
                        + "Number of seats: " + seats + "\n"
                        + "Status: " + rentedStatus;
        return vehicle_details;
    }

    public void printDetails() {
        System.out.println(this.getDetails());
    }

    public RentalRecord getLatestRentalRecord() {
        if (this.allRentalRecords.size() == 0) {
            System.out.println("No rental record found for vehicle " + this.id);
            return null;
        } else {
            return allRentalRecords.get(allRentalRecords.size() - 1);
        }
    }

    public void printLatestRentalRecordDetails() {
        RentalRecord latestRentalRecord = this.getLatestRentalRecord();
        if (latestRentalRecord != null) {
            latestRentalRecord.getDetails();
        }
    }

    public void printAllRentalRecordDetails() {
        for (RentalRecord record : this.allRentalRecords) {
            record.getDetails();
        }
    }

    public boolean equals(Vehicle vehicle) {
        return this.id.equals(vehicle.getID());
    }

}
