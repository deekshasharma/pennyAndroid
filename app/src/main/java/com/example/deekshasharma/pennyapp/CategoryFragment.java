package com.example.deekshasharma.pennyapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.model.CategoriesSingleton;
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

            CategoriesSingleton categoriesSingleton = new CategoriesSingleton(getActivity(), this, groupName);
            setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, categoriesSingleton.getListOfCategories()));

        }
    }

    /*
    Get the names of all categories from the List<CategoryItem>
     */
    private List<String> getAllCategoryNames(List<CategoryItem> categoryItems)
    {
        List<String> allCategoryNames = new ArrayList<>();
        for(CategoryItem item: categoryItems)
        {
            allCategoryNames.add(item.getName());
        }
        return allCategoryNames;
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
