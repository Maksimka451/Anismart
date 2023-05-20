package com.example.anismart.Model;

public class AnimeData {

    private String Description, Format, Genre, Released, Source, Status;

    public AnimeData() {

    }

    public AnimeData(String description, String format, String genre, String released, String source, String status) {
        this.Description = description;
        this.Format = format;
        this.Genre = genre;
        this.Released = released;
        this.Source = source;
        this.Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        this.Format = format;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        this.Released = released;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        this.Source = source;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}
