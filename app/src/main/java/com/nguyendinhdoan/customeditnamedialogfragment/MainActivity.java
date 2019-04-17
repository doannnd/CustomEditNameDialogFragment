package com.nguyendinhdoan.customeditnamedialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        EditNameDialogFragment.EditNameDialogListener {

    public static final String EDIT_NAME_DIALOG_TAG = "EDIT_NAME_DIALOG_TAG";
    private Button showDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialogButton = findViewById(R.id.show_dialog_button);
        showDialogButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.show_dialog_button) {
            showEditNameDialog();
        }
    }

    private void showEditNameDialog() {
        String editNameDialogTitle = "Edit Name";
        EditNameDialogFragment editNameDialogFragment = EditNameDialogFragment.newInstance(editNameDialogTitle);
        editNameDialogFragment.show(getSupportFragmentManager(), EDIT_NAME_DIALOG_TAG);
    }

    @Override
    public void onFinishEditNameListener(String yourName) {
        Toast.makeText(this, "name: " + yourName, Toast.LENGTH_SHORT).show();
    }
}
