package com.annasblackhat.roomrxjava.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Mia Khalifa on 19/07/2017.
 */

@Dao
public interface UserDao {

    //becaouse we're using Rxjava, then we use flowable here
    @Query("SELECT * FROM User LIMIT 1")
    Flowable<User> getUser();

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);


}
