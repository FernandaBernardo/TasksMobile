package com.example.tasksmobile;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tasksmobile.model.Task;

public class ListTasksAdapter extends BaseAdapter{
	private final List<Task> tasks;
	private final Activity activity;
	
	public ListTasksAdapter(Activity activity, List<Task> tasks) {
		this.activity = activity;
		this.tasks = tasks;
	}
	
	@Override
	public int getCount() {
		return tasks.size();
	}

	@Override
	public Object getItem(int pos) {
		return tasks.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return tasks.get(pos).getId();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		View view = activity.getLayoutInflater().inflate(R.layout.list_item, null);
		
		Task task = tasks.get(pos);
		
		TextView data = (TextView) view.findViewById(R.id.data);
		TextView nome = (TextView) view.findViewById(R.id.nome);
		
		data.setText(task.getData());
		nome.setText(task.getNome());
		
		return view;
	}
	
}
