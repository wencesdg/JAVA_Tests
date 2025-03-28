package io.gongarce.ud2_mvc.presentation.controller;

import io.gongarce.ud2_mvc.presentation.model.ListModel;
import io.gongarce.ud2_mvc.presentation.model.LoadingStatusModel;
import java.awt.event.ActionEvent;
import static java.util.Objects.nonNull;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.swing.AbstractAction;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor
public class SearchListController2<T> extends AbstractAction {

    @NonNull
    private final SearchListControllerAction<T> searchAction;

    @NonNull
    private final Document searchInput;

    @NonNull
    private final ListModel<T> listModel;

    @Nullable
    private final LoadingStatusModel loadingStatusModel;

    @Override
    public void actionPerformed(ActionEvent e) {
        setLoadingStatus(true);
        (new Thread(() -> {
            try {
                listModel.setData(searchAction.search(getSearchInput()));
            } finally {
                setLoadingStatus(false);
            }
        })).start();
    }

    private Optional<String> getSearchInput() {
        try {
            String text = searchInput.getText(0, searchInput.getLength());
            return text.isBlank() ? Optional.empty() : Optional.of(text);
        } catch (BadLocationException e) {
            return Optional.empty();
        }
    }

    private void setLoadingStatus(boolean status) {
        if (nonNull(loadingStatusModel)) {
            loadingStatusModel.setLoadingStatus(status);
        }
    }
}
