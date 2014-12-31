package com.schneider.seapp.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class ImageAdapter extends BaseAdapter {

	  private Context mContext;       
	  private int[] mImageResourceIds;
	  private int galleryWidth;
	  private int galleryHeight;
	           
	          
	          public ImageAdapter(Context context,int[] mImageResourceIds,int galleryWidth,int galleryHeight) {
	              this.mContext = context;
	              this.mImageResourceIds = mImageResourceIds;
	              this.galleryWidth = galleryWidth;
	              this.galleryHeight= galleryHeight;
	              
	        }
	        
	         
	         public int getCount() {
	              return mImageResourceIds.length;
	         }
	  
	         
	          public Object getItem(int position) {
	            return mImageResourceIds[position];
	          }
	 
	         
	         public long getItemId(int position) {
	              return position;
	          }
	  
	         
	          public View getView(int position, View convertView, ViewGroup parent) {
	             ImageView imageView = new ImageView(mContext);
	             imageView.setImageResource(mImageResourceIds[position]);
	             imageView.setLayoutParams(new Gallery.LayoutParams(galleryWidth, galleryHeight));
	             imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	             return imageView;
	          }
	         
	      }