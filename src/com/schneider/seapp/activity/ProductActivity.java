package com.schneider.seapp.activity;


import com.schneider.seapp.R;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.util.ImageAdapter;
import com.schneider.seapp.util.ProductHelper;

import android.os.Bundle;
import android.view.Window;
import android.widget.Gallery;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class ProductActivity extends GenericActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		int productId = Integer.parseInt(getIntent().getStringExtra("product_id"));
		
		ProductBean product = ProductHelper.getProductById(getAssets(), new StringBuilder("product_").append(productId).append(".xml").toString(), productId);
		String productName = product.getProductName();
		String[] images = product.getImageList();
		int[] mImageResourceIds= new int[images.length];
		for(int i=0; i<images.length; i++) {
			mImageResourceIds[i] = getResources().getIdentifier(images[i], "drawable", "com.schneider.seapp");
		}
		int galleryWidth = Integer.parseInt(getResources().getString(R.string.galleryWidth));
		int galleryHeight = Integer.parseInt(getResources().getString(R.string.galleryHeight));		
		String productPriceTxt = product.getPriceTxt();
		String productModel = product.getModel();
		String productFunction = product.getFunction();
		String productDimension =  Double.toString(product.getDimension());
		String productAttachment = product.getAttachment();
		String productSpec = product.getSpec();
		String productCurrentTxt = product.getCurrentTxt();		
		

		getActionBar().setTitle(R.string.productDetails);
		
		TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText(productName);
		Gallery imgGallery = (Gallery)this.findViewById(R.id.imgGallery);
		ImageAdapter adapter = new ImageAdapter(ProductActivity.this, mImageResourceIds,galleryWidth,galleryHeight);
		imgGallery.setAdapter(adapter);		
		TextView price = (TextView)findViewById(R.id.price);
		price.setText(productPriceTxt);
		TextView model = (TextView)findViewById(R.id.model);
		model.setText(new StringBuilder(getResources().getString(R.string.model)).append(productModel).toString());
		TextView function = (TextView)findViewById(R.id.function);
		function.setText(new StringBuilder(getResources().getString(R.string.function)).append(productFunction).toString());
		TextView dimension = (TextView)findViewById(R.id.dimension);
		dimension.setText(new StringBuilder(getResources().getString(R.string.dimension)).append(productDimension).toString());
		TextView attachment = (TextView)findViewById(R.id.attachment);
		attachment.setText(new StringBuilder(getResources().getString(R.string.attachment)).append(productAttachment).toString());
		TextView spec = (TextView)findViewById(R.id.spec);
		spec.setText(new StringBuilder(getResources().getString(R.string.spec)).append(productSpec).toString());
		TextView currentTxt = (TextView)findViewById(R.id.currentTxt);
		currentTxt.setText(new StringBuilder(getResources().getString(R.string.current)).append(productCurrentTxt).toString());	
				
	}
}
