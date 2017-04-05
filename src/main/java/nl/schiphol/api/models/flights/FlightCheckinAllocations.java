package nl.schiphol.api.models.flights;

import lombok.Data;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class FlightCheckinAllocations implements Iterable<FlightCheckinAllocation> {

    private List<FlightCheckinAllocation> checkinAllocations;

    private Remarks remarks;

    FlightCheckinAllocations() { }

    @Override
    @Nonnull
    public Iterator<FlightCheckinAllocation> iterator() {
        return getCheckinAllocations().iterator();
    }


    @Data
    public static class Remarks implements Iterable<String> {

        private List<String> remarks;

        Remarks() { }

        @Override
        @Nonnull
        public Iterator<String> iterator() {
            return remarks.iterator();
        }
    }

}
