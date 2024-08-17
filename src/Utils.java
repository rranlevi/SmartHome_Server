import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Utils {
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

    public static String processFrameFromVideo(int frameNum, String videoPath) {
        File videoFile = new File(videoPath);

        if (!videoFile.exists()) {
            System.out.println("Video file not found: " + videoPath);
            return "";
        }

        // Updated estimated byte offset per frame (based on 20-second duration)
        long byteOffsetPerFrame = 5709; // As calculated

        long positionInFile = frameNum * byteOffsetPerFrame;

        try {
            long fileSize = Files.size(Paths.get(videoPath));
            System.out.println("Video file size: " + fileSize + " bytes");

            if (positionInFile < fileSize) {
                System.out.println("Simulated processing at byte position: " + positionInFile);

                // Read the frame data (simulated)
                try (FileInputStream videoInFile = new FileInputStream(videoFile)) {
                    videoInFile.skip(positionInFile);
                    byte[] frameData = new byte[(int) byteOffsetPerFrame];
                    int bytesRead = videoInFile.read(frameData, 0, (int) byteOffsetPerFrame);

                    if (bytesRead > 0) {
                        // Convert the frame data to a Base64 string
                        return Base64.getEncoder().encodeToString(frameData);
                    } else {
                        System.out.println("Could not read frame data.");
                        return "";
                    }
                }
            } else {
                System.out.println("Frame number exceeds video length.");
                return "";
            }

        } catch (IOException e) {
            System.out.println("Error reading the video file: " + e.getMessage());
            return "";
        }
    }
}
