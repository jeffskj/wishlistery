package com.jeffskj.wishlist.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.jboss.seam.transaction.Transactional;

import com.jeffskj.wishlist.domain.User;


@Named
@Transactional
@RequestScoped
public class UserBean extends BaseBean<User> {
    private static final long serialVersionUID = 1L;

    private String passwordConfirm;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    protected User newEntityInstance() {
        return new User();
    }
}
