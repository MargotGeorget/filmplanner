package utils;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputVerification {

    public InputVerification() {
    }

    public boolean isStringWithoutSpecialSymbol(String str){
        Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*@£¨µ%§;:!?,°&3#`?^$\\\\|]");
        Matcher m = SPECIAL_REGEX_CHARS.matcher(str);
        if(m.find()){
            return false;
        }else {
            return true;
        }
    }

    //TODO: factoriser les fonctions
    public boolean isPhoneNumber(String str){
        if(str.length()==10) {
            Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[0-9]{10}");
            Matcher m = SPECIAL_REGEX_CHARS.matcher(str);
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isEmail(String str){
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //TODO: Test unitaire pour les fonctions

    public static void main(String[] args) {
        InputVerification iv = new InputVerification();
        System.out.println(iv.isPhoneNumber("09954323454365"));
        System.out.println(iv.isPhoneNumber("0781131045"));
        System.out.println(iv.isPhoneNumber("365"));
        System.out.println(iv.isPhoneNumber("erg34"));
        System.out.println(iv.isPhoneNumber("fdgh"));

    }
}

