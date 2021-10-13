package com.baldware.feedmonty;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class PrivacyDialogFragment extends DialogFragment {

    public PrivacyDialogFragment() {
        // Empty constructor required
    }

    public static PrivacyDialogFragment newInstance(String title) {
        PrivacyDialogFragment privacyDialogFragment = new PrivacyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        privacyDialogFragment.setArguments(args);
        return privacyDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = null;
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }

        LayoutInflater layoutInflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.fragment_privacy_policy, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setView(dialogView)
                .setPositiveButton(R.string.privacy_policy_acceptance_text, (dialog, which) -> {
                    HistoryHandler historyHandler = new HistoryHandler(Objects.requireNonNull(PrivacyDialogFragment.this.getContext()), MainActivity.HISTORY_FILE_TAG);
                    historyHandler.writeHistory(MainActivity.PRIVACY_POLICY_KEY, "true", HistoryHandler.Category.SETTINGS);
                });

        return builder.create();
    }
}
