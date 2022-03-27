package com.baldware.feedmonty.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.baldware.feedmonty.Activities.FullScreenActivity;
import com.baldware.feedmonty.Utils.HistoryHandler;
import com.baldware.feedmonty.Activities.MainActivity;
import com.baldware.feedmonty.R;

public class HintDialogFragment extends DialogFragment {

    /**
     * Constructor
     */
    public HintDialogFragment() {
        // Empty constructor required
    }

    /**
     * Returns a new dialog fragment instance
     * @param _hintKey The dialog fragments history handler key
     * @param _hintText The dialog fragments text
     * @return The new dialog fragment instance
     */
    public static HintDialogFragment newInstance(String _hintKey, String _hintText) {
        HintDialogFragment hintDialogFragment = new HintDialogFragment();
        Bundle args = new Bundle();
        args.putString("hintKey", _hintKey);
        args.putString("hintText", _hintText);
        hintDialogFragment.setArguments(args);
        return hintDialogFragment;
    }

    /**
     * The dialog fragments on create method
     * @param _savedInstanceState The saved instance state
     * @return The created dialog fragment
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle _savedInstanceState) {
        String hintKey = getArguments().getString("hintKey");
        String hintText = getArguments().getString("hintText");

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.fragment_hint, null);
        TextView textView = dialogView.findViewById(R.id.hint_text);
        textView.setText(hintText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.hint_title)
                .setView(dialogView)
                .setPositiveButton(R.string.hint_positive, (dialog, which) -> {
                    HistoryHandler historyHandler = new HistoryHandler(HintDialogFragment.this.getContext(), MainActivity.HISTORY_FILE_TAG);
                    historyHandler.writeHistory(hintKey, "true", HistoryHandler.Category.SETTINGS);
                });

        return builder.create();
    }
}