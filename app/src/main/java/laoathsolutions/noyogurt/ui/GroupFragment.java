package laoathsolutions.noyogurt.ui;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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
        ContentApi api = ContentApiFactory.getApi();
        mGroupInfo = api.getActiveGroupInfo();
        mUserPositionMap = new HashMap<String, Integer>(mGroupInfo.getSize());
        int position = 0;
        for(User user : mGroupInfo.getUsers()) {
            mUserPositionMap.put(user.getId(), position++);
        }
        setListAdapter(new TransactionAdapter(getActivity().getBaseContext(), mGroupInfo.getTransactions(), mUserPositionMap));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv = getListView();
        lv.setStackFromBottom(true);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.current_meal, null);
        ImageView bigPlate = ((ImageView) item.findViewById(R.id.big_plate));
        if(bigPlate != null ) {
            bigPlate.setImageResource(getBigPlateForGroup());
        }
        lv.addFooterView(item);
        LegendView legendView = new LegendView(getActivity());
        legendView.setUsers(mGroupInfo.getUsers());
        lv.addFooterView(legendView);
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
}
