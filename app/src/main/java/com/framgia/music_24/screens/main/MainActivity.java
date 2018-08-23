package com.framgia.music_24.screens.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.framgia.music_24.R;

import static com.framgia.music_24.utils.SearchViewAnimate.animateSearchToolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View,
        MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

    private static final int NUM_OF_MENU_ICON = 1;
    private static final long TIME_DELAY_SEARCH = 200;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private MainContract.Presenter mPresenter;
    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.color_primary));
        }
        setContentView(R.layout.activity_main);
        initViews();
        initComponents();
    }

    private void initComponents() {
        mPresenter = new MainPresenter();
        mPresenter.setView(this);
        mHandler = new Handler();
        //Init Toolbar
        setSupportActionBar(mToolbar);
        //Init Navigartion Drawable
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mSearchItem = menu.findItem(R.id.search_action);
        MenuItemCompat.setOnActionExpandListener(mSearchItem, this);
        initSearchView();
        return true;
    }

    private void initSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
        SearchView.SearchAutoComplete searchAutoComplete =
                mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.hint_text_color));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.text_search_color));
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        // Called when SearchView is collapsing
        if (mSearchItem.isActionViewExpanded()) {
            animateSearchToolbar(getApplicationContext(), NUM_OF_MENU_ICON, false, false, mToolbar,
                    mDrawerLayout);
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        // Called when SearchView is expanding
        animateSearchToolbar(getApplicationContext(), NUM_OF_MENU_ICON, true, true, mToolbar,
                mDrawerLayout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mHandler.postDelayed(runable, TIME_DELAY_SEARCH);
        return false;
    }

    Runnable runable = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(runable);
            String query = mSearchView.getQuery().toString();
            if (!query.isEmpty()) {
                //TO DO
            }
        }
    };

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(runable);
        super.onDestroy();
    }
}
