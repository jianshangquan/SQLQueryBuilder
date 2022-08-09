package Builder;


public class SQLUtils {
    public static String formatValue(String value){
        if(SQLValidator.isNumeric(value)){
            return value;
        }else{
            return "\"" + value + "\"";
        }
    }
}
