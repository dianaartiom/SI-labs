package utm.shifter;

import utm.DictionaryLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RoShifter extends Shifter {
    @Override
    public String shiftWord(String word, int times) throws IOException {
        List<String> alphabet = loadAlphabet();
        char[] charArray = word.toCharArray();
        String temp = "";
        for (int i = 0; i < charArray.length; i++) {
//            alphabet.contains()

            int ascii = (int) charArray[i] + times;
            if (ascii > 122) {
                ascii = ascii - 122 + 96;
            }
            charArray[i] = (char)(ascii);
            temp += (char)(ascii);
        }
        return temp;
    }

    List<String> loadAlphabet() throws IOException {
        String content = DictionaryLoader.readFile("C:/Users/ArtiomDiana/projects/SI-labs/lab3/src/main/resources/ro-alphabet");
        String contentArray[] = content.split(" ");

//        HashMap<String, Integer> alphabet
        return new ArrayList<>(Arrays.asList(contentArray));
    }
}
