package com.example.client;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String account;
    public String name;
    public String password;
    public String studentId;

    public Student(String account, String password, String name, String studentId) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
    }

    // Room 仍然需要一个无参构造函数
    public Student() {}
}


