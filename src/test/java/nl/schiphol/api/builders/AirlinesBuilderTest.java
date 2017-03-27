package nl.schiphol.api.builders;

import org.junit.Test;

/**
 * Created by Thomas on 27-3-2017.
 */
public class AirlinesBuilderTest extends RequestBuilderTest {

    @Override
    RequestBuilder getInstance() {
        return new AirlinesBuilder();
    }
}
