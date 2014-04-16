package com.example.tasksmobile.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tasksmobile.model.Task;

public class TaskDao extends SQLiteOpenHelper{
	private static final String DATABASE = "TasksMobile";
	private static final String TABELA = "tasks";
	private static final String[] COLUNAS= {"id", "nome", "materia", "descricao", "data"};
	
	public TaskDao(Context context) {
		super(context, DATABASE, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABELA + " " +
				"(id INTEGER PRIMARY KEY, " +
				"nome TEXT UNIQUE NOT NULL, " +
				"materia TEXT, " +
				"descricao TEXT, " +
				"data TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABELA + ";");		
	}
	
	public void salva(Task task) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = toContentValues(task);
		db.insert(TABELA, null, values);
	}
	
	public void deletar(Task task) {
		String[] args = {task.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}
	
	public void atualiza(Task task) {
		String[] args = {task.getId().toString()};
		ContentValues values = toContentValues(task);
		getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<Task> getListaOrdenada() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Cursor cursor = getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Task task = new Task();
			task.setId(cursor.getLong(0));
			task.setNome(cursor.getString(1));
			task.setMateria(cursor.getString(2));
			task.setDescricao(cursor.getString(3));
			task.setData(cursor.getString(4));
			tasks.add(task);
		}
		cursor.close();
		
		Collections.sort(tasks, new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});
		
		return tasks;
	}

	private ContentValues toContentValues(Task task) {
		ContentValues values = new ContentValues();
		values.put("nome", task.getNome());
		values.put("materia", task.getMateria());
		values.put("descricao", task.getDescricao());
		values.put("data", task.getData());
		return values;
	}
}
