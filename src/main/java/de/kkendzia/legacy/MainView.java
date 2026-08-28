package de.kkendzia.legacy;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;

@Route("")
@PageTitle("Legacy customers")
public class MainView extends VerticalLayout {

    private final CustomerService service;
    private final Grid<Customer> grid = new Grid<>(Customer.class);
    private final TextField filter = new TextField();

    public MainView(CustomerService service) {
        this.service = service;
    }

    @PostConstruct
    private void initialize() {
        H1 heading = new H1("Legacy customer administration");

        filter.setPlaceholder("Filter by last name");
        filter.setClearButtonVisible(true);
        filter.addValueChangeListener(event -> refresh());

        Button refresh = new Button("Refresh", event -> {
            refresh();
            Notification.show("Customer list refreshed");
        });

        grid.setColumns("id", "firstName", "lastName", "email");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        add(heading, new HorizontalLayout(filter, refresh), grid);
        setSizeFull();
        grid.setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(service.findAll(filter.getValue()));
    }
}

