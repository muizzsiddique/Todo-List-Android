package com.example.todolist;

/* TODO:
    - make text more transparent or striked when complete
    - implement delete function

 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = findViewById(R.id.taskList);
        EditText editTextTask = findViewById(R.id.editTextTask);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTaskListItem(editTextTask.getText().toString());
                editTextTask.setText("");
            }
        });

//        for (int i = 0; i < 3; i++) {
//            createTaskListItem("Hello " + i);
//        }
    }

    private int createTaskListItem(String text) {
        ConstraintLayout cl = new ConstraintLayout(this);
        cl.setId(View.generateViewId());
        cl.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));

        CheckBox cb = new CheckBox(this);
        cb.setId(View.generateViewId());
        cb.setLayoutParams(new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        cb.setWidth(0);
        cb.setText(text);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // strike-through or make transparent
                } else {
                    // use original text formatting
                }
            }
        });

        Button b = new Button(this);
        b.setId(View.generateViewId());
        b.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));
        b.setText("Delete");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete this item off of the list
            }
        });

        cl.addView(cb);
        cl.addView(b);

        ConstraintSet cs = new ConstraintSet();
        cs.clone(cl);
        cs.connect(cb.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        cs.connect(cb.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        cs.connect(cb.getId(), ConstraintSet.RIGHT, b.getId(), ConstraintSet.LEFT);

        cs.connect(b.getId(), ConstraintSet.BASELINE, cb.getId(), ConstraintSet.BASELINE);
        cs.connect(b.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);

        cs.applyTo(cl);
        taskList.addView(cl);
        return cl.getId();
    }
}

/*
<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/taskItem1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <CheckBox
        android:id="@+id/checkboxTask1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/buttonTask1"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:text="Example task"/>

    <Button
        android:id="@+id/buttonTask1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintBaseline_toBaselineOf="@+id/checkboxTask1"
        android:text="Delete"/>
</androidx.constraintlayout.widget.ConstraintLayout>
 */