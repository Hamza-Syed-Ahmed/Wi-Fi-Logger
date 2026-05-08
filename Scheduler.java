import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public void startLogging() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            String wifiData = WifiScanner.scanNetworks();
            Logger.log(wifiData);
            System.out.println("Scan completed and logged.");
        };
        executor.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);
    }
}