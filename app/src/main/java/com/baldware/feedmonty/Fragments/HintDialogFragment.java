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

    public HintDialogFragment() {
        // Empty constructor required
    }

    public static HintDialogFragment newInstance(String _hintKey, String _hintText) {
        HintDialogFragment ingredientsHintDialogFragment = new HintDialogFragment();
        Bundle args = new Bundle();
        args.putString("hintKey", _hintKey);
        args.putString("hintText", _hintText);
        ingredientsHintDialogFragment.setArguments(args);
        return ingredientsHintDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String hintKey = null;
        String hintText = null;
        if (getArguments() != null) {
            hintKey = getArguments().getString("hintKey");
            hintText = getArguments().getString("hintText");
        }

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.fragment_hint, null);
        TextView textView = dialogView.findViewById(R.id.hint_text);
        textView.setText(hintText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String finalHintKey = hintKey;
        builder.setTitle(R.string.hint_title)
                .setView(dialogView)
                .setPositiveButton(R.string.hint_positive, (dialog, which) -> {
                    HistoryHandler historyHandler = new HistoryHandler(HintDialogFragment.this.getContext(), MainActivity.HISTORY_FILE_TAG);
                    historyHandler.writeHistory(finalHintKey, "true", HistoryHandler.Category.SETTINGS);
                });

        return builder.create();
    }
}