import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Utils {
    public static final String videoPath = "stream/ezgif-frame-";

    // Method to encode image to Base64
    public static String encodeImageToBase64(String imagePath) {
        imagePath = "Devices_Images/" + imagePath;
        File file = new File(imagePath);
        if (!file.exists()) {
            System.out.println("File not found: " + imagePath);
            return null;
        }
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            int bytesRead = 0;
            int offset = 0;
            while (offset < imageData.length && (bytesRead = imageInFile.read(imageData, offset, imageData.length - offset)) >= 0) {
                offset += bytesRead;
            }
            if (offset < imageData.length) {
                System.out.println("Could not completely read file: " + imagePath);
                return null;
            }
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            System.out.println("Error encoding file: " + e.getMessage());
            return null;
        }
    }

    public static String processFrameFromVideo(int frameNum) {
        String videoPath = Utils.videoPath;
        int realFrameNum = frameNum % 200;
        String formattedNumber = String.format("%03d", realFrameNum);
        return Utils.encodeImageToBase64(videoPath + formattedNumber + ".jpg");
    }
}
