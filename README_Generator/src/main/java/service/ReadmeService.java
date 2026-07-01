package service;

import model.ProjectInfo;

public interface ReadmeService {

    /**
     * 依照 Template 產生 README
     */
    String generate(ProjectInfo project, String templateName);

}