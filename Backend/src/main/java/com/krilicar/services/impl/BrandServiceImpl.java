package com.krilicar.services.impl;

import com.krilicar.dtos.BrandDTO;
import com.krilicar.entities.Brand;
import com.krilicar.exceptions.ResourceNotFoundException;
import com.krilicar.mappers.BrandMapper;
import com.krilicar.repositories.BrandRepository;
import com.krilicar.services.interfaces.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        if (brandRepository.existsByName(brandDTO.getName())) {
            throw new IllegalArgumentException("La marque '" + brandDTO.getName() + "' existe déjà.");
        }
        Brand brand = brandMapper.toEntity(brandDTO);
        return brandMapper.toDTO(brandRepository.save(brand));
    }

    @Override
    public BrandDTO updateBrand(String code, BrandDTO brandDTO) {
        Brand existingBrand = brandRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Marque introuvable avec le code : " + code));

        if (!existingBrand.getName().equalsIgnoreCase(brandDTO.getName())
                && brandRepository.existsByName(brandDTO.getName())) {
            throw new IllegalArgumentException("Le nom '" + brandDTO.getName() + "' est déjà utilisé.");
        }

        existingBrand.setName(brandDTO.getName());
        return brandMapper.toDTO(brandRepository.save(existingBrand));
    }

    @Override
    @Transactional
    public void deleteBrand(String code) {
        if (!brandRepository.findByCode(code).isPresent()) {
            throw new ResourceNotFoundException("Marque introuvable avec le code : " + code);
        }
        brandRepository.deleteByCode(code);
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO getBrandById(String code) {
        return brandRepository.findByCode(code)
                .map(brandMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Marque introuvable avec le code : " + code));
    }
}