package com.kumar.nikhil.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = BucketlistItem.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

     public abstract BucketlistDao bucketlistDao();
     private final static String NAME_DATABASE = "bucketlist";
     private static AppDatabase mInstance;

     public static AppDatabase getInstance(Context context) {

          if(mInstance == null) {
               mInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
          }
          return mInstance;
     }
}