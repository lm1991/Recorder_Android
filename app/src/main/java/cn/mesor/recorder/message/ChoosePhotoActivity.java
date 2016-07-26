package cn.mesor.recorder.message;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mesor.recorder.R;

/**
 * Created by Limeng on 2016/7/25.
 */
public class ChoosePhotoActivity extends AppCompatActivity {

    @BindView(R.id.titleLayout)
    Toolbar toolbar;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        ButterKnife.bind(this);
        toolbar.setTitleTextColor(0xffffffff);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_right);
        toolbar.setOverflowIcon(drawable);
        initPager();
        initTab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if(viewPager.getCurrentItem() != 0)
                    break;
                String currentPath = ((GalleryFragment)((PagerAdapter)viewPager.getAdapter()).getItem(0)).getCurrentPath();
                if(TextUtils.isEmpty(currentPath)){
                    break;
                }
                Intent intent = new Intent(this, CreateMessageActivity.class);
                intent.putExtra("path", currentPath);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void initTab() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("图库"));
        tabLayout.addTab(tabLayout.newTab().setText("照片"));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle("图库");
                        break;
                    case 1:
                        toolbar.setTitle("照片");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        Fragment galleryFragment = GalleryFragment.newInstance();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(galleryFragment);
        fragments.add(new Fragment());
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        toolbar.setTitle("图库");
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.fragments.addAll(fragments);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "图库";
                    break;
                case 1:
                    title = "照片";
                    break;
            }
            return title;
        }

        @Override
        public Fragment getItem(int position) {
//            while(position >= fragments.size()) {
//                fragments.add(null);
//            }
//            Fragment fragment = fragments.get(position);
//            if(fragments.get(position) == null){
//                switch (position % 2) {
//                    case 0:
//                        fragment = GalleryFragment.newInstance();
//                        fragments.set(position, fragment);
//                        break;
//                    case 1:
//                        fragment = new Fragment();
//                        fragments.set(position, fragment);
//                        break;
//                }
//            }
            System.out.print(position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
