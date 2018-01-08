package integration;

import org.junit.Test;
import utm.EncryptedString;
import utm.language.EnLanguage;
import utm.matcher.*;
import utm.shifter.EnShifter;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LanguageMatcherTest {

    @Test
    public void shoudMatchSuccessfully() throws IOException {
        EncryptedString es = new EncryptedString("XOXKR IETG BL MH UX VHGLBWXKXW");
//        EncryptedString es = new EncryptedString("e");
//        EncryptedString es = new EncryptedString("dk");
        EnMatcher rm = new EnMatcher();
//        AbstractMatcher lm = new LanguageMatcher();
        System.out.println(rm.match(es, new EnLanguage(), new EnShifter()));
//        assertNotEquals(lm.match(es), null);
//        assertTrue(lm.match(es) == true);
    }
}
