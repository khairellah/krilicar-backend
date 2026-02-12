package com.krilicar.services.interfaces;

import com.krilicar.dtos.ModelDTO;
import java.util.List;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);
    ModelDTO updateModel(String code, ModelDTO modelDTO);
    void deleteModel(String code);
    ModelDTO getModelByCode(String code);
    List<ModelDTO> getAllModels();
    List<ModelDTO> getModelsByBrand(String brandCode);
}