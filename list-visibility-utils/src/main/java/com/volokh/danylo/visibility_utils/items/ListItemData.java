package com.volokh.danylo.visibility_utils.items;

import android.app.LauncherActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.volokh.danylo.visibility_utils.utils.Config;
import com.volokh.danylo.visibility_utils.utils.Logger;

import java.util.List;


public class ListItemData {
    private static final boolean SHOW_LOGS = Config.SHOW_LOGS;
    private static final String TAG = LauncherActivity.ListItem.class.getSimpleName();

    private View mView;

    private boolean mIsMostVisibleItemChanged;

    public View getView() {
        return mView;
    }

    public ListItemData fillWithData(View view) {
        mView = view;
        return this;
    }

    public boolean isAvailable() {
        boolean isAvailable = mView != null;
        if(SHOW_LOGS) Logger.v(TAG, "isAvailable " + isAvailable);
        return isAvailable;
    }

    public int getVisibilityPercents(ArrayAdapter<? extends ListItem> adapter, int index) {
        if (index < 0) return 0;

        int visibilityPercents = adapter.getItem(index).getVisibilityPercents(getView());
        if(SHOW_LOGS) Logger.v(TAG, "getVisibilityPercents, visibilityPercents " + visibilityPercents);
        return visibilityPercents;
    }

    public void setMostVisibleItemChanged(boolean isDataChanged) {
        mIsMostVisibleItemChanged = isDataChanged;
    }

    public boolean isMostVisibleItemChanged() {
        return mIsMostVisibleItemChanged;
    }

    @Override
    public String toString() {
        return "ListItemData{" +
                ", mView=" + mView +
                ", mIsMostVisibleItemChanged=" + mIsMostVisibleItemChanged +
                '}';
    }
}
