package th.ac.su.cp.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.cp.speedrecords.model.Record;

@Dao
public interface RecordDao {

    @Query("SELECT * FROM records")
    Record[] getAllRecords();

    @Query("SELECT * FROM records WHERE speed = :speed")
    Record getRecordById(int speed);

    @Insert
    void addRecord(Record... records);

}

