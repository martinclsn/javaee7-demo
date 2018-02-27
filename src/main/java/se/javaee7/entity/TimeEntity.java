package se.javaee7.entity;

import javax.persistence.*;

@Entity
public class TimeEntity {

    @Id
    @GeneratedValue(generator="timeSeq")
    @SequenceGenerator(name="timeSeq",sequenceName="TIME_ID_SEQ")
    private String id;

    String time;

    public TimeEntity() {
    }

    public TimeEntity(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
