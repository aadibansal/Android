package com.example.aditya.third_party_library;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.support.v7.graphics.Palette;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Aditya on 2/10/2017.
 */

public class ImageDetailActivity extends AppCompatActivity {

    private static final String ARG_SELECTED_IMAGE_URL = "image_url";
    private Toolbar mToolbar;

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(ARG_SELECTED_IMAGE_URL, url);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        String url = getIntent().getStringExtra(ARG_SELECTED_IMAGE_URL);

        if (!TextUtils.isEmpty(url)) {
            loadImage(url);
        }
    }

    private void loadImage(String url) {
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(this).load(url).fit().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                onLoadImageSuccess(imageView.getDrawable());
            }

            @Override
            public void onError() {

            }
        });
    }

    private void onLoadImageSuccess(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    if (palette.getLightMutedSwatch() != null) {
                        int rgb = palette.getLightMutedSwatch().getRgb();
                        mToolbar.setBackgroundColor(rgb);
                    }
                }
            });
        }
    }
}
