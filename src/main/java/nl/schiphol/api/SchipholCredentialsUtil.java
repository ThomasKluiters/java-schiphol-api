package nl.schiphol.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Thomas on 22-3-2017.
 */
public class SchipholCredentialsUtil {

    /**
     * Attempts to load the specified file as a SchipholCredentials object.
     *
     * @param path the path to the json formatted file of the id and key.
     *
     * @return if successful the SchipholCredentials representation of the file, else null
     */
    public synchronized static SchipholCredentials loadFrom(@Nonnull final String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(fis, SchipholCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
