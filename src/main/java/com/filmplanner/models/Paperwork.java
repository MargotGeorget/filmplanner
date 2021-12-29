package com.filmplanner.models;

public class Paperwork {

    private Long id;
    private String fileName;
    private String directoryPath;
    private String description;
    private Project project;

    public Paperwork(Long id, String fileName, String directoryPath, String description, Project project) {
        this.id = id;
        this.fileName = fileName;
        this.directoryPath = directoryPath;
        this.description = description;
        this.project = project;
    }

    public Paperwork(Long id, Paperwork paperwork) {
        this(id, paperwork.getFileName(), paperwork.getDirectoryPath(), paperwork.getDescription(), paperwork.getProject());
    }

    public Paperwork(String fileName, String directoryPath, String description, Project project) {
        this(null, fileName, directoryPath, description, project);
    }


    /*
    Getters
     */

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }


    /*
    Setters
     */

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    Methods
     */

    public void exportTo(String path) {
        // TODO
    }

    @Override
    public String toString() {
        return this.directoryPath + "/" + this.fileName +
                "\n\t- " + this.description +
                "\n\t(in project " + this.project.getId() + ")";
    }
}
