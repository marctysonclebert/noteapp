package ht.codewithmarc.noteapp.models;

import java.time.LocalDate;

public class Note {
    private Long id;
    private String title;
    private String contents;
    private LocalDate date;

    public Note() {
    }

    public Note(Long id, String title, String contents, LocalDate date) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date=" + date +
                '}';
    }
}
