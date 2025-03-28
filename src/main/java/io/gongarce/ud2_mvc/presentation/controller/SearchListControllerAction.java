package io.gongarce.ud2_mvc.presentation.controller;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo
 */
public interface SearchListControllerAction<T> {
    List<T> search(Optional<String> searchText);
}
