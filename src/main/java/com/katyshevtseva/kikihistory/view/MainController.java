package com.katyshevtseva.kikihistory.view;

import com.katyshevtseva.fx.FxImageCreationUtil;
import com.katyshevtseva.fx.FxUtils;
import com.katyshevtseva.fx.WindowBuilder;
import com.katyshevtseva.fx.WindowBuilder.FxController;
import com.katyshevtseva.fx.switchcontroller.AbstractSwitchController;
import com.katyshevtseva.fx.switchcontroller.Section;
import com.katyshevtseva.kikihistory.view.dates.MainDatesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

import static com.katyshevtseva.fx.FxImageCreationUtil.getIcon;
import static com.katyshevtseva.kikihistory.view.utils.ViewConstants.HistoryNodeInfo.MAIN_DATES;

public class MainController extends AbstractSwitchController implements FxController {
    @FXML
    private Pane mainPane;
    @FXML
    private VBox buttonBox;
    @FXML
    private ImageView logoImageView;

    @FXML
    private void initialize() {
        logoImageView.setImage(getIcon(FxImageCreationUtil.IconPicture.HISTORY_COVER));
        init(getSections(), mainPane, this::placeButton);
    }

    private List<Section> getSections() {
        return Arrays.asList(new Section("Dates", new MainDatesController(),
                        controller -> WindowBuilder.getNode(MAIN_DATES, controller)),
                new Section("Dates", new MainDatesController(),
                        controller -> WindowBuilder.getNode(MAIN_DATES, controller)));
    }

    private void placeButton(Button button) {
        FxUtils.setWidth(button, 350);
        buttonBox.getChildren().addAll(FxUtils.getPaneWithHeight(40), button);
    }
}