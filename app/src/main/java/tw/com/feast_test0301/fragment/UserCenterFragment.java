package tw.com.feast_test0301.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tw.com.feast_test0301.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserCenterFragment} factory method to
 * create an instance of this fragment.
 */
public class UserCenterFragment extends Fragment {

    public UserCenterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_center, container, false);
        return view;
    }
}