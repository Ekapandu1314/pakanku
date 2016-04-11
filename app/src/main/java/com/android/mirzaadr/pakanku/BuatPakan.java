package com.android.mirzaadr.pakanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.StackView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.app.ActionBar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class BuatPakan extends AppCompatActivity {

    /*private static StackView stackView;
    private static ArrayList<Stack_Items> list;

    //Integer array for images
    private static final Integer[] icons = { R.drawable.logo, R.drawable.logo,
            R.drawable.logo, R.drawable.logo2 };/*/

    int imageSwitcherImages[] = {R.drawable.sapi, R.drawable.ayam, R.drawable.kambing, R.drawable.domba};

    ImageSwitcher myImageSwitcher;
    int switcherImage = imageSwitcherImages.length;
    int counter = 0;
    Animation animationOut;
    Animation animationIn;
    Animation animationprevOut;
    Animation animationprevIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pakan);

        /*stackView = (StackView) findViewById(R.id.stackView1);
        list = new ArrayList<Stack_Items>();

        //Adding items to the list
        for (int i = 0; i < icons.length; i++) {
            list.add(new Stack_Items("Item " + i, icons[i]));
        }

        //Calling adapter and setting it over stackview
        Stack_Adapter adapter = new Stack_Adapter(BuatPakan.this, list);
        stackView.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        // Spinner element
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        //spinner.setOnItemSelectedListener();

        // Spinner Drop down elements
        /*List<String> categories = new ArrayList<>();
        categories.add("Kerbau");
        categories.add("Sapi");
        categories.add("Kuda");
        categories.add("Kambing");
        categories.add("Babi");
        categories.add("Domba");
        categories.add("Ayam");
        categories.add("Bebek");
        categories.add("Burung");
        categories.add("Kelinci");

        List<String> tujuan = new ArrayList<>();
        tujuan.add("Potong");
        tujuan.add("Perah");
        tujuan.add("Petelur");
        tujuan.add("Hobi");
        tujuan.add("Kerja");*/

        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        //ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tujuan);

        // Drop down layout style - list view with radio button
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        //spinner.setAdapter(dataAdapter);
        //spinner2.setAdapter(dataAdapter2);

        myImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        myImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                switcherImageView.setImageResource(R.drawable.sapi);
                //switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });

        animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        animationprevOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        animationprevIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
    }

    public void nextImageButton(View view) {
        counter++;
        myImageSwitcher.setOutAnimation(animationOut);
        myImageSwitcher.setInAnimation(animationIn);

        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
    }

    public void prevImageButton(View view) {
        counter--;
        myImageSwitcher.setOutAnimation(animationprevOut);
        myImageSwitcher.setInAnimation(animationprevIn);

        int max = switcherImage - 1;
        if (counter == -1)
            counter = max;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
    }

    public void nextClick(View v) {
        Intent intent = new Intent(this, ternak2.class);
        startActivity(intent);
    }


}
