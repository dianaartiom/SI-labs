package utm.shifter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Shifter {

    public float sharedElementsPercentage(List<String> sample, List<String> dictionary) {
        float wordOccurence = 0;
        for (String str : sample) {
            if (dictionary.contains(str)) {
                wordOccurence++;
            }
        }
        return wordOccurence / sample.size();
    }

    public List<String> shiftRight(List<String> expression, int times) throws IOException {
        List<String> bSet = new ArrayList<>(expression);

        for (int i = 0; i <bSet.size(); i++) {
            bSet.set(i, shiftWord(bSet.get(i), times));
        }
        return bSet;
    }

    public abstract String shiftWord(String word, int times) throws IOException;
}
