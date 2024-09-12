package week1.day3.examples.example2.entities;

import java.io.Serializable;

public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private String value;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public Entry(String key, String value, int ttl) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Key: " + key + " Value: " + value;
    }
}
