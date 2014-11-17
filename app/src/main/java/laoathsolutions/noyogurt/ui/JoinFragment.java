package laoathsolutions.noyogurt.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import laoathsolutions.noyogurt.R;
import laoathsolutions.noyogurt.api.ContentApi;
import laoathsolutions.noyogurt.api.ContentApiFactory;

/**
 * Main fragment to join a table
 */
public class JoinFragment extends Fragment {

    public JoinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("Chandra", ContentApiFactory.getApi().getGroupInfo("group1234").toString());
        return inflater.inflate(R.layout.join_fragment, container, false);
    }
}
