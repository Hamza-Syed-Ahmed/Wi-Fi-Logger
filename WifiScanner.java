import java.io.*;
import java.util.*;

public class WifiScanner {
    public static String scanNetworks() {
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "netsh wlan show networks mode=bssid");
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<Integer> speeds = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("SSID") || line.startsWith("Signal") || line.startsWith("Authentication")) {
                    result.append(line).append("\n");
                }
                if (line.startsWith("Basic rates") || line.startsWith("Other rates")) {
                    result.append(line).append("\n");
                    String[] parts = line.replaceAll("[^0-9 ]", "").trim().split("\s+");
                    for (String part : parts) {
                        try {
                            speeds.add(Integer.parseInt(part));
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }
            if (!speeds.isEmpty()) {
                int maxSpeed = Collections.max(speeds);
                result.append("Max Speed (Mbps): ").append(maxSpeed).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}