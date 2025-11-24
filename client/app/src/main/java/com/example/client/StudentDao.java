package com.example.client;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM students")
    List<Student> getAll();

    @Query("SELECT * FROM students WHERE account IN (:accounts)")
    List<Student> findByAccount(String[] accounts);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student  student);
}
