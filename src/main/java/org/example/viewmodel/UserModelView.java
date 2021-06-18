package org.example.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.entity.User;
import org.example.model.UserModel;

public class UserModelView {
    private static UserModelView SINGLETON;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleObjectProperty<User> user;
    private final SimpleBooleanProperty isTranslator;

    UserModel userModel;

    public UserModelView(UserModel userModel) {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        user = new SimpleObjectProperty<>();
        isTranslator = new SimpleBooleanProperty();
        this.userModel = userModel;
    }

    public static UserModelView getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new UserModelView(new UserModel());
        }
        return SINGLETON;
    }

    public boolean register() {
        return userModel.register(username.get(), password.get(), !isTranslator.get(), isTranslator.get());
    }

    public boolean login() {
        User user = userModel.login(username.get(), password.get());
        if (user != null) {
            this.user.setValue(user);
            return true;
        }
        return false;
    }

    public void logout() {
        user.setValue(null);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public boolean isTranslator() {
        return isTranslator.get();
    }

    public SimpleBooleanProperty isTranslatorProperty() {
        return isTranslator;
    }

    public void setIsTranslator(boolean isTranslator) {
        this.isTranslator.set(isTranslator);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public User getUser() {
        return user.get();
    }

    public SimpleObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }
}
