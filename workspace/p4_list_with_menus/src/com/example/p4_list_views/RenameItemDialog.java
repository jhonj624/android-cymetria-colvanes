package com.example.p4_list_views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RenameItemDialog extends DialogFragment {

	public interface Renamer{
		public void rename(int pos, String newName);
	}
	
	public String theItemName;
	public int theItemPosition;
	public CountriesFragment theFragment;
	private EditText textEdited;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateDialog(savedInstanceState);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.edit_dialog_text);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View viewInDialog = inflater.inflate(R.layout.edit_item, null);
		textEdited = (EditText) viewInDialog.findViewById(R.id.editTextItem);
		textEdited.setText(theItemName);
		
		builder.setView(viewInDialog);
		builder.setTitle(R.string.edit_dialog_title);
		builder.setPositiveButton(R.string.edit_dialog_positive_button, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Renamer myInterface = (Renamer)theFragment;
				myInterface.rename(theItemPosition, textEdited.getText().toString());
			}
		});
		builder.setNegativeButton(R.string.edit_dialog_negative_button, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Cancelando...", Toast.LENGTH_SHORT).show();
			}
		});
		
		return builder.create();
		
	}

}
