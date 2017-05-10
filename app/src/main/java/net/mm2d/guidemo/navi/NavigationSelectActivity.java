/*
 * Copyright (c) 2016 大前良介(OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.navi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.mm2d.guidemo.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationSelectActivity extends AppCompatActivity {
    private static class Entry {
        private final String mName;
        private final Class<?> mClass;

        public Entry(String name, Class<?> cls) {
            mName = name;
            mClass = cls;
        }

        public void startActivity(Context context) {
            context.startActivity(new Intent(context, mClass));
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
        mEntries.add(new Entry("ViewPager + PagerTab", TabbedActivity.class));
        mEntries.add(new Entry("Scrolling", ScrollingActivity.class));
        mEntries.add(new Entry("Settings", SettingsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_select);
        final ListView listView = (ListView) findViewById(R.id.listView);
        assert listView != null;
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mEntries));
        listView.setOnItemClickListener((parent, view, position, id) -> mEntries.get(position).startActivity(NavigationSelectActivity.this));
    }
}
