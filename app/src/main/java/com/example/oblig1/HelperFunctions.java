package com.example.oblig1;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HelperFunctions {

    public static void hideKeyboard(Dialog dialog) {
        // Check if no view has focus:
        View view = dialog.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
