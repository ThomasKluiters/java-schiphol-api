package nl.schiphol.api.builders.flights;

/**
 * Created by Thomas on 22-3-2017.
 */
public class FlightCheckinAllocation {

    private String endTime;

    private String startTime;


    public class Rows {

        private Row[] rows;

        Rows() { }

        public Row[] getRows() {
            return rows;
        }
    }

    public class Row {

        private Desks desks;

        private String position;

        Row() { }

        public Desks getDesks() {
            return desks;
        }

        public String getPosition() {
            return position;
        }
    }

    public class Desks {

        private Desk[] desks;

        Desks() { }

        public Desk[] getDesks() {
            return desks;
        }



    }

    public class Desk {

        private CheckinClass checkinClass;

        private int position;

        Desk() { }

        public CheckinClass getCheckinClass() {
            return checkinClass;
        }

        public int getPosition() {
            return position;
        }
    }

    public class CheckinClass {

        private String code;

        private String description;

        CheckinClass() { }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

}
