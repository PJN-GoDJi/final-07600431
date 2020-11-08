package th.ac.su.cp.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "records")
public class Record {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "distance")
    public final String distance;

    @ColumnInfo(name = "duration")
    public final String duration;

    @ColumnInfo(name = "speed")
    public final String speed;


    public Record(int id, String distance, String duration, String speed) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
        this.speed = speed;
    }
}

