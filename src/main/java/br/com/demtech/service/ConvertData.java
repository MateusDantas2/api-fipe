package br.com.demtech.service;

import br.com.demtech.impl.ConvertsDataImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

/**
 * @author Mateus Dantas
 */
public class ConvertData implements ConvertsDataImpl {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getList(String json, Class<T> tClass) {
        CollectionType list = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        try {
            return objectMapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
