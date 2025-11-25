package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service("database")
public class DataBaseFileService implements IFileService {

    @Override
    public void upload(String identificador, String tipo, byte[] conteudo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upload'");
    }

}
