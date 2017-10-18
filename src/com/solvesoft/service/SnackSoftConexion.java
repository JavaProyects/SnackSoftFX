package com.solvesoft.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SnackSoftConexion {

    private static SnackSoftConexion instance = null;
    private EntityManagerFactory factory = null;

    public static SnackSoftConexion getInstance() {
        if (instance == null) {
            instance = new SnackSoftConexion();
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("SnackSoftFXPU");
            }
        } catch (Exception e) {
            throw e;
        }
        return factory;
    }
}
