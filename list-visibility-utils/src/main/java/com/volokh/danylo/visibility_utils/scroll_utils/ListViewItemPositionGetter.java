package com.volokh.danylo.visibility_utils.scroll_utils;

import android.view.View;
import android.widget.ListView;

/**
 * This class is an API for {@link com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator}
 * Using this class is can access all the data from ListView
 *
 * Created by danylo.volokh on 1/17/2016.
 */
public class ListViewItemPositionGetter implements ItemsPositionGetter {
    private final ListView mListView;

    public ListViewItemPositionGetter(ListView listView) {
        mListView = listView;
    }

    @Override
    public View getChildAt(int position) {
        return mListView.getChildAt(mListView.getHeaderViewsCount() + position);
    }

    @Override
    public int indexOfChild(View view) {
        return mListView.indexOfChild(view) - mListView.getHeaderViewsCount();
    }

    @Override
    public int getChildCount() {
        return mListView.getChildCount() - mListView.getHeaderViewsCount() - mListView.getFooterViewsCount();
    }

    @Override
    public int getLastVisiblePosition() {

        int position = mListView.getLastVisiblePosition() - mListView.getHeaderViewsCount();

        if (position >= mListView.getChildCount())
            position -= mListView.getFooterViewsCount();

        return position;
    }

    @Override
    public int getFirstVisiblePosition() {

        int position = mListView.getFirstVisiblePosition() - mListView.getHeaderViewsCount();

        if (position < 0)
            position += mListView.getHeaderViewsCount();

        return position;
    }
}

