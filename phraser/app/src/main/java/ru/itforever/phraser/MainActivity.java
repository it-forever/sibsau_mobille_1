package ru.itforever.phraser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gen.setOnClickListener(onClickPhrase);
    }

    public void onClickPhrase(View view) {
        TextView textView = findViewById(R.id.textView);
        TextView tv_result = findViewById(R.id.tv_result);
        textView.setText("Все, что нам нужно - это "+Phraser.PhraserGen(false));
        tv_result.setText(Phraser.PhraserGen(true)+" - это всё, что нам нужно");
    }

}
