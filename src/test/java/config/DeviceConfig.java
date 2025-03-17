package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})
public interface DeviceConfig extends Config {
    @Key("deviceModel")
    @DefaultValue("Galaxy A71")
    String getDeviceModel();

    @Key("systemVersion")
    @DefaultValue("13")
    String getSystemVersion();

    @Key("appVersion")
    String getAppVersion();

    @Key("appUrl")
    String getAppUrl();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();
}