package br.com.demtech.impl;

import java.util.List;

/**
 * @author Mateus Dantas
 */
public interface ConvertsDataImpl {
    <T> T getData(String json, Class<T> tClass);
    <T> List<T> getList(String json, Class<T> tClass);
}
