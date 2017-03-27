package nl.schiphol.api.builders.exceptions;

/**
 * Created by Thomas on 27-3-2017.
 */
public class RequiredHeaderException extends RuntimeException {

    private final String name;

    public RequiredHeaderException(String name) {
        super(String.format("The required header '%s' must be supplied.", name));

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
