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
                "A smart light that can be dimmed and controlled remotely.",
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
                "A smart air conditioner with temperature control and remote access.",
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
                "A security camera with live feed streaming to monitor your living space.",
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
                "A smart doorbell with video and audio capabilities to see and talk to visitors.",
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
                "A smart TV with streaming capabilities and voice control.",
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
                "A washing machine with multiple wash cycles and remote start.",
                createSpecialDeviceDataController("Washing Machine"),
                createSpecialDeviceActionController("Washing Machine", mockDeviceDataController),
                Utils.encodeImageToBase64("washing-machine.png"),
                createMockDeviceFactoryInfo("Washing Machine")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Heater");
        devices.add(new HouseholdDevice(
                "7",
                "Heater",
                "Living Room",
                "A smart heater that adjusts temperature based on room conditions.",
                createSpecialDeviceDataController("Heater"),
                createSpecialDeviceActionController("Heater", mockDeviceDataController),
                Utils.encodeImageToBase64("heater.png"),
                createMockDeviceFactoryInfo("Heater")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Refrigerator");
        devices.add(new HouseholdDevice(
                "8",
                "Refrigerator",
                "Kitchen",
                "A smart refrigerator with temperature control and energy-saving features.",
                createSpecialDeviceDataController("Refrigerator"),
                createSpecialDeviceActionController("Refrigerator", mockDeviceDataController),
                Utils.encodeImageToBase64("fridge.png"),
                createMockDeviceFactoryInfo("Refrigerator")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Oven");
        devices.add(new HouseholdDevice(
                "9",
                "Oven",
                "Kitchen",
                "A smart oven with pre-set cooking modes and remote start.",
                createSpecialDeviceDataController("Oven"),
                createSpecialDeviceActionController("Oven", mockDeviceDataController),
                Utils.encodeImageToBase64("oven.png"),
                createMockDeviceFactoryInfo("Oven")
        ));

        mockDeviceDataController = createSpecialDeviceDataController("Fan");
        devices.add(new HouseholdDevice(
                "10",
                "Fan",
                "Bedroom",
                "A smart fan with adjustable speed and oscillation control.",
                createSpecialDeviceDataController("Fan"),
                createSpecialDeviceActionController("Fan", mockDeviceDataController),
                Utils.encodeImageToBase64("fan.png"),
                createMockDeviceFactoryInfo("Fan")
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
                deviceData.add(new DeviceInfo(new Info("Live Feed", Utils.processFrameFromVideo(0), ""), new DeviceChannel("CameraChannelFrame", "/camera/frame")));
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
            case "Heater":
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("HeaterChannelPower", "/heater/power")));
                deviceData.add(new DeviceInfo(new Info("Temperature", "22", "°C"), new DeviceChannel("HeaterChannelTemp", "/heater/temperature")));
                break;
            case "Refrigerator":
                deviceData.add(new DeviceInfo(new Info("Temperature", "4", "°C"), new DeviceChannel("RefrigeratorChannelTemp", "/refrigerator/temperature")));
                deviceData.add(new DeviceInfo(new Info("Power", "On", ""), new DeviceChannel("RefrigeratorChannelPower", "/refrigerator/power")));
                break;
            case "Oven":
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("OvenChannelPower", "/oven/power")));
                deviceData.add(new DeviceInfo(new Info("Temperature", "180", "°C"), new DeviceChannel("OvenChannelTemp", "/oven/temperature")));
                break;
            case "Fan":
                deviceData.add(new DeviceInfo(new Info("Power", "Off", ""), new DeviceChannel("FanChannelPower", "/fan/power")));
                deviceData.add(new DeviceInfo(new Info("Speed", "Medium", ""), new DeviceChannel("FanChannelSpeed", "/fan/speed")));
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
                        new Slider("Slider", "0", "", "0", "100"),
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
                        new Slider("Slider", "25", "°C", "16", "33"),
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
                        new CameraStream("CameraStream", Utils.processFrameFromVideo(0)),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/camera/next_frame"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Camera Action Controller", "Camera Actions", deviceActions);
                break;

            case "Doorbell":
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
                        new Slider("Slider", "10", "", "0", "100"),
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

            case "Heater":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("HeaterChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the heater on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/heater/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("HeaterChannelTemp"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Temperature",
                        "Set the heater temperature",
                        new Slider("Slider", "22", "°C", "20", "40"),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/heater/set_temp"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Heater Action Controller", "Heater Actions", deviceActions);
                break;

            case "Refrigerator":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("RefrigeratorChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the refrigerator on or off",
                        new Switch("Switch", true),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/refrigerator/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("RefrigeratorChannelTemp"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Temperature",
                        "Set the refrigerator temperature",
                        new Slider("Slider", "4", "°C", "0", "16"),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/refrigerator/set_temp"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Refrigerator Action Controller", "Refrigerator Actions", deviceActions);
                break;

            case "Oven":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("OvenChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the oven on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/oven/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("OvenChannelTemp"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Temperature",
                        "Set the oven temperature",
                        new Slider("Slider", "180", "°C", "30", "350"),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/oven/set_temp"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Oven Action Controller", "Oven Actions", deviceActions);
                break;

            case "Fan":
                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("FanChannelPower"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Power",
                        "Turn the fan on or off",
                        new Switch("Switch", false),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/fan/set_power"),
                        deviceInfo.getChannel()
                )));

                info = mockDeviceDataController.getDeviceData().stream()
                        .filter(device -> device.getChannel().getChannelName().equals("FanChannelSpeed"))
                        .findFirst();
                info.ifPresent(deviceInfo -> deviceActions.add(new DeviceAction(
                        "Set Speed",
                        "Set the fan speed",
                        new Dropdown("Dropdown", new ArrayList<>(Arrays.asList("Low", "Medium", "High"))),
                        true,
                        new DeviceChannel(deviceInfo.getChannel().getChannelName(), "/fan/set_speed"),
                        deviceInfo.getChannel()
                )));
                actionController = new DeviceActionController("Fan Action Controller", "Fan Actions", deviceActions);
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
            case "Heater":
                factoryInfo.add(new Info("FactoryName", "Heater Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "HeatMaster", ""));
                break;

            case "Refrigerator":
                factoryInfo.add(new Info("FactoryName", "Refrigerator Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "CoolTech", ""));
                break;

            case "Oven":
                factoryInfo.add(new Info("FactoryName", "Oven Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "BakeBest", ""));
                break;

            case "Fan":
                factoryInfo.add(new Info("FactoryName", "Fan Factory", ""));
                factoryInfo.add(new Info("Manufacturer", "BreezeCo", ""));
                break;
        }
        return factoryInfo;
    }
}
