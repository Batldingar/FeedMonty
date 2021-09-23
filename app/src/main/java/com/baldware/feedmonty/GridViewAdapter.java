package com.baldware.feedmonty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> imageIDList;

    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, ArrayList<Integer> imageIDList) {
        this.context = context;
        this.imageIDList = imageIDList;
    }

    @Override
    public int getCount() {
        return imageIDList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageIDList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return imageIDList.get(i);
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
        imageView.setImageResource(imageIDList.get(i));

        return view;
    }

    public void removeItemAt(int i) {
        imageIDList.remove(i);
        notifyDataSetChanged();
    }
}
