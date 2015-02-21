package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewTransactionFragment extends Fragment{

    public ViewTransactionFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view_transaction, container, false);

        return rootView;
    }
}
