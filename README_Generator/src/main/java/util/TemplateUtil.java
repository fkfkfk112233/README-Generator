package util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TemplateUtil {

    public static List<String> getTemplates() {

        List<String> templates = new ArrayList<>();

        try {

            URL url =
                    TemplateUtil.class
                            .getClassLoader()
                            .getResource("template");

            if (url == null) {
                return templates;
            }

            File folder = new File(url.toURI());

            File[] files = folder.listFiles();

            if (files == null) {
                return templates;
            }

            for (File file : files) {

                String name = file.getName();

                if (name.endsWith(".md")) {

                    templates.add(
                            name.replace(".md", "")
                    );

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return templates;

    }

}
