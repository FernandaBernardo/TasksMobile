package com.example.tasksmobile;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tasksmobile.dao.TaskDao;
import com.example.tasksmobile.model.Task;
import com.example.tasksmobile.swipe.SwipeTouchListener;

public class ListTasksActivity extends Activity {

	private ListTasksAdapter adapter;
	private ListView tasksView;
	private List<Task> tasks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_tasks);

		tasksView = (ListView) findViewById(R.id.tasks);
		registerForContextMenu(tasksView);

		SwipeTouchListener touchListener = new SwipeTouchListener(tasksView, new SwipeTouchListener.DismissCallbacks() {
				@Override
				public boolean canDismiss(int position) {
					return true;
				}

				@Override
				public void onDismiss(ListView listView, int[] reverseSortedPositions) {
					TaskDao dao = new TaskDao(ListTasksActivity.this);
					for (int position : reverseSortedPositions) {
						dao.deletar(tasks.get(position));
					}
					dao.close();
					carregaLista();
				}
			});
		tasksView.setOnTouchListener(touchListener);
        tasksView.setOnScrollListener(touchListener.makeScrollListener());
        
        tasksView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				Intent description = new Intent(ListTasksActivity.this, TaskDescriptionActivity.class);
				description.putExtra("task", tasks.get(pos));
				startActivity(description);
			}
		});
    }

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		TaskDao dao = new TaskDao(this);
		tasks = dao.getLista();

		dao.close();
		adapter = new ListTasksAdapter(this, tasks);
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

		if (itemId == R.id.menu_novo) {
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
