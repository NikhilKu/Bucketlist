package com.kumar.nikhil.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

     private FloatingActionButton mSaveFab;
     private EditText mTitleEditView, mDescriptionEditView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_add);
          mSaveFab = findViewById(R.id.saveFab);
          mTitleEditView = findViewById(R.id.titleEditView);
          mDescriptionEditView = findViewById(R.id.descriptionEditView);

          mSaveFab.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    String mTitleText = mTitleEditView.getText().toString();
                    String mDescriptionText = mDescriptionEditView.getText().toString();

                    BucketlistItem bucketlistItem = new BucketlistItem(mTitleText, mDescriptionText);

                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EXTRA_BUCKETLISTITEM, bucketlistItem);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
               }
          });
     }

}
