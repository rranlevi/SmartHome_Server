import Classes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Mocking {
    public static List<HouseholdDevice> householdDevices = createMockDevices();

    public static List<HouseholdDevice> createMockDevices() {
        List<HouseholdDevice> devices = new ArrayList<>();
        DeviceDataController mockDeviceDataController = createSpecialDeviceDataController("Room Light");
        devices.add(new HouseholdDevice(
                "1",
                "Room Light",
                "Living Room",
                "Light Description",
                mockDeviceDataController,
                createSpecialDeviceActionController("Room Light", mockDeviceDataController),
                Utils.encodeImageToBase64("light.png"),
                createMockDeviceFactoryInfo("Room Light")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("AC");
        devices.add(new HouseholdDevice(
                "2",
                "AC",
                "Bedroom",
                "AC Description",
                mockDeviceDataController,
                createSpecialDeviceActionController("AC", mockDeviceDataController),
                Utils.encodeImageToBase64("ac.png"),
                createMockDeviceFactoryInfo("AC")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Camera");
        devices.add(new HouseholdDevice(
                "3",
                "Camera",
                "Living Room",
                "Camera In Living Room",
                createSpecialDeviceDataController("Camera"),
                createSpecialDeviceActionController("Camera", mockDeviceDataController),
                Utils.encodeImageToBase64("camera.png"),
                createMockDeviceFactoryInfo("Camera")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Doorbell");
        devices.add(new HouseholdDevice(
                "4",
                "Doorbell",
                "Front Door",
                "Doorbell Description",
                createSpecialDeviceDataController("Doorbell"),
                createSpecialDeviceActionController("Doorbell", mockDeviceDataController),
                Utils.encodeImageToBase64("doorbell.png"),
                createMockDeviceFactoryInfo("Doorbell")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Smart TV");
        devices.add(new HouseholdDevice(
                "5",
                "Smart TV",
                "Living Room",
                "Smart TV Description",
                createSpecialDeviceDataController("Smart TV"),
                createSpecialDeviceActionController("Smart TV", mockDeviceDataController),
                Utils.encodeImageToBase64("tv.png"),
                createMockDeviceFactoryInfo("Smart TV")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Washing Machine");
        devices.add(new HouseholdDevice(
                "6",
                "Washing Machine",
                "Laundry Room",
                "Washing Machine Description",
                createSpecialDeviceDataController("Washing Machine"),
                createSpecialDeviceActionController("Washing Machine", mockDeviceDataController),
                Utils.encodeImageToBase64("washing-machine.png"),
                createMockDeviceFactoryInfo("Washing Machine")
        ));

        return devices;
    }

    private static DeviceDataController createSpecialDeviceDataController(String deviceType) {

        List<DeviceInfo> deviceData = new ArrayList<>();
        switch (deviceType) {
            case "Room Light":
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("LightChannelPower", "/light/power")));
                deviceData.add(new DeviceInfo(new Info("Brightness", "70", "%"), new DeviceChannel("LightChannelBrightness", "/light/brightness")));
                break;
            case "AC":
                deviceData.add(new DeviceInfo(new Info("Temperature", "24", "°C"), new DeviceChannel("ACChannelTemp", "/ac/temperature")));
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("ACChannelPower", "/ac/power")));
                break;
            case "Camera":
                deviceData.add(new DeviceInfo(new Info("Current Frame", "", ""), new DeviceChannel("CameraChannelFrame", "/camera/frame")));
                break;
            case "Doorbell":
                deviceData.add(new DeviceInfo(new Info("Status", "Idle", ""), new DeviceChannel("DoorbellChannelStatus", "/doorbell/status")));
                deviceData.add(new DeviceInfo(new Info("Last Ring", "10:45 AM", ""), new DeviceChannel("DoorbellChannelLast", "/doorbell/last")));
                break;
            case "Smart TV":
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("TVChannelPower", "/tv/power")));
                deviceData.add(new DeviceInfo(new Info("Volume", "15", ""), new DeviceChannel("TVChannelVolume", "/tv/volume")));
                deviceData.add(new DeviceInfo(new Info("Channel", "HBO", ""), new DeviceChannel("TVChannelChannel", "/tv/channel")));
                break;
            case "Washing Machine":
                deviceData.add(new DeviceInfo(new Info("Cycle", "Medium", ""), new DeviceChannel("WashingMachineChannelCycle", "/washingmachine/cycle")));
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("WashingMachineChannelPower", "/washingmachine/power")));
                break;
            default:
                break;
        }

        return new DeviceDataController(deviceType + " Data Controller", deviceType + " Data Description", deviceData);
    }

    private static DeviceActionController createSpecialDeviceActionController(String deviceType, DeviceDataController mockDeviceDataController) {
        //Name of the action channel must be set by the name of the data channel - if we want it to be sync

        List<DeviceAction> deviceActions = new ArrayList<>();
        DeviceActionController actionController;
        Optional<DeviceInfo> info;
        switch (deviceType) {
            case "Room Light":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("LightChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the light on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/light/set_power"),
                        deviceInfo.getChannel()
                )));
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("LightChannelBrightness"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Brightness",
                        "Turn the brightness up or down",
                        new Slider("Slider", "0", ""),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/light/set_brightness"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Light Action Controller", "Room Light Actions", deviceActions);
                break;

            case "AC":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("ACChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the AC on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/ac/set_power"),
                        deviceInfo.getChannel()
                )));
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("ACChannelTemp"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Temperature",
                        "Turn the temperature up or down",
                        new Slider("Slider", "25", "°C"),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/ac/set_temp"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("AC Action Controller", "AC Actions", deviceActions);
                break;

            case "Camera":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("CameraChannelFrame"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Camera Frame",
                        "Gets the camera frame",
                        new CameraStream("CameraStream"),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/camera/next_frame"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Camera Action Controller", "Camera Actions", deviceActions);
                break;

            case "Doorbell":
//                deviceActions.add(new DeviceAction(
//                        "Ring",
//                        "Ring the doorbell",
//                        new Switch("Button","NotImplemented"),
//                        true,
//                        new DeviceChannel("DoorbellChannel", "/doorbell/ring")
//                ));
                actionController = new DeviceActionController("Doorbell Action Controller", "Doorbell Actions", deviceActions);
                break;

            case "Smart TV":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("TVChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the TV on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/tv/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("TVChannelChannel"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Change Channel",
                        "Change the TV channel",
                        new Dropdown("Dropdown", new ArrayList<>(Arrays.asList("HBO", "CNN", "Fox News"))),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/tv/set_channel"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("TVChannelVolume"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Change the TV volume",
                        "Change the TV channel",
                        new Slider("Slider", "10", ""),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/tv/set_volume"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("TV Action Controller", "Smart TV Actions", deviceActions);
                break;

            case "Washing Machine":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("WashingMachineChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the Washing Machine on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/washingmachine/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("WashingMachineChannelCycle"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Cycle",
                        "Set the washing cycle",
                        new Dropdown("Dropdown", new ArrayList<>(Arrays.asList("Short", "Medium", "Long"))),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/washingmachine/set_cycle"),
                        deviceInfo.getChannel()
                )));
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
            case "Camera":
                factoryInfo.add(new Info("FactoryName", "Camera Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "CamTech", ""));
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
