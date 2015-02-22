package com.example.deekshasharma.pennyapp.model;

import java.util.ArrayList;

public class AllCategoryItems {

    public static ArrayList<CategoryItem> categoryItemList;

    public AllCategoryItems()
    {
        CategoryItem item = new CategoryItem("Groceries", "1", "Food And Drink");
        CategoryItem item1 = new CategoryItem("Restaurants", "2", "Food And Drink");
        CategoryItem item2 = new CategoryItem("Dessert", "3", "Food And Drink");
        CategoryItem item3 = new CategoryItem("Alcohol", "4", "Food And Drink");
        categoryItemList = new ArrayList<>();
        categoryItemList.add(item);
        categoryItemList.add(item1);
        categoryItemList.add(item2);
        categoryItemList.add(item3);
    }

    public ArrayList<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }


    public ArrayList<String> getCategoryNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < categoryItemList.size(); i++)
        {
            CategoryItem item = categoryItemList.get(i);
            names.add(item.getName());
        }
        return names;
    }
}
