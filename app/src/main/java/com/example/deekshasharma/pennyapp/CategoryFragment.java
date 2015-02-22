package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CategoryFragment extends Fragment{

    private ListView categoryListView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        // added now for testing
        String[] items = {"Groceries","Restaurant","Coffee","Bar","Dessert"};
        categoryListView = (ListView) rootView.findViewById(R.id.category_list_view);
        categoryListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items));

        return rootView;
    }


}
