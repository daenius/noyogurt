package laoathsolutions.noyogurt.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import laoathsolutions.noyogurt.R;
import laoathsolutions.noyogurt.api.ContentApi;
import laoathsolutions.noyogurt.api.ContentApiFactory;
import laoathsolutions.noyogurt.api.GroupInfo;
import laoathsolutions.noyogurt.api.User;

/**
 * A fragment representing a list of Items.
 */
public class GroupFragment extends ListFragment {


    private ImageView mBigPlate;
    private LegendView mLegendView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupFragment() {
    }

    private GroupInfo mGroupInfo;
    private HashMap<String, Integer> mUserPositionMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GroupInfoAsyncTask().execute(ContentApiFactory.getApi().getActiveGroupId());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv = getListView();
        lv.setStackFromBottom(true);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.current_meal, null);
        mBigPlate = ((ImageView) item.findViewById(R.id.big_plate));
        lv.addFooterView(item);
        mLegendView = new LegendView(getActivity());
        lv.addFooterView(mLegendView);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    private int getBigPlateForGroup() {
        float min = Float.MAX_VALUE;
        String minUser = "";
        for(User user: mGroupInfo.getUsers()) {
            if(TextUtils.isEmpty(minUser) ||  user.getScore() < min) {
                minUser = user.getId();
                min = user.getScore();
            }
        }
        return PlateImageHelper.getBigPlateImageForUserAt(mUserPositionMap.get(minUser));
    }

    private void loadList(GroupInfo groupInfo) {
        mGroupInfo = groupInfo;
        mUserPositionMap = new HashMap<String, Integer>(mGroupInfo.getSize());
        int position = 0;
        for(User user : mGroupInfo.getUsers()) {
            mUserPositionMap.put(user.getId(), position++);
        }
        setListAdapter(new TransactionAdapter(getActivity().getBaseContext(), mGroupInfo.getTransactions(), mUserPositionMap));
        if(mBigPlate != null ) {
            mBigPlate.setImageResource(getBigPlateForGroup());
        }
        mLegendView.setUsers(mGroupInfo.getUsers());
    }

    private class GroupInfoAsyncTask extends AsyncTask<String, String, GroupInfo> {

        @Override
        protected GroupInfo doInBackground(String... strings) {
            return ContentApiFactory.getApi().getGroupInfo(strings[0]);
        }

        @Override
        protected void onPostExecute(GroupInfo groupInfo) {
            super.onPostExecute(groupInfo);
            if(groupInfo == null) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                loadList(groupInfo);
            }
        }
    }
}
