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
    private final View mHeaderView;
    private final View mFooterView;

    public ListViewItemPositionGetter(ListView listView, View headerView, View footerView) {
        mListView = listView;
        mHeaderView = headerView;
        mFooterView = footerView;
    }

    @Override
    public View getChildAt(int position) {
        for(int i = 0; i < mListView.getChildCount(); i++)
        {
            View v = mListView.getChildAt(i);

            if (v == mHeaderView || v == mFooterView)
                position++;
        }

        return mListView.getChildAt(position);
    }

    @Override
    public int indexOfChild(View view) {
        int position = mListView.indexOfChild(view);

        for(int i = 0; i < position; i++)
        {
            View v = mListView.getChildAt(i);

            if (v == mHeaderView || v == mFooterView)
                position++;
        }

        return position;
    }

    @Override
    public int getChildCount() {
        int count = 0;

        for(int i = 0; i < mListView.getChildCount(); i++)
        {
            View v = mListView.getChildAt(i);

            if (v != mHeaderView && v != mFooterView)
                count++;
        }

        return count;
    }

    @Override
    public int getLastVisiblePosition() {
        int position = mListView.getLastVisiblePosition() - mListView.getHeaderViewsCount();

        if (position < 0)
            position = 0;

        int count = mListView.getCount() - mListView.getHeaderViewsCount() - mListView.getFooterViewsCount();

        if (position > count - 1)
            position = count - 1;

        return position;
    }

    @Override
    public int getFirstVisiblePosition() {
        int position = mListView.getFirstVisiblePosition() - mListView.getHeaderViewsCount();

        if (position < 0)
            position = 0;

        return position;
    }
}

