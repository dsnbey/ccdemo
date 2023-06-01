package com.example.ccdemo;


import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.catastrophecompass.DataLayer.Model.UserLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ManagerLoginFBRepo {

    private static DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("UserList");
    int id;

    public User validateLogin(UserLogin userLogin) throws ExecutionException, InterruptedException {
        ArrayList<User>[] users = new ArrayList[1];

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userName = userSnapshot.getKey();
                    String userType = userSnapshot.child("userType").getValue(String.class);

                    User newUser = new User(userName, userType);//?
                    users[0].add(newUser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(checkCredentials(userLogin.getUserName(), userLogin.getPassword()))
        {
            for(User u:users[0])
            {
                if(u.getUserName().equals(userLogin.getUserName()))
                {
                    return u;
                }
            }
        }
        return null;
    }

    public static boolean checkCredentials(String username, String password) throws ExecutionException, InterruptedException {
        boolean[] result = new boolean[1];

        databaseRef.child(username).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String storedPassword = dataSnapshot.getValue(String.class);
                    boolean isPasswordCorrect = storedPassword.equals(password);
                    result[0] = isPasswordCorrect;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                result[0] = false;
            }
        });

        return result[0];
    }
}