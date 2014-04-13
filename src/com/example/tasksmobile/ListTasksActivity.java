package com.example.tasksmobile;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tasksmobile.dao.TaskDao;
import com.example.tasksmobile.model.Task;

public class ListTasksActivity extends Activity {

	private ArrayAdapter<String> adapter;
	private ListView tasksView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_tasks);

		tasksView = (ListView) findViewById(R.id.tasks);
		registerForContextMenu(tasksView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}
	
	private void carregaLista() {
		TaskDao dao = new TaskDao(this);
		List<Task> tasks = dao.getLista();
		List<String> names = new ArrayList<String>();
		for (Task task : tasks) {
			names.add(task.getNome());
		}
		
		dao.close();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
		tasksView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		int itemId = item.getItemId();
		
		if(itemId == R.id.menu_novo) {
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
