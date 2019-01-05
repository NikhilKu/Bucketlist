package com.kumar.nikhil.bucketlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BucketlistItemAdapter extends RecyclerView.Adapter<BucketlistItemAdapter.ViewHolder> {


     private List<BucketlistItem> mBucketlistItems;
     private Context currentContext;

     public BucketlistItemAdapter(List<BucketlistItem> mBucketlistItems) {
          this.mBucketlistItems = mBucketlistItems;
     }

     public class ViewHolder extends RecyclerView.ViewHolder {
          public TextView titleTextView, descriptionTextView;
          public CheckBox checkBoxView;
          public View view;

          public ViewHolder(View itemView) {
               super(itemView);
               titleTextView = itemView.findViewById(R.id.titleTextView);
               descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
               checkBoxView = itemView.findViewById(R.id.checkBoxView);
               view = itemView;
          }
     }

     @Override
     public BucketlistItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          Context context = parent.getContext();
          currentContext = context;
          LayoutInflater inflater = LayoutInflater.from(context);
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_layout, parent, false);
          // Return a new holder instance
          BucketlistItemAdapter.ViewHolder viewHolder = new BucketlistItemAdapter.ViewHolder(view);
          return viewHolder;
     }

     @Override
     public void onBindViewHolder(final BucketlistItemAdapter.ViewHolder holder, final int position) {
          final BucketlistItem bucketlistItem = mBucketlistItems.get(position);
          holder.titleTextView.setText(bucketlistItem.getTitleText());
          holder.descriptionTextView.setText(bucketlistItem.getDescriptionText());
          bucketlistItem.toggleCheckBox(bucketlistItem.isChecked(), holder);
          holder.checkBoxView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    bucketlistItem.toggleCheckBox(holder.checkBoxView.isChecked(), holder);
                    MainActivity mainActivity = (MainActivity) currentContext;
                    mainActivity.updateBucketlistItem(position);
               }
          });
     }

     public void swapList (List<BucketlistItem> newList) {
          mBucketlistItems = newList;
          if (newList != null) {
               // Force the RecyclerView to refresh
               this.notifyDataSetChanged();
          }
     }

     @Override
     public int getItemCount() {
          return mBucketlistItems.size();
     }

}
