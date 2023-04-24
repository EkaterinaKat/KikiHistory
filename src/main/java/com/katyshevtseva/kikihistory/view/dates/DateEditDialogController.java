package com.katyshevtseva.kikihistory.view.dates;

import com.katyshevtseva.fx.FxUtils;
import com.katyshevtseva.fx.WindowBuilder.FxController;
import com.katyshevtseva.general.NoArgsKnob;
import com.katyshevtseva.kikihistory.core.EntryService;
import com.katyshevtseva.kikihistory.core.model.Entry;
import com.katyshevtseva.kikihistory.core.model.EntryType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import static com.katyshevtseva.fx.FxUtils.associateButtonWithControls;
import static com.katyshevtseva.fx.FxUtils.closeWindowThatContains;

class DateEditDialogController implements FxController {
    //    private ImageContainer image;
    private final Entry existing;
    private final NoArgsKnob onSaveListener;
    //    @FXML
//    private Pane imagePane;
    @FXML
    private TextArea value1TextField;
    @FXML
    private TextArea value2TextField;
    @FXML
    private ComboBox<EntryType> typeComboBox;
    @FXML
    private Button saveButton;

    DateEditDialogController(Entry existing, NoArgsKnob onSaveListener) {
        this.existing = existing;
        this.onSaveListener = onSaveListener;
    }

    @FXML
    private void initialize() {
        associateButtonWithControls(saveButton, value1TextField, value2TextField, typeComboBox);
        FxUtils.setComboBoxItems(typeComboBox, EntryType.values());
        saveButton.setOnAction(event -> save());
//        showImage(FxImageCreationUtil.getIcon(FxImageCreationUtil.IconPicture.GREY_PLUS));
        setExistingDateInfo();
    }

    private void setExistingDateInfo() {
        if (existing != null) {
//            if (existing.getImageName() != null) {
//                image = AuthorImageUtils.getImageContainer(existing);
//                showImage(image.getImage());
//            }
            typeComboBox.setValue(existing.getType());
            typeComboBox.setDisable(true);
            value1TextField.setText(existing.getValue1());
            value2TextField.setText(existing.getValue2());
        }
    }

    private void save() {
        EntryService.saveEntry(existing, value1TextField.getText(), value2TextField.getText(), null, typeComboBox.getValue());
//                image != null ? image.getFileName() : null);
        onSaveListener.execute();
        closeWindowThatContains(value1TextField);
    }

//    private void openImageSelectionDialog() {
//        new StandardDialogBuilder().openImageSelectionDialog(
//                AuthorImageUtils.getFreeImagesForAuthorCreation(),
//                imageContainer -> {
//                    image = imageContainer;
//                    showImage(image.getImage());
//                });
//    }
//
//    private void showImage(Image image) {
//        imagePane.getChildren().clear();
//        Node imageNode = placeImageInSquare(new ImageView(image), 400);
//        imageNode.setOnMouseClicked(event -> openImageSelectionDialog());
//        imagePane.getChildren().add(imageNode);
//    }

}
