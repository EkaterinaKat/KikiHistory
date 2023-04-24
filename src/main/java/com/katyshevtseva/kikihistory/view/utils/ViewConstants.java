package com.katyshevtseva.kikihistory.view.utils;

import com.katyshevtseva.fx.Size;
import com.katyshevtseva.fx.WindowBuilder;
import com.katyshevtseva.kikihistory.core.CoreConstants;
import lombok.Getter;

public class ViewConstants {
    private static final String FXML_LOCATION = "/fxml/";
    public static final Size WINDOW_SIZE = new Size(1000, 1500);

    public enum HistoryNodeInfo implements WindowBuilder.NodeInfo {
        MAIN_DATES(FXML_LOCATION, "section_main.fxml"),
        DATES_LIST(FXML_LOCATION, "entries_list.fxml");

        private final String location;
        private final String fileName;

        HistoryNodeInfo(String location, String fileName) {
            this.location = location;
            this.fileName = fileName;
        }

        public String getFullFileName() {
            return location + fileName;
        }
    }

    public enum HistoryDialogInfo implements WindowBuilder.DialogInfo {
        MAIN(FXML_LOCATION, "main.fxml", WINDOW_SIZE, CoreConstants.APP_NAME),
        DATES_EDIT(FXML_LOCATION, "entry_edit_dialog.fxml", new Size(700, 700), CoreConstants.APP_NAME);

        private final String location;
        private final String fileName;
        @Getter
        private final Size size;
        @Getter
        private final String title;

        HistoryDialogInfo(String location, String fileName, Size size, String title) {
            this.location = location;
            this.fileName = fileName;
            this.size = size;
            this.title = title;
        }

        public String getFullFileName() {
            return location + fileName;
        }
    }
}
