package com.framgia.music_24.screens.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
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
import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.screens.discover.DiscoverFragment;
import com.framgia.music_24.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.screens.splash.SplashActivity.EXTRA_GENRE;
import static com.framgia.music_24.utils.SearchViewAnimate.animateSearchToolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View,
        MenuItemCompat.OnActionExpandListener, SearchView.OnQueryTextListener {

    private static final int NUM_OF_MENU_ICON = 1;
    private static final long WAITING_TIME = 500;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private MainContract.Presenter mPresenter;
    private MenuItem mSearchItem;

    private CountDownTimer mCountDownTimer = new CountDownTimer(WAITING_TIME, WAITING_TIME) {

        public void onTick(long millisUntilFinished) {
            // no ops
        }

        public void onFinish() {
            //TO DO
        }
    };

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
        setupNavigation();
        sendDataToFragment();
    }

    private void setupNavigation() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void sendDataToFragment() {
        ArrayList<Discover> discovers = getIntent().getParcelableArrayListExtra(EXTRA_GENRE);
        DisplayUtils.addFragment(getSupportFragmentManager(),
                DiscoverFragment.newInstance(discovers), R.id.frame_main_layout,
                DiscoverFragment.TAG);
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

    public static Intent getProfileIntent(Context context, List<Discover> discovers) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_GENRE,
                (ArrayList<? extends Parcelable>) discovers);
        return intent;
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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
        SearchView.SearchAutoComplete searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.color_gray_400));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.color_gray_900));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
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
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        }
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if (!query.isEmpty()) {
            assert mCountDownTimer != null;
            mCountDownTimer.start();
        }
        return false;
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
