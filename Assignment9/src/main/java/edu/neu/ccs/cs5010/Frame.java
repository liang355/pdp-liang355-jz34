package edu.neu.ccs.cs5010;

public class Frame {
    private String tag;
    private String message;

    public Frame() {
        this("", "");
    }

    public Frame(String tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return tag + ":" + message;
    }
}
