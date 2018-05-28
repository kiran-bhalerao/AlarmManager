package com.example.kiran.alarmmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by kiran on 27-05-2018.
 */

public class DialogFragment extends android.support.v4.app.DialogFragment {

    Button button;
    TimePicker timePicker;
    Communicator communicator;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_frag, null);
        setCancelable(false);

        button = view.findViewById(R.id.set_button);
        timePicker = view.findViewById(R.id.timePicker);
        button.setOnClickListener((v) -> {
            dismiss();
            communicator.communicate(timePicker);
            Toast.makeText(context, "set Clicked from dialog", Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        communicator = (Communicator) context;
    }

    interface Communicator {
        void communicate(TimePicker timePicker);
    }
}
