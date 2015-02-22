package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.GroupListAdapter;
import com.example.deekshasharma.pennyapp.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment{

    private String[] groupTitles;
    private TypedArray groupIcons;
    private List<NavDrawerItem> allGroups;
    private ListView groupListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        //added now
        groupListView = (ListView) rootView.findViewById(R.id.group_list_view);
        getAllGroups();
        groupListView.setAdapter(new GroupListAdapter(getActivity(),allGroups));

        return rootView;
    }

    private void getAllGroups()
    {
        groupTitles = getResources().getStringArray(R.array.group_titles);
        groupIcons= getResources().obtainTypedArray(R.array.group_icons);
        allGroups = new ArrayList<>();
        for(int i = 0; i < groupIcons.length();i++)
        {
            NavDrawerItem groupItem = new NavDrawerItem(groupTitles[i],groupIcons.getResourceId(i,-1));
            allGroups.add(groupItem);
        }
    }


}
