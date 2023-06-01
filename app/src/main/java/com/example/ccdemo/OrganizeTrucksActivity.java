package com.example.ccdemo;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrganizeTrucksActivity extends AppCompatActivity implements OrganizeTrucksInterface{

    private RecyclerView recyclerView;
    private TruckAdapter truckAdapter;
    public OrganizeTrucksVM vm;
    private List<DriverItem> truckList;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this activity
        setContentView(R.layout.activity_organize_trucks);

        vm = new ViewModelProvider(this).get(OrganizeTrucksVM.class);
        vm.getAvailableDrivers(this, HQOrganizerCommon.organizationName);
        // TODO common
        recyclerView = findViewById(R.id.rec_organize_truck_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        truckList = new ArrayList<>();
        truckAdapter = new TruckAdapter(this, truckList, this);
        recyclerView.setAdapter(truckAdapter);
    }

    @Override
    public void setDisplay(List<DriverItem> list) {
        // Initialize the RecyclerView, its layout manager and the adapter
        truckList = list;

        //truckList.clear();
        //truckList =new ArrayList<>(list);
        truckAdapter.notifyDataSetChanged(); // TODO test notifyDataSetChanged()
    }

    @Override
    public void warnUser() {

    }

    // You can add more methods for managing the Truck data here if needed
}
