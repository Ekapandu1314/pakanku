package com.android.mirzaadr.pakanku;

/**
 * Created by Eka Pandu Winata on 4/12/2016.
 */
public class interfaces {

    public interface FragmentCommunicator{
        public void passDataToFragment(String value);
        //public void passDataToFragment2(Boolean someValue);
        //public void passDataToFragment3(Boolean someValue);
    }


    public interface ActivityCommunicator{
        public void passDataToActivity(String value);
    }
}
