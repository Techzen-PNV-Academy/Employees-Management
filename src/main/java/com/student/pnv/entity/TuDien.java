package com.student.pnv.entity;

public class TuDien {
    private int id;
    private String word;
    public TuDien() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TuDien(int id, String word) {
        this.id = id;
        this.word = word;
    }
}
