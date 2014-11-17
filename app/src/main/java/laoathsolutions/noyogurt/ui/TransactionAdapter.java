package laoathsolutions.noyogurt.ui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import laoathsolutions.noyogurt.R;
import laoathsolutions.noyogurt.api.Transaction;

/**
 * Created by ckrishna on 11/16/14.
 */
public class TransactionAdapter implements ListAdapter {
    private List<Transaction> mTransactions;

    private Context mContext;

    private Map<String, Integer> mUserPositionMap;

    TransactionAdapter(Context c, List<Transaction> transactions, Map<String, Integer> userPositionMap) {
        mTransactions = transactions;
        mContext = c;
        mUserPositionMap = userPositionMap;
    }

    public void setTransactions(List<Transaction> mTransactions) {
        this.mTransactions = mTransactions;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return mTransactions.size();
    }

    @Override
    public Object getItem(int i) {
        return mTransactions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Transaction transaction = mTransactions.get(i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(getLayoutForTransaction(transaction), parent, false);
        ((TextView) item.findViewById(R.id.timeStamp)).setText(new SimpleDateFormat().format(transaction.getTimeStamp()));
        ImageView plate = ((ImageView) item.findViewById(R.id.plate));
        if(plate != null ) {
            plate.setImageResource(PlateImageHelper.getPlateImageForUserAt(mUserPositionMap.get(transaction.getUser())));
        }
        return item;
    }

    @Override
    public int getItemViewType(int i) {
        int ret = 0;
        Transaction transaction = mTransactions.get(i);
        if(transaction.getSize() == Transaction.Size.OC) ret = 1;
        if(transaction.getSize() == Transaction.Size.OO) ret = 2;
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return mTransactions.isEmpty();
    }

    private int getLayoutForTransaction(Transaction transaction) {
        int ret = R.layout.transaction_item_o;
        if(transaction.getSize() == Transaction.Size.OC) ret = R.layout.transaction_item_oc;
        if(transaction.getSize() == Transaction.Size.OO) ret = R.layout.transaction_item_oo;
        return ret;
    }
}
