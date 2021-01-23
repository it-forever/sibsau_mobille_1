package ru.itforever.catgson;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CAT murzik = new CAT();
        murzik.name = "Мурзик";
        murzik.age = 9;
        murzik.color = Color.BLACK;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Log.i("GSON", gson.toJson(murzik));
        String result = gson.toJson(murzik);

        String jsonText = "{'name':'Барсик','color':-26368,'age':8}";
        builder = new GsonBuilder();
        gson = builder.create();
        murzik = gson.fromJson(jsonText, CAT.class);
        Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);
        result+="\nИмя: " + murzik.name + "\nВозраст: " + murzik.age + "\nЦвет:";
        TextView textView = findViewById(R.id.text_view);
        textView.setText(result);

        View view = findViewById(R.id.view_color);
        view.setBackgroundColor(murzik.color);


    }
}