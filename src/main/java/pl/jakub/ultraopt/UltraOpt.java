package pl.jakub.ultraopt;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltraOpt implements ModInitializer {

    public static final String MOD_ID = "ultraopt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("UltraOpt loaded successfully!");
    }
}
