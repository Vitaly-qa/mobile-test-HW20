package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${platform}.properties"
})
public interface DeviceConfig extends Config {

    String device();

    String version();

    String deviceUrl();

}