package utm.shifter;

public class EnShifter extends Shifter {

    @Override
    public String shiftWord(String word, int times) {
        char[] charArray = word.toCharArray();
        String temp = "";
        for (int i = 0; i < charArray.length; i++) {
            int ascii = (int) charArray[i] + times;
            if (ascii > 122) {
                ascii = ascii - 122 + 96;
            }
            charArray[i] = (char)(ascii);
            temp += (char)(ascii);
        }
        return temp;
    }
}
