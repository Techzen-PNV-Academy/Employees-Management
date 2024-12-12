package com.student.pnv.model;

public class Dictionary {
    private int id;
    private String word;

    public Dictionary(){}

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dictionary(int id, String word) {
        this.id = id;
        this.word = word;
    }
}
