package com.example.deekshasharma.pennyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.deekshasharma.pennyapp.R;
import com.example.deekshasharma.pennyapp.model.NavDrawerItem;
import java.util.List;

public class GroupListAdapter extends BaseAdapter{

    private Context context;
    private List<NavDrawerItem> navDrawerItems;

    public GroupListAdapter(Context context, List<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.group_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.group_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.group_title);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        return convertView;
    }


    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
