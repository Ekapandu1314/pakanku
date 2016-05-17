package com.android.mirzaadr.pakanku;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class BuatPakan extends AppCompatActivity {

//    int imageSwitcherImages[] = {R.drawable.sapi, R.drawable.ayam, R.drawable.kambing, R.drawable.domba};
//    Animation animationOut;
//    Animation animationIn;
//    Animation animationprevOut;
//    Animation animationprevIn;
//    ImageSwitcher myImageSwitcher;

    private String[] namaTernak = {"Sapi", "Ayam", "Kambing", "Domba"};
    private ImageView indicatorImages[];

    private ViewPager viewPager;
    int switcherImage;
    int prev;
    int counter = 0;
    private int previousState, currentState;

    ImagePagerAdapter adapter = new ImagePagerAdapter();

    RadioButton check_potong;
    RadioButton check_perah;
    RadioButton check_petelur;
    RadioButton check_hobi;
    RadioButton check_kerja;

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

    String hewan = namaTernak[0];
    String tujuan;
    String nama;
    double berat1;
    int jumlah;
    int lama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pakan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switcherImage = adapter.getCount();

        displayInit();

        buttonPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusButton(editBobot);
            }
        });

        buttonMin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               minButton(editBobot);

            }
        });

        buttonPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               plusButton(editJumlah);

            }
        });

        buttonMin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minButton(editJumlah);

            }
        });

        buttonPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusButton(editHari);

            }
        });

        buttonMin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minButton(editHari);

            }
        });

        //myImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        viewPager = (ViewPager) findViewById(R.id.imageSwitcher);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pageSelected) {
                counter = viewPager.getCurrentItem();
                textEdit.setText(namaTernak[counter]);
                indicatorImages[prev].setImageResource(R.drawable.holo_circle);
                indicatorImages[counter].setImageResource(R.drawable.fill_circle);

                hewan = namaTernak[counter];
                selectTujuan(hewan);

                prev = counter;
            }

            @Override
            public void onPageScrolled(int pageSelected, float positionOffset,
                                       int positionOffsetPixel) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int currentPage = viewPager.getCurrentItem();
                if (currentPage == 3 || currentPage == 0) {
                    previousState = currentState;
                    currentState = state;
                    if (previousState == 1 && currentState == 0) {
                        viewPager.setCurrentItem(currentPage == 0 ? 3 : 0);
                    }
                }
            }
        });

//        myImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                ImageView switcherImageView = new ImageView(getApplicationContext());
//                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
//                        ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT
//                ));
//                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                switcherImageView.setImageResource(R.drawable.sapi);
//                textEdit.setText(namaTernak[0]);
//                indicatorImages[0].setImageResource(R.drawable.fill_circle);
//                return switcherImageView;
//            }
//        });
//
//        animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
//        animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
//
//        animationprevOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
//        animationprevIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rellayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                relativeLayout.requestFocus();
            }
        });

    }

