package com.schneider.seapp.listview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.activity.ProductActivity;

public class CategoryListItemAdapter extends ArrayAdapter<CategoryListItem> {

	private int resourceId;
	
	private Activity activity;
	
	public CategoryListItemAdapter(Context context, int textViewResourceId,
			List<CategoryListItem> objects) {
		super(context, textViewResourceId, objects);
		
		this.resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		
		CategoryListItem item = getItem(position);
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		view = inflater.inflate(resourceId, parent, false);
		
		ProductClickListener pcl = new ProductClickListener();
		
		ImageView imgProduct1 = (ImageView)view.findViewById(R.id.imgProduct1);
		imgProduct1.setImageResource(item.getProduct1Image());
		imgProduct1.setTag(item.getProduct1Id());
		imgProduct1.setOnClickListener(pcl);
		
		ImageView imgProduct2 = (ImageView)view.findViewById(R.id.imgProduct2);
		imgProduct2.setImageResource(item.getProduct2Image());
		imgProduct2.setTag(item.getProduct2Id());
		imgProduct2.setOnClickListener(pcl);
		
		TextView txtProduct1 = (TextView)view.findViewById(R.id.txtProduct1);
		txtProduct1.setText(item.getProduct1Name());
		txtProduct1.setTag(item.getProduct1Id());
		txtProduct1.setOnClickListener(pcl);
		
		TextView txtProduct2 = (TextView)view.findViewById(R.id.txtProduct2);
		txtProduct2.setText(item.getProduct2Name());
		txtProduct2.setTag(item.getProduct2Id());
		txtProduct2.setOnClickListener(pcl);
		
		TextView txtProduct1Price = (TextView)view.findViewById(R.id.txtProductPrice1);
		txtProduct1Price.setText(new StringBuilder(item.getProduct1PriceUnit()).append(item.getProduct1Price()).toString());
		
		TextView txtProduct2Price = (TextView)view.findViewById(R.id.txtProductPrice2);
		txtProduct2Price.setText(new StringBuilder(item.getProduct2PriceUnit()).append(item.getProduct2Price()).toString());
		
		TextView txtCategoryInfo = (TextView)view.findViewById(R.id.txtCategoryInfo);
		txtCategoryInfo.setText(item.getCategoryName());
		
		return view;
	}
	
	public void setActivity(Activity activity){
		this.activity = activity;
	}
	
	class ProductClickListener implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(activity, ProductActivity.class);
			intent.putExtra("product_id", v.getTag().toString());
			activity.startActivityForResult(intent, 9003);
		}
	}

}


