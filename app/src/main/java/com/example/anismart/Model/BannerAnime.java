package com.example.anismart.Model;

public class BannerAnime {

    Integer id;
    String animeName;
    String imageUrl;
    String fileUrl;


    public BannerAnime(Integer id, String animeName, String imageUrl, String fileUrl) {
        this.id = id;
        this.animeName = animeName;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
