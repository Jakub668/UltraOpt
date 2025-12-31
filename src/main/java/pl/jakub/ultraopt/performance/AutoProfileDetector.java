package pl.jakub.ultraopt.performance;

public class AutoProfileDetector {

    public static PerformanceProfile detect() {
        int cores = Runtime.getRuntime().availableProcessors();

        if (cores <= 2) return PerformanceProfile.LOW;
        if (cores <= 4) return PerformanceProfile.MEDIUM;
        if (cores <= 6) return PerformanceProfile.HIGH;
        return PerformanceProfile.ULTRA;
    }
}