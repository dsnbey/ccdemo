package com.example.ccdemo.DI;

import com.example.ccdemo.CloudRestApi;
import com.example.ccdemo.FBRepos.FieldOrganizatonInfoFBRepo;
import com.example.ccdemo.FieldOrganizer.FieldOrganizationDomain;
import com.example.ccdemo.LocalDBDaos.LocalDB;
import com.example.ccdemo.LocalRepos.FieldOrganizationInfoLocalRepo;
import com.example.ccdemo.ManagerLoginFBRepo;
import com.example.ccdemo.ManagerLoginUC;
import com.example.ccdemo.VectorDatabaseRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public FieldOrganizationDomain FieldOrganizationDomain(FieldOrganizationInfoLocalRepo localRepo, FieldOrganizatonInfoFBRepo FBRepo, VectorDatabaseRepo vectorRepo) {
        return new FieldOrganizationDomain(localRepo, FBRepo, vectorRepo);
    }

    @Provides
    @Singleton
    public FieldOrganizationInfoLocalRepo provideFieldOrganizationInfoLocalRepo(LocalDB db) {
        return new FieldOrganizationInfoLocalRepo(db);
    }


    @Provides
    @Singleton
    public CloudRestApi provideCloudRestApi() {
        return new CloudRestApi();
    }

    @Provides
    @Singleton
    public ManagerLoginUC provideManagerLoginUC(ManagerLoginFBRepo FBRepo, LocalDB db) {
        return new ManagerLoginUC(FBRepo, db);
    }

    @Provides
    @Singleton
    public VectorDatabaseRepo provideVBDR(CloudRestApi cloudRestApi) {
        return new VectorDatabaseRepo(cloudRestApi);
    }


    @Provides
    @Singleton
    public FieldOrganizatonInfoFBRepo provideFieldOrganizatonInfoFBRepo(FieldOrganizationInfoLocalRepo localRepo) {
        return new FieldOrganizatonInfoFBRepo(localRepo);
    }

    @Provides
    @Singleton
    public ManagerLoginFBRepo managerLoginFBRepo() {
        return new ManagerLoginFBRepo();
    }
}

