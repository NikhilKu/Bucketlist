package com.kumar.nikhil.bucketlist;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "bucketListItem")
public class BucketlistItem implements Parcelable {

     @PrimaryKey(autoGenerate = true)
     protected Long id;

     @ColumnInfo(name = "titleText")
     private String mTitleText;

     @ColumnInfo(name = "descriptionText")
     private String mDescriptionText;

     @ColumnInfo(name = "checked")
     private boolean mChecked;


     public BucketlistItem(String mTitleText, String mDescriptionText) {
          this.mTitleText = mTitleText;
          this.mDescriptionText = mDescriptionText;
     }

     protected BucketlistItem(Parcel in) {
          this.id = (Long) in.readValue(Long.class.getClassLoader());
          this.mTitleText = in.readString();
          this.mDescriptionText = in.readString();
          this.mChecked = in.readByte() != 0;
     }

     public static final Creator<BucketlistItem> CREATOR = new Creator<BucketlistItem>() {
          @Override
          public BucketlistItem createFromParcel(Parcel in) {
               return new BucketlistItem(in);
          }

          @Override
          public BucketlistItem[] newArray(int size) {
               return new BucketlistItem[size];
          }
     };

     public Long getId() { return id; }

     public void setId(Long id) { this.id = id; }

     public String getTitleText() {
          return mTitleText;
     }

     public void setTitleText(String mTitleText) {
          this.mTitleText = mTitleText;
     }

     public String getDescriptionText() {
          return mDescriptionText;
     }

     public void setDescriptionText(String mDescriptionText) {
          this.mDescriptionText = mDescriptionText;
     }

     public boolean isChecked() {
          return mChecked;
     }

     public void setChecked(boolean mChecked) {
          this.mChecked = mChecked;
     }

     public void toggleCheckBox(boolean mChecked, BucketlistItemAdapter.ViewHolder holder) {
          if (mChecked){
               holder.titleTextView.setPaintFlags(holder.titleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
               holder.descriptionTextView.setPaintFlags(holder.descriptionTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
          }
          else {
               holder.titleTextView.setPaintFlags(holder.titleTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
               holder.descriptionTextView.setPaintFlags(holder.descriptionTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
          }

          holder.checkBoxView.setChecked(mChecked);
          setChecked(mChecked);
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel parcel, int i) {
          parcel.writeValue(this.id);
          parcel.writeString(mTitleText);
          parcel.writeString(mDescriptionText);
          parcel.writeByte((byte) (mChecked ? 1 : 0));
     }
}