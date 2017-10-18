package com.solvesoft.dao;

import com.solvesoft.model.Usuario;
import com.solvesoft.service.SnackSoftConexion;
import com.solvesoft.service.SnackSoftService;
import javax.persistence.Query;

/**
 *
 * @author theo
 */
public class LoginDao extends SnackSoftService<Usuario> {

    @Override
    public boolean Update(Usuario t) throws Exception {
        return super.Update(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Create(Usuario t) throws Exception {
        return super.Create(t); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario login(String user, String pass) {
        Usuario u = null;
        try {
            em = SnackSoftConexion.getInstance().getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :user AND u.password = :pass");
            query.setParameter("user", user);
            query.setParameter("pass", pass);
            u = (Usuario) query.getSingleResult();
            em.clear();
        } catch (Exception e) {
            throw e;
        }
        return u;
    }

}
