package com.whitelabel.app.utils;

import android.support.v4.app.Fragment;

import com.whitelabel.app.application.WhiteLabelApplication;
import com.whitelabel.app.data.DataManager;
import com.whitelabel.app.fragment.HomeHomeFragment;
import com.whitelabel.app.ui.home.HomeHomeFragmentV1;
import com.whitelabel.app.ui.home.HomeHomeFragmentV2;

/**
 * Created by ray on 2017/5/3.
 */

public class FragmentFactory {
    private static FragmentFactory fragmentFactory;
    private FragmentFactory(){


    }
    public  static FragmentFactory newInstance(){
        if(fragmentFactory==null){
            synchronized (DataManager.class){
                fragmentFactory= new FragmentFactory();
            }
        }
        return fragmentFactory;
    }
    public Fragment  getHomeFragment(){
        if(WhiteLabelApplication.getAppConfiguration().getLayoutStyle().getHomeType()==1){
            return HomeHomeFragmentV1.newInstance();
        }else if(WhiteLabelApplication.getAppConfiguration().getLayoutStyle().getHomeType()==2){
            return HomeHomeFragmentV2.newInstance();
        }else if(WhiteLabelApplication.getAppConfiguration().getLayoutStyle().getHomeType()==3){
            return HomeHomeFragment.newInstance(HomeHomeFragment.TYPE_FRAGMENT_VERTICAL);
        }else if(WhiteLabelApplication.getAppConfiguration().getLayoutStyle().getHomeType()==4){
            return HomeHomeFragment.newInstance(HomeHomeFragment.TYPE_FRAGMENT_HORIZONTAL);
        }
        return new HomeHomeFragment();
    }
}