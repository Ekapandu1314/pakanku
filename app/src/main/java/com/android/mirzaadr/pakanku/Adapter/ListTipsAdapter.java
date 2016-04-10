package com.android.mirzaadr.pakanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mirzaadr.pakanku.ArticleView;
import com.android.mirzaadr.pakanku.Info_tips;
import com.android.mirzaadr.pakanku.Internet.ImageLoader;
import com.android.mirzaadr.pakanku.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListTipsAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public ListTipsAdapter(Context context,
						   ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
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

		// Locate the TextViews in listview_item.xml
		rank = (TextView) itemView.findViewById(R.id.rank);
		country = (TextView) itemView.findViewById(R.id.country);
		population = (TextView) itemView.findViewById(R.id.population);

		// Locate the ImageView in listview_item.xml
		flag = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		rank.setText(resultp.get(Info_tips.JUDUL));
		country.setText(resultp.get(Info_tips.DESKRIPSI));
		population.setText(resultp.get(Info_tips.LINK));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(Info_tips.GAMBAR), flag);
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
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
