package nl.schiphol.api.builders;

import nl.schiphol.api.models.airlines.Airlines;

/**
 * Created by Thomas on 22-3-2017.
 */
public class AirlinesBuilder extends RequestBuilder<Airlines, AirlinesBuilder> {
    public AirlinesBuilder() {
        super(Airlines.class, "/public-flights/airlines");
        resourceVersion("v1");
    }

    @Override
    protected AirlinesBuilder getThis() {
        return this;
    }
}
