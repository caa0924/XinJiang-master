package com.mhimine.jdk.operations_managementApp.FileManage;

public class FileInfo {


    public FileInfo(String fileName, String fileFullPath) {
        this.fileName = fileName;
        this.fileFullPath = fileFullPath;
    }

    private String fileName;
    private String fileFullPath;

    public String getFileName() {
        return fileName;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }
}
