package com.schneider.seapp.fragment;

import java.util.List;

import android.R.integer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.net.sip.SipAudioCall.Listener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schneider.seapp.R;
import com.schneider.seapp.bean.CategoryBean;
import com.schneider.seapp.bean.ProductBean;
import com.schneider.seapp.util.CategoryHelper;

public class UpdateFragment extends Fragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().getActionBar().setTitle(getResources().getString(R.string.update_title));
		List<CategoryBean> categories = getCategoryList();
		List<ProductBean> products1 = categories.get(0).getProducts();
		List<ProductBean> products2 = categories.get(1).getProducts();
		List<ProductBean> products3 = categories.get(2).getProducts();
		
		RelativeLayout rlUpdate= (RelativeLayout)inflater.inflate(R.layout.fragment_update, container, false);
		TextView textNoteTextView = (TextView)rlUpdate.findViewById(R.id.txtUpdate);
		textNoteTextView.setText(this.getResources().getString(R.string.update_note_currently_cagetory));
		Button btnUpdateButton = (Button)rlUpdate.findViewById(R.id.btnUpdate);
		btnUpdateButton.setText(this.getResources().getString(R.string.update_note_update_selected_category));
		
		TextView textViewCat1= (TextView)rlUpdate.findViewById(R.id.txtCat1);
//		textViewCat1.setText(this.getResources().getString(R.string.update_list_categry_name1));
		String textString = categories.get(0).getCategoryName() + "  ("+products1.size()+") ";
		textViewCat1.setText(textString);
		
		TextView textViewCat2= (TextView)rlUpdate.findViewById(R.id.txtCat2);
//		textViewCat2.setText(this.getResources().getString(R.string.update_list_categry_name2));
		textString= categories.get(1).getCategoryName() + "  ("+products2.size()+") ";
		textViewCat2.setText(textString);
		
		TextView textViewCat3= (TextView)rlUpdate.findViewById(R.id.txtCat3);
//		textViewCat1.setText(this.getResources().getString(R.string.update_list_categry_name1));
		textString = categories.get(2).getCategoryName() + "  ("+products3.size()+") ";
		textViewCat3.setText(textString);

		return rlUpdate;
	}
	
	public List<CategoryBean> getCategoryList(){
		return CategoryHelper.read(this.getActivity().getAssets(), this.getResources().getString(R.string.category_xml_file));
	}
	
}
