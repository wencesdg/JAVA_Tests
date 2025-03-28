package io.gongarce.ud2_mvc.presentation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Singular;

/**
 *
 * @author Gonzalo
 */
@Builder
public class LoadingStatusModel {

    public static interface LoadingStatusModelListener {

        void loadingStatusChanged(boolean loadingStatus);
    }

    @Builder.Default
    private boolean isLoading = false;
    @Singular
    private final Set<LoadingStatusModelListener> listeners;

    public void setLoadingStatus(boolean loading) {
        this.isLoading = loading;
        notifyListeners();
    }

    private void notifyListeners() {
        if (Objects.nonNull(listeners)) {
            listeners.forEach((l) -> l.loadingStatusChanged(isLoading));
        }
    }
    
    public void addStatusChangeListener(LoadingStatusModelListener listener) {
        listeners.add(listener);
    }
    
    public void removeStatusChangeListener(LoadingStatusModelListener listener) {
        listeners.remove(listener);
    }
}
