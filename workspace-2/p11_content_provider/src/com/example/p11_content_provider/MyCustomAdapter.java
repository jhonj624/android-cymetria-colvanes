package com.example.p11_content_provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends ArrayAdapter<MyWord> {
	
	private Context myContext;
	private MyWord[] myWords;
	
	public MyCustomAdapter(Context context, MyWord[] words) {
		super(context, R.layout.word_row, words);

		this.myContext = context;
		this.myWords = words;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater myInflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = myInflater.inflate(R.layout.word_row, parent, false);
		TextView wordName = (TextView)rowView.findViewById(R.id.wordName);
		TextView wordId = (TextView)rowView.findViewById(R.id.wordId);
		TextView wordLocale = (TextView)rowView.findViewById(R.id.wordLocale);
		
		wordName.setText(myWords[position].word);
		wordId.setText(String.valueOf(myWords[position]._id));
		wordLocale.setText(myWords[position].locale);
		
		return rowView;
		
	}
}
