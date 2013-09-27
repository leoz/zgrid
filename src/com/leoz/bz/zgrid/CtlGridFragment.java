package com.leoz.bz.zgrid;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.leoz.bz.zbase.ZFile;
import com.leoz.bz.zclient.ZCltSettings;
import com.leoz.bz.zfile.FileOpen;

public class CtlGridFragment extends Fragment {

	private static final String TAG = "CtlGridFragment"; /// FIX ME
	
	private CtlGridAdapter adapter;
	private GridView view;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		FileOpen.setActivity(activity);
		createInternalView();
		setFragmentData(ZCltSettings.INSTANCE.getAppDir());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return view;
	}
	
	private void createInternalView() {
		view = new GridView (getActivity());
		view.setPadding(10, 10, 10, 10);
		view.setVerticalSpacing(10);
		view.setHorizontalSpacing(10);
		view.setNumColumns(GridView.AUTO_FIT);
		view.setColumnWidth(96 + 8); /// FIX ME
		view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		view.setGravity(Gravity.CENTER);
		setFragmentData (null);
		view.setOnItemClickListener(itemClickListener);		
	}

	public void setFragmentData (ZFile f) {

    	if (view != null) {
	        if (adapter == null) {
		    	adapter = new CtlGridAdapter(getActivity(), f);
		    	view.setAdapter(adapter);
	        }
	        else {
	        	view.setAdapter(null);
	        	adapter.clearData();
	        	adapter.setData(f);
	        	view.setAdapter(adapter);
	        }
	    }
	}
	
	private OnItemClickListener itemClickListener = new OnItemClickListener() {
		
	    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    	
			ZFile f = (ZFile) adapter.getItem(position);
			Log.v(TAG, "onItemClick: " + f.getAbsolutePath()); /// FIX ME
			FileOpen.fileSelected (f, ((Fragment)parent.getParent()).getId());
	    }
	};
	
    public void updateVisibleItem(String file, Bitmap b) {
        GridView lv = view;
        int childCount = lv.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View v = lv.getChildAt(i);
            ImageView iv = (ImageView) v.findViewById(1002);
            String name = (String) iv.getTag();
            if (name.equals(file)) {
            	iv.setImageBitmap(b);
            }
        }
    }
}
