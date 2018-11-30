/*
 * Copyright (c) 2016 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.navi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.mm2d.guidemo.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationSelectActivity extends AppCompatActivity {
    private static class Entry {
        private final String mName;
        private final Class<?> mClass;
        private final int mParam;

        Entry(
                final String name,
                final Class<?> cls) {
            this(name, cls, 0);
        }

        Entry(
                final String name,
                final Class<?> cls,
                final int param) {
            mName = name;
            mClass = cls;
            mParam = param;
        }

        void startActivity(Context context) {
            final Intent intent = new Intent(context, mClass);
            intent.putExtra(Const.EXTRA_PARAM, mParam);
            context.startActivity(intent);
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    private final List<Entry> mEntries;

    public NavigationSelectActivity() {
        mEntries = new ArrayList<>();
        mEntries.add(new Entry("NavigationDrawer V4", NavigationDrawerV4Activity.class));
        mEntries.add(new Entry("NavigationDrawer V7", NavigationDrawerV7Activity.class));
        mEntries.add(new Entry("NavigationDrawer Material", NavigationDrawerMaterialActivity.class));
        mEntries.add(new Entry("NavigationDrawer Blur Effect", NavigationDrawerBlurActivity.class));
        mEntries.add(new Entry("Master / Detail Flow", ItemListActivity.class));
        mEntries.add(new Entry("ViewPager + PagerTab", TabbedActivity.class, 0));
        mEntries.add(new Entry("ViewPager + TabLayout", TabbedActivity.class, 1));
        mEntries.add(new Entry("ViewPager + PageTransformer", ViewPagerActivity.class));
        mEntries.add(new Entry("Scrolling", ScrollingActivity.class));
        mEntries.add(new Entry("Settings", SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_select);
        final ListView listView = findViewById(R.id.listView);
        assert listView != null;
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mEntries));
        listView.setOnItemClickListener((parent, view, position, id) -> mEntries.get(position).startActivity(NavigationSelectActivity.this));
    }
}
