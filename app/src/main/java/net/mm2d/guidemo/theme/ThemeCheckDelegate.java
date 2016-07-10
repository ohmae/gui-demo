/*
 * Copyright (c) 2016 大前良介(OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.theme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import net.mm2d.guidemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeCheckDelegate {
    private Activity mActivity;

    public ThemeCheckDelegate(Activity activity) {
        mActivity = activity;
    }

    protected void onCreate(int theme, String name) {
        if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
            mActivity.setContentView(R.layout.activity_theme_check_legacy);
        } else {
            if (theme < android.R.style.Theme_Holo) {
                mActivity.setContentView(R.layout.activity_theme_check_legacy);
            } else {
                mActivity.setContentView(R.layout.activity_theme_check);
            }
        }
        final TextView title = (TextView) findViewById(R.id.title);
        title.setText("Theme: " + name);
        setupNavigation();
        setupParts();
    }

    private void setupNavigation() {
        final String[] mode = new String[]{"GUI", "ListView", "ListView2"};
        final View parent1 = findViewById(R.id.parent1);
        final View parent2 = findViewById(R.id.parent2);
        final View parent3 = findViewById(R.id.parent3);
        final Spinner modeSelect = (Spinner) findViewById(R.id.modeSelect);
        modeSelect.setAdapter(new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, mode));
        modeSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    default:
                    case 0:
                        parent1.setVisibility(View.VISIBLE);
                        parent2.setVisibility(View.GONE);
                        parent3.setVisibility(View.GONE);
                        break;
                    case 1:
                        parent1.setVisibility(View.GONE);
                        parent2.setVisibility(View.VISIBLE);
                        parent3.setVisibility(View.GONE);
                        break;
                    case 2:
                        parent1.setVisibility(View.GONE);
                        parent2.setVisibility(View.GONE);
                        parent3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final View group = findViewById(R.id.group);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.enableSwitch);
        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAllEnabled(group, isChecked);
                setAllEnabled(parent2, isChecked);
                setAllEnabled(parent3, isChecked);
            }
        });
    }

    private void setupParts() {
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(50);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(50);
        final ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setMax(100);
        progressBar2.setProgress(50);
        progressBar2.setSecondaryProgress(75);
        final OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(v.getId());
            }
        };
        findViewById(R.id.dialogButton1).setOnClickListener(listener);
        findViewById(R.id.dialogButton2).setOnClickListener(listener);
        findViewById(R.id.dialogButton3).setOnClickListener(listener);
        findViewById(R.id.dialogButton4).setOnClickListener(listener);
        findViewById(R.id.dialogButton5).setOnClickListener(listener);
        findViewById(R.id.dialogButton6).setOnClickListener(listener);
        findViewById(R.id.dialogButton7).setOnClickListener(listener);
        final List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final Map<String, String> map = new HashMap<>();
            map.put("title", "item" + i);
            map.put("summary", "summary" + i);
            data.add(map);
        }
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new SimpleAdapter(mActivity, data, android.R.layout.simple_list_item_2,
                new String[]{"title", "summary"}, new int[]{android.R.id.text1, android.R.id.text2}));
        final List<Map<String, String>> groupData = new ArrayList<>();
        final List<List<Map<String, String>>> childData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Map<String, String> map = new HashMap<>();
            map.put("title", "group" + i);
            groupData.add(map);
            final List<Map<String, String>> childList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                final Map<String, String> childMap = new HashMap<>();
                childMap.put("title", "child" + j);
                childMap.put("summary", "group" + i);
                childList.add(childMap);
            }
            childData.add(childList);
        }
        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new SimpleExpandableListAdapter(mActivity, groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"title"}, new int[]{android.R.id.text1},
                childData, android.R.layout.simple_expandable_list_item_2,
                new String[]{"title", "summary"}, new int[]{android.R.id.text1, android.R.id.text2}));
    }

    private View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    private void showAlertDialog(int v) {
        final String[] items = {"item_0", "item_1", "item_2"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Title");
        switch (v) {
            case R.id.dialogButton1:
                builder.setMessage("Message");
                break;
            case R.id.dialogButton2:
                builder.setMessage("Message");
                builder.setPositiveButton("OK", null);
                break;
            case R.id.dialogButton3:
                builder.setMessage("Message");
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                break;
            case R.id.dialogButton4:
                builder.setMessage("Message");
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                builder.setNeutralButton("Later", null);
                break;
            case R.id.dialogButton5:
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.dialogButton6:
                builder.setSingleChoiceItems(items, 0, null);
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                break;
            case R.id.dialogButton7:
                builder.setMultiChoiceItems(items, null, null);
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                break;
        }
        builder.show();
    }

    private void setAllEnabled(View view, boolean enabled) {
        if (view instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                setAllEnabled(group.getChildAt(i), enabled);
            }
        }
        view.setEnabled(enabled);
    }
}
