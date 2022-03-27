package com.baldware.feedmonty.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.baldware.feedmonty.Utils.HistoryHandler;
import com.baldware.feedmonty.Activities.MainActivity;
import com.baldware.feedmonty.R;

public class PrivacyDialogFragment extends DialogFragment {

    /**
     * Constructor
     */
    public PrivacyDialogFragment() {
        // Empty constructor required
    }

    /**
     * Returns a new dialog fragment instance
     * @param _title The dialog fragments title
     * @return The new dialog fragment instance
     */
    public static PrivacyDialogFragment newInstance(String _title) {
        PrivacyDialogFragment privacyDialogFragment = new PrivacyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", _title);
        privacyDialogFragment.setArguments(args);
        return privacyDialogFragment;
    }

    /**
     * The dialog fragments on create method
     * @param _savedInstanceState The saved instance state
     * @return The created dialog fragment
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle _savedInstanceState) {
        String title = getArguments().getString("title");

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.fragment_privacy_policy, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setView(dialogView)
                .setPositiveButton(R.string.privacy_policy_acceptance_text, (dialog, which) -> {
                    HistoryHandler historyHandler = new HistoryHandler(PrivacyDialogFragment.this.getContext(), MainActivity.HISTORY_FILE_TAG);
                    historyHandler.writeHistory(MainActivity.PRIVACY_POLICY_KEY, "true", HistoryHandler.Category.SETTINGS);
                });

        return builder.create();
    }
}
