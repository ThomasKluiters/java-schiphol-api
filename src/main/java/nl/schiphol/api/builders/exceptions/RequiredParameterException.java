package nl.schiphol.api.builders.exceptions;

/**
 * Created by Thomas on 27-3-2017.
 */
public class RequiredParameterException extends RuntimeException {

    private final String name;

    public RequiredParameterException(String name) {
        super(String.format("The required parameter '%s' must be supplied.", name));

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
