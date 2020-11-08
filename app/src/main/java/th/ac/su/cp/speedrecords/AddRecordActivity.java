package th.ac.su.cp.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import th.ac.su.cp.speedrecords.db.AppDatabase;
import th.ac.su.cp.speedrecords.model.Record;
import th.ac.su.cp.speedrecords.util.AppExecutors;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enterDistance = findViewById(R.id.enter_distance_edit_text);
                String textDistanceNumber = enterDistance.getText().toString();

                EditText enterDuration = findViewById(R.id.enter_duration_edit_text);
                String textDurationNumber = enterDuration.getText().toString();

                double distance = Double.parseDouble(textDistanceNumber);
                double duration = Double.parseDouble(textDurationNumber);
                double speedRecord = (distance / duration) * 18 / 5;

                String distanceStr = String.format(
                        Locale.getDefault(), "%.1f", distance
                );

                String durationStr = String.format(
                        Locale.getDefault(), "%.1f", duration
                );

                String speedRecordStr = String.format(
                        Locale.getDefault(), "%.1f", speedRecord
                );

                final Record record = new Record(0, distanceStr, durationStr, speedRecordStr);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { //worker thread
                        AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                        db.recordDao().addRecord(record);
                        finish();
                    }
                });

            }
        });
    }
}