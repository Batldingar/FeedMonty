package com.baldware.feedmonty.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.baldware.feedmonty.R;

public class AdvertisementDialogFragment extends DialogFragment {

    public AdvertisementDialogFragment() {
        // Empty constructor required
    }

    public static AdvertisementDialogFragment newInstance(String title) {
        AdvertisementDialogFragment privacyDialogFragment = new AdvertisementDialogFragment();
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

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.fragment_advertisement, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setView(dialogView)
                .setNegativeButton(R.string.ad_button_negative, (dialog, which) -> {
                })
                .setPositiveButton(R.string.ad_button_positive, (dialog, which) -> {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.baldware.feedmonty"));
                    startActivity(playStoreIntent);
                });

        return builder.create();
    }
}
