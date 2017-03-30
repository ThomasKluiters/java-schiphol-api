package nl.schiphol.api.models.flights;

import lombok.Data;

/**
 * Created by Thomas on 22-3-2017.
 */
@Data
public class FlightCheckinAllocation {

    private String endTime;

    private String startTime;

    private Rows rows;

    FlightCheckinAllocation() { }

    @Data
    public static class Rows {

        private Row[] rows;

        Rows() { }

    }

    @Data
    public static class Row {

        private Desks desks;

        private String position;

        Row() { }

    }

    @Data
    public static class Desks {

        private Desk[] desks;

        Desks() { }

    }

    @Data
    public static class Desk {

        private CheckinClass checkinClass;

        private int position;

        Desk() { }

    }

    @Data
    public static class CheckinClass {

        private String code;

        private String description;

        CheckinClass() { }

    }

}
