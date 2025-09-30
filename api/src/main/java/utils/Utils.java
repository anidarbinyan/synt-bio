package utils;

import java.util.concurrent.Callable;

public class Utils {

    public static void waitFor(long timeoutSec, Callable<Boolean> action, long pollInterval, String message) {
        long start = System.currentTimeMillis();
        long end = start + timeoutSec * 1000;

        try {
            while (System.currentTimeMillis() < end) {
                if (action.call()) {
                    return;
                }
                Thread.sleep(pollInterval * 1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while waiting: " + e.getMessage(), e);
        }

        throw new RuntimeException("Timeout after " + timeoutSec + "s. " + message);
    }
}
