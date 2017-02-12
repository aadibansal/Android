package com.example.aditya.third_party_library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String[] IMAGE_URLS = {
            "https://image.tmdb.org/t/p/w780/xfWac8MTYDxujaxgPVcRD9yZaul.jpg",
            "https://image.tmdb.org/t/p/w780/4Iu5f2nv7huqvuYkmZvSPOtbFjs.jpg",
            "https://image.tmdb.org/t/p/w780/wFFlaVHmQG4U43m0l3eQqHZluvn.jpg",
            "https://image.tmdb.org/t/p/w780/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg",
            "https://image.tmdb.org/t/p/w780/6I2tPx6KIiBB4TWFiWwNUzrbxUn.jpg",
            "https://image.tmdb.org/t/p/w780/zBK4QZONMQXhcgaJv1YYTdCW7q9.jpg",
            "https://image.tmdb.org/t/p/w780/oFOG2yIRcluKfTtYbzz71Vj9bgz.jpg",
            "https://image.tmdb.org/t/p/w780/anmLLbDx9d98NMZRyVUtxwJR6ab.jpg",
            "https://image.tmdb.org/t/p/w780/i9flZtw3BwukADQpu5PlrkwPYSY.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ItemAdapter(IMAGE_URLS));
        setContentView(recyclerView);
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

        private final String[] mImageUrls;

        ItemAdapter(String[] imageUrls) {
            mImageUrls = imageUrls;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, int position) {
            Picasso.with(holder.itemView.getContext())
                    .load(mImageUrls[position])
                    .fit()
                    .into(holder.mImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(ImageDetailActivity.getIntent(MainActivity.this,
                            mImageUrls[holder.getAdapterPosition()]));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mImageUrls.length;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;

            ItemViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }
}
