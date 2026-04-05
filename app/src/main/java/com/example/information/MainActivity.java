package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tVResult, tVResultLabel;
    EditText editTextId;

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();

                            tVResultLabel.setText("전송\n정보\n출력");
                            String str = "아이디: " + data.getStringExtra("id");
                            str = str + "\n이름: " + data.getStringExtra("name");
                            str = str + "\n나이: " + data.getStringExtra("age");
                            str = str + "\n성별: " + data.getStringExtra("gender");
                            str = str + "\n자격증: " + data.getStringExtra("license");
                            tVResult.setText(str);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReq = findViewById(R.id.btnRequest);
        Button btnEnd = findViewById(R.id.btnEnd);
        editTextId = findViewById(R.id.editTextID);
        tVResultLabel = findViewById(R.id.tVResultLabel);
        tVResult = findViewById(R.id.tVResult);

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextId.getText().toString();

                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                intent.putExtra("id", id);
                resultLauncher.launch(intent);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //comit1 -- 기본 기능 구현
    //comit2 -- 수정
}

