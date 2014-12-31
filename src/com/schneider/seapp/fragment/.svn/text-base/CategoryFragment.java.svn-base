package com.schneider.seapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.schneider.seapp.R;
import com.schneider.seapp.activity.ProductListActivity;
import com.schneider.seapp.bean.CategoryBean;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.listview.CategoryListItem;
import com.schneider.seapp.listview.CategoryListItemAdapter;
import com.schneider.seapp.util.CategoryHelper;

public class CategoryFragment extends Fragment {

	private List<CategoryBean> categories;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		RelativeLayout rl= (RelativeLayout)inflater.inflate(R.layout.fragment_category, container, false);
		
		getActivity().getActionBar().setTitle(getResources().getString(R.string.category_title));
		
		ListView listCategory = (ListView)rl.findViewById(R.id.listCategory);
		categories = getCategoryList();
		CategoryListItemAdapter adapter = new CategoryListItemAdapter(container.getContext(),R.layout.category_list_item,getListItems(categories));
		adapter.setActivity(getActivity());
		listCategory.setAdapter(adapter);
		listCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(CategoryFragment.this.getActivity(),ProductListActivity.class);
				CategoryBean categoryBean =categories.get(position);
				intent.putExtra("category", categoryBean);
				CategoryFragment.this.getActivity().startActivityForResult(intent,9001);
				
			}
		});
		
		return rl;
	}
	
	public List<CategoryBean> getCategoryList(){
		return CategoryHelper.read(this.getActivity().getAssets(), this.getResources().getString(R.string.category_xml_file));
	}
	
	private List<CategoryListItem> getListItems(List<CategoryBean> categories){
		List<CategoryListItem> cats = new ArrayList<CategoryListItem>();
		
		if(null!=categories){
			
			for(CategoryBean theCat : categories){
				CategoryListItem item = new CategoryListItem();
				String categoryName = new StringBuilder(theCat.getCategoryName()).append("(").append(theCat.getProducts().size()).append(")").toString();
				item.setCategoryName(categoryName);
				item.setCategoryId(theCat.getCategoryId());
				
				if(theCat.getProducts().size()>0){
					ProductBean pb1 = theCat.getProducts().get(0);
					item.setProduct1Id(pb1.getProductId());
					item.setProduct1Name(pb1.getProductName());
					item.setProduct1Price(pb1.getPrice());
					item.setProduct1Image(getResources().getIdentifier(new StringBuilder("product_").append(pb1.getProductId()).append("_small").toString(), "drawable", "com.schneider.seapp"));
					item.setProduct1PriceUnit(pb1.getPriceUnit());
					
					if(theCat.getProducts().size()>1){
						ProductBean pb2 = theCat.getProducts().get(1);
						item.setProduct2Id(pb2.getProductId());
						item.setProduct2Name(pb2.getProductName());
						item.setProduct2Price(pb2.getPrice());
						item.setProduct2Image(getResources().getIdentifier(new StringBuilder("product_").append(pb2.getProductId()).append("_small").toString(), "drawable", "com.schneider.seapp"));
						item.setProduct2PriceUnit(pb2.getPriceUnit());
					}
				}
				
				cats.add(item);
			}
			
		}
		
		return cats;
	}
	
}
