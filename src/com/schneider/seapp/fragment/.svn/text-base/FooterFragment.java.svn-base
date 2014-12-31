package com.schneider.seapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.schneider.seapp.R;
import com.schneider.seapp.footer.FooterCallback;

public class FooterFragment extends Fragment {
	
	private FooterCallback callbacks = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        if(!(activity instanceof FooterCallback)) {
            throw new IllegalStateException("Activity must implements fragment's callbacks !");
        }
        callbacks = (FooterCallback) activity;
    }
   
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioGroup radioGroup = (RadioGroup) inflater.inflate(R.layout.fragment_footer, container, false).findViewById(R.id.rgFooter);
        
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				callbacks.onItemSelected(checkedId);
			}
        	
        });
        return radioGroup;
    }
	
}
