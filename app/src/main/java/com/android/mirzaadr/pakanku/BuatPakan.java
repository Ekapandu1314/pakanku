package com.android.mirzaadr.pakanku;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.StackView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
    private String[] namaTernak = {"Sapi", "Ayam", "Kambing", "Domba"};
    //ImageView id1, id2, id3, id4;
    public ImageView indicatorImages[];

    ImageSwitcher myImageSwitcher;
    int switcherImage = imageSwitcherImages.length;
    int prev;
    int counter = 0;
    Animation animationOut;
    Animation animationIn;
    Animation animationprevOut;
    Animation animationprevIn;

    CheckBox check_potong;
    CheckBox check_perah;
    CheckBox check_petelur;
    CheckBox check_hobi;
    CheckBox check_kerja;

    TextView buttonPlus1;
    TextView buttonPlus2;
    TextView buttonPlus3;
    TextView buttonMin1;
    TextView buttonMin2;
    TextView buttonMin3;

    EditText textEdit;
    EditText editBobot;
    EditText editJumlah;
    EditText editHari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pakan);

        indicatorImages = new ImageView[switcherImage];
        indicatorImages[0] = (ImageView) findViewById(R.id.btn1);
        indicatorImages[1] = (ImageView) findViewById(R.id.btn2);
        indicatorImages[2] = (ImageView) findViewById(R.id.btn3);
        indicatorImages[3] = (ImageView) findViewById(R.id.btn4);


        check_potong = (CheckBox) findViewById(R.id.checkPotong);
        check_perah = (CheckBox) findViewById(R.id.checkPerah);
        check_petelur = (CheckBox) findViewById(R.id.checkPetelur);
        check_hobi = (CheckBox) findViewById(R.id.checkHobi);
        check_kerja = (CheckBox) findViewById(R.id.checkKerja);

        buttonPlus1 = (TextView) findViewById(R.id.buttonplus1);
        buttonPlus2 = (TextView) findViewById(R.id.buttonplus2);
        buttonPlus3 = (TextView) findViewById(R.id.buttonplus3);
        buttonMin1 = (TextView) findViewById(R.id.buttonmin1);
        buttonMin2 = (TextView) findViewById(R.id.buttonmin2);
        buttonMin3 = (TextView) findViewById(R.id.buttonmin3);

        editBobot = (EditText) findViewById(R.id.editbobot);
        editJumlah = (EditText) findViewById(R.id.editjumlah);
        editHari = (EditText) findViewById(R.id.editHari);

        buttonPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editBobot.getText().toString().trim().length() > 0) {

                    editBobot.setText(String.valueOf(Integer.parseInt(editBobot.getText().toString()) + 1));

                }
                else {

                    editBobot.setText(String.valueOf(Integer.parseInt("0")));

                }



            }
        });

        buttonMin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(editBobot.getText().toString()) <= 0) {

                    editBobot.setText(String.valueOf(0));

                }
                else {

                    editBobot.setText(String.valueOf(Integer.parseInt(editBobot.getText().toString()) - 1));

                }



            }
        });

        buttonPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editJumlah.getText().toString().trim().length() > 0) {

                    editJumlah.setText(String.valueOf(Integer.parseInt(editJumlah.getText().toString()) + 1));

                }
                else {

                    editJumlah.setText(String.valueOf(Integer.parseInt("0")));

                }

            }
        });

        buttonMin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(editJumlah.getText().toString()) <= 0) {

                    editJumlah.setText(String.valueOf(Integer.parseInt(editJumlah.getText().toString())));

                }
                else {

                    editJumlah.setText(String.valueOf(Integer.parseInt(editJumlah.getText().toString()) - 1));

                }



            }
        });

        buttonPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editHari.getText().toString().trim().length() > 0) {

                    editHari.setText(String.valueOf(Integer.parseInt(editHari.getText().toString()) + 1));

                }
                else {

                    editHari.setText(String.valueOf(Integer.parseInt("0")));

                }



            }
        });

        buttonMin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(editHari.getText().toString()) <= 0) {

                    editHari.setText(String.valueOf(0));

                }
                else {

                    editHari.setText(String.valueOf(Integer.parseInt(editHari.getText().toString()) - 1));

                }



            }
        });

        check_potong.setChecked(true);

        check_potong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_potong.setChecked(true);
                check_perah.setChecked(false);
                check_petelur.setChecked(false);
                check_hobi.setChecked(false);
                check_kerja.setChecked(false);
            }
        });

        check_perah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_potong.setChecked(false);
                check_perah.setChecked(true);
                check_petelur.setChecked(false);
                check_hobi.setChecked(false);
                check_kerja.setChecked(false);
            }
        });

        check_petelur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_potong.setChecked(false);
                check_perah.setChecked(false);
                check_petelur.setChecked(true);
                check_hobi.setChecked(false);
                check_kerja.setChecked(false);
            }
        });

        check_hobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_potong.setChecked(false);
                check_perah.setChecked(false);
                check_petelur.setChecked(false);
                check_hobi.setChecked(true);
                check_kerja.setChecked(false);
            }
        });

        check_kerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_potong.setChecked(false);
                check_perah.setChecked(false);
                check_petelur.setChecked(false);
                check_hobi.setChecked(false);
                check_kerja.setChecked(true);
            }
        });

        textEdit = (EditText) findViewById(R.id.JenisTernak);
        //textEdit.setFocusableInTouchMode(false);
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
                textEdit.setText(namaTernak[0]);
                indicatorImages[0].setImageResource(R.drawable.fill_circle);
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
        prev = counter;
        counter ++;
        myImageSwitcher.setOutAnimation(animationOut);
        myImageSwitcher.setInAnimation(animationIn);

        if (counter == switcherImage)
            counter = 0;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
        textEdit.setText(namaTernak[counter]);
        indicatorImages[prev].setImageResource(R.drawable.holo_circle);
        indicatorImages[counter].setImageResource(R.drawable.fill_circle);
    }

    public void prevImageButton(View view) {
        prev = counter;
        counter--;
        myImageSwitcher.setOutAnimation(animationprevOut);
        myImageSwitcher.setInAnimation(animationprevIn);

        int max = switcherImage - 1;
        if (counter == -1)
            counter = max;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
        textEdit.setText(namaTernak[counter]);
        indicatorImages[prev].setImageResource(R.drawable.holo_circle);
        indicatorImages[counter].setImageResource(R.drawable.fill_circle);
    }

    public void editAction (View view){
        textEdit.setFocusableInTouchMode(true);
        textEdit.requestFocus();
    }

    public void editBobot (View view){
        editBobot.setFocusableInTouchMode(true);
        editBobot.requestFocus();
    }

    public void editJumlah (View view){
        editJumlah.setFocusableInTouchMode(true);
        editJumlah.requestFocus();
    }

    public void editHari (View view){
        editHari.setFocusableInTouchMode(true);
        editHari.requestFocus();
    }

    public void nextClick(View v) {

        final Dialog dialog = new Dialog(BuatPakan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_paket);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        final CheckBox check_hemat = (CheckBox) dialog.findViewById(R.id.cekPaket);
        final CheckBox check_hebat = (CheckBox) dialog.findViewById(R.id.cekPaket2);
        final CheckBox check_buat = (CheckBox) dialog.findViewById(R.id.cekBuat);
        final Button buat_ransum = (Button) dialog.findViewById(R.id.buttonk);

        check_buat.setChecked(true);

        check_hemat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_hemat.setChecked(true);
                check_hebat.setChecked(false);
                check_buat.setChecked(false);

            }
        });

        check_hebat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_hemat.setChecked(false);
                check_hebat.setChecked(true);
                check_buat.setChecked(false);

            }
        });

        check_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_hemat.setChecked(false);
                check_hebat.setChecked(false);
                check_buat.setChecked(true);

            }
        });

        buat_ransum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BuatPakan.this, ternak2.class);
                startActivity(intent);

            }
        });



        dialog.show();

    }

}
