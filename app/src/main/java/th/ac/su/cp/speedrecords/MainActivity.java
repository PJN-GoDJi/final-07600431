package th.ac.su.cp.speedrecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.cp.speedrecords.adapter.RecordAdapter;
import th.ac.su.cp.speedrecords.db.AppDatabase;
import th.ac.su.cp.speedrecords.model.Record;
import th.ac.su.cp.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;

    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Record[] records = db.recordDao().getAllRecords();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        RecordAdapter adapter = new RecordAdapter(MainActivity.this, records);
                        mRecyclerView.setAdapter(adapter);

                        int overLimit = 0;
                        for (Record s : records) {
                            double speed = Double.parseDouble(s.speed);
                            if (speed >= 80) {
                                overLimit++;
                            }
                        }

                        TextView over_Limit = findViewById(R.id.over_limit_text_view);
                        over_Limit.setText("OVER LIMIT : " + overLimit);

                        TextView total = findViewById(R.id.total_text_view);
                        total.setText("TOTAL : " + records.length);
                    }
                });
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.speed_records_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addRecordButton = findViewById(R.id.add_record_button);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });
    }
}