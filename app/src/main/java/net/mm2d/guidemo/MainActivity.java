/*
 * Copyright (c) 2016 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.mm2d.guidemo.navi.NavigationSelectActivity;
import net.mm2d.guidemo.theme.ThemeSelectActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static class Entry {
        private final String mName;
        private final Class<?> mClass;

        Entry(
                String name,
                Class<?> cls) {
            mName = name;
            mClass = cls;
        }

        void startActivity(Context context) {
            context.startActivity(new Intent(context, mClass));
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    private final List<Entry> mEntries;

    public MainActivity() {
        mEntries = new ArrayList<>();
        mEntries.add(new Entry("テーマごとの標準GUI", ThemeSelectActivity.class));
        mEntries.add(new Entry("Activityバリエーション", NavigationSelectActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.listView);
        assert listView != null;
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mEntries));
        listView.setOnItemClickListener((parent, view, position, id) -> mEntries.get(position).startActivity(MainActivity.this));
    }
}
