package org.learn.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.learn.controller.LearningController;

public class BackToSectionsHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        LearningController.getState().showSections();
    }
}
