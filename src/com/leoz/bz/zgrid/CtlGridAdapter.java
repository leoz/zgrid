package com.leoz.bz.zgrid;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoz.bz.zbase.ZFile;
import com.leoz.bz.zfile.CtlBaseAdapter;

public class CtlGridAdapter extends CtlBaseAdapter {
	
    public CtlGridAdapter(Context context, ZFile f) {
    	super(context, f);	
    }
    
	public View getView(int position, View convertView, ViewGroup parent) {
		GridViewHolder holder;
        if (convertView == null) {
        	// Do some preparation
        	convertView = new LinearLayout(mContext);
//        	((LinearLayout) convertView).setGravity(Gravity.CENTER);
        	((LinearLayout) convertView).setOrientation(LinearLayout.VERTICAL);
//       	((LinearLayout) convertView).setLayoutParams(new GridView.LayoutParams(50, 50));
//       	((LinearLayout) convertView).setPadding(48, 48, 48, 48);
        	holder = new GridViewHolder();
        	
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            
            // Create icon
            holder.icon = new ImageView(mContext);
            holder.icon.setId(1002);
            holder.icon.setLayoutParams(new GridView.LayoutParams(96, 96));
            holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.icon.setPadding(8, 8, 8, 8); //           holder.icon.setLayoutParams(new LinearLayout.LayoutParams(48, 48));
//        	holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
//       	holder.icon.setAdjustViewBounds(false);
        	((ViewGroup) convertView).addView(holder.icon, 0);
            
            // Create name
            holder.name = new TextView(mContext);
            holder.name.setGravity(Gravity.CENTER);
            holder.name.setTextSize(10);
            holder.name.setLines(2);
//          holder.name.setEllipsize(TruncateAt.MIDDLE);
            ((ViewGroup) convertView).addView(holder.name, 1, params);
        	
        	////
        	convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }

        // Set icon
//        holder.icon.setImageBitmap(mInfo.getFileBitmap(mFiles[position])); 
        holder.icon.setTag(mFiles[position].getAbsolutePath());
        setViewBitmap(position, holder.icon);
        
        // Set name
        holder.name.setText(mFiles[position].getName());

        return convertView;
	}
	
	static class GridViewHolder {
        ImageView icon;
        TextView name;
    }
}
