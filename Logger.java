import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wifi_log.txt", true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            writer.write("\n[" + sdf.format(new Date()) + "]\n");
            writer.write(data);
            writer.write("\n------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}