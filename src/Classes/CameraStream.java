package Classes;

public class CameraStream extends Widget {
    private String currentFrame;
    private String currentFrameNum;

    public CameraStream(String text, String currentFrame) {
        super(text);
        this.currentFrame = currentFrame;
    }

    public String getCurrentFrame() {
        return this.currentFrame;
    }

    public void setCurrentFrame(String currentFrame) {
        this.currentFrame = currentFrame;
    }

    public String getCurrentFrameNum() {
        return this.currentFrameNum;
    }

    public void setCurrentFrameNum(String currentFrameNum) {
        this.currentFrameNum = currentFrameNum;
    }
}
