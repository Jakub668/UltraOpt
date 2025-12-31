package pl.jakub.ultraopt.settings;

public class ToggleOption {

    private boolean enabled;

    public ToggleOption(boolean defaultValue) {
        this.enabled = defaultValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void set(boolean value) {
        this.enabled = value;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}
