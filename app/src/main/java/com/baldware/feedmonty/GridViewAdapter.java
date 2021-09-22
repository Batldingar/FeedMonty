package com.baldware.feedmonty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int[] imageIDArray;

    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, int[] imageIDArray) {
        this.context = context;
        this.imageIDArray = imageIDArray;
    }

    @Override
    public int getCount() {
        return imageIDArray.length;
    }

    @Override
    public Object getItem(int i) {
        return imageIDArray[i];
    }

    @Override
    public long getItemId(int i) {
        return imageIDArray[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = view.findViewById(R.id.grid_item_image_view);
        imageView.setImageResource(imageIDArray[i]);

        return view;
    }
}
