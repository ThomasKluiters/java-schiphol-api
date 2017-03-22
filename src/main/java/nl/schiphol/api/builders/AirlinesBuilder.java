package nl.schiphol.api.builders;

import nl.schiphol.api.builders.airlines.Airlines;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AirlinesBuilder extends SimpleRequestBuilder<Airlines> {
    public AirlinesBuilder() {
        super(Airlines.class, "/public-flights/airlines", "v1");
    }
}
