package com.schneider.seapp.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.activity.ProductListActivity;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.util.ProductComparePool;
import com.schneider.seapp.util.ProductHelper;

public class ProductListItemAdapter extends ArrayAdapter<ProductBean> {

	private int resourceId;
	private ProductListActivity activity;
	private static List<ProductBean> prodCompPoll = ProductComparePool.getComparePool();
	
	public ProductListItemAdapter(Context context, int textViewResourceId,
			List<ProductBean> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(resourceId, parent, false);
		
		ProductBean pBean = getItem(position);
		
		TextView txtProductName = (TextView)view.findViewById(R.id.txtProductName);
		txtProductName.setText(pBean.getProductName());
		txtProductName.setTag(pBean.getProductId());
		
		ImageView imgProduct = (ImageView)view.findViewById(R.id.imgProduct);
		imgProduct.setImageResource(inflater.getContext().getResources().getIdentifier(new StringBuilder("product_").append(pBean.getProductId()).append("_small").toString(), "drawable", "com.schneider.seapp"));
		
		TextView txtProductPrice = (TextView)view.findViewById(R.id.txtProductPrice);
		txtProductPrice.setText(new StringBuilder(pBean.getPriceUnit()).append(pBean.getPrice()).toString());
		
		CheckBox chbAddToComp = (CheckBox)view.findViewById(R.id.ckbAddtoComp);
		chbAddToComp.setTag(pBean.getProductId());
		chbAddToComp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean checked = ((CheckBox) v).isChecked();
				List<ProductBean> prodCompPoll = ProductComparePool.getComparePool();
				CheckBox ckbAddtoComp = (CheckBox)v.findViewById(R.id.ckbAddtoComp);
				int productId = Integer.parseInt(ckbAddtoComp.getTag().toString());
				
				// Refresh all checkbox on this fragment.
				ListView listProducts = (ListView)activity.findViewById(R.id.listProducts);
					
				if (checked == true) {
					if (prodCompPoll.size() < 2) {
						ProductBean pb = ProductHelper.getProductById(activity.getAssets(), 
								new StringBuilder("product_").append(productId).append(".xml").toString(), productId);
						prodCompPoll.add(pb);
					} 
					
					if (prodCompPoll.size() == 2) {
						for(int i=0; i < listProducts.getChildCount(); i++){
						    RelativeLayout itemLayout = (RelativeLayout)listProducts.getChildAt(i);
						    CheckBox cb = (CheckBox)itemLayout.findViewById(R.id.ckbAddtoComp);	
						    for (ProductBean pdBean : ProductComparePool.getComparePool()){
						    	if (Integer.parseInt(cb.getTag().toString()) == pdBean.getProductId()) {
						    		cb.setEnabled(true);
						    		break;
						    	} else {
						    		cb.setEnabled(false);
						    	}
						    }
						}
						
						// Start compare activitiy
						int radioCompare = activity.getIntent().getIntExtra("tab_type",R.id.radioCompare);
						activity.onItemSelected(radioCompare);
					}
				} else {
					ProductBean pb = ProductHelper.getProductById(activity.getAssets(), 
							new StringBuilder("product_").append(productId).append(".xml").toString(), productId);
					prodCompPoll.remove(pb);
					
					for(int i=0; i < listProducts.getChildCount(); i++){
					    RelativeLayout itemLayout = (RelativeLayout)listProducts.getChildAt(i);
					    CheckBox cb = (CheckBox)itemLayout.findViewById(R.id.ckbAddtoComp);	
					    //for (ProductBean pdBean : ProductComparePool.getComparePool()){
					    	cb.setEnabled(true);
					    //}
					}
				}
			}
		});
		
		if (prodCompPoll.size() == 2) {
			for (ProductBean prodBean : prodCompPoll) {	
				if (prodBean.getProductId() == pBean.getProductId()) {
					chbAddToComp.setEnabled(true);
					chbAddToComp.setChecked(true);
					break;
				} else {
					chbAddToComp.setEnabled(false);
				}
			}
		}
		
		return view;
	}
	
	public void setActivity(ProductListActivity activity){
		this.activity = activity;
	}
}
