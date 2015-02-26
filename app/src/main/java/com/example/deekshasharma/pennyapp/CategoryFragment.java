package com.example.deekshasharma.pennyapp;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.model.Categories;
import com.example.deekshasharma.pennyapp.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends ListFragment{

    private OnCategorySelectListener listener;



    public interface OnCategorySelectListener {
        public void onCategorySelected(int position);
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Bundle args = getArguments();
        if (args != null) {
            String groupName = args.getString("groupName");

            Categories categoriesSingleton = new Categories(getActivity(), this, groupName);
            setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Categories.allCategories));
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.onCategorySelected(position);
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
