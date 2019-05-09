import java.text.DecimalFormat;

public class RentalRecord {
    private String vehicleID;
    private String customerID;

    private DateTime rentDate;
    private DateTime estReturnDate;
    private DateTime actReturnDate;

    private double rentalFee;
    private double lateFee;

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public RentalRecord(String vehicleID, String customerID, DateTime rentDate, DateTime estReturnDate) {
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.rentDate = rentDate;
        this.estReturnDate = estReturnDate;
    }

    public String getRecordID() {
        return vehicleID + "_" + customerID + "_" + rentDate.getEightDigitDate();
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public DateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(DateTime rentDate) {
        this.rentDate = rentDate;
    }

    public DateTime getEstReturnDate() {
        return estReturnDate;
    }

    public String getStringEstReturnDate() {
        return estReturnDate.getFormattedDate();
    }

    public void setEstReturnDate(DateTime estReturnDate) {
        this.estReturnDate = estReturnDate;
    }

    public DateTime getActReturnDate() {
        return actReturnDate;
    }

    public String getStringActReturnDate() {
        return actReturnDate.getFormattedDate();
    }

    public void setActReturnDate(DateTime actReturnDate) {
        this.actReturnDate = actReturnDate;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public String toString() {
        return String.join(":", getRecordID(), getRentDate().getFormattedDate(), getStringEstReturnDate(), getStringActReturnDate());
    }

    public void getDetails() {
        System.out.printf("%-15s %15s %n", "Record ID: ", getRecordID());
        System.out.printf("%-15s %15s %n", "Rent Date:", getRentDate());
        System.out.printf("%-15s %15s %n", "Estimated Return Date:", getEstReturnDate());
        if (this.actReturnDate != null) {
            System.out.printf("%-15s %15s %n", "Actual Return Date:", getActReturnDate());
            System.out.printf("%-15s %15s %n", "Rental Fee:", rentalFee);
            System.out.printf("%-15s %15s %n", "Late Fee: ", lateFee);
        }
    }

}