//    public void nextImageButton(View view) {
//        prev = counter;
//        counter ++;
//        myImageSwitcher.setOutAnimation(animationOut);
//        myImageSwitcher.setInAnimation(animationIn);
//
//        if (counter == switcherImage)
//            counter = 0;
//        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
//        textEdit.setText(namaTernak[counter]);
//        hewan = namaTernak[counter];
//
//        check_kerja.setClickable(true);
//        check_hobi.setClickable(true);
//        check_petelur.setClickable(true);
//        check_potong.setClickable(true);
//        check_perah.setClickable(true);
//
//        check_petelur.setVisibility(View.VISIBLE);
//        check_hobi.setVisibility(View.VISIBLE);
//        check_kerja.setVisibility(View.VISIBLE);
//        check_potong.setVisibility(View.VISIBLE);
//        check_perah.setVisibility(View.VISIBLE);
//
//        selectTujuan(hewan);
//        indicatorImages[prev].setImageResource(R.drawable.holo_circle);
//        indicatorImages[counter].setImageResource(R.drawable.fill_circle);
//    }
//
//    public void prevImageButton(View view) {
//        prev = counter;
//        counter--;
//        myImageSwitcher.setOutAnimation(animationprevOut);
//        myImageSwitcher.setInAnimation(animationprevIn);
//
//        int max = switcherImage - 1;
//        if (counter == -1)
//            counter = max;
//        myImageSwitcher.setImageResource(imageSwitcherImages[counter]);
//        textEdit.setText(namaTernak[counter]);
//        hewan = namaTernak[counter];
//
//        check_kerja.setClickable(true);
//        check_hobi.setClickable(true);
//        check_petelur.setClickable(true);
//        check_potong.setClickable(true);
//        check_perah.setClickable(true);
//
//        check_petelur.setVisibility(View.VISIBLE);
//        check_hobi.setVisibility(View.VISIBLE);
//        check_kerja.setVisibility(View.VISIBLE);
//        check_potong.setVisibility(View.VISIBLE);
//        check_perah.setVisibility(View.VISIBLE);
//
//        selectTujuan(hewan);
//        indicatorImages[prev].setImageResource(R.drawable.holo_circle);
//        indicatorImages[counter].setImageResource(R.drawable.fill_circle);
//    }

    public void displayInit(){
        indicatorImages = new ImageView[switcherImage];
        indicatorImages[0] = (ImageView) findViewById(R.id.btn1);
        indicatorImages[1] = (ImageView) findViewById(R.id.btn2);
        indicatorImages[2] = (ImageView) findViewById(R.id.btn3);
        indicatorImages[3] = (ImageView) findViewById(R.id.btn4);

        check_potong = (RadioButton) findViewById(R.id.checkPotong);
        check_perah = (RadioButton) findViewById(R.id.checkPerah);
        check_petelur = (RadioButton) findViewById(R.id.checkPetelur);
        check_hobi = (RadioButton) findViewById(R.id.checkHobi);
        check_kerja = (RadioButton) findViewById(R.id.checkKerja);

        buttonPlus1 = (TextView) findViewById(R.id.buttonplus1);
        buttonPlus2 = (TextView) findViewById(R.id.buttonplus2);
        buttonPlus3 = (TextView) findViewById(R.id.buttonplus3);
        buttonMin1 = (TextView) findViewById(R.id.buttonmin1);
        buttonMin2 = (TextView) findViewById(R.id.buttonmin2);
        buttonMin3 = (TextView) findViewById(R.id.buttonmin3);

        textEdit = (EditText) findViewById(R.id.JenisTernak);
        editBobot = (EditText) findViewById(R.id.editbobot);
        editJumlah = (EditText) findViewById(R.id.editjumlah);
        editHari = (EditText) findViewById(R.id.editHari);

        editHari.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editBobot.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editJumlah.setImeOptions(EditorInfo.IME_ACTION_DONE);

        indicatorImages[0].setImageResource(R.drawable.fill_circle);
        check_potong.setChecked(true);
        textEdit.setText(namaTernak[0]);
        selectTujuan(hewan);
    }

    public void nextImageButton(View view) {
        counter = viewPager.getCurrentItem()+1;
        if (counter == switcherImage)
            counter = 0;
        viewPager.setCurrentItem(counter, true);

    }

    public void prevImageButton(View view) {
        counter = viewPager.getCurrentItem()-1;
        if (counter == -1)
            counter = switcherImage;
        viewPager.setCurrentItem(counter, true);

    }

    public void editAction (View view){
        textEdit.setFocusableInTouchMode(true);
        textEdit.requestFocus();
    }

    public void nextClick(View v) {

        if(hewan.equals("Ayam") || check_hobi.isChecked() || check_kerja.isChecked() || check_petelur.isChecked()) {

            Toast.makeText(getBaseContext(), "Fitur belum tersedia, aplikasi masih dalam versi beta", Toast.LENGTH_SHORT).show();

        }
        else {
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

                    if (check_buat.isChecked()) {

                        Intent intent = new Intent(BuatPakan.this, PilihBahan.class);
                        Bundle var_resep = new Bundle();

                        var_resep.putString("nama", nama);
                        var_resep.putString("hewan", hewan);
                        var_resep.putString("tujuan", tujuan);
                        var_resep.putDouble("berat1", berat1);
                        var_resep.putInt("jumlah", jumlah);
                        var_resep.putInt("lama", lama);
                        intent.putExtras(var_resep);

                        startActivity(intent);

                        dialog.dismiss();

                    }
                    else {

                        Toast.makeText(getBaseContext(), "Fitur belum tersedia, aplikasi masih dalam versi beta", Toast.LENGTH_SHORT).show();

                    }


                }
            });

            dialog.show();

            if (check_potong.isChecked()) {

                tujuan = "Potong";

            } else if (check_perah.isChecked()) {

                tujuan = "Perah";

            } else if (check_petelur.isChecked()) {

                tujuan = "Petelur";

            } else if (check_hobi.isChecked()) {

                tujuan = "Hobi";

            } else if (check_kerja.isChecked()) {

                tujuan = "Kerja";

            } else {

                Toast.makeText(getBaseContext(), "No checkbox selected", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }


            if (editBobot.getText().toString().trim().length() > 0) {

                if (editBobot.getText().toString().equals("0")) {

                    Toast.makeText(getBaseContext(), "Bobot tidak boleh nol", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else {

                    berat1 = Double.parseDouble(editBobot.getText().toString());

                }


            } else {

                Toast.makeText(getBaseContext(), "Bobot tidak boleh kosong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

            if (editJumlah.getText().toString().trim().length() > 0) {

                if (editJumlah.getText().toString().equals("0")) {

                    Toast.makeText(getBaseContext(), "Jumlah tidak boleh nol", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else {

                    jumlah = Integer.parseInt(editJumlah.getText().toString());

                }

            } else {

                Toast.makeText(getBaseContext(), "Banyak ternak tidak boleh kosong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

            if (editHari.getText().toString().trim().length() > 0) {

                if (editHari.getText().toString().equals("0")) {

                    Toast.makeText(getBaseContext(), "Lama pemeliharaan tidak boleh nol", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else {

                    lama = Integer.parseInt(editHari.getText().toString());

                }

            } else {

                Toast.makeText(getBaseContext(), "Lama pemeliharaan tidak boleh kosong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

            if (textEdit.getText().toString().trim().length() > 0) {

                nama = textEdit.getText().toString();

            } else {

                Toast.makeText(getBaseContext(), "Nama ternak tidak boleh kosong", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }
        }

    }

    public void plusButton(EditText edtxt){
        if(edtxt.getText().toString().trim().length() > 0) {

            edtxt.setText(String.valueOf(Integer.parseInt(edtxt.getText().toString()) + 1));
        }
        else {
            edtxt.setText(String.valueOf(Integer.parseInt("0")));
        }
    }

    public void minButton(EditText edtxt){
        if(edtxt.getText().toString().trim().length() > 0) {

            if (Integer.parseInt(edtxt.getText().toString()) <= 0) {
                edtxt.setText(String.valueOf(0));
            }
            else {
                edtxt.setText(String.valueOf(Integer.parseInt(edtxt.getText().toString()) - 1));
            }
        }
        else {
            edtxt.setText(String.valueOf(0));
        }
    }

    public void selectTujuan(String jns_hewan){

        check_kerja.setClickable(true);
        check_hobi.setClickable(true);
        check_petelur.setClickable(true);
        check_potong.setClickable(true);
        check_perah.setClickable(true);

        check_petelur.setVisibility(View.VISIBLE);
        check_hobi.setVisibility(View.VISIBLE);
        check_kerja.setVisibility(View.VISIBLE);
        check_potong.setVisibility(View.VISIBLE);
        check_perah.setVisibility(View.VISIBLE);

        check_kerja.setTextColor(ContextCompat.getColor(this ,R.color.White));
        check_hobi.setTextColor(ContextCompat.getColor(this ,R.color.White));
        check_petelur.setTextColor(ContextCompat.getColor(this ,R.color.White));
        check_perah.setTextColor(ContextCompat.getColor(this ,R.color.White));

        switch (jns_hewan) {
            case "Sapi":
            {
                check_hobi.setClickable(false);
                check_petelur.setClickable(false);
                check_hobi.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_petelur.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                //check_hobi.setVisibility(View.GONE);
                //check_petelur.setVisibility(View.GONE);
                break;
            }
            case "Kambing":
            {
                check_kerja.setClickable(false);
                check_hobi.setClickable(false);
                check_petelur.setClickable(false);
                check_kerja.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_hobi.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_petelur.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                //check_petelur.setVisibility(View.GONE);
                //check_hobi.setVisibility(View.GONE);
                //check_kerja.setVisibility(View.GONE);
                break;
            }
            case "Domba":
            {
                check_kerja.setClickable(false);
                check_hobi.setClickable(false);
                check_petelur.setClickable(false);
                check_kerja.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_hobi.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_petelur.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                //check_petelur.setVisibility(View.GONE);
                //check_hobi.setVisibility(View.GONE);
                //check_kerja.setVisibility(View.GONE);
                break;
            }
            case "Ayam":
            {
                check_kerja.setClickable(false);
                check_perah.setClickable(false);
                check_kerja.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                check_perah.setTextColor(ContextCompat.getColor(this ,R.color.Black));
                //check_kerja.setVisibility(View.GONE);
                //check_perah.setVisibility(View.GONE);
                break;
            }
        }

        check_potong.setChecked(true);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {
                R.drawable.sapi,
                R.drawable.ayam,
                R.drawable.kambing,
                R.drawable.domba
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = BuatPakan.this;
            ImageView imageView = new ImageView(context);
//            int padding = context.getResources().getDimensionPixelSize(
//                    R.dimen.activity_horizontal_margin);
//            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

}
