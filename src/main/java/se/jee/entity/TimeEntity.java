package se.jee.entity;

import javax.persistence.*;

@Entity
public class TimeEntity {

    @Id
    @GeneratedValue(generator="timeSeq")
    @SequenceGenerator(name="timeSeq",sequenceName="TIME_ID_SEQ")
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TimeEntity{" +
                "id=" + id +
                ", time='" + time + '\'' +
                '}';
    }
}
