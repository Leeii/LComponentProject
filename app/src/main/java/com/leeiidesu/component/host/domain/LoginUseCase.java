package com.leeiidesu.component.host.domain;


/**
 * Created by liyi on 2018/4/27.
 */
public class LoginUseCase {

    private DataRepository repository;

    public LoginUseCase(DataRepository repository) {
        this.repository = repository;
    }

    public String execute(String string, String string1) {
        return repository.append(String.format("LoginUseCase:  username = %s,password = %s", string, string1));
    }
}
