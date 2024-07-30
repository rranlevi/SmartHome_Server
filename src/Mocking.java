import Classes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mocking {
    public static List<HouseholdDevice> createMockDevices() {
        List<HouseholdDevice> devices = new ArrayList<>();

        devices.add(new HouseholdDevice(
                "1",
                "Room Light",
                "Living Room",
                "Light Description",
                createSpecialDeviceDataController("Room Light"),
                createSpecialDeviceActionController("Room Light"),
                Utils.encodeImageToBase64("light.png"),
                createMockDeviceFactoryInfo("Room Light")
        ));

        devices.add(new HouseholdDevice(
                "2",
                "AC",
                "Bedroom",
                "AC Description",
                createSpecialDeviceDataController("AC"),
                createSpecialDeviceActionController("AC"),
                Utils.encodeImageToBase64("ac.png"),
                createMockDeviceFactoryInfo("AC")
        ));

        devices.add(new HouseholdDevice(
                "3",
                "Thermostat",
                "Hallway",
                "Thermostat Description",
                createSpecialDeviceDataController("Thermostat"),
                createSpecialDeviceActionController("Thermostat"),
                Utils.encodeImageToBase64("thermostat.png"),
                createMockDeviceFactoryInfo("Thermostat")
        ));

        devices.add(new HouseholdDevice(
                "4",
                "Doorbell",
                "Front Door",
                "Doorbell Description",
                createSpecialDeviceDataController("Doorbell"),
                createSpecialDeviceActionController("Doorbell"),
                Utils.encodeImageToBase64("doorbell.png"),
                createMockDeviceFactoryInfo("Doorbell")
        ));

        devices.add(new HouseholdDevice(
                "5",
                "Smart TV",
                "Living Room",
                "Smart TV Description",
                createSpecialDeviceDataController("Smart TV"),
                createSpecialDeviceActionController("Smart TV"),
                Utils.encodeImageToBase64("tv.png"),
                createMockDeviceFactoryInfo("Smart TV")
        ));

        devices.add(new HouseholdDevice(
                "6",
                "Washing Machine",
                "Laundry Room",
                "Washing Machine Description",
                createSpecialDeviceDataController("Washing Machine"),
                createSpecialDeviceActionController("Washing Machine"),
                Utils.encodeImageToBase64("washing-machine.png"),
                createMockDeviceFactoryInfo("Washing Machine")
        ));

        return devices;
    }

    private static DeviceDataController createSpecialDeviceDataController(String deviceType) {

        List<DeviceInfo> deviceData = new ArrayList<>();
        switch (deviceType) {
            case "Room Light":
                deviceData.add(new DeviceInfo(new Info("Brightness", "70", "%"), new DeviceChannel("LightChannel", "/path/to/light/brightness")));
                deviceData.add(new DeviceInfo(new Info("Power", "On", ""), new DeviceChannel("LightChannel", "/path/to/light/power")));
                break;
            case "AC":
                deviceData.add(new DeviceInfo(new Info("Temperature", "24", "°C"), new DeviceChannel("ACChannel", "/path/to/ac/temperature")));
                deviceData.add(new DeviceInfo(new Info("Power", "On", ""), new DeviceChannel("ACChannel", "/path/to/ac/power")));
                break;
            case "Thermostat":
                deviceData.add(new DeviceInfo(new Info("Current Temperature", "22", "°C"), new DeviceChannel("ThermostatChannel", "/path/to/thermostat/current")));
                deviceData.add(new DeviceInfo(new Info("Target Temperature", "20", "°C"), new DeviceChannel("ThermostatChannel", "/path/to/thermostat/target")));
                break;
            case "Doorbell":
                deviceData.add(new DeviceInfo(new Info("Status", "Idle", ""), new DeviceChannel("DoorbellChannel", "/path/to/doorbell/status")));
                deviceData.add(new DeviceInfo(new Info("Last Ring", "10:45 AM", ""), new DeviceChannel("DoorbellChannel", "/path/to/doorbell/last")));
                break;
            case "Smart TV":
                deviceData.add(new DeviceInfo(new Info("Volume", "15", ""), new DeviceChannel("TVChannel", "/path/to/tv/volume")));
                deviceData.add(new DeviceInfo(new Info("Channel", "HBO", ""), new DeviceChannel("TVChannel", "/path/to/tv/channel")));
                break;
            case "Washing Machine":
                deviceData.add(new DeviceInfo(new Info("Cycle", "Spin", ""), new DeviceChannel("WashingMachineChannel", "/path/to/washingmachine/cycle")));
                deviceData.add(new DeviceInfo(new Info("Status", "Running", ""), new DeviceChannel("WashingMachineChannel", "/path/to/washingmachine/status")));
                break;
            default:
                break;
        }

        return new DeviceDataController(deviceType + " Data Controller", deviceType + " Data Description", deviceData);
    }

    private static DeviceActionController createSpecialDeviceActionController(String deviceType) {
        List<DeviceAction> deviceActions = new ArrayList<>();
        DeviceActionController actionController;

        switch (deviceType) {
            case "Room Light":
                deviceActions.add(new DeviceAction(
                        "Turn On",
                        "Turn the light on",
                        new Button("Button"),
                        true,
                        new DeviceChannel("LightChannel", "/path/to/light/on")
                ));
                deviceActions.add(new DeviceAction(
                        "Turn Off",
                        "Turn the light off",
                        new Button("Button"),
                        true,
                        new DeviceChannel("LightChannel", "/path/to/light/off")
                ));
                actionController = new DeviceActionController("Light Action Controller", "Room Light Actions", deviceActions);
                break;

            case "AC":
                deviceActions.add(new DeviceAction(
                        "Turn On",
                        "Turn the AC on",
                        new Button("Button"),
                        true,
                        new DeviceChannel("ACChannel", "/path/to/ac/on")
                ));
                deviceActions.add(new DeviceAction(
                        "Turn Off",
                        "Turn the AC off",
                        new Button("Button"),
                        true,
                        new DeviceChannel("ACChannel", "/path/to/ac/off")
                ));
                deviceActions.add(new DeviceAction(
                        "Set Temperature",
                        "Set the AC temperature",
                        new Slider("Slider", "25", "°C"),
                        true,
                        new DeviceChannel("ACChannel", "/path/to/ac/settemp")
                ));
                actionController = new DeviceActionController("AC Action Controller", "AC Actions", deviceActions);
                break;

            case "Thermostat":
                deviceActions.add(new DeviceAction(
                        "Increase Temp",
                        "Increase the temperature",
                        new Button("Button"),
                        true,
                        new DeviceChannel("ThermostatChannel", "/path/to/thermostat/increase")
                ));
                deviceActions.add(new DeviceAction(
                        "Decrease Temp",
                        "Decrease the temperature",
                        new Button("Button"),
                        true,
                        new DeviceChannel("ThermostatChannel", "/path/to/thermostat/decrease")
                ));
                deviceActions.add(new DeviceAction(
                        "Set Temp",
                        "Set the temperature",
                        new Widget("Slider"),
                        true,
                        new DeviceChannel("ThermostatChannel", "/path/to/thermostat/set")
                ));
                actionController = new DeviceActionController("Thermostat Action Controller", "Thermostat Actions", deviceActions);
                break;

            case "Doorbell":
                deviceActions.add(new DeviceAction(
                        "Ring",
                        "Ring the doorbell",
                        new Button("Button"),
                        true,
                        new DeviceChannel("DoorbellChannel", "/path/to/doorbell/ring")
                ));
                actionController = new DeviceActionController("Doorbell Action Controller", "Doorbell Actions", deviceActions);
                break;

            case "Smart TV":
                deviceActions.add(new DeviceAction(
                        "Turn On",
                        "Turn the TV on",
                        new Button("Button"),
                        true,
                        new DeviceChannel("TVChannel", "/path/to/tv/on")
                ));
                deviceActions.add(new DeviceAction(
                        "Turn Off",
                        "Turn the TV off",
                        new Button("Button"),
                        true,
                        new DeviceChannel("TVChannel", "/path/to/tv/off")
                ));
                deviceActions.add(new DeviceAction(
                        "Change Channel",
                        "Change the TV channel",
                        new Dropdown("Dropdown", new ArrayList<>(Arrays.asList("HBO", "CNN", "Fox News"))),
                        true,
                        new DeviceChannel("TVChannel", "/path/to/tv/channel")
                ));
                actionController = new DeviceActionController("TV Action Controller", "Smart TV Actions", deviceActions);
                break;

            case "Washing Machine":
                deviceActions.add(new DeviceAction(
                        "Start",
                        "Start the washing machine",
                        new Button("Button"),
                        true,
                        new DeviceChannel("WashingMachineChannel", "/path/to/washingmachine/start")
                ));
                deviceActions.add(new DeviceAction(
                        "Stop",
                        "Stop the washing machine",
                        new Button("Button"),
                        true,
                        new DeviceChannel("WashingMachineChannel", "/path/to/washingmachine/stop")
                ));
                deviceActions.add(new DeviceAction(
                        "Set Cycle",
                        "Set the washing cycle",
                        new Dropdown("Dropdown", new ArrayList<>(Arrays.asList("Short", "Medium", "Long"))),
                        true,
                        new DeviceChannel("WashingMachineChannel", "/path/to/washingmachine/cycle")
                ));
                actionController = new DeviceActionController("Washing Machine Action Controller", "Washing Machine Actions", deviceActions);
                break;

            default:
                actionController = new DeviceActionController("Default Action Controller", "Default Actions", deviceActions);
                break;
        }

        return actionController;
    }

    private static List<Info> createMockDeviceFactoryInfo(String deviceType) {
        List<Info> factoryInfo = new ArrayList<>();
        switch (deviceType) {
            case "Room Light":
                factoryInfo.add(new Info("FactoryName", "Room Light Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "Light Co.", ""));
                break;
            case "AC":
                factoryInfo.add(new Info("FactoryName", "AC Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "AC Inc.", ""));
                break;
            case "Thermostat":
                factoryInfo.add(new Info("FactoryName", "Thermostat Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "ThermoTech", ""));
                break;
            case "Doorbell":
                factoryInfo.add(new Info("FactoryName", "Doorbell Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "Doorbell Corp.", ""));
                break;
            case "Smart TV":
                factoryInfo.add(new Info("FactoryName", "Smart TV Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "Smart TV Ltd.", ""));
                break;
            case "Washing Machine":
                factoryInfo.add(new Info("FactoryName", "Washing Machine Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "Washer World", ""));
                break;
            default:
                factoryInfo.add(new Info("FactoryName", "Default Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "Default Manufacturer", ""));
                break;
        }
        return factoryInfo;
    }
}
