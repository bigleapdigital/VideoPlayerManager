package com.volokh.danylo.visibility_utils.scroll_utils;

import android.view.View;
import android.widget.ListView;

import com.volokh.danylo.visibility_utils.utils.Logger;

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

            if (v == mHeaderView)
                position++;
        }

        return mListView.getChildAt(position);
    }

    @Override
    public int indexOfChild(View view) {
        int position = 0;
        for(int i = 0; i < mListView.getChildCount(); i++)
        {
            View v = mListView.getChildAt(i);

            if (v == view)
                return position;

            if ((mListView.getHeaderViewsCount() > 0 && mHeaderView != null && v == mHeaderView)
                    || (mListView.getFooterViewsCount() > 0 && mFooterView != null && v == mFooterView))
            {
                // pass
            }
            else
            {
                position++;
            }
        }

        return 0;
    }

    @Override
    public int getChildCount() {
        int count = 0;

        for(int i = 0; i < mListView.getChildCount(); i++)
        {
            View v = mListView.getChildAt(i);

            if ((mListView.getHeaderViewsCount() > 0 && mHeaderView != null && v == mHeaderView)
                    || (mListView.getFooterViewsCount() > 0 && mFooterView != null && v == mFooterView))
            {
                // pass
            }
            else
            {
                count++;
            }
        }

        return count;
    }

    @Override
    public int getLastVisiblePosition() {
        int position = mListView.getLastVisiblePosition() - mListView.getHeaderViewsCount();

        int count = mListView.getCount() - mListView.getHeaderViewsCount() - mListView.getFooterViewsCount();

        if (position > count - 1 && count > 0)
            position = count - 1;

        if (position < 0)
            position = 0;

        return position;
    }

    @Override
    public int getFirstVisiblePosition() {
        int position = mListView.getFirstVisiblePosition() - mListView.getHeaderViewsCount();

        if (position < 0)
            position = 0;

        return position;
    }

    @Override
    public int getPositionForView(View view) {
        if (view == null)
            return 0;

        return mListView.getPositionForView(view) - mListView.getHeaderViewsCount();
    }
}

