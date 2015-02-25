package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.GroupListAdapter;
import com.example.deekshasharma.pennyapp.model.IconWithTitleItem;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment{

    private String[] groupTitles;
    private TypedArray groupIcons;
    private List<IconWithTitleItem> groupList;
    private ListView groupListView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        groupListView = (ListView) rootView.findViewById(R.id.group_list_view);
        getAllGroups();
        groupListView.setAdapter(new GroupListAdapter(getActivity(), groupList));
        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Fragment categoryFragment = new CategoryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                if(position == 0)
                {
                    args.putString("groupName","Food & Drink");
                }
                else if(position == 6)
                {
                    args.putString("groupName","Sports & Fitness");
                }
                else if(position == 7)
                {
                    args.putString("groupName", "Gifts & Donations");
                }
                else if(position == 8)
                {
                    args.putString("groupName", "Health & Medical");
                }
                else
                {
                    args.putString("groupName", groupTitles[position]);
                }

                categoryFragment.setArguments(args);
                transaction.replace(R.id.frame_container,categoryFragment).commit();
                setTitle("Select Category");
            }
        });
        return rootView;
    }

    /*
    Sets the title on the action bar
     */
    public void setTitle(CharSequence title) {
        android.support.v7.app.ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(title);
    }

    /*
    Add all IconWithTitleItem objects into groupList
     */
    private void getAllGroups()
    {
        groupTitles = getResources().getStringArray(R.array.group_titles);
        groupIcons= getResources().obtainTypedArray(R.array.group_icons);
        groupList = new ArrayList<>();
        for(int i = 0; i < groupIcons.length();i++)
        {
            IconWithTitleItem groupItem = new IconWithTitleItem(groupTitles[i],groupIcons.getResourceId(i,-1));
            groupList.add(groupItem);
        }
    }


}
