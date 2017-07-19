package com.annasblackhat.roomrxjava.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Mia Khalifa on 19/07/2017.
 */

@Database(entities = {User.class}, version = 1)
public abstract class RoomRxJavaDatabase extends RoomDatabase{
    public abstract UserDao userDao();
}
