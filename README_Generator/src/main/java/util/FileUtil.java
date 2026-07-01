package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    /**
     * 儲存文字到指定檔案
     *
     * @param content  檔案內容
     * @param filePath 完整檔案路徑
     */
    public static void save(String content, String filePath) {

        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(filePath),
                StandardCharsets.UTF_8)) {

            writer.write(content);

        } catch (IOException e) {
            throw new RuntimeException("檔案儲存失敗：" + filePath, e);
        }

    }

    /**
     * 讀取文字檔
     *
     * @param filePath 檔案路徑
     * @return 檔案內容
     */
    public static String read(String filePath) {

        try {

            return Files.readString(
                    Path.of(filePath),
                    StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("檔案讀取失敗：" + filePath, e);
        }

    }

    /**
     * 判斷檔案是否存在
     */
    public static boolean exists(String filePath) {

        return Files.exists(Path.of(filePath));

    }

    /**
     * 建立資料夾
     */
    public static void createDirectory(String directoryPath) {

        try {

            Files.createDirectories(Path.of(directoryPath));

        } catch (IOException e) {
            throw new RuntimeException("建立資料夾失敗：" + directoryPath, e);
        }

    }

}
