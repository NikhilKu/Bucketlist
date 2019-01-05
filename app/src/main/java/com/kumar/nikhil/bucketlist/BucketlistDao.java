package com.kumar.nikhil.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BucketlistDao {
     @Query("SELECT * FROM bucketListItem")
     public LiveData<List<BucketlistItem>> getAllBucketlistItems();

     @Insert
     public void insertBucketlistItem(BucketlistItem bucketlistItem);

     @Update
     public void updateBucketlistItem(BucketlistItem bucketlistItem);

     @Delete
     public void deleteBucketlistItem(BucketlistItem bucketlistItem);
}