package com.J0aoPaulo.SpotSearch.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
