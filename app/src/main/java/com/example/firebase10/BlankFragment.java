package com.example.firebase10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class BlankFragment extends Fragment {
    private TextInputEditText emailTET,passwordTET;
    private Button logInBtn;
    private FirebaseAuth u_auth;
    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailTET=view.findViewById(R.id.email_input);
        passwordTET=view.findViewById(R.id.password_input);
        logInBtn=view.findViewById(R.id.loginBtn);
        u_auth=FirebaseAuth.getInstance();

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailTET.getText().toString();
                String password=passwordTET.getText().toString();

                loginUser(email,password);
            }
        });





    }

    private void loginUser(String email, String password) {

    u_auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            Toast.makeText(getContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
        }
    });


    }


}