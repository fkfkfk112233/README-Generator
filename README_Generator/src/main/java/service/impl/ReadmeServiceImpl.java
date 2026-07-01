package service.impl;

import model.ProjectInfo;
import service.ReadmeService;
import util.TemplateEngine;

public class ReadmeServiceImpl implements ReadmeService {

    @Override
    public String generate(ProjectInfo p, String templateName) {

        String markdown = TemplateEngine.loadTemplate(templateName);

        markdown = markdown.replace("${projectName}", nvl(p.getProjectName()));
        markdown = markdown.replace("${description}", nvl(p.getDescription()));
        markdown = markdown.replace("${author}", nvl(p.getAuthor()));
        markdown = markdown.replace("${version}", nvl(p.getVersion()));
        markdown = markdown.replace("${github}", nvl(p.getGithubUrl()));
        markdown = markdown.replace("${installation}", nvl(p.getInstallation()));
        markdown = markdown.replace("${usage}", nvl(p.getUsage()));
        markdown = markdown.replace("${features}", nvl(p.getFeatures()));

        return markdown;

    }

    private String nvl(String value) {
        return value == null ? "" : value;
    }

}
