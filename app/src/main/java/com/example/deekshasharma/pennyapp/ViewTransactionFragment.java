package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;
import com.example.deekshasharma.pennyapp.model.TransactionItem;

import java.util.ArrayList;
import java.util.List;

public class ViewTransactionFragment extends Fragment{

    // added for testing
    TransactionItem transactionItem = new TransactionItem(R.drawable.two,"Costco","$45", R.drawable.food);
    private ListView listView;

    public ViewTransactionFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view_transaction, container, false);

        //added for testing
        List<TransactionItem> list = new ArrayList<>();
        list.add(transactionItem);
        listView = (ListView) rootView.findViewById(R.id.transaction_list_view);
        listView.setAdapter(new ViewTransactionListAdapter(getActivity(),list));



        return rootView;
    }
}
