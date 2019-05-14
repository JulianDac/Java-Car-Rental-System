import java.util.ArrayList;

public class Vehicle {
    protected String id;
    protected String year;
    protected String make;
    protected String model;
    protected String seats;
    protected boolean beingRented = false;
    protected boolean beingMaintained = false;
    protected String rentedStatus = "Available";
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

    public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException, MaintenanceException {
        if (beingRented) {
            assert !this.beingMaintained;
            throw new RentException("This vehicle is being rented by someone else.");
        } else if (beingMaintained) {
            throw new MaintenanceException("This vehicle is under maintenance");
        } else {
            DateTime estReturnDate = new DateTime(rentDate, numOfRentDay);
            if (this.allRentalRecords.size() == Vehicle.MAX_RENTAL_RECORDS) {
                this.allRentalRecords.remove(0);
            }
            this.beingRented = true;
            RentalRecord new_rental_record = new RentalRecord(this.id, customerId, rentDate, estReturnDate, estReturnDate, 0, 0);
            allRentalRecords.add(new_rental_record);
        }
    }

    public void returnVehicle(DateTime returnDate) throws ReturnException{
        if (!this.beingRented) {
            throw new ReturnException("This vehicle was never rented out in the first place.");
        } else {
            assert !this.allRentalRecords.isEmpty();
            this.beingRented = false;
        }
        RentalRecord latest_rental_record = allRentalRecords.get(allRentalRecords.size()-1);
        latest_rental_record.setActReturnDate(returnDate);
        latest_rental_record.setActReturnDate(returnDate);
        this.beingRented = false;
    }

    public void performMaintenance() throws RentException, MaintenanceException {
        if (this.beingRented) {
            throw new RentException("This vehicle is currently rented out.");
        } else if (this.beingMaintained) {
            throw new MaintenanceException("This vehicle is already under maintenance.");
        } else {
            System.out.println("We are sending this vehicle away for maintenance.");
            beingMaintained = true;
        }
    }

    public void completeMaintenance(DateTime completionDate) throws RentException, MaintenanceException{
        if (this.beingRented) {
            throw new RentException("This vehicle is currently rented out and not under maintenance.");
        } else if (!this.beingMaintained) {
            throw new MaintenanceException("This vehicle is not under maintenance.");
        } else {
            MaintenanceRecord new_maintenance_record = new MaintenanceRecord(this.id, completionDate);
            allMaintenanceRecords.add(new_maintenance_record);
            this.lastMaintenanceDate = completionDate;
            this.beingMaintained = false;
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
