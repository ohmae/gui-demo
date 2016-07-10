/*
 * Copyright (c) 2016 大前良介(OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.guidemo.theme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import net.mm2d.guidemo.R;

public class ThemeCheckBaseActivity extends Activity {
    private ThemeCheckDelegate mDelegate = new ThemeCheckDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra(ThemeSelectActivity.EXTRA_NAME);
        final int theme = intent.getIntExtra(ThemeSelectActivity.EXTRA_THEME, 0);
        setTheme(theme);
        mDelegate.onCreate(theme, name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
