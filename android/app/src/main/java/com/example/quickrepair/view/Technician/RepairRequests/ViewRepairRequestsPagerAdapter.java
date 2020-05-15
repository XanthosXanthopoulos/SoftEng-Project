package com.example.quickrepair.view.Technician.RepairRequests;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quickrepair.R;

public class ViewRepairRequestsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] { R.string.unconfirmed_position1, R.string.confirmed_position2, R.string.completed_position3 };

    private final Context mContext;
    public ViewRepairRequestsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if (position == 0) {
            return UnconfirmedRepairRequests.newInstance();
        } else if(position == 1) {
            return ConfirmedRepairRequests.newInstance();
        }else{
            return CompletedRepairRequests.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}