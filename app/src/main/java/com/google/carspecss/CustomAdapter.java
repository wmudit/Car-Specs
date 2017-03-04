package com.google.carspecss;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class CustomAdapter extends ArrayAdapter<String> {

    static class ViewHolder {
        TextView textView;
    }

    public CustomAdapter(Context context, List<String> brandlist) {
        super(context, R.layout.custom_adapter ,brandlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_adapter, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.customAdapterTextView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        String singleBrand = getItem(position);
        //TextView customAdapterTextView = (TextView) view.findViewById(R.id.customAdapterTextView);
        holder.textView.setText(singleBrand);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Overpass-SemiBold.ttf");
        holder.textView.setTypeface(typeface);
        return convertView;

    }
}
