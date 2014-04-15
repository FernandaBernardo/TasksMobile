package com.example.tasksmobile;

import com.example.tasksmobile.dao.TaskDao;
import com.example.tasksmobile.model.Task;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Button botao = (Button) findViewById(R.id.botao);
		
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Task task = new Task();
				task.setMateria(((EditText)findViewById(R.id.materia)).getText().toString());
				task.setData(((EditText)findViewById(R.id.data)).getText().toString());
				task.setDescricao(((EditText)findViewById(R.id.descricao)).getText().toString());
				task.setNome(((EditText)findViewById(R.id.nome)).getText().toString());
				
				TaskDao dao = new TaskDao(FormularioActivity.this);
				dao.salva(task);
				dao.close();
				finish();
			}
		});
	}
}
