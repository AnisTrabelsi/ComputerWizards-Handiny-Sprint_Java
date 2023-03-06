/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entite.Reclamation;

import javafx.scene.control.ListCell;
//public class UserListCell extends ListCell<Utilisateur> {
//    @Override
//    public void updateItem(Utilisateur user, boolean empty) {
//        super.updateItem(user, empty);
//
//        if (empty || user == null) {
//            setText(null);
//        } else {
//            setText(user.getLogin() + " - " + user.getEmail());
//        }
//    }
//}

/**
 *
 * @author Chayma
 */
public class ReclamationCell extends ListCell<Reclamation>  {
    @Override
    public void updateItem(Reclamation r, boolean empty) {
        super.updateItem(r,empty);

        if (empty || r == null) {
            setText(null);
        } else {
            setText(r.getType_reclamation() + "                      " + r.getEtat_reclamation());
        }
    }
}
