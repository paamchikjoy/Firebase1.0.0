package com.example.firebase10;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class RegistrationFragment extends Fragment {

    public EditText emailET, passET, confirmPassET, mobileNumET;
    public Button registerBtn;
    private FirebaseAuth auth;



    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailET = view.findViewById(R.id.reg_emailInput);
        passET = view.findViewById(R.id.reg_passwordInput);
        confirmPassET = view.findViewById(R.id.reg_confirmPasswordInput);
        mobileNumET = view.findViewById(R.id.reg_mobileNumber_Input);
        registerBtn = view.findViewById(R.id.reg_saveBtn);
        auth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = emailET.getText().toString();
                String txt_password = passET.getText().toString();
                String txt_confirmPassword=confirmPassET.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)||TextUtils.isEmpty(txt_confirmPassword))
                {
                    Toast.makeText(getContext(), "Empty credential", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length() < 6)
                {
                    Toast.makeText(getContext(), "Password is too short", Toast.LENGTH_SHORT).show();
                }

                else if(!txt_password.equals(txt_confirmPassword))
                {
                    Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(txt_email, txt_password);
                    Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_blankFragment);
                }

            }
        });

    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Activity) getContext(),new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}