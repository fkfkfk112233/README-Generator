package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TemplateEngine {

    public static String loadTemplate(String templateName) {

        String fileName = "template/" + templateName + ".md";

        try (InputStream is = TemplateEngine.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException("Template 不存在：" + templateName);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}