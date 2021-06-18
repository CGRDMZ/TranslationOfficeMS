package org.example.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import org.example.model.UserModel;

public class UserModelView {
    private static UserModelView SINGLETON;

    public static UserModelView getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new UserModelView();
        }
        return SINGLETON;
    }
}
