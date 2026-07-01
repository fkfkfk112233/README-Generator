package model;

public class Project {
    private int id;

    private String projectName;

    private String description;

    private String author;

    private String version;

    private String githubUrl;

    private String installation;

    private String usage;

    private String features;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String projectName, String description, String author, String version, String githubUrl,
			String installation, String usage, String features) {
		super();
		this.projectName = projectName;
		this.description = description;
		this.author = author;
		this.version = version;
		this.githubUrl = githubUrl;
		this.installation = installation;
		this.usage = usage;
		this.features = features;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getInstallation() {
		return installation;
	}

	public void setInstallation(String installation) {
		this.installation = installation;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
    
    
}
