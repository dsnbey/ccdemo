package com.example.ccdemo;

public class VectorDatabaseRepo {

    VectorDatabaseApiService vectorDatabaseApiService;
    CloudRestApi cloudRestApi;

    @Inject
    public VectorDatabaseRepo(CloudRestApi cloudRestApi) {
        this.vectorDatabaseApiService = RetrofitClientForVectorDB.createService();
        this.cloudRestApi = cloudRestApi;
    }

    @SuppressLint("CheckResult")
    public boolean updateAidStatusInfo(InventoryList list, String organizationName, DemographicInfo demographicInfo, InventoryList arrivingInfo) {
        VectorUpsertRequest request = parseRequest(list, organizationName, demographicInfo, arrivingInfo);
        final boolean[] status = {false};

        vectorDatabaseApiService.upsertVectors(request)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onSubscribe: ");

                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onSuccess: ");

                        if (o instanceof PineconeUpsertSucceedResponse) {
                            status[0] = true;
                        }

                        if (o instanceof PineconeUpsertFailedResponse) {
                            String message = ((PineconeUpsertFailedResponse) o).getMessage();
                            Log.d("VectorDatabaseRepo", "onSuccess called yet error exists: " + message);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("VectorDatabaseRepo", "upsertVectors onError: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
        return status[0];
    }

    public VectorUpsertRequest parseRequest(InventoryList list, String name, DemographicInfo demographicInfo, InventoryList arrivingInfo) {

        List<Float> amountsFor20Days = getTotalAmounts(demographicInfo);

        List<Float> internalsAsAmount = getInternalsAsAmount(list);
        List<Float> arrivingAsAmount = getInternalsAsAmount(arrivingInfo);
        // total - list(amount)
        // create Vector object
        Vector vector = new Vector();

        List<Float> values = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            values.add(amountsFor20Days.get(i) - (internalsAsAmount.get(i) + arrivingAsAmount.get(i)));
        }

        vector.setValues(values);
        vector.setId(name);

        // create request

        VectorUpsertRequest request = new VectorUpsertRequest();

        List<Vector> vectors = new ArrayList<>(1);
        vectors.add(vector);

        request.setVectors(vectors);

        return request;
    }

    public List<Float> getTotalAmounts(DemographicInfo info) {
        List<Float> amounts = new ArrayList<>();

        int population = info.getM0_3() + info.getM3_15() + info.getM15_64() + info.getM65() + info.getW0_3() + info.getW3_15() + info.getW15_64() + info.getW65();

        Float food = (float) (population * 1);
        Float heater = (float) population / 5;
        Float manCloth = (float) (info.getM15_64() + info.getM65()) / 10;
        Float womanCloth = (float) (info.getW15_64() + info.getW65()) / 10;
        Float childCloth = (float) (info.getM0_3() + info.getM3_15() + info.getW0_3() + info.getW3_15()) / 6;
        Float hygene = (float) population * 1;
        Float kitchenMaterial = (float) population;
        Float powerBank = (float) population;

        amounts.add(food);
        amounts.add(heater);
        amounts.add(manCloth);
        amounts.add(womanCloth);
        amounts.add(childCloth);
        amounts.add(hygene);
        amounts.add(kitchenMaterial);
        amounts.add(powerBank);

        return amounts;

    }

    public List<Float> getInternalsAsAmount(InventoryList list) {

        List<Float> amounts = new ArrayList<>();

        Float food = (float) (list.getFood() * 1);
        Float heater = (float) list.getHeater() / 5;
        Float manCloth = (float) list.getManCloth() / 10;
        Float womanCloth = (float) list.getWomanCloth() / 10;
        Float childCloth = (float) list.getChildCloth() / 6;
        Float hygene = (float) list.getHygene() * 1;
        Float kitchenMaterial = (float) list.getKitchenMaterial();
        Float powerBank = (float)list.getPowerbank();

        amounts.add(food);
        amounts.add(heater);
        amounts.add(manCloth);
        amounts.add(womanCloth);
        amounts.add(childCloth);
        amounts.add(hygene);
        amounts.add(kitchenMaterial);
        amounts.add(powerBank);

        return amounts;

    }

    public boolean syncVectorDB(LogisticInfo driver, FieldOrganization dropPlace, InventoryList fieldList, InventoryList fieldPossesedList, DemographicInfo demographicInfo) {
        //Todo: reduce driver list from dropPlace
        InventoryList newList = new InventoryList();

        int food = fieldList.getFood() - driver.getInventoryList().getFood();
        newList.setFood(food);

        int heater = fieldList.getHeater() - driver.getInventoryList().getHeater();
        newList.setHeater(heater);

        int manCloth = fieldList.getManCloth() - driver.getInventoryList().getManCloth();
        newList.setManCloth(manCloth);

        int womanCloth = fieldList.getWomanCloth() - driver.getInventoryList().getWomanCloth();
        newList.setWomanCloth(womanCloth);

        int childCloth = fieldList.getChildCloth() - driver.getInventoryList().getChildCloth();
        newList.setChildCloth(childCloth);

        int hygene = fieldList.getHygene() - driver.getInventoryList().getHygene();
        newList.setHygene(hygene);

        int kitchenMaterial = fieldList.getKitchenMaterial() - driver.getInventoryList().getKitchenMaterial();
        newList.setKitchenMaterial(kitchenMaterial);

        int powerBank = fieldList.getPowerbank() - driver.getInventoryList().getPowerbank();
        newList.setPowerbank(powerBank);

        updateAidStatusInfo(fieldPossesedList, dropPlace.getName(), demographicInfo, newList);
        Log.d("VectorDatabaseRepo", "syncVectorDB: it reaches to the statement after of updateAidStatusInfo, sequential nature works properly. ");

        boolean status = cloudRestApi.decideDropPlace(driver.getInventoryList(), driver.getGetName());

        return status;
    }
}
