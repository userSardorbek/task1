import org.telegram.telegrambots.meta.api.objects.Update;

public class ExtraMethods {
    public static boolean parseDouble(String text) {
        try {
            double d = Double.parseDouble(String.valueOf(text));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
