package io.gongarce.ud2_mvc.presentation.controller.person;

import io.gongarce.ud2_mvc.application.GetPersonsUserCase;
import io.gongarce.ud2_mvc.presentation.App;
import io.gongarce.ud2_mvc.presentation.controller.SearchListController;
import io.gongarce.ud2_mvc.presentation.model.ListModel;
import io.gongarce.ud2_mvc.presentation.model.LoadingStatusModel;
import io.gongarce.ud2_mvc.presentation.model.person.TablePerson;
import io.gongarce.ud2_mvc.presentation.model.person.TablePersonMapper;
import java.util.List;
import java.util.Optional;
import javax.swing.text.Document;

/**
 *
 * @author Gonzalo
 */
public class PersonListController extends SearchListController<TablePerson> {

    public PersonListController(Document searchInput, ListModel<TablePerson> listModel, LoadingStatusModel loadingStatusModel) {
        super(searchInput, listModel, loadingStatusModel);
    }

    @Override
    public List<TablePerson> search(Optional<String> searchText) {
        GetPersonsUserCase useCase = App.get(GetPersonsUserCase.class);
        return TablePersonMapper.INSTANCE.toView(useCase.get(searchText));
    }
}
