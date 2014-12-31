package com.schneider.seapp.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.footer.FooterCallback;
import com.schneider.seapp.fragment.CategoryFragment;
import com.schneider.seapp.fragment.CompareFragment;
import com.schneider.seapp.fragment.UpdateFragment;
import com.schneider.seapp.util.ProductComparePool;

public class MainActivity extends FragmentActivity implements FooterCallback{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		int product_or_update = getIntent().getIntExtra("tab_type",R.id.radioProducts);
		onItemSelected(product_or_update);
	}
	
	
	
	@Override
	public void onItemSelected(int item) {
		
        Fragment fragment = null;
        
        switch(item){
        case R.id.radioProducts: 
        	 fragment = new CategoryFragment();
        	 ProductComparePool.getComparePool().clear();
        	 break;
        
        case R.id.radioUpdate: 
        	fragment = new UpdateFragment();
        	ProductComparePool.getComparePool().clear();
        	break;
        	
        case R.id.radioCompare: 
        	fragment = new CompareFragment();
        	break;
       
        }
        
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_detail_FrameLayout, fragment).commit();
        
	}
	
	public void showAll(View view){
		View vLayout= ((View)view.getParent().getParent().getParent().getParent().getParent()).findViewById(R.id.relComp);
		TableLayout tblLayout = (TableLayout)vLayout.findViewById(R.id.tblCompareResults);
		if (tblLayout instanceof TableLayout) {
			for (int i =0; i< tblLayout.getChildCount(); i++ ) {
				if (tblLayout.getChildAt(i) instanceof TableRow ) {
					TableRow tbrRow = (TableRow)tblLayout.getChildAt(i);
					if ( tbrRow.getId() == R.id.trRow) {
						tbrRow.setVisibility(View.VISIBLE);
					}
				}
			}
		}

	}
	
	public void showDiff(View view){
		View vLayout= ((View)view.getParent().getParent().getParent().getParent().getParent()).findViewById(R.id.relComp);
		TableLayout tblLayout = (TableLayout)vLayout.findViewById(R.id.tblCompareResults);
		if (tblLayout instanceof TableLayout) {
			for (int i =0; i< tblLayout.getChildCount(); i++ ) {
				if (tblLayout.getChildAt(i) instanceof TableRow ) {
					TableRow tbrRow = (TableRow)tblLayout.getChildAt(i);
					if ( tbrRow.getId() == R.id.trRow) {
						tbrRow.setVisibility(View.GONE);
					}
				}
			}
		}
	}
	
	public void closeProd(View view){
		Button btnCloseProd = (Button)view;
		if (btnCloseProd != null && btnCloseProd instanceof Button){
			int productID = Integer.parseInt(btnCloseProd.getTag().toString());
			List<ProductBean> comparePool = ProductComparePool.getComparePool();
			
			for (ProductBean pd: comparePool) {
				if (productID == pd.getProductId()) {
					comparePool.remove(pd);
					break;
				}
			}
			
			int pos = 1;
			if (btnCloseProd.getParent() instanceof RelativeLayout) {
				RelativeLayout rlLayout = (RelativeLayout)btnCloseProd.getParent();
				pos = rlLayout.getId() == R.id.btnGroupProdA ? 1:2;
			}

			View vLayout= ((View)view.getParent().getParent().getParent().getParent().getParent()).findViewById(R.id.relComp);
			TableLayout tblLayout = (TableLayout)vLayout.findViewById(R.id.tblCompareResults);
			
			if (tblLayout instanceof TableLayout) {
				for (int i =0; i< tblLayout.getChildCount(); i++ ) {
					if (tblLayout.getChildAt(i) instanceof TableRow ) {
						TableRow tbrRow = (TableRow)tblLayout.getChildAt(i);
						
						if ( tbrRow.getId() == R.id.tbrControler ) {
							Button btnClose = pos == 1 ?  (Button)tbrRow.findViewById(R.id.btncloseProdA) : (Button)tbrRow.findViewById(R.id.btncloseProdB);
							btnClose.setVisibility(View.GONE);
							Button btnSelect = pos == 1 ?  (Button)tbrRow.findViewById(R.id.btnSelectProdA) : (Button)tbrRow.findViewById(R.id.btnSelectProdB);
							btnSelect.setVisibility(View.VISIBLE);
						} else {
							TextView txtVi = (TextView)tbrRow.getChildAt(pos);
							if (txtVi != null) {
								txtVi.setVisibility(View.INVISIBLE);
							}
						}
					}
				}
			}
		}
	}
	
	public void selectProd (View view){
		finish();
	}
}
