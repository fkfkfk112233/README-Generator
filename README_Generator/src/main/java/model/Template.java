package model;

public class Template {

    private int id;
    private String templateName;
    private String content;

    public Template() {
    }

    public Template(int id, String templateName, String content) {
        this.id = id;
        this.templateName = templateName;
        this.content = content;
    }

    public Template(String templateName, String content) {
        this.templateName = templateName;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}