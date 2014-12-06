package com.envia.p3_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FirstArticle extends Fragment implements OnClickListener{

	onSendData myInterface;

	public interface onSendData{
		public void shareMyData(String companyName, String color);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.first_article, container, false);
	}

	@Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    
    // This makes sure that the container activity has implemented
    // the callback interface. If not, it throws an exception
    try {
        myInterface = (onSendData) activity;
    } catch (ClassCastException e) {
        throw new ClassCastException(activity.toString()
                + " la clase no ha implementado la interfaz");
    }
  }

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Button sendButton = (Button) getActivity().findViewById(R.id.send_data_btn);
		sendButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String companyName = ((EditText) getActivity().findViewById(R.id.companyName)).getText().toString();
		
		RadioGroup radioColorGroup = (RadioGroup) getActivity().findViewById(R.id.radio_group_colors);
		
		int selectedId = radioColorGroup.getCheckedRadioButtonId();
		
		String color = ((RadioButton) getActivity().findViewById(selectedId)).getText().toString();
		
		myInterface.shareMyData(companyName,color);
	}

}
