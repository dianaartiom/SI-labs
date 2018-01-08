package utm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representation of the input encrypted string
 */
public class EncryptedString {
    List<String> encryptedString;

    /**
     * Constructor
     * @param encryptedString - String to be split by blank spaces
     */
    public EncryptedString(String encryptedString) {
        String contentArray[] = encryptedString
                .toLowerCase().split(" ");
        this.encryptedString = new ArrayList<>(Arrays.asList(contentArray));
    }

    /**
     * @return list of strings
     */
    public List<String> getEncryptedString() {
        return this.encryptedString;
    }

    /**
     * Setter
     * @param encryptedString
     */
    public void setEncryptedString(List<String> encryptedString) {
        this.encryptedString = encryptedString;
    }
}
