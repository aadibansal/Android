package com.example.aditya.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ColorListFragment.OnColorChosenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Color> colors = new ArrayList<>();
        colors.add(new Color("Black", "#000000"));
        colors.add(new Color("Red", "#ff0000"));
        colors.add(new Color("Green", "#00ff00"));
        colors.add(new Color("Blue", "#0000ff"));
        colors.add(new Color("Grey", "#C0C0C0"));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.colorListContainer, ColorListFragment.newInstance(colors))
                .commit();
    }

    @Override
    public void onColorChosen(Color color) {
        if (color != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.colorDetailContainer, ColorDetailFragment.newInstance(color.code))
                    .commit();
        }
    }
}

class Color implements Parcelable {

    public static final Parcelable.Creator<Color> creator = new Parcelable.Creator<Color>(){
        @Override
        public Color createFromParcel(Parcel source) {
            return new Color(source);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };
    public final String name;
    final String code;

    Color(String name, String code) {
        this.name = name;
        this.code = code;
    }

    private Color(Parcel in) {
        this.name = in.readString();
        this.code = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.code);
    }
}
