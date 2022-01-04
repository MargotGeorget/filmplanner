package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * Test if a string contains special characters
     * @param str
     * @return
     */
    public static boolean isStringWithoutSpecialSymbol(String str){
        Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*@£¨µ%§;:!?,°&3#`?^$\\\\|]");
        Matcher m = SPECIAL_REGEX_CHARS.matcher(str);
        if(m.find()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Test if a string matches a phone number
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str){
        if(str.length()==10) {
            Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[0-9]{10}");
            Matcher m = SPECIAL_REGEX_CHARS.matcher(str);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * Test if a string matches an email
     * @param str
     * @return
     */
    public static boolean isEmail(String str){
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * Test if a string matches a date
     * @param str
     * @return
     */
    public static boolean isDate(String str){
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-](0[1-9]|1[012])[\\/\\-]\\d{4}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //TODO: Test unitaire pour les fonctions

    public static void main(String[] args) {

        System.out.println(ValidationUtils.isPhoneNumber("09954323454365"));
        System.out.println(ValidationUtils.isPhoneNumber("0781131045"));
        System.out.println(ValidationUtils.isPhoneNumber("365"));
        System.out.println(ValidationUtils.isPhoneNumber("erg34"));
        System.out.println(ValidationUtils.isPhoneNumber("fdgh"));


        System.out.println(ValidationUtils.isDate("34/12/2023"));
        System.out.println(ValidationUtils.isDate("12/12/2023"));
        System.out.println(ValidationUtils.isDate("344/12/2023"));
        System.out.println(ValidationUtils.isDate("31/2/2023"));
        System.out.println(ValidationUtils.isDate("4/12/2023"));
        System.out.println(ValidationUtils.isDate("31/02/2023"));
        System.out.println(ValidationUtils.isDate("04/12/2023"));
    }
}

