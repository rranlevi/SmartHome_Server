package Classes;

public class DeviceChannel {
    private String channelName;
    private String channelPath;

    public DeviceChannel(String channelName, String channelPath) {
        this.channelName = channelName;
        this.channelPath = channelPath;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }
}