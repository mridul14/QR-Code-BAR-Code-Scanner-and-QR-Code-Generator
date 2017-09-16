package com.example.apple.miniproject;

/**
 * Created by apple on 15/07/17.
 */

public class Model {
    private String result;
    private long id;

    public Model(String result, long id) {
        this.result = result;
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
