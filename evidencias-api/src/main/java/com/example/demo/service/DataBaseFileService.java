package com.example.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EvidencesController;

@Service("database")
public class DataBaseFileService implements IFileService {

    private static Logger logger = LoggerFactory.getLogger(EvidencesController.class);

    @Override
    public void upload(String identificador, String tipo, byte[] conteudo) {
        
        logger.info(">>>>> arquivo logado");
    }

}
