package com.example.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class RegisterStudent extends AppCompatActivity {
    private EditText editAccount, editPassword1, editPassword2, editName;
    private Button buttonCommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_student);
        setEdit();
        buttonCommit = findViewById(R.id.reg_st_commit_id);
        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editAccount.getText().toString().equals("")) {
                    Toast.makeText(RegisterStudent.this,"注册信息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    List<Student> studentsAccount = AppDataBase.getInstance(RegisterStudent.this).studentDao().getAll();
                    boolean differ = true;
                    for(int i=0; i< studentsAccount.size(); i++) {
                        if (studentsAccount.get(i).account.equals(editAccount.getText().toString())) {
                            Toast.makeText(RegisterStudent.this, "账号已存在", Toast.LENGTH_SHORT).show();
                            differ = false;
                            break;
                        }
                    }
                    if (editPassword1.getText().toString().equals(editPassword2.getText().toString())) {
                        Student student = new Student(editAccount.getText().toString(), editPassword1.getText().toString(), editName.getText().toString(), "");
                        AppDataBase.getInstance(RegisterStudent.this).studentDao().insertAll(student);
                        Toast.makeText(RegisterStudent.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterStudent.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterStudent.this,"两次密码不相同",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void setEdit() {
        editAccount = findViewById(R.id.reg_st_acc_id);
        editPassword1 = findViewById(R.id.reg_st_pass1_id);
        editPassword2 = findViewById(R.id.reg_st_pass2_id);
        editName = findViewById(R.id.reg_st_name_id);
    }
}