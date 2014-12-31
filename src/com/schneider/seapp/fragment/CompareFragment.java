package com.schneider.seapp.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.util.ProductComparePool;


public class CompareFragment extends Fragment {
	
	//private final int[] backgroundColors = {R.color.seGrey, R.color.seLightYellow, R.color.seLightBlue};
	//private static final String fILE_PREFIX = "product_";
	//private static int prodidA = -1;
	//private static int prodidB = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle(getResources().getString(R.string.compare_title));
		
		return rendingTable(inflater, container);
	}
	
	private RelativeLayout rendingTable(LayoutInflater inflater, ViewGroup container) {
		
		RelativeLayout rl= (RelativeLayout)inflater.inflate(R.layout.fragment_compare, container, false);
		TextView txtNote = (TextView)rl.findViewById(R.id.txtNote);
		TableLayout tblCompareResults = (TableLayout)rl.findViewById(R.id.tblCompareResults);
		
		List<ProductBean> comparePool = ProductComparePool.getComparePool();
		//comparePool.clear();
		
		//测试数据
		/*ProductBean pb1 = ProductHelper.getProductById(getActivity().getAssets(), 
				new StringBuilder(fILE_PREFIX).append("1230").append(".xml").toString(), 1230);
		ProductBean pb2 = ProductHelper.getProductById(getActivity().getAssets(), 
				new StringBuilder(fILE_PREFIX).append("1201").append(".xml").toString(), 1201);
		comparePool.add(pb1);
		comparePool.add(pb2);*/
		//

		if(comparePool.size()<2){
			txtNote.setText(this.getResources().getString(R.string.compare_note_product__more_than_2));
			tblCompareResults.setVisibility(View.INVISIBLE);
		}else{
			/*
			//product name
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_name));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getProductName");
			//price
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_price));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getPriceUnit", "getPrice");
			//function
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_function));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getFunction");
			//dimension
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_dimension));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getDimension");
			//attachment
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_attachment));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getAttachment");
			//spec
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_spec));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getSpec");
			//current
			createHeaderPerAttr(tblCompareResults, getResources().getString(R.string.column_prod_current));
			showCompareDataPerAttr(tblCompareResults, comparePool, "getCurrent", "getCurrentUnit");
			*/
						
			HashMap<Integer, String> hm = new HashMap<Integer, String>();
			hm.put(R.string.column_prod_name, "getProductName");
			hm.put(R.string.column_prod_price, "getPrice");
			hm.put(R.string.column_prod_function, "getFunction");
			hm.put(R.string.column_prod_dimension, "getDimension");
			hm.put(R.string.column_prod_attachment, "getAttachment");
			hm.put(R.string.column_prod_spec, "getSpec");
			hm.put(R.string.column_prod_current, "getCurrent");
			hm.put(R.string.column_prod_id, "getProductId");

			String prodA = null , prodB = null, prodName = null;
			String valueA = null, valueB = null;
			int prodA_id = 0, prodB_id = 0;
			for(Entry<Integer, String> entry : hm.entrySet()) {
			    Integer key = entry.getKey();
			    String methodName = entry.getValue();
			    
			    if (key != R.string.column_prod_name && key != R.string.column_prod_id) {
			    	prodName = getResources().getString(key);
			    }
			    
			    int count = 1;
			    valueA = "";
			    valueB = "";
			    for (ProductBean pd : comparePool) {
			    	try {
			    		if (key == R.string.column_prod_name) {
			    			if (count == 1) {
			    				prodA = String.valueOf(pd.getClass().getDeclaredMethod(methodName).invoke(pd));
			    				++count;
			    			} else {
			    				prodB = String.valueOf(pd.getClass().getDeclaredMethod(methodName).invoke(pd));
			    			}
			    			continue;
					    } else if (key == R.string.column_prod_id){
					    	if (count == 1) {
					    		prodA_id = (Integer) pd.getClass().getDeclaredMethod(methodName).invoke(pd);
					    		++count;
					    	} else {
					    		prodB_id = (Integer) pd.getClass().getDeclaredMethod(methodName).invoke(pd);
					    	}
					    	continue;
					    } else {
					    	if (count == 1) {
					    		++count;
					    		valueA = String.valueOf(pd.getClass().getDeclaredMethod(methodName).invoke(pd));
					    	} else {
					    		valueB = String.valueOf(pd.getClass().getDeclaredMethod(methodName).invoke(pd));
					    	}
					    }
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			    
			    TableRow trRow = null;
			    if (valueA.length()>0 && valueB.length()>0 &&  valueA.equals(valueB)) {
			    	trRow = (TableRow)inflater.inflate(R.layout.compare_table_row, tblCompareResults, false);
			    } else {
			    	trRow = (TableRow)inflater.inflate(R.layout.compare_table_row_diff, tblCompareResults, false);
			    }
			    
			    if (key != R.string.column_prod_name  && key != R.string.column_prod_id) {
			    	TextView txtAttrName = (TextView)trRow.findViewById(R.id.tvAttrName);
				    txtAttrName.setText(prodName);
				    TextView txtAttrValueA = (TextView)trRow.findViewById(R.id.tvAttrValueA);
				    txtAttrValueA.setText(valueA);
				    TextView txtAttrValueB = (TextView)trRow.findViewById(R.id.tvAttrValueB);
				    txtAttrValueB.setText(valueB);
			    	tblCompareResults.addView(trRow);
			    }
			}
			
			if (prodA != null && prodA.length()>0) {
				TextView txtProdA = (TextView)tblCompareResults.findViewById(R.id.txtProdA);
				txtProdA.setText(prodA);
			}
			
			if (prodB != null && prodB.length()>0) {
				TextView txtProdB = (TextView)tblCompareResults.findViewById(R.id.txtProdB);
				txtProdB.setText(prodB);
			}
			
			if (prodA_id > 0) {
				Button btnCloseProdA = (Button)tblCompareResults.findViewById(R.id.btncloseProdA);
				btnCloseProdA.setTag(prodA_id);
				btnCloseProdA.setVisibility(View.VISIBLE);
				Button btnSelectProdA = (Button)tblCompareResults.findViewById(R.id.btnSelectProdA);
				btnSelectProdA.setTag(prodA_id);
				btnSelectProdA.setVisibility(View.GONE);
			} else {
				Button btnCloseProdA = (Button)tblCompareResults.findViewById(R.id.btncloseProdA);
				btnCloseProdA.setVisibility(View.GONE);
				Button btnSelectProdA = (Button)tblCompareResults.findViewById(R.id.btnSelectProdA);
				btnSelectProdA.setVisibility(View.VISIBLE);
			}
			
			if (prodB_id > 0) {
				Button btnCloseProdB = (Button)tblCompareResults.findViewById(R.id.btncloseProdB);
				btnCloseProdB.setTag(prodB_id);
				btnCloseProdB.setVisibility(View.VISIBLE);
				Button btnSelectProdB = (Button)tblCompareResults.findViewById(R.id.btnSelectProdB);
				btnSelectProdB.setTag(prodB_id);
				btnSelectProdB.setVisibility(View.GONE);
			} else {
				Button btnCloseProdB = (Button)tblCompareResults.findViewById(R.id.btncloseProdB);
				btnCloseProdB.setVisibility(View.GONE);
				Button btnSelectProdB = (Button)tblCompareResults.findViewById(R.id.btnSelectProdB);
				btnSelectProdB.setVisibility(View.VISIBLE);
			}
			
			tblCompareResults.setVisibility(View.VISIBLE);
		}
		
		return rl;
	}
	
/*	
	private void showCompareDataPerAttr(TableLayout tl, List<ProductBean> comparePool,String... methodName){
		
		int countOfProducts = comparePool.size();
		
		for(int i=0;i<countOfProducts;i++){
			TableRow row_c = createTableRow();
			TextView txt_c1 = createTextView(150,1,i);
			TextView txt_c2 = createTextView(300,2,i);
			
			String model = comparePool.get(i).getModel();
			txt_c1.setText(model);
			
			String colValue = "";
			try {
				if(methodName.length==1){
					colValue = String.valueOf(comparePool.get(i).getClass().getDeclaredMethod(methodName[0]).invoke(comparePool.get(i)));
				}else if(methodName.length==2){
					colValue = new StringBuilder(comparePool.get(i).getClass().getDeclaredMethod(methodName[0]).invoke(comparePool.get(i)).toString())
									.append(comparePool.get(i).getClass().getDeclaredMethod(methodName[1]).invoke(comparePool.get(i))).toString();
				}
					
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			txt_c2.setText(colValue);
			
			row_c.addView(txt_c1);
			row_c.addView(txt_c2);
			tl.addView(row_c);
		}
		
	}
	
	private void createHeaderPerAttr(TableLayout tl, String attrName){
		
		TableRow row = createTableRow();
		
		TextView txt_c1 = createTextView(100,1,0);
		TextView txt_c2 = createTextView(220,2,0);
		
		txt_c2.setText(attrName);
		txt_c2.setGravity(Gravity.CENTER);
		
		txt_c1.setBackgroundColor(getResources().getColor(R.color.seBlack));
		txt_c1.setTextColor(Color.WHITE);
		
		txt_c2.setBackgroundColor(getResources().getColor(R.color.seBlack));
		txt_c2.setTextColor(Color.WHITE);
		
		row.addView(txt_c1);
		row.addView(txt_c2);
		tl.addView(row);
	}
	
	private TableRow createTableRow(){
		TableRow tr = new TableRow(getActivity());
		
		//导入LayoutParams要特别注意其所在的包，否则会导致表格不显示
		LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		tr.setLayoutParams(params);
		
		return tr;
	}*/
	
	/**
	 * 
	 * @param width
	 * @param index : 1 or 2. 1 = the first column of the table, 2 - the second column 
	 * @param position : the position of the product in the compare pool.
	 * @return
	 */
/*
	private TextView createTextView(int width, int index, int position){
		TextView tv = new TextView(getActivity());
		
		LayoutParams params = new LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
		if(index==1){
			params.setMargins(0, 0, 0, 1);
		}
		else {
			params.setMargins(1, 0, 0, 1);
		}

		tv.setBackgroundColor(getResources().getColor(backgroundColors[position%3]));
		
		tv.setLayoutParams(params);
		tv.setTextSize(12);
		
		return tv;
	}
*/
	
}
