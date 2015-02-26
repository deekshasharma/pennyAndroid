package com.example.deekshasharma.pennyapp;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;
//import com.example.deekshasharma.pennyapp.model.Transaction;
import com.example.deekshasharma.pennyapp.model.Categories;
import com.example.deekshasharma.pennyapp.model.TransactionItem;
import com.example.deekshasharma.pennyapp.model.TransactionsEndPoint;

import java.util.List;

public class ViewTransactionFragment extends ListFragment{

//    private ListView listView;

    public ViewTransactionFragment(){}

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_view_transaction, container, false);
//
//        //added for testing
//        List<TransactionItem> list = new ArrayList<>();
//        list.add(transactionItem);
//        listView = (ListView) rootView.findViewById(R.id.transaction_list_view);
//        listView.setAdapter(new ViewTransactionListAdapter(getActivity(),list));
//
//        return rootView;
//    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        TransactionsEndPoint transactionsEndPoint = new TransactionsEndPoint(getActivity(), this);
        List<TransactionItem> list = transactionsEndPoint.getTransactionList();
        setListAdapter(new ViewTransactionListAdapter(getActivity(),list));

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_view_transaction, container, false);
//
//
//
//        return rootView;
//    }
}
