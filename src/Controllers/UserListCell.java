/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Utilisateur;
import javafx.scene.control.ListCell;

/**
 *
 * @author Chayma
 */
public class UserListCell extends ListCell<Utilisateur> {
    @Override
    public void updateItem(Utilisateur user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {
            setText(null);
        } else {
            setText(user.getLogin() + " - " + user.getEmail());
        }
    }
}
     
    

