package org.example.springboot2.service;



import org.example.springboot2.exception.UnsupportedCodeException;
import org.example.springboot2.model.Request;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RequestUnsupportedCodeMatchException implements
        UnsupportedCodeMatchException{

        @Override
                public void isUidMatch(Request request) throws UnsupportedCodeException {
            if (Objects.equals(request.getUid(), "123")){
                throw new UnsupportedCodeException();
            }
        }
}
