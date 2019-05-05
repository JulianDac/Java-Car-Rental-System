public class MaintenanceRecord {
    private String vehicleID;
    private DateTime maintenanceDate;
    private DateTime completionDate;

    public MaintenanceRecord(String vehicleID , DateTime completionDate) {
        this.vehicleID = vehicleID;
        this.completionDate = completionDate;
    }


}
