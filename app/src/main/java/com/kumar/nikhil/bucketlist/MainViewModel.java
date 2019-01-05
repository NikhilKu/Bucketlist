package com.kumar.nikhil.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

class MainViewModel {
     private BucketlistRepo mRepository;
     private LiveData<List<BucketlistItem>> mBucketlistItems;

     public MainViewModel(Context context) {
          mRepository = new BucketlistRepo(context);
          mBucketlistItems = mRepository.getAllReminders();
     }

     public LiveData<List<BucketlistItem>> getmBucketlistItems() {
          return mBucketlistItems;
     }

     public void insert(BucketlistItem bucketlistItem) {
          mRepository.insert(bucketlistItem);
     }

     public void update(BucketlistItem bucketlistItem) {
          mRepository.update(bucketlistItem);
     }

     public void delete(BucketlistItem bucketlistItem) {
          mRepository.delete(bucketlistItem);
     }
}
