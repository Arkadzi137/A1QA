package test.Api;

import Utils.ConfigUtils;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;

public class ImageComparisonUtils {
    private final static String IMAGE_EXTENSION = ConfigUtils.getTestConfig("imageExtension");
    private final static String IMAGE_PATH = ConfigUtils.getTestConfig("imagePath");
    private final static String DOWNLOAD_IMAGE_NAME = ConfigUtils.getTestConfig("downloadImageName");
    private final static String UPLOAD_IMAGE_NAME = ConfigUtils.getTestConfig("uploadImageName");

    @SneakyThrows
    public static void savePhoto(String path) {
        URL url = new URL(path);
        ImageIO.write(ImageIO.read(url), IMAGE_EXTENSION, new File(String.format("%s%s", IMAGE_PATH,DOWNLOAD_IMAGE_NAME)));
    }

    public static ImageComparisonResult runComparison() {
        return new ImageComparison(
                ImageComparisonUtil.readImageFromResources(String.format("%s%s",IMAGE_PATH,UPLOAD_IMAGE_NAME)),
                ImageComparisonUtil.readImageFromResources(String.format("%s%s",IMAGE_PATH,DOWNLOAD_IMAGE_NAME)))
                .compareImages();
    }
    @SneakyThrows
    public static void makeScreenshotOfElement(WebElement ele) {
        File screenshot = ele.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(String.format("%s%s",IMAGE_PATH,DOWNLOAD_IMAGE_NAME)));
    }
}
