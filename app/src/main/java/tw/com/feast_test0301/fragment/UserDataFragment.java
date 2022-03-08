package tw.com.feast_test0301.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.com.feast_test0301.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDataFragment#} factory method to
 * create an instance of this fragment.
 */
public class UserDataFragment extends Fragment {

    public UserDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_data, container, false);
    }
}