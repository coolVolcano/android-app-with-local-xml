package com.schneider.seapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.bean.CategoryBean;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.listview.ProductListItemAdapter;
import com.schneider.seapp.util.ProductComparePool;

public class ProductListActivity extends GenericActivity{
	
	private ProductListItemAdapter adapter;
	private int maxCount = 0;
	private List<ProductBean> products;
	private ListView listProducts;
	private int theScrollState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productlist);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		listProducts = (ListView)findViewById(R.id.listProducts);
		
		CategoryBean categoryBean = getIntent().getParcelableExtra("category");
		String categoryName = categoryBean.getCategoryName();
		products = categoryBean.getProducts();
		maxCount = products.size();
		
		List<ProductBean> subProducts = new ArrayList<ProductBean>();
		//subProducts = products.subList(0, 10);
		//here the reason why don't use subList is duplicated items will be return if invoke this line in "adapter.add(products.get(i));" of OnScrollListener's onScroll function.
		for(int i=0;i<(maxCount<10?maxCount:10);i++){
			subProducts.add(products.get(i));
		}
		
		adapter = new ProductListItemAdapter(this,R.layout.product_list_item,subProducts);
		adapter.setActivity(this);
		listProducts.setAdapter(adapter);
		listProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView txtProductName = (TextView)view.findViewById(R.id.txtProductName);
				int productId = Integer.parseInt(txtProductName.getTag().toString());
				Intent intent = new Intent(ProductListActivity.this,ProductActivity.class);
				intent.putExtra("product_id", String.valueOf(productId));
				ProductListActivity.this.startActivityForResult(intent,9004);
			}
		});
		
		
		listProducts.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				theScrollState = scrollState;
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				//come to the bottom of the list
				if(firstVisibleItem + visibleItemCount >= totalItemCount && theScrollState ==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
					int count = adapter.getCount();
					for(int i=count;i<count+5&&i<maxCount;i++){
						adapter.add(products.get(i));
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					adapter.notifyDataSetChanged();
				}
			}
		});
		
		getActionBar().setTitle(categoryName);
	}
	
	
	
	@Override
	public void onResume() {
		ListView listProducts = (ListView)findViewById(R.id.listProducts);
		List<ProductBean> prodCompPoll = ProductComparePool.getComparePool();
		
		if (prodCompPoll.size() == 2) {
			for (int i=0; i< listProducts.getChildCount();i++ ){
				View view = listProducts.getChildAt(i);
				CheckBox cbkProd = (CheckBox)view.findViewById(R.id.ckbAddtoComp);
				for (ProductBean pb: prodCompPoll) {
					if (pb.getProductId() == Integer.parseInt(cbkProd.getTag().toString()) ) {
						cbkProd.setChecked(true);
						cbkProd.setEnabled(true);
						break;
					} else {
						cbkProd.setChecked(false);
						cbkProd.setEnabled(false);
					}
				}
			}
		} else if (prodCompPoll.size() == 1) {
			for (int i=0; i< listProducts.getChildCount();i++ ){
				View view = listProducts.getChildAt(i);
				CheckBox cbkProd = (CheckBox)view.findViewById(R.id.ckbAddtoComp);
				
				for (ProductBean pb: prodCompPoll) {
					if (pb.getProductId() == Integer.parseInt(cbkProd.getTag().toString()) ) {
						cbkProd.setChecked(true);
						cbkProd.setEnabled(true);
						break;
					} else {
						cbkProd.setChecked(false);
						cbkProd.setEnabled(true);
					}
				}
			}
		} else {
			for (int i=0; i< listProducts.getChildCount();i++ ){
				View view = listProducts.getChildAt(i);
				CheckBox cbkProd = (CheckBox)view.findViewById(R.id.ckbAddtoComp);
				cbkProd.setChecked(false);
				cbkProd.setEnabled(true);
			}
		}
		
	    super.onResume();
	}
	
}
