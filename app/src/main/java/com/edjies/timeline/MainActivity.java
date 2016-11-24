package com.edjies.timeline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edjies.timeline.module.project.ProjectMainFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager mFm;
    private Fragment mCurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        switchTab(R.id.nav_project);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switchTab(id);
        if (id == R.id.nav_project) {

        } else if (id == R.id.nav_learn) {


        } else if (id == R.id.nav_single) {

        } else if (id == R.id.nav_common) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void switchTab(int id) {
        if(mFm == null) {
            mFm = getSupportFragmentManager();
        }
        FragmentTransaction ft = mFm.beginTransaction();
        hideAllFragment(ft);


        if(id == R.id.nav_project) {
            Fragment mFgProject = mFm.findFragmentByTag(ProjectMainFragment.TAG);
            if(mFgProject == null) {
                mFgProject = ProjectMainFragment.newInstance();
                ft.add(R.id.ll_container, mFgProject, ProjectMainFragment.TAG);
            }
            mCurFragment = mFgProject;
            setTitle("我的项目");
        }

        ft.show(mCurFragment).commit();

    }

    private void hideAllFragment(FragmentTransaction ft) {
        Fragment fragment;
        fragment = mFm.findFragmentByTag(ProjectMainFragment.TAG);
        if(fragment != null) {
            ft.hide(fragment);
        }

//        fragment = mFm.findFragmentByTag(TAG_ONE);
//        if(fragment != null) {
//            ft.hide(fragment);
//        }
//
//        fragment = mFm.findFragmentByTag(TAG_NOTICE);
//        if(fragment != null) {
//            ft.hide(fragment);
//        }
//
//        fragment = mFm.findFragmentByTag(TAG_ME);
//        if(fragment != null) {
//            ft.hide(fragment);
//        }
    }
}
