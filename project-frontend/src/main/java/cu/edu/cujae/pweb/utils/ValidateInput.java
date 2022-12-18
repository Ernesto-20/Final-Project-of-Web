package cu.edu.cujae.pweb.utils;

public class ValidateInput {
    public static boolean isName(String text){
        for(int i=0; i<text.length(); i++)
            if(Character.isDigit(text.charAt(i)))
                return false;
        return true;
    }
}
