package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.Activity.BukaArtikel;
import com.android.mirzaadr.pakanku.Fragment.InfoTips;
import com.android.mirzaadr.pakanku.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public class ListTipsAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	HashMap<String, String> resultp = new HashMap<String, String>();
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	ImageLoader imageLoader = ImageLoader.getInstance();

	public ListTipsAdapter(Context context,
						   ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_empty)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.considerExifParams(true)
				.build();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		TextView country;
		TextView population;
		ImageView flag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.list_item_tips, parent, false);
		// Get the position
		resultp = data.get(position);


		rank = (TextView) itemView.findViewById(R.id.rank);
		country = (TextView) itemView.findViewById(R.id.country);
		population = (TextView) itemView.findViewById(R.id.population);


		flag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		rank.setText(resultp.get(InfoTips.JUDUL));
		country.setText(resultp.get(InfoTips.DESKRIPSI).substring(0, 60) + "....");
		population.setText(resultp.get(InfoTips.LINK).substring(0, 24) + "....");
		// Capture position and set results to the ImageView
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		imageLoader.displayImage(resultp.get(InfoTips.GAMBAR), flag, options, animateFirstListener);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, BukaArtikel.class);
				// Pass all data rank
				intent.putExtra("judul", resultp.get(InfoTips.JUDUL));
				// Pass all data country
				intent.putExtra("deskripsi", resultp.get(InfoTips.DESKRIPSI));
				// Pass all data population
				intent.putExtra("link",resultp.get(InfoTips.LINK));
				// Pass all data flag
				intent.putExtra("gambar", resultp.get(InfoTips.GAMBAR));

				context.startActivity(intent);

			}
		});
		return itemView;
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
