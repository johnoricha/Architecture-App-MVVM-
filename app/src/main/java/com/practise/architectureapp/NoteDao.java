package com.practise.architectureapp;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface NoteDao {

    // The Integer type parameter tells Room to use a
    // PositionalDataSource object.
    @Query("SELECT * FROM note_table ORDER BY title DESC")
    DataSource.Factory<Integer, Note> noteByTitle();

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

//    @Query("SELECT * FROM note_table ORDER BY priority DESC")
//    LiveData<List<Note>> getAllNotes();

    @RawQuery(observedEntities = Note.class)
    LiveData<List<Note>> getAllNotes(SupportSQLiteQuery query);
}

