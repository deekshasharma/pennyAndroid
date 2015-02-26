package com.example.deekshasharma.pennyapp;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class AddTransactionFragment extends Fragment {

    private TextView date;
    private Calendar myCalender;
    private DatePickerDialog datePickerDialog;
    private String[] allMonths = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private TextView categoryName;
    private EditText transactionName;
    private Button addTransactionButton;
    private EditText amount;
    private String selectedCategoryId;
    private String selectedCategoryGroup;


    public AddTransactionFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);
        onDateClickListener(rootView);
        setDate();
//        onCategoryClickListener(rootView);

        /////
        Bundle args = getArguments();
        categoryName = (TextView) rootView.findViewById(R.id.category);
        categoryName.setText(args.getString("selectedCategoryName"));
        selectedCategoryId = args.getString("selectedCategoryId");
        selectedCategoryGroup = args.getString("selectedCategoryGroupName");
        onAddTransactionClickListener(rootView);
        return rootView;
    }

    /*
    Listens to the date EditText
     */
    public void onDateClickListener(View view) {
        date = (TextView) view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }


    /*
    Allows user to select the date in a date picker and set the value in date field.
     */
    private void setDate() {
        myCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);
                        String month = allMonths[selectedDate.get(Calendar.MONTH)];
                        date.setText(new StringBuilder()
                                .append(selectedDate.get(Calendar.DAY_OF_MONTH)).append("-")
                                .append(month).append("-")
                                .append(selectedDate.get(Calendar.YEAR)));
                    }
                }
                , myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH));
    }


    /*
    Listens to the categoryName TextView
*/
    private void onCategoryClickListener(View view)
    {
        categoryName = (TextView) view.findViewById(R.id.category);
        categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment groupFragment = new GroupFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, groupFragment).commit();
                setTitle("Select Group");
//                setTitle(R.string.group_fragment_title);
            }
        });
    }

    /*
    Sets the title on the action bar
     */
    public void setTitle(CharSequence title) {
        android.support.v7.app.ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(title);
    }


    private void onAddTransactionClickListener(final View view) {
        addTransactionButton = (Button) view.findViewById(R.id.add_transaction_button);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTransaction();
                Toast.makeText(getActivity(),"Transaction Added", Toast.LENGTH_SHORT).show();

                //Go to View Transaction Fragment
                Fragment viewTransactionFragment = new ViewTransactionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,viewTransactionFragment).commit();
            }
        });
    }



    private void postJSON(final View view) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://api-pennyapp.rhcloud.com/rest/transactions";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("volleyError", volleyError.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new LinkedHashMap<>();
                params.put("memberId","d8922b44-75af-4810-a87e-77adcf433cfd"); // member is hardcoded
                params.put("categoryId", "94dc9ccd-819e-4fc6-8760-af15d57d2ad6"); // categoryId hardcoded
                transactionName = (EditText) view.findViewById(R.id.transaction_name);
                params.put("name", transactionName.getText().toString());
                amount = (EditText) view.findViewById(R.id.amount);
                params.put("amount", amount.getText().toString());
                params.put("transactionDate", "2015-02-23T21:10:47.863"); // date is hardcoded right now
                params.put("debit", "true");
                Log.d("PARAMS", params.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("BEARER", "55b885274e7912280095ef80ac1cb937:d8922b44-75af-4810-a87e-77adcf433cfd:760000000");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(postRequest);

    }


    private void postTransaction() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String URL = "https://api-pennyapp.rhcloud.com/rest/transactions";
        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(getParams()),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.d("Error on Response",error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("BEARER", "55b885274e7912280095ef80ac1cb937:d8922b44-75af-4810-a87e-77adcf433cfd:760000000");
                return headers;
            }
        };

        queue.add(req);
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        params.put("memberId", "d8922b44-75af-4810-a87e-77adcf433cfd");
        params.put("categoryId", selectedCategoryId);

        transactionName = (EditText) getView().findViewById(R.id.transaction_name);
        params.put("name", transactionName.getText().toString());

        amount = (EditText) getView().findViewById(R.id.amount);
        params.put("amount", amount.getText().toString());

        params.put("transactionDate", "2015-02-25T21:10:47.863");

        params.put("debit", "true");
        return params;
    }
}