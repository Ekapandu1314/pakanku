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

    ImageSwitcher myImageSwitcher;
    int switcherImage = imageSwitcherImages.length;
    int counter = 0;
    EditText textEdit;
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
    TextView buttonMin1;
    TextView buttonMin2;

    EditText editBobot;
    EditText editJumlah;
    EditText editHari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pakan);

        check_potong = (CheckBox) findViewById(R.id.checkPotong);
        check_perah = (CheckBox) findViewById(R.id.checkPerah);
        check_petelur = (CheckBox) findViewById(R.id.checkPetelur);
        check_hobi = (CheckBox) findViewById(R.id.checkHobi);
        check_kerja = (CheckBox) findViewById(R.id.checkKerja);

        buttonPlus1 = (TextView) findViewById(R.id.buttonplus1);
        buttonPlus2 = (TextView) findViewById(R.id.buttonplus2);
        buttonMin1 = (TextView) findViewById(R.id.buttonmin1);
        buttonMin2 = (TextView) findViewById(R.id.buttonmin2);

        editBobot = (EditText) findViewById(R.id.editbobot);
        editJumlah = (EditText) findViewById(R.id.editjumlah);
        editHari = (EditText) findViewById(R.id.editHari);

        editJumlah.setText("0");
        editBobot.setText("0");

        buttonPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editBobot.setText(String.valueOf(Integer.parseInt(editBobot.getText().toString()) + 1));

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

                editJumlah.setText(String.valueOf(Integer.parseInt(editJumlah.getText().toString()) + 1));

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
        textEdit.setText(namaTernak[counter]);
    }

    public void prevImageButton(View view) {
        counter--;
        myImageSwitcher.setOutAnimation(animationprevOut);
        myImageSwitcher.setInAnimation(animationprevIn);

        int max = switcherImage - 1;
        if (counter == -1)
            counter = max;
        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
        textEdit.setText(namaTernak[counter]);
    }

    public void editAction (View view){
        textEdit.setFocusableInTouchMode(true);
        textEdit.requestFocus();
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
