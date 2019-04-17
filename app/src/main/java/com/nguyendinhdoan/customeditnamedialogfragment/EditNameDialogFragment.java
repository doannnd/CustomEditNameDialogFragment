package com.nguyendinhdoan.customeditnamedialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditNameDialogFragment extends DialogFragment implements View.OnClickListener, TextView.OnEditorActionListener {

    public static final String TITLE_KEY = "TITLE_KEY";

    private EditText yourNameEditText;
    private Button submitNameButton;

    private EditNameDialogListener listener;

    public static EditNameDialogFragment newInstance(String dialogTitle) {
        EditNameDialogFragment editNameDialogFragment = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, dialogTitle);
        editNameDialogFragment.setArguments(args);
        return editNameDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditNameDialogListener) {
            listener = (EditNameDialogListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement EditNameDialogListener ");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_edit_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupUI();
        addEvents();
    }

    private void initViews(View view) {
        yourNameEditText = view.findViewById(R.id.your_name_edit_text);
        submitNameButton = view.findViewById(R.id.submit_name_button);
    }

    private void setupUI() {
        if (getArguments() != null) {

            // get title from bundle and set title for dialog
            String titleDialog = getArguments().getString(TITLE_KEY);
            getDialog().setTitle(titleDialog);
            setCancelable(false);

            // focus edit text and show soft keyboard automatically
            yourNameEditText.requestFocus();
            if (getDialog().getWindow() != null) {
                getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        }
    }

    private void addEvents() {
        yourNameEditText.setOnEditorActionListener(this);
        submitNameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_name_button) {
            displayYourName();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            displayYourName();
            return true;
        }
        return false;
    }

    private void displayYourName() {
        String yourName = yourNameEditText.getText().toString();
        listener.onFinishEditNameListener(yourName);
        // close the dialog and back to the parent activity
        dismiss();
    }

    public interface EditNameDialogListener {
        void onFinishEditNameListener(String yourName);
    }
}
