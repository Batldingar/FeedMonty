package com.baldware.feedmonty.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.baldware.feedmonty.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Integer> imageIDList;
    private final ArrayList<Integer> imageValueList;

    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, ArrayList<Integer> imageIDList, ArrayList<Integer> imageValueList) {
        this.context = context;
        this.imageIDList = new ArrayList<>(imageIDList);
        this.imageValueList = new ArrayList<>(imageValueList);
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

    public int getItemValue(int i) {
        return imageValueList.get(i);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, null, false);
        }

        ImageView imageView = view.findViewById(R.id.grid_item_image_view);
        imageView.setImageResource(imageIDList.get(i));

        return view;
    }

    public void removeItemAt(int i) {
        imageIDList.remove(i);
        imageValueList.remove(i);
        notifyDataSetChanged();
    }
}
