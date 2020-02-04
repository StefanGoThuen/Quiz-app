package com.example.oblig1.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.oblig1.HelperFunctions;
import com.example.oblig1.R;

public class OwnerDialog extends Dialog {

    private OnSingleInput listener;
    private String name;
    public OwnerDialog(@NonNull Context context, OnSingleInput listener, String name) {
        super(context);
        this.listener = listener;
        this.name = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_owner);

        final EditText editText = findViewById(R.id.ownerDialogEditText);
        editText.setText(name);
        Button button = findViewById(R.id.ownerDialogButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                if (input.equals("")) {
                    listener.onEmptyInput();
                } else {
                    HelperFunctions.hideKeyboard(OwnerDialog.this);
                    listener.onInput(input);
                    OwnerDialog.this.dismiss();
                }
            }
        });

    }

}


