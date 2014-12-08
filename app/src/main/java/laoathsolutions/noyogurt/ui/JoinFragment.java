package laoathsolutions.noyogurt.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import laoathsolutions.noyogurt.R;
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
        View root = inflater.inflate(R.layout.join_fragment, container, false);
        final TextView phoneNumber = (TextView) root.findViewById(R.id.phoneNumber);
        Button join = (Button) root.findViewById(R.id.joinBtn);
                join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JoinAsyncTask().execute(phoneNumber.getText().toString());
            }
        });
        return root;
    }
    private class JoinAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return ContentApiFactory.getApi().join(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(TextUtils.isEmpty(s)) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new GroupFragment());
                ft.commit();
            }
        }
    }
}
