package org.example.springboot2.service;

import org.example.springboot2.exception.UnsupportedCodeException;
import org.example.springboot2.model.Request;
import org.springframework.stereotype.Service;

@Service
public interface UnsupportedCodeMatchException {
    void isUidMatch(Request request) throws UnsupportedCodeException;
}
