package nl.schiphol.api.models.flights;

import lombok.Data;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class FlightCheckinAllocations {

    private FlightCheckinAllocation[] checkinAllocations;

    private Remarks remarks;

    FlightCheckinAllocations() { }

    @Data
    public static class Remarks {

        private String[] remarks;

        Remarks() { }
    }

}
