/*
 * Copyright (c) 2016 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.navi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import net.mm2d.guidemo.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int param = getIntent().getIntExtra(Const.EXTRA_PARAM, 0);
        switch (param) {
            default:
            case 0:
                setContentView(R.layout.activity_navigation_tabbed1);
                break;
            case 1:
                setContentView(R.layout.activity_navigation_tabbed2);
                break;
        }

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        final float density = getResources().getDisplayMetrics().density;
        final ViewPager viewPager = findViewById(R.id.container);
        assert viewPager != null;
        viewPager.setPageMargin((int) (1 * density + 0.5f));
        viewPager.setPageMarginDrawable(new ColorDrawable(Color.BLACK));
        viewPager.setAdapter(sectionsPagerAdapter);

        final FloatingActionButton fab = findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        static PlaceholderFragment newInstance(int sectionNumber) {
            final PlaceholderFragment fragment = new PlaceholderFragment();
            final Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(
                @NonNull LayoutInflater inflater,
                ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_navigation_tabbed, container, false);
            final TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.large_text));
            return rootView;
        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "TAB " + (position + 1);
        }
    }
}
