package nl.schiphol.api.builders;

/**
 * Created by Thomas on 27-3-2017.
 */
public class RequiredHeaderException extends RuntimeException {

    private final String name;

    public RequiredHeaderException(String name) {
        super(String.format("Required header %s has not been supplied!", name));

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
