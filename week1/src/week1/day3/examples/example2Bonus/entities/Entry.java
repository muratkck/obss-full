package week1.day3.examples.example2Bonus.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private String value;
    private int ttl = -1;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
        this.startDate = LocalDateTime.now();
    }
    public Entry(String key, String value, int ttl) {
        this.key = key;
        this.value = value;
        this.startDate = LocalDateTime.now();
        this.ttl = ttl;
        this.endDate = LocalDateTime.now().plusSeconds(ttl);
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
    public boolean isExpired() {
        return !(ttl == -1 || endDate.isAfter(LocalDateTime.now()));
    }

    @Override
    public String toString() {
        return "Key: " + key + " Value: " + value;
    }
}
