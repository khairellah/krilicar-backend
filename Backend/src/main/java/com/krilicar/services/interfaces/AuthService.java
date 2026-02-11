package com.krilicar.services.interfaces;

import com.krilicar.dtos.registration.ClientRegistrationDTO;
import com.krilicar.dtos.registration.ClientRegistrationResponseDTO;
import com.krilicar.dtos.registration.CompanyRegistrationDTO;
import com.krilicar.dtos.registration.CompanyRegistrationResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {

    // Prend le fichier en plus des données
    ClientRegistrationResponseDTO registerClient(ClientRegistrationDTO registrationDTO, MultipartFile imageFile) throws IOException;

    // Prend le fichier en plus des données
    CompanyRegistrationResponseDTO registerCompany(CompanyRegistrationDTO registrationDTO, MultipartFile imageFile) throws IOException;
}