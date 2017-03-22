package nl.schiphol.api.builders.flights;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightCheckinAllocations {

    private FlightCheckinAllocation[] checkinAllocations;

    private String[] remarks;

    FlightCheckinAllocations() { }

    public String[] getRemarks() {
        return remarks;
    }

    public FlightCheckinAllocation[] getCheckinAllocations() {
        return checkinAllocations;
    }

}
