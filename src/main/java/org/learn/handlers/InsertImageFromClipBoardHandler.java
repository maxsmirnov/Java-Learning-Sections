package org.learn.handlers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import org.learn.datatranslation.entities.ImageContent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class InsertImageFromClipBoardHandler implements EventHandler<ActionEvent> {

    private final ByteArrayOutputStream os = new ByteArrayOutputStream();
    private TextArea answerResult;
    private List<ImageContent> contents;
    private int id = 1;

    public InsertImageFromClipBoardHandler(TextArea answerResult, List<ImageContent> contents) {
        this.answerResult = answerResult;
        this.contents = contents;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("try");
        Image image = Clipboard.getSystemClipboard().getImage();
        String base64Data = "";
        if (image != null) {
            try {
                BufferedImage awtImage = SwingFXUtils.fromFXImage(image, null);

                ImageIO.write(awtImage, "PNG", os);
                base64Data = Base64.getEncoder().encodeToString(os.toByteArray());
            } catch (Exception exc) {
                System.out.println("error");
            }
            contents.add(new ImageContent(base64Data, String.valueOf(id)));
            answerResult.setText(answerResult.getText() + "\n" + "*image" + id + "*\n");
            id++;
        } else {
            System.out.println("Image is null");
        }

    }
}
