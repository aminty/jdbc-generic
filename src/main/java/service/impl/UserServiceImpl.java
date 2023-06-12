package service.impl;

import base.service.impl.BaseServiceImpl;
import model.User;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl<Integer, User, UserRepository>
implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
