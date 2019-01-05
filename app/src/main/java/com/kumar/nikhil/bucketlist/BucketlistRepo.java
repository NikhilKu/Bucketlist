package com.kumar.nikhil.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class BucketlistRepo {
     private AppDatabase mAppDatabase;
     private BucketlistDao mBucketlistDao;
     private LiveData<List<BucketlistItem>> mBucketlistItems;
     private Executor mExecutor = Executors.newSingleThreadExecutor();

     public BucketlistRepo(Context context) {
          mAppDatabase = AppDatabase.getInstance(context);
          mBucketlistDao = mAppDatabase.bucketlistDao();
          mBucketlistItems = mBucketlistDao.getAllBucketlistItems();
     }

     public LiveData<List<BucketlistItem>> getAllReminders() {
          return mBucketlistItems;
     }

     public void insert(final BucketlistItem bucketlistItem) {
          mExecutor.execute(new Runnable() {
               @Override
               public void run() {
                    mBucketlistDao.insertBucketlistItem(bucketlistItem);
               }
          });
     }

     public void update(final BucketlistItem bucketlistItem) {
          mExecutor.execute(new Runnable() {
               @Override
               public void run() {
                    mBucketlistDao.updateBucketlistItem(bucketlistItem);
               }
          });
     }

     public void delete(final BucketlistItem bucketlistItem) {
          mExecutor.execute(new Runnable() {
               @Override
               public void run() {
                    mBucketlistDao.deleteBucketlistItem(bucketlistItem);
               }
          });
     }
}
