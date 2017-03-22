package nl.schiphol.api.builders.flights;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightCheckinAllocations {

    private FlightCheckinAllocation[] checkinAllocations;

    private Remarks remarks;

    FlightCheckinAllocations() { }

    public Remarks getRemarks() {
        return remarks;
    }

    public FlightCheckinAllocation[] getCheckinAllocations() {
        return checkinAllocations;
    }

    public static class Remarks {

        private String[] remarks;

        Remarks() { }

        public String[] getRemarks() {
            return remarks;
        }
    }

}
