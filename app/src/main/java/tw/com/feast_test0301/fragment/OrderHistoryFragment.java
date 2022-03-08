package tw.com.feast_test0301.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.com.feast_test0301.R;
import tw.com.feast_test0301.adapter.OrderHistoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderHistoryFragment#} factory method to
 * create an instance of this fragment.
 */
public class OrderHistoryFragment extends Fragment {


    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_order_history, container, false);
        OrderHistoryAdapter oha = new OrderHistoryAdapter();
        oha.showOrderHistory(view);
        return view;
    }
}