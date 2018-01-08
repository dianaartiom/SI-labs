package utm.language;

import utm.DictionaryLoader;

import java.io.*;
import java.util.*;

public abstract class Language {

    protected String name;

    /**
     * loads 100 most common english words
     * @return a list of strings - 100 most common english or romanian words
     * @throws IOException
     */

    public List<String> getDictionary() throws IOException {
        String content = DictionaryLoader.readFile(
                String.format("C:/Users/ArtiomDiana/projects/SI-labs/lab3/src/main/resources/%s.json",
                        this.name));
        String contentArray[] = content.split(" ");

        return new ArrayList<>(Arrays.asList(contentArray));
    }
}
