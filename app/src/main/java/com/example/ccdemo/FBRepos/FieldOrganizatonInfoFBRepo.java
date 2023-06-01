package com.example.ccdemo.FBRepos;

import androidx.annotation.NonNull;

import com.example.catastrophecompass.DataLayer.LocalRepository.FieldOrganizationInfoLocalRepo;
import com.example.catastrophecompass.DataLayer.Model.DemographicInfo;
import com.example.catastrophecompass.DataLayer.Model.HousingInfo;
import com.example.catastrophecompass.DataLayer.Model.InventoryList;
import com.example.catastrophecompass.DataLayer.Model.User;
import com.example.ccdemo.LocalRepos.FieldOrganizationInfoLocalRepo;
import com.example.ccdemo.Model.DemographicInfo;
import com.example.ccdemo.Model.HousingInfo;
import com.example.ccdemo.Model.InventoryList;
import com.example.ccdemo.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FieldOrganizatonInfoFBRepo
{
    FieldOrganizationInfoLocalRepo localRepo;
    public FieldOrganizatonInfoFBRepo(FieldOrganizationInfoLocalRepo localRepo) {
        this.localRepo = localRepo;
    }

    public boolean updateDemographicInfo(DemographicInfo demographicInfo, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("demographic");
        final boolean[] success = {true};

        ref.setValue(demographicInfo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];
    }

    public boolean updateHousingInfo(HousingInfo housingInfo, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("housing");

        final boolean[] success = {true};
        ref.setValue(housingInfo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];
    }
    InventoryList[] currentList = new InventoryList[1];
    public boolean updateAidStatusInfo(InventoryList inventoryList, String organizationName)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory");

        final boolean[] success = {true};
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            currentList[0] = dataSnapshot.getValue(InventoryList.class);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    });

        ref.setValue(inventoryList).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                success[0] = false;
            }
        });
        return success[0];

    }

    public void revertChanges(String organizationName)
    {
        FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory").setValue(currentList[0]);
    }

    public void attachListeners(User user, String organizationName)
    {
        DatabaseReference path1 = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("demographic");
        DatabaseReference path2 = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("housing");
        DatabaseReference path3 = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("currentInventory");
        DatabaseReference path4 = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(organizationName).child("arrivingInventory");

        path1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localRepo.recordDemographicInfo(snapshot.getValue(DemographicInfo.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        path2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localRepo.recordHousingInfo(snapshot.getValue(HousingInfo.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        path3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localRepo.recordInventoryInfo(snapshot.getValue(InventoryList.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        path4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                localRepo.recordArrivingAid(snapshot.getValue(InventoryList.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
