package com.oyokungroup.mypicjournal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPicDetailActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvDescription;
    TextView tvBlogText;
    private ImageView imageDeal;
    ArrayList<MyJournalPic> deals;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    MyJournalPic deal;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picdetail);
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvBlogText = (TextView)findViewById(R.id.tvTextBlog);
        imageDeal = (ImageView)findViewById(R.id.image);
        Intent intent = getIntent();
        MyJournalPic deal = (MyJournalPic) intent.getSerializableExtra("Deal");
        this.deal = deal;
        tvTitle.setText(deal.getTitle());
        //txtPrice.setText(deal.getPrice());
        tvBlogText.setText(deal.getDescription());
        showImage(deal.getImageUrl());
    }

    private void showImage(String url) {
        if (url != null && url.isEmpty()==false) {
            Picasso.with(imageDeal.getContext())
                    .load(url)
                    .resize(160, 160)
                    .centerCrop()
                    .into(imageDeal);
        }
    }
}