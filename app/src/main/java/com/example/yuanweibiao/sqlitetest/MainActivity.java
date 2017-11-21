package com.example.yuanweibiao.sqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.yuanweibiao.sqlitetest.db.ImageDbManager;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_url);
    }

    private String getUrl() {
        return editText.getText().toString();
    }

    public void handleInsert(View view) {
        String url = getUrl();
        boolean result = ImageDbManager.getInstance().insertImage(url);
        Log.e("Bill", "result:" + result);
    }

    public void handleSelect(View view) {
        String url = getUrl();
        boolean result = ImageDbManager.getInstance().checkImageExist(url);
        Log.e("Bill", "select result:" + result);
    }
}
