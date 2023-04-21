package com.katyshevtseva.kikihistory.view.dates;

import com.katyshevtseva.fx.Size;
import com.katyshevtseva.fx.Styler;
import com.katyshevtseva.fx.WindowBuilder;
import com.katyshevtseva.fx.component.ComponentBuilder;
import com.katyshevtseva.fx.component.controller.PageableBlockListController;
import com.katyshevtseva.fx.dialog.StandardDialogBuilder;
import com.katyshevtseva.fx.switchcontroller.SectionController;
import com.katyshevtseva.kikihistory.core.DateService;
import com.katyshevtseva.kikihistory.core.model.Entry;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static com.katyshevtseva.fx.FxUtils.getPaneWithHeight;
import static com.katyshevtseva.fx.FxUtils.getPaneWithWidth;
import static com.katyshevtseva.fx.Styler.ThingToColor.BACKGROUND;
import static com.katyshevtseva.fx.Styler.getColorfullStyle;
import static com.katyshevtseva.fx.Styler.setHoverStyle;
import static com.katyshevtseva.kikihistory.view.utils.ViewConstants.HistoryDialogInfo.DATES_EDIT;

public class DatesListController implements SectionController {
    private static final Size LIST_SIZE = new Size(750, 900);
    private PageableBlockListController<Entry> entryListController;
    @FXML
    private Button newEntryButton;
    @FXML
    private Pane entriesPane;

    @FXML
    private void initialize() {
        newEntryButton.setOnAction(event -> openEntryEditDialog(null));

        adjustBlockListController();
        updateContent();
    }

    private void openEntryEditDialog(Entry entry) {
        WindowBuilder.openDialog(DATES_EDIT, new DateEditDialogController(entry, this::updateContent));
    }

    private void adjustBlockListController() {
        ComponentBuilder.Component<PageableBlockListController<Entry>> component =
                new ComponentBuilder().setSize(LIST_SIZE).getPageableBlockListComponent();
        entriesPane.getChildren().add(component.getNode());
        entryListController = component.getController();
    }

    private Node getEntryNode(Entry entry, int blockWidth) {
        Label dateLabel = new Label(entry.getValue2());
        Label eventLabel = new Label(entry.getValue1());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                getPaneWithHeight(10),
                dateLabel,
                getPaneWithHeight(10),
                eventLabel);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(getPaneWithWidth(10), vBox, getPaneWithWidth(10));
        hBox.setStyle(Styler.getBlackBorderStyle());
        setHoverStyle(hBox, getColorfullStyle(BACKGROUND, "#FAB036"));
        hBox.setOnContextMenuRequested(event -> showAuthorContextMenu(hBox, event, entry));
        return hBox;
    }

    private void showAuthorContextMenu(Node node, ContextMenuEvent event, Entry entry) {
        ContextMenu menu = new ContextMenu();

        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction(event1 -> openEntryEditDialog(entry));

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(event1 -> new StandardDialogBuilder().openQuestionDialog("Delete?", b -> {
            if (b) {
                DateService.delete(entry);
                updateContent();
            }
        }));

        menu.getItems().addAll(editItem, deleteItem);
        menu.show(node, event.getScreenX(), event.getScreenY());
    }

    private void updateContent() {
        entryListController.show(DateService::getDatePage, this::getEntryNode);
    }
}
