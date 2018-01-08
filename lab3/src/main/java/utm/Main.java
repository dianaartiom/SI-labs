package utm;

import utm.language.EnLanguage;
import utm.matcher.EnMatcher;
import utm.shifter.EnShifter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EncryptedString es = new EncryptedString("AB ZNRL MAX WXTWEBGX YHK MABL ETUHKTMHKR BL MAX MPXGMBXMA HY WXVXFUXK B PHNEW EBDX MH PBLA RHN ZHHW ENVD PBMA BM TGW ATOX T GBVX EBYX");
        EnMatcher rm = new EnMatcher();
        System.out.println(rm.match(es, new EnLanguage(), new EnShifter()));
    }
}
