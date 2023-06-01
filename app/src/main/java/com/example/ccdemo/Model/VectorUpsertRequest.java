package com.example.ccdemo.Model;

import com.example.catastrophecompass.RemoteDataRepository.VectorDatabaseRepo.VectorModels.Vector;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VectorUpsertRequest {
    @SerializedName("vectors")
    private List<Vector> vectors;

    public List<Vector> getVectors() {
        return vectors;
    }

    public void setVectors(List<Vector> vectors) {
        this.vectors = vectors;
    }

    public VectorUpsertRequest(List<Vector> vectors) {
        this.vectors = vectors;
    }

    public VectorUpsertRequest() {
    }
}
