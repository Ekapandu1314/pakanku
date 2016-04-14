package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.ArticleView;
import com.android.mirzaadr.pakanku.Info_tips;
import com.android.mirzaadr.pakanku.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public class ListTipsAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	//ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	ImageLoader imageLoader = ImageLoader.getInstance();

	public ListTipsAdapter(Context context,
						   ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		//imageLoader = new ImageLoader(context);
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
		rank.setText(resultp.get(Info_tips.JUDUL));
		country.setText(resultp.get(Info_tips.DESKRIPSI).substring(0, 60) + "....");
		population.setText(resultp.get(Info_tips.LINK).substring(0, 24) + "....");
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		//BaseActivity.imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseCont‌​ext()))

		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		//imageLoader.displayImage(resultp.get(MainActivity.GAMBAR), flag);
		imageLoader.displayImage(resultp.get(Info_tips.GAMBAR), flag, options, animateFirstListener);
		//imageLoader.DisplayImage(resultp.get(MainActivity.GAMBAR), flag);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, ArticleView.class);
				// Pass all data rank
				intent.putExtra("judul", resultp.get(Info_tips.JUDUL));
				// Pass all data country
				intent.putExtra("deskripsi", resultp.get(Info_tips.DESKRIPSI));
				// Pass all data population
				intent.putExtra("link",resultp.get(Info_tips.LINK));
				// Pass all data flag
				intent.putExtra("gambar", resultp.get(Info_tips.GAMBAR));

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
