package com.example.deekshasharma.pennyapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.model.CategoryItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment{

    private ListView categoryListView;
    private ArrayList<String> allCategoryNames;
    private OnCategorySelectListener listener;



    // added for testing now, this interface should be implemented by an activity that will call
    public interface OnCategorySelectListener {
        public void onCategorySelected(int position);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        Bundle args = getArguments();
        if(args != null)
        {
            allCategoryNames = args.getStringArrayList("categories");
        }
        categoryListView = (ListView) rootView.findViewById(R.id.category_list_view);
        categoryListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, allCategoryNames));
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             listener.onCategorySelected(position);

            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        // to make sure if the Main Activity has implemented the interface
        try
        {
            listener = (OnCategorySelectListener) activity;
        }catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + "must implement OnCategorySelectListener interface");
        }
    }

}
