package pl.jakub.ultraopt.performance;

public class PerformanceManager {

    private static PerformanceProfile current = PerformanceProfile.HIGH;

    public static PerformanceProfile getProfile() {
        return current;
    }

    public static void setProfile(PerformanceProfile profile) {
        current = profile;
    }
}