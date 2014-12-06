package com.envia.p3_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class SecondArticle extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.second_article, container, false);
	}

	public void updateData(String companyName, String color) {
		// TODO Auto-generated method stub
		
		TextView labelCompany = (TextView) getActivity().findViewById(R.id.company_label_result);
		
		labelCompany.setText(companyName);
		
		labelCompany.setTextColor(getResources().getColor(R.color.white));
		
		switch (color) {
		case "Rojo":
			getView().setBackgroundColor(getResources().getColor(R.color.red));
			break;
		case "Verde":
			getView().setBackgroundColor(getResources().getColor(R.color.green));
			break;
		case "Azul":
			getView().setBackgroundColor(getResources().getColor(R.color.blue));
			break;
		default:
			getView().setBackgroundColor(getResources().getColor(R.color.white));
			labelCompany.setTextColor(getResources().getColor(R.color.black));
			break;
		}
	}
}
