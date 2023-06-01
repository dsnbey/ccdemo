package com.example.ccdemo;

public class CloudRestApi {

    CloudApiService apiService;

    public CloudRestApi() {
        this.apiService = RetrofitClientForCloud.createService();
    }

    @SuppressLint("CheckResult")
    public boolean decideDropPlace(InventoryList inventoryList, String driverName) {

        final boolean[] status = {true};

        Payload payload = new Payload();
        payload.setInventoryList(inventoryList);
        payload.setDriverName(driverName);

        apiService.sendRequest(payload)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("CloudRestApi", " decideDropPlace onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        Log.d("CloudRestApi", " decideDropPlace onSuccess: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("CloudRestApi", " decideDropPlace onError " + e.getMessage());
                        e.printStackTrace();
                        status[0] = false;
                    }
                });
        return status[0];

    }
}