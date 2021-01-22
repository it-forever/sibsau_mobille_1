package ru.itforever.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String message = intent.getStringExtra(RViewAdapter.ARG_ITEM_DETAILS);
        TextView details = findViewById(R.id.details);
        details.setText(message);
    }
}
