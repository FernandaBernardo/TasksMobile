package com.example.tasksmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tasksmobile.model.Task;

public class TaskDescriptionActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_description);
		
		Intent intent = getIntent();
		Task task = (Task) intent.getExtras().getSerializable("task");
		
		TextView nome = (TextView) findViewById(R.id.nome_desc);
		TextView materia = (TextView) findViewById(R.id.materia_desc);
		TextView data = (TextView) findViewById(R.id.data_desc);
		TextView descricao = (TextView) findViewById(R.id.descricao_desc);
		
		nome.setText(task.getNome());
		materia.setText(task.getMateria());
		data.setText(task.getData());
		descricao.setText(task.getDescricao());
	}
}
