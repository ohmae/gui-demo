/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.navi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.mm2d.guidemo.R;

public class ViewPagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        final float density = getResources().getDisplayMetrics().density;
        final ViewPager viewPager = findViewById(R.id.container);
        assert viewPager != null;
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setPageMargin((int) (1 * density + 0.5f));
        viewPager.setPageMarginDrawable(new ColorDrawable(Color.BLACK));
        viewPager.setPageTransformer(true, (page, position) -> {
            if (Math.abs(position) > 1.0f) {
                page.setAlpha(0f);
                return;
            }
            final float scale = 1f - position / 4f;
            page.setScaleX(scale);
            page.setScaleY(scale);
            if (position > 0) {
                page.setAlpha(1f - position / 2f);
                page.setTranslationX(-1f * page.getWidth() * position / 2f);
                return;
            }
            page.setAlpha(1f + position / 4f);
            page.setTranslationX(page.getWidth() * position / 8f);
        });
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
            rootView.setBackgroundColor(Color.WHITE);
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
    }
}
