package com.android.mirzaadr.pakanku.Interface;

/**
 * Created by Eka Pandu Winata on 4/12/2016.
 */
public class BahanCommunicator {

    public interface FragmentCommunicator{
        public void passDataToFragment(String value);
        //public void passDataToFragment2(Boolean someValue);
        //public void passDataToFragment3(Boolean someValue);
    }


    public interface ActivityCommunicator{
        public void passDataToActivity(String value);
    }
}
