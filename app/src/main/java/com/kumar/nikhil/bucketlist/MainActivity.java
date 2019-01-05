package com.kumar.nikhil.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private List<BucketlistItem> mBucketlistItems = new ArrayList<>();
     private RecyclerView mRecyclerView;
     private BucketlistItemAdapter mAdapter;

     public static final String EXTRA_BUCKETLISTITEM = "BucketlistItem";
     public static final int ADDREQUEST = 1234;
     private MainViewModel mMainViewModel;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);



          mRecyclerView = findViewById(R.id.recyclerView);
          mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

          // Set adapter on recyclerview
          mAdapter = new BucketlistItemAdapter(mBucketlistItems);
          mRecyclerView.setAdapter(mAdapter);

          mMainViewModel = new MainViewModel(getApplicationContext());
          mMainViewModel.getmBucketlistItems().observe(this, new Observer<List<BucketlistItem>>() {
               @Override
               public void onChanged(@Nullable List<BucketlistItem> bucketlistItems) {
                    mBucketlistItems = bucketlistItems;
                    updateUI();
               }
          });

          FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addFab);
          fab.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                    startActivityForResult(intent, ADDREQUEST);
               }
          });

          final ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                  new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                       @Override
                       public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                               target) {
                            return false;
                       }

                       //Called when a user swipes left or right on a ViewHolder
                       @Override
                       public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                            //Get the index corresponding to the selected position
                            int position = (viewHolder.getAdapterPosition());

                            //A bucket list item can be removed from the list by swiping
                            mMainViewModel.delete(mBucketlistItems.get(position));
                       }
                  };

          ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
          itemTouchHelper.attachToRecyclerView(mRecyclerView);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          if (resultCode == RESULT_OK) {
               BucketlistItem bucketlistItem = data.getParcelableExtra(MainActivity.EXTRA_BUCKETLISTITEM);
               if (requestCode == ADDREQUEST) {
                    mMainViewModel.insert(bucketlistItem);
               }
          }
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_main, menu);
          return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          // Handle action bar item clicks here. The action bar will
          // automatically handle clicks on the Home/Up button, so long
          // as you specify a parent activity in AndroidManifest.xml.
          int id = item.getItemId();

          //noinspection SimplifiableIfStatement
          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }

     private void updateUI() {
          if (mAdapter == null) {
               mAdapter = new BucketlistItemAdapter(mBucketlistItems);
               mRecyclerView.setAdapter(mAdapter);
          } else {
               mAdapter.swapList(mBucketlistItems);
          }
     }

     //Created separate method for updating so that it can be called from the adapter, where the onClick method is located
     public void updateBucketlistItem(int position){
          mMainViewModel.update(mBucketlistItems.get(position));
     }
}