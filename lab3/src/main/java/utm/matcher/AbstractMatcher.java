package utm.matcher;

import utm.EncryptedString;
import utm.language.Language;
import utm.shifter.Shifter;

import java.io.IOException;
import java.util.List;

abstract class AbstractMatcher {
    int size;

    public List<String> match(EncryptedString es, Language language, Shifter shifter) throws IOException {
        List<String> elementList;
        for (int i = 0; i <= size; i++) {
            elementList = es.getEncryptedString();
            List<String> shifted = shifter.shiftRight(elementList, i);
            float percentage = shifter.sharedElementsPercentage(shifted, language.getDictionary());
            if (percentage >= 0.1) {
                return shifted;
            }
        }
        return null;
    }
}
