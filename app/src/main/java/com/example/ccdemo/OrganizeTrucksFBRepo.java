package com.example.ccdemo;

import com.example.ccdemo.Model.DemographicInfo;
import com.example.ccdemo.Model.DriverItem;
import com.example.ccdemo.Model.FieldOrganization;
import com.example.ccdemo.Model.InventoryList;
import com.example.ccdemo.Model.LogisticInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class OrganizeTrucksFBRepo {
    private VectorDatabaseRepo vectorRepo;

    @Inject
    public OrganizeTrucksFBRepo(VectorDatabaseRepo vectorRepo){
        this.vectorRepo = vectorRepo;
    }
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Logistics");
    public void getAvailableDrivers(OrganizeTrucksInterface Interface, String organizationName)
    {
        ArrayList<DriverItem> drivers = new ArrayList<DriverItem>();
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot names: snapshot.getChildren())
                {
                    if(names.child("registeredOrganization").getValue().equals(organizationName) && names.child("status").getValue().equals("available"))
                    {
                        DriverItem driver = new DriverItem(names.getKey(), names.child("status").getValue(String.class),names.child("TruckSize").getValue(String.class));
                        drivers.add(driver);
                    }

                }
                Interface.setDisplay(drivers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Interface.warnUser();
            }
        });

    }

    public boolean reassignGET(String driverName)
    {
        DatabaseReference driver = databaseRef.child(driverName);
        boolean[] deletionStatus = {false};
        String[] name = new String[1];
        driver.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name[0]  = snapshot.child("getName").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        driver.child("getName").setValue("");
        driver.child("getAdress").setValue("");

        DatabaseReference orgRef = FirebaseDatabase.getInstance().getReference("Organizations").child(name[0]).child("arrivingTruckList").child(driverName);

        orgRef.removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        deletionStatus[0] = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        deletionStatus[0] = false;
                    }
                });

        DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("Organizations").child(name[0]).child("requests").child(driverName);
        reqRef.child("requestSize").setValue(driver.child("TruckSize"));
        reqRef.child("collected").setValue(driver.child("Inventory"));

        return deletionStatus[0];
    }

    public boolean reassignDrop(String driverName)
    {
        DatabaseReference driver = databaseRef.child(driverName);
        boolean[] success = new boolean[1];
        success[0] = false;
        String[] name = new String[1];
        String[] address = new String[1];
        String[] getName = new String[1];
        String[] getAddress = new String[1];
        String[] status = new String[1];
        String[] pictureUrl = new String[1];
        boolean[] getStatus = new boolean[1];
        boolean[] dropStatus = new boolean[1];
        int[] size = new int[1];
        String[] refKey = new String[1];
        driver.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name[0]  = snapshot.child("dropName").getValue(String.class);
                address[0] = snapshot.child("dropAddress").getValue(String.class);
                getName[0]  = snapshot.child("getName").getValue(String.class);
                getAddress[0] = snapshot.child("getAddress").getValue(String.class);
                status[0] = snapshot.child("status").getValue(String.class);
                pictureUrl[0] = snapshot.child("pictureUrl").getValue(String.class);
                getStatus[0] = snapshot.child("getStatus").getValue(Boolean.class);
                dropStatus[0] = snapshot.child("dropStatus").getValue(Boolean.class);
                size[0] = snapshot.child("TruckSize").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        driver.child("dropName").setValue(null);
        driver.child("dropAddress").setValue(null);
        InventoryList[] fieldList = new InventoryList[1];
        DemographicInfo[] demographicInfo = new DemographicInfo[1];

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FieldOrganizations").child(name[0]);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refKey[0] = snapshot.getKey();
                fieldList[0] = snapshot.child("currentInventory").getValue(InventoryList.class);
                demographicInfo[0] = snapshot.child("demographic").getValue(DemographicInfo.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        int[] food = new int[1];
        int[] heater = new int[1];
        int[] manCloth = new int[1];
        int[] womanCloth = new int[1];
        int[] childCloth = new int[1];
        int[] hygene = new int[1];
        int[] kitchenMaterial = new int[1];
        int[] powerbank = new int[1];

        driver.child("Inventory").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                food[0] = snapshot.child("Inventory").getValue(InventoryList.class).getFood();
                heater[0] = snapshot.child("Inventory").getValue(InventoryList.class).getHeater();
                manCloth[0] = snapshot.child("Inventory").getValue(InventoryList.class).getManCloth();
                womanCloth[0] = snapshot.child("Inventory").getValue(InventoryList.class).getWomanCloth();
                childCloth[0] = snapshot.child("Inventory").getValue(InventoryList.class).getChildCloth();
                hygene[0] = snapshot.child("Inventory").getValue(InventoryList.class).getHygene();
                kitchenMaterial[0] = snapshot.child("Inventory").getValue(InventoryList.class).getKitchenMaterial();
                powerbank[0] = snapshot.child("Inventory").getValue(InventoryList.class).getPowerbank();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        InventoryList list = new InventoryList(0, food[0], heater[0],manCloth[0], womanCloth[0], childCloth[0], hygene[0], kitchenMaterial[0], powerbank[0]);
        ref.child("arrivingAid").setValue(list).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success[0] = true;
                LogisticInfo driverItem = new LogisticInfo(getName[0], getAddress[0], name[0], address[0], status[0], pictureUrl[0], getStatus[0], dropStatus[0], list, size[0]);
                vectorRepo.syncVectorDB(driverItem, new FieldOrganization(name[0], address[0]), list, fieldList[0], demographicInfo[0]);
            }
        });

        return success[0];
    }

}
