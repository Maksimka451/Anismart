package com.example.anismart.Model;

public class SearchModel {
    String heading;
    String imageUrl;
    String fileUrl;

    public SearchModel(String heading, String imageUrl) {
        this.heading = heading;
        this.imageUrl = imageUrl;
    }

    public String getHeading() {
        return heading;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
