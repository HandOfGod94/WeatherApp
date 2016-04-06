package com.example.gahan.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment
{
    public static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    public static final String FORECAST_STR_HASHTAG = "#WeatherApp";
    public String mForecastStr;

    public DetailActivityFragment()
    {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        mShareActionProvider.setShareIntent(createShareForecastIntent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        ((TextView) rootView.findViewById(R.id.textview_detail)).setText(mForecastStr);
        return rootView;
    }

    private Intent createShareForecastIntent()
    {
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                mForecastStr+"-"+FORECAST_STR_HASHTAG);
        return shareIntent;
    }
}
