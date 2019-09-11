package com.example.bondconsult;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private CircleImageView circleImageView;
    private TextView usrName;

    final int SIGN_IN = 1;
    final int SIGN_UP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentAdapter mFragmentAdapter = new mFragmentAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(mFragmentAdapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.fab_main);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewPostActivity.class));
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user_small);
        }

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_sign_in:
                        Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(intent1,SIGN_IN);
                        break;
                    case R.id.nav_sign_up:
                        Intent intent2 = new Intent(MainActivity.this,SignUpActivity.class);
                        startActivityForResult(intent2,SIGN_UP);
                        break;
                }
                return true;
            }

        });

        View headerView = navigationView.getHeaderView(0);
        circleImageView =(CircleImageView)headerView.findViewById(R.id.nav_avatar);

        usrName = (TextView)headerView.findViewById(R.id.nav_name);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("back", "succ");
        switch (requestCode){
            case SIGN_IN:
                if(resultCode==RESULT_OK){
                    User user1 = (User)data.getSerializableExtra("usr_data");
                    circleImageView.setImageBitmap(Util.byte2Bitmap(user1.getAvatar()));
                    usrName.setText(user1.getName());

                }
                break;
            case SIGN_UP:
                if(resultCode==RESULT_OK){
                    User user2 = (User)data.getSerializableExtra("usr_data");
                    circleImageView.setImageBitmap(Util.byte2Bitmap(user2.getAvatar()));
                    usrName.setText(user2.getName());
                }
        }
    }
}
