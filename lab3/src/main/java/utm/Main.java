package utm;

import utm.language.EnLanguage;
import utm.matcher.EnMatcher;
import utm.shifter.EnShifter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        EncryptedString es = new EncryptedString("RD QTAJ KTW YMJ WTRFS JRUNWJ NX ZSIJSNFGQD LWJFYJW YMFS KTW RDXJQK. YMJ LWJFYJXY JRUNWJ JAJW YT MFAJ JCNXYJI. N UQJILJ RD JYJWSFQ XJWANYZIJ FSI N FR KTWJAJW GTZSI YT XJWAJ NY, NS QNKJ FSI NS IJFYM. YMJD MFAJ RJWJQD LNAJS ZX: WTFIX, HJSYWFQ MJFYNSL, HTSHWJYJ, YMJ HFQJSIFW, FSI KQZXMNSL YTNQJYX FSI XJBJWX.");
//        EncryptedString es = new EncryptedString("e");
//        EncryptedString es = new EncryptedString("dk");
        EnMatcher rm = new EnMatcher();
//        AbstractMatcher lm = new LanguageMatcher();
        System.out.println(rm.match(es, new EnLanguage(), new EnShifter()));
//        assertNotEquals(lm.match(es), null);
//        assertTrue(lm.match(es) == true);

    }

    public void compute(String[] args) {

    }

    public void compareToEnglishDictionary() {

    }
}
