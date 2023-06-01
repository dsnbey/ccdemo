package com.example.ccdemo;

public class ManagerLoginUC {
    private ManagerLoginFBRepo FBRepo;
    private CurrentUserDao currentUserDao;

    private LocalDB db;
    public ManagerLoginUC(ManagerLoginFBRepo FBRepo, LocalDB DB) {
        this.db = DB;
        this.FBRepo = FBRepo;
        this.currentUserDao = DB.currentUserDao();
    }

    public String validateLogin(UserLogin userLogin){
        User user = null;
        try {
            user = FBRepo.validateLogin(userLogin);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (user!=null)
        {
            currentUserDao.recordUser(user)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() { // TODO Complete methods
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            Log.d("ManagerLogin", "recordUser() onSubscribe() called");
                        }

                        @Override
                        public void onComplete() {
                            Log.d("ManagerLogin", "recordUser() onComplete() called");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("ManagerLogin", "recordUser() onError() called");
                        }
                    });
            return user.getUserType();
        }
        else
            return null;
    }
}