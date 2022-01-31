package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventListener;

import javax.swing.plaf.basic.BasicSplitPaneDivider;

import org.w3c.dom.NodeList;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Interface1 extends Application {
	static Connection databaseLink;
	static ObservableList<ObservableList> data;
	static ObservableList<ObservableList> dataEmployees;
	static ObservableList<ObservableList> dataOffices;
	static ObservableList<ObservableList> dataOrderDetails;
	static ObservableList<ObservableList> dataOrders;
	static ObservableList<ObservableList> dataPayments;
	static ObservableList<ObservableList> dataProductLines;
	static ObservableList<ObservableList> dataProducts;
	static ObservableList<ObservableList> dataQuery1;
	static ObservableList<ObservableList> dataQuery2;
	static ObservableList<ObservableList> dataQuery3;
	static ObservableList<ObservableList> dataQuery4;
	static ObservableList<ObservableList> dataQuery5;
	static ObservableList<ObservableList> dataQuery6;
	static ObservableList<ObservableList> dataQuery7;
	static ObservableList<ObservableList> dataQuery8;
	static ObservableList<ObservableList> dataQuery9;
	static ObservableList<ObservableList> dataQuery10;
	static TableView tableview = new TableView();
	static TableView tableviewEmployees = new TableView();
	static TableView tableviewOffices = new TableView();
	static TableView tableviewOrderDetails = new TableView();
	static TableView tableviewOrders = new TableView();
	static TableView tableviewPayments = new TableView();
	static TableView tableviewProductLines = new TableView();
	static TableView tableviewProducts = new TableView();
	static TableView tableviewQuery1 = new TableView();
	static TableView tableviewQuery2 = new TableView();
	static TableView tableviewQuery3 = new TableView();
	static TableView tableviewQuery4 = new TableView();
	static TableView tableviewQuery5 = new TableView();
	static TableView tableviewQuery6 = new TableView();
	static TableView tableviewQuery7 = new TableView();
	static TableView tableviewQuery8 = new TableView();
	static TableView tableviewQuery9 = new TableView();
	static TableView tableviewQuery10 = new TableView();

	@Override
	public void start(Stage primaryStage) throws Exception {
//		getConnection();
		Stage window = primaryStage;
		Scene primaryScene = new Scene(mainPane());
		window.setScene(primaryScene);
		window.setTitle("Database Controller");

		window.show();

	}

	public static Connection getConnection() {
		String dataBaseName = "201904045_danial";
		String dataBaseUser = "root";
		String dataBasePassword = "";
		String url = "jdbc:mysql://localhost/" + dataBaseName;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, dataBaseUser, dataBasePassword);
		} catch (Exception e) {
			System.out.println("Failed");
			e.printStackTrace();

		}
		return databaseLink;
	}

	public static BorderPane mainPane() {
		BorderPane firstPane = new BorderPane();
		TabPane bar = new TabPane();
		Tab dataBaseMenu = new Tab("Database");
		dataBaseMenu.setContent(dataBasePane());
		Tab informationMenu = new Tab("Information");
		informationMenu.setContent(informationPane());
		bar.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		bar.getTabs().addAll(dataBaseMenu, informationMenu);
		firstPane.setTop(bar);
		return firstPane;
	}

	public static BorderPane dataBasePane() {
		BorderPane dbPane = new BorderPane();
		dbPane.setPadding(new Insets(10, 10, 10, 10));
		TabPane menu = new TabPane();
		Tab customerTab = new Tab("Customers", customerPane());
		Tab employeeTab = new Tab("Employees", employeePane());
		Tab officeTab = new Tab("Offices", officePane());
		Tab orderDetailTab = new Tab("Order Details", orderDetailsPane());
		Tab ordersTab = new Tab("Orders", ordersPane());
		Tab paymentTab = new Tab("Payments", paymentsPane());
		Tab productlineTab = new Tab("Product Lines", productLinesPane());
		Tab productsTab = new Tab("Products", productsPane());
		Tab SpecialQueriesTab = new Tab("Specials", queriesPane());
		menu.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		menu.getTabs().addAll(customerTab, employeeTab, officeTab, orderDetailTab, ordersTab, paymentTab,
				productlineTab, productsTab, SpecialQueriesTab);

//		employeeTab.setContent(employeePane());
//		officeTab.setContent(officesPane());
		dbPane.setTop(menu);
		return dbPane;
	}

	@SuppressWarnings("unchecked")
	public static BorderPane customerPane() {
		BorderPane custPane = new BorderPane();
		Button btnSearch = new Button("Search");
		// Left Pane
		VBox bpaneLeft = new VBox();
		bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
		Label addNewCustLbl = new Label("Add new customer");
		addNewCustLbl.setMinSize(200, 10);
		addNewCustLbl.setAlignment(Pos.TOP_CENTER);
		Label addCustNumber = new Label("Customer Number: ");
		TextField addNumberTxt = new TextField();
		Label addCustName = new Label("Customer Name: ");
		TextField addNameTxt = new TextField();
		Label addCustCity = new Label("City: ");
		TextField addCityTxt = new TextField();
		Label addCustCountry = new Label("Country: ");
		TextField addCountryTxt = new TextField();
		Label addSalesRep = new Label("Sales Rep: ");
		TextField addSalesRepTxt = new TextField();
		Label addCreditLimit = new Label("CreditLimit: ");
		TextField addCreditLimitTxt = new TextField();
		Button addCustomer = new Button("Add");

		HBox hb1 = new HBox(10);
		hb1.getChildren().add(0, btnSearch);
		hb1.getChildren().add(0, addCustomer);

		try {
			String empNumber = "SELECT employeeNumber FROM employees";
			java.sql.Statement stmt1 = getConnection().createStatement();
			java.sql.ResultSet rs2 = stmt1.executeQuery(empNumber);
			rs2.last();
			int counter2 = rs2.getRow();
			rs2.first();
			String[] employeeNumberArray = new String[counter2 - 1];
			counter2 = 0;
			while (rs2.next()) {
				employeeNumberArray[counter2] = rs2.getString("employeeNumber");
				counter2++;
			}
			Collections.sort(Arrays.asList(employeeNumberArray));

			final ComboBox<String> salesEmpNumber = new ComboBox<String>(
					FXCollections.observableArrayList(employeeNumberArray));
			bpaneLeft.getChildren().add(0, addCustNumber);
			bpaneLeft.getChildren().add(1, addNumberTxt);
			bpaneLeft.getChildren().add(2, addCustName);
			bpaneLeft.getChildren().add(3, addNameTxt);
			bpaneLeft.getChildren().add(4, addCustCity);
			bpaneLeft.getChildren().add(5, addCityTxt);
			bpaneLeft.getChildren().add(6, addCustCountry);
			bpaneLeft.getChildren().add(7, addCountryTxt);
			bpaneLeft.getChildren().add(8, addSalesRep);
			bpaneLeft.getChildren().add(9, salesEmpNumber);
			bpaneLeft.getChildren().add(10, addCreditLimit);
			bpaneLeft.getChildren().add(11, addCreditLimitTxt);
			bpaneLeft.getChildren().add(12, hb1);
			bpaneLeft.setAlignment(Pos.CENTER_LEFT);

			custPane.setLeft(bpaneLeft);

//		Bottom Pane
			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			custPane.setBottom(bpaneBot);

			data = FXCollections.observableArrayList();
			try {
				String SQL2 = "SELECT * from customers";
				java.sql.ResultSet rs = getConnection().createStatement().executeQuery(SQL2);
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col2 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
					col2.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableview.getColumns().addAll(col2);
				}
				while (rs.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rs.getString(i));
					}
					data.add(row);
				}
				tableview.setItems(data);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			custPane.setCenter(tableview);

			btnSearch.setOnAction(actionEvent -> {
				tableview.getColumns().clear();
				tableview.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM customers";
					if (!(addNumberTxt.getText().isEmpty() && addNameTxt.getText().isEmpty()
							&& addCityTxt.getText().isEmpty() && addCountryTxt.getText().isEmpty()
							&& addCreditLimitTxt.getText().isEmpty() && salesEmpNumber.getValue() == null)) {
						searchIDSQL += " WHERE ";
						if (!(addNumberTxt.getText().isEmpty())) {
							boolean b = true;
							while (b) {
								if (isNumber(addNumberTxt.getText()) == true) {
									b = false;
									searchIDSQL += "customerNumber = " + (addNumberTxt.getText()) + " OR ";
								} else if (isNumber(addNumberTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}
						}
						if (!(addNameTxt.getText().isEmpty())) {
							searchIDSQL += "customerName LIKE '%" + addNameTxt.getText().trim() + "%' OR ";
						}
						if (!(addCityTxt.getText().isEmpty())) {
							searchIDSQL += "city LIKE '%" + addCityTxt.getText().trim() + "%' OR ";
						}
						if (!(addCountryTxt.getText().isEmpty())) {
							searchIDSQL += "country LIKE '%" + addCountryTxt.getText().trim() + "%' OR ";
						}
						if (!(addCreditLimitTxt.getText().isEmpty())) {
							boolean b = true;
							while (b) {

								if (isDouble(addCreditLimitTxt.getText()) == true) {
									b = false;
									searchIDSQL += "creditLimit = " + Double.parseDouble(addCreditLimitTxt.getText())
											+ " OR ";
								} else if (isDouble(addCreditLimitTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}
						}
						if (!(salesEmpNumber.getValue() == null)) {
							searchIDSQL += "salesRepEmployeeNumber = "
									+ Integer.parseInt(salesEmpNumber.getSelectionModel().getSelectedItem()) + " OR ";
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rs = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableview.getColumns().addAll(col1);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							data.add(row);
						}
						tableview.setItems(data);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement()
								.executeQuery("SELECT * FROM customers");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col2 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col2.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableview.getColumns().addAll(col2);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							data.add(row);
						}
						tableview.setItems(data);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(addNumberTxt.textProperty(), addNameTxt.textProperty(), addCityTxt.textProperty(),
							addCountryTxt.textProperty(), salesEmpNumber.valueProperty(),
							addCreditLimitTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (addNumberTxt.getText().isEmpty() || addNameTxt.getText().isEmpty()
							|| addCityTxt.getText().isEmpty() || addCountryTxt.getText().isEmpty()
							|| (salesEmpNumber.getSelectionModel().getSelectedItem() == null
									|| salesEmpNumber.getSelectionModel().getSelectedItem().isEmpty())
							|| addCreditLimitTxt.getText().isEmpty());
				}
			};
			addCustomer.disableProperty().bind(bb);

			addCustomer.setOnAction(actionEvent -> {
				try {
					java.sql.Statement stmt4 = getConnection().createStatement();
					String numberConfirm = "SELECT customerNumber FROM customers";
					java.sql.ResultSet rs = stmt4.executeQuery(numberConfirm);
					rs.last();
					int counter = rs.getRow();
					rs.first();
					String[] customerNumberArray = new String[counter];
					counter = 0;
					while (rs.next()) {
						customerNumberArray[counter] = rs.getString("customerNumber");
						counter++;
					}
					String addQuery = "INSERT INTO customers (customerNumber, customerName, city, country, salesRepEmployeeNumber, creditLimit) VALUES ('"
							+ Integer.parseInt(addNumberTxt.getText()) + "', '" + addNameTxt.getText() + "', '"
							+ addCityTxt.getText() + "', '" + addCountryTxt.getText() + "', '"
							+ Integer.parseInt(salesEmpNumber.getValue()) + "', '" + addCreditLimitTxt.getText()
							+ "');";
					stmt4.executeUpdate(addQuery);
					resultTxt.setText("Customer " + addNameTxt.getText() + " with number " + addNumberTxt.getText()
							+ " was Added.");
				}

				catch (Exception e2) {
					e2.printStackTrace();

				}
			});
			custPane.setCenter(tableview);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return custPane;

	}

	@SuppressWarnings("unchecked")
	public static BorderPane employeePane() {
		BorderPane empPane = new BorderPane();
		Button btnSearch = new Button("Search");
		// Left Pane
		VBox left = new VBox();
		left.setPadding(new Insets(10, 10, 10, 10));
		Label addNewEmpLbl = new Label("Add new employee");
		addNewEmpLbl.setMinSize(200, 10);
		addNewEmpLbl.setAlignment(Pos.TOP_CENTER);
		Label addEmpNumber = new Label("Employee Number: ");
		TextField addEmpNumberTxt = new TextField();
		Label addEmpFName = new Label("First Name: ");
		TextField addEmpFNameTxt = new TextField();
		Label addEmpLName = new Label("Last Name: ");
		TextField addEmpLNameTxt = new TextField();
		Label addEmpExtension = new Label("Extension: ");
		TextField addExtensionTxt = new TextField();
		Label addEmpEmail = new Label("Email: ");
		TextField addEmpEmailTxt = new TextField();
		Label addJobTitle = new Label("JobTitle: ");
		TextField addJobTitleTxt = new TextField();
		Button addEmployee = new Button("Add");

		HBox hb1 = new HBox(10);
		hb1.getChildren().add(0, btnSearch);
		hb1.getChildren().add(0, addEmployee);

		try {
			String oCodes = "SELECT officeCode FROM offices";
			java.sql.Statement stmt1 = getConnection().createStatement();
			java.sql.ResultSet rsOCodes = stmt1.executeQuery(oCodes);
			rsOCodes.last();
			int counter1 = rsOCodes.getRow();
			rsOCodes.first();
			String[] officeCodesArray = new String[counter1 - 1];
			counter1 = 0;
			while (rsOCodes.next()) {
				officeCodesArray[counter1] = rsOCodes.getString("officeCode");
				counter1++;
			}
			Collections.sort(Arrays.asList(officeCodesArray));

			Label addOfficeCode = new Label("OfficeCode: ");
			ComboBox<String> boxOfficeCodes = new ComboBox<String>(FXCollections.observableArrayList(officeCodesArray));

			String repTo = "SELECT employeeNumber FROM employees";
			java.sql.Statement stmtRepTo = getConnection().createStatement();
			java.sql.ResultSet rsRepTo = stmtRepTo.executeQuery(repTo);
			rsRepTo.last();
			int x = rsRepTo.getRow();
			rsRepTo.first();
			String[] repToArray = new String[x - 1];
			x = 0;
			while (rsRepTo.next()) {
				repToArray[x] = rsRepTo.getString("employeeNumber");
				x++;
			}
			Collections.sort(Arrays.asList(repToArray));
			Label addReportsTo = new Label("Reports To: ");
			ComboBox<String> boxReportsTo = new ComboBox<String>(FXCollections.observableArrayList(repToArray));

			left.getChildren().add(0, addEmpNumber);
			left.getChildren().add(1, addEmpNumberTxt);
			left.getChildren().add(2, addEmpFName);
			left.getChildren().add(3, addEmpFNameTxt);
			left.getChildren().add(4, addEmpLName);
			left.getChildren().add(5, addEmpLNameTxt);
			left.getChildren().add(6, addEmpExtension);
			left.getChildren().add(7, addExtensionTxt);
			left.getChildren().add(8, addEmpEmail);
			left.getChildren().add(9, addEmpEmailTxt);
			left.getChildren().add(10, addOfficeCode);
			left.getChildren().add(11, boxOfficeCodes);
			left.getChildren().add(12, addReportsTo);
			left.getChildren().add(13, boxReportsTo);
			left.getChildren().add(14, addJobTitle);
			left.getChildren().add(15, addJobTitleTxt);
			left.getChildren().add(16, hb1);
			left.setAlignment(Pos.CENTER_LEFT);

			empPane.setLeft(left);

//			Bottom Pane
			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			empPane.setBottom(bpaneBot);

			dataEmployees = FXCollections.observableArrayList();
			try {
				String SQL = "SELECT * from employees";
				java.sql.ResultSet rs = getConnection().createStatement().executeQuery(SQL);
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
					col1.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewEmployees.getColumns().addAll(col1);
				}
				while (rs.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rs.getString(i));
					}
					dataEmployees.add(row);
				}
				tableviewEmployees.setItems(dataEmployees);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			empPane.setCenter(tableviewEmployees);

			btnSearch.setOnAction(actionEvent -> {
				tableviewEmployees.getColumns().clear();
				tableviewEmployees.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM employees";
					if (!(addEmpNumberTxt.getText().isEmpty() && addEmpFNameTxt.getText().isEmpty()
							&& addEmpLName.getText().isEmpty() && addExtensionTxt.getText().isEmpty()
							&& addEmpEmailTxt.getText().isEmpty() && boxOfficeCodes.getValue() == null
							&& boxReportsTo.getValue() == null && addJobTitleTxt.getText().isEmpty())) {
						searchIDSQL += " WHERE ";
						if (!(addEmpNumberTxt.getText().isEmpty())) {
							boolean b = true;
							while (b) {
								if (isNumber(addEmpNumberTxt.getText()) == true) {
									b = false;
									searchIDSQL += "employeeNumber = " + addEmpNumberTxt.getText() + " OR ";
								} else if (isNumber(addEmpNumberTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}

						}

						if (!(addEmpFNameTxt.getText().isEmpty())) {
							searchIDSQL += "firstName LIKE '%" + addEmpFNameTxt.getText().trim() + "%' OR ";
						}
						if (!(addEmpLName.getText().isEmpty())) {
							searchIDSQL += "lastName LIKE '%" + addEmpLName.getText().trim() + "%' OR ";
						}
						if (!(addExtensionTxt.getText().isEmpty())) {
							searchIDSQL += "extension LIKE '%" + addExtensionTxt.getText().trim() + "%' OR ";
						}
						if (!(addEmpEmailTxt.getText().isEmpty())) {
							searchIDSQL += "email LIKE '%" + addEmpEmailTxt.getText() + "%' OR ";
						}
						if (!(boxOfficeCodes.getValue() == null)) {
							searchIDSQL += "officeCode = " + boxOfficeCodes.getSelectionModel().getSelectedItem()
									+ " OR ";
						}
						if (!(boxReportsTo.getValue() == null)) {
							searchIDSQL += "reportsTo = " + boxReportsTo.getValue() + " OR ";
						}
						if (!(addJobTitleTxt.getText().isEmpty())) {
							searchIDSQL += "jobTitle LIKE '%" + addJobTitleTxt.getText().trim() + "%' OR ";
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsEmployees = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsEmployees.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsEmployees.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewEmployees.getColumns().addAll(col1);
						}
						while (rsEmployees.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsEmployees.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsEmployees.getString(i));
							}
							dataEmployees.add(row);
						}
						tableviewEmployees.setItems(dataEmployees);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement()
								.executeQuery("SELECT * FROM employees");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewEmployees.getColumns().addAll(col1);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							dataEmployees.add(row);
						}
						tableviewEmployees.setItems(dataEmployees);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(addEmpNumberTxt.textProperty(), addEmpFNameTxt.textProperty(),
							addEmpLName.textProperty(), addExtensionTxt.textProperty(), addEmpEmailTxt.textProperty(),
							boxOfficeCodes.valueProperty(), boxReportsTo.valueProperty(),
							addJobTitleTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (addEmpNumberTxt.getText().isEmpty() || addEmpFNameTxt.getText().isEmpty()
							|| addEmpLName.getText().isEmpty() || addExtensionTxt.getText().isEmpty()
							|| addEmpEmailTxt.getText().isEmpty()
							|| (boxOfficeCodes.getSelectionModel().getSelectedItem() == null
									|| boxOfficeCodes.getSelectionModel().getSelectedItem().isEmpty())
							|| (boxReportsTo.getSelectionModel().getSelectedItem() == null
									|| boxReportsTo.getSelectionModel().getSelectedItem().isEmpty())
							|| addJobTitleTxt.getText().isEmpty());
				}
			};
			addEmployee.disableProperty().bind(bb);

			addEmployee.setOnAction(actionEvent -> {
				try {
					java.sql.Statement stmt4 = getConnection().createStatement();
					String numberConfirm = "SELECT employeeNumber FROM employees";
					java.sql.ResultSet rs = stmt4.executeQuery(numberConfirm);
					rs.last();
					int counter = rs.getRow();
					rs.first();
					String[] employeeNumbersArr = new String[counter];
					counter = 0;
					while (rs.next()) {
						employeeNumbersArr[counter] = rs.getString("employeeNumber");
						counter++;
					}

					String addQuery = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES ('"
							+ Integer.parseInt(addEmpNumberTxt.getText()) + "', '" + addEmpLNameTxt.getText() + "', '"
							+ addEmpFNameTxt.getText() + "', '" + addExtensionTxt.getText() + "', '"
							+ addEmpEmailTxt.getText() + "', '" + boxOfficeCodes.getValue() + "', '"
							+ boxReportsTo.getValue() + "', '" + addJobTitleTxt.getText() + "');";
					stmt4.executeUpdate(addQuery);
					resultTxt.setText("Employee " + addEmpFNameTxt.getText() + " " + addEmpLNameTxt.getText()
							+ " with number " + addEmpNumberTxt.getText() + " was Added.");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			});
			empPane.setCenter(tableviewEmployees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empPane;
	}

	@SuppressWarnings("unchecked")
	public static BorderPane officePane() {
		BorderPane offPane = new BorderPane();

		// Left Pane
		VBox bpaneLeft = new VBox();
		bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
		Label officeLbl = new Label("Offices");
		officeLbl.setMinSize(200, 10);
		officeLbl.setAlignment(Pos.TOP_CENTER);
		Label addOfficeCode = new Label("Office Code: ");
		TextField addOfficeCodeTxt = new TextField();
		Label addOfficeCity = new Label("City: ");
		TextField addOfficeCityTxt = new TextField();
		Label addOfficeState = new Label("State: ");
		TextField addOfficeStateTxt = new TextField();
		Label addOfficeCountry = new Label("Country: ");
		TextField addOfficeCountryTxt = new TextField();
		Button btnAdd = new Button("Add");
		Button btnSearch = new Button("Search");
		HBox hb1 = new HBox(10);
		hb1.getChildren().add(0, btnSearch);
		hb1.getChildren().add(0, btnAdd);

		try {
			bpaneLeft.getChildren().add(0, officeLbl);
			bpaneLeft.getChildren().add(1, addOfficeCode);
			bpaneLeft.getChildren().add(2, addOfficeCodeTxt);
			bpaneLeft.getChildren().add(3, addOfficeCity);
			bpaneLeft.getChildren().add(4, addOfficeCityTxt);
			bpaneLeft.getChildren().add(5, addOfficeState);
			bpaneLeft.getChildren().add(6, addOfficeStateTxt);
			bpaneLeft.getChildren().add(7, addOfficeCountry);
			bpaneLeft.getChildren().add(8, addOfficeCountryTxt);
			bpaneLeft.getChildren().add(9, hb1);

			bpaneLeft.setAlignment(Pos.CENTER_LEFT);

			offPane.setLeft(bpaneLeft);

			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			offPane.setBottom(bpaneBot);

			dataOffices = FXCollections.observableArrayList();
			try {
				String SQL3 = "SELECT * from offices";
				java.sql.ResultSet rsOffices = getConnection().createStatement().executeQuery(SQL3);
				for (int i = 0; i < rsOffices.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col2 = new TableColumn(rsOffices.getMetaData().getColumnName(i + 1));
					col2.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewOffices.getColumns().addAll(col2);
				}
				while (rsOffices.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsOffices.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsOffices.getString(i));
					}
					dataOffices.add(row);
				}
				tableviewOffices.setItems(dataOffices);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			offPane.setCenter(tableviewOffices);

			btnSearch.setOnAction(actionEvent -> {
				tableviewOffices.getColumns().clear();
				tableviewOffices.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM offices";
					if (!(addOfficeCodeTxt.getText().isEmpty() && addOfficeCityTxt.getText().isEmpty()
							&& addOfficeStateTxt.getText().isEmpty() && addOfficeCountryTxt.getText().isEmpty())) {
						searchIDSQL += " WHERE ";
						if (!(addOfficeCodeTxt.getText().isEmpty())) {
							boolean b = true;
							while (b) {
								if (isNumber(addOfficeCodeTxt.getText()) == true) {
									b = false;
									searchIDSQL += "officeCode = " + Integer.parseInt(addOfficeCodeTxt.getText())
											+ " OR ";
								} else if (isNumber(addOfficeCode.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}
						}
						if (!(addOfficeCityTxt.getText().isEmpty())) {
							searchIDSQL += "city LIKE '%" + addOfficeCityTxt.getText().trim() + "%' OR ";
						}
						if (!(addOfficeStateTxt.getText().isEmpty())) {
							searchIDSQL += "state LIKE '%" + addOfficeStateTxt.getText().trim() + "%' OR ";
						}
						if (!(addOfficeCountryTxt.getText().isEmpty())) {
							searchIDSQL += "country LIKE '%" + addOfficeCountryTxt.getText().trim() + "%' OR ";
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsOfficeSearch = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsOfficeSearch.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col1 = new TableColumn(rsOfficeSearch.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOffices.getColumns().addAll(col1);
						}
						while (rsOfficeSearch.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsOfficeSearch.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsOfficeSearch.getString(i));
							}
							dataOffices.add(row);
						}
						tableviewOffices.setItems(dataOffices);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM offices");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col2 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col2.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOffices.getColumns().addAll(col2);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							dataOffices.add(row);
						}
						tableviewOffices.setItems(dataOffices);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(addOfficeCodeTxt.textProperty(), addOfficeCityTxt.textProperty(),
							addOfficeStateTxt.textProperty(), addOfficeCountryTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (addOfficeCodeTxt.getText().isEmpty() || addOfficeCityTxt.getText().isEmpty()
							|| addOfficeStateTxt.getText().isEmpty() || addOfficeCountryTxt.getText().isEmpty());
				}
			};
			btnAdd.disableProperty().bind(bb);

			btnAdd.setOnAction(actionEvent -> {
				try {
					java.sql.Statement stmt4 = getConnection().createStatement();
					String addQuery = "INSERT INTO offices (officeCode, city, state,country) VALUES ('"
							+ Integer.parseInt(addOfficeCodeTxt.getText()) + "', '" + addOfficeCityTxt.getText()
							+ "', '" + addOfficeStateTxt.getText() + "', '" + addOfficeCountryTxt.getText() + "');";
					stmt4.executeUpdate(addQuery);
					resultTxt.setText("Office with code " + addOfficeCodeTxt.getText() + " was added.");
				}

				catch (Exception e2) {
					e2.printStackTrace();

				}
			});
			offPane.setCenter(tableviewOffices);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return offPane;
	}

	@SuppressWarnings("unchecked")
	public static BorderPane orderDetailsPane() {
		BorderPane oDetailsPane = new BorderPane();

		try {

			String orderNumber = "SELECT orderNumber FROM orders";
			java.sql.Statement stmtp = getConnection().createStatement();
			java.sql.ResultSet oNumCodes = stmtp.executeQuery(orderNumber);
			oNumCodes.last();
			int counter1 = oNumCodes.getRow();
			oNumCodes.first();
			String[] orderNumbersArray = new String[counter1 - 1];
			counter1 = 0;
			while (oNumCodes.next()) {
				orderNumbersArray[counter1] = oNumCodes.getString("orderNumber");
				counter1++;
			}
			String productCode = "SELECT productCode FROM products";
			java.sql.Statement stmtPCode = getConnection().createStatement();
			java.sql.ResultSet rsPCode = stmtPCode.executeQuery(productCode);
			rsPCode.last();
			int x = rsPCode.getRow();
			rsPCode.first();
			String[] pCodeArray = new String[x - 1];
			x = 0;
			while (rsPCode.next()) {
				pCodeArray[x] = rsPCode.getString("productCode");
				x++;
			}
			Collections.sort(Arrays.asList(pCodeArray));

			String productLines = "SELECT distinct orderLineNumber FROM orderDetails";
			java.sql.Statement stmtPLine = getConnection().createStatement();
			java.sql.ResultSet rsPLine = stmtPCode.executeQuery(productLines);
			rsPLine.last();
			int count = rsPLine.getRow();
			rsPLine.first();
			String[] pLineArray = new String[count - 1];
			count = 0;
			while (rsPLine.next()) {
				pLineArray[count] = rsPLine.getString("orderLineNumber");
				count++;
			}
			Collections.sort(Arrays.asList(pLineArray));

			// Left Pane
			VBox bpaneLeft = new VBox();
			bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
			Label orderDetailsLbl = new Label("Order Details");
			orderDetailsLbl.setMinSize(200, 10);
			orderDetailsLbl.setAlignment(Pos.TOP_CENTER);
			Label searchOrderNumber = new Label("Order Number: ");
			ComboBox<String> orderNumberBox = new ComboBox<String>(
					FXCollections.observableArrayList(orderNumbersArray));
			Label searchProductCode = new Label("Product Code: ");
			ComboBox<String> productCodeBox = new ComboBox<String>(FXCollections.observableArrayList(pCodeArray));
			Label searchQuantityOrdered = new Label("Quantity Ordered: ");
			TextField searchQuantityOrderedTxt = new TextField();
			Label searchPriceOfEach = new Label("Price: ");
			TextField searchPriceOfEachTxt = new TextField();
			Label searchProductLineNumber = new Label("Order Line Number: ");
			ComboBox<String> productLineNumberBox = new ComboBox<String>(FXCollections.observableArrayList(pLineArray));
			Button btnSearch = new Button("Search");
			HBox hb1 = new HBox(10);
			hb1.getChildren().add(0, btnSearch);
			bpaneLeft.getChildren().add(0, orderDetailsLbl);
			bpaneLeft.getChildren().add(1, searchOrderNumber);
			bpaneLeft.getChildren().add(2, orderNumberBox);
			bpaneLeft.getChildren().add(3, searchProductCode);
			bpaneLeft.getChildren().add(4, productCodeBox);
			bpaneLeft.getChildren().add(5, searchQuantityOrdered);
			bpaneLeft.getChildren().add(6, searchQuantityOrderedTxt);
			bpaneLeft.getChildren().add(7, searchPriceOfEach);
			bpaneLeft.getChildren().add(8, searchPriceOfEachTxt);
			bpaneLeft.getChildren().add(9, searchProductLineNumber);
			bpaneLeft.getChildren().add(10, productLineNumberBox);
			bpaneLeft.getChildren().add(11, hb1);

			bpaneLeft.setAlignment(Pos.CENTER_LEFT);

			oDetailsPane.setLeft(bpaneLeft);

			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			oDetailsPane.setBottom(bpaneBot);

			dataOrderDetails = FXCollections.observableArrayList();
			try {
				String orderDetailsSQL = "SELECT * from orderDetails";
				java.sql.ResultSet rsOD = getConnection().createStatement().executeQuery(orderDetailsSQL);
				for (int i = 0; i < rsOD.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col1 = new TableColumn(rsOD.getMetaData().getColumnName(i + 1));
					col1.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewOrderDetails.getColumns().addAll(col1);
				}
				while (rsOD.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsOD.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsOD.getString(i));
					}
					dataOrderDetails.add(row);
				}
				tableviewOrderDetails.setItems(dataOrderDetails);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			oDetailsPane.setCenter(tableviewOrderDetails);

			btnSearch.setOnAction(actionEvent -> {
				tableviewOrderDetails.getColumns().clear();
				tableviewOrderDetails.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM orderDetails";
					if (!(orderNumberBox.getValue() == null && productCodeBox.getValue() == null
							&& searchQuantityOrderedTxt.getText().isEmpty() && searchPriceOfEachTxt.getText().isEmpty()
							&& productLineNumberBox.getValue() == null)) {
						searchIDSQL += " WHERE ";
						if (!(orderNumberBox.getValue() == null)) {
							searchIDSQL += "orderNumber = " + orderNumberBox.getValue() + " OR ";
						}
						if (!(productCodeBox.getValue() == null)) {
							searchIDSQL += "productCode LIKE '%" + productCodeBox.getValue() + "%' OR ";
						}
						if (!(searchQuantityOrderedTxt.getText().isEmpty())) {
							boolean b = true;

							while (b) {

								if (isNumber(searchQuantityOrderedTxt.getText()) == true) {

									b = false;
									searchIDSQL += "quantityOrdered LIKE '%" + searchQuantityOrderedTxt.getText().trim()
											+ "%' OR ";
								} else if (isNumber(searchQuantityOrderedTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}

						}
						if (!(searchPriceOfEachTxt.getText().isEmpty())) {
							boolean b = true;

							while (b) {

								if (isDouble(searchPriceOfEachTxt.getText()) == true) {

									b = false;
									searchIDSQL += "priceEach LIKE '%" + searchPriceOfEachTxt.getText().trim()
											+ "%' OR ";
								} else if (isDouble(searchPriceOfEachTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}
						}
						if (!(productLineNumberBox.getValue() == null)) {
							searchIDSQL += "orderLineNumber = " + productLineNumberBox.getValue() + " OR ";
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsOrderDetails = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsOrderDetails.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsOrderDetails.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOrderDetails.getColumns().addAll(col1);
						}
						while (rsOrderDetails.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsOrderDetails.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsOrderDetails.getString(i));
							}
							dataOrderDetails.add(row);
						}
						tableviewOrderDetails.setItems(dataOrderDetails);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement()
								.executeQuery("SELECT * FROM orderDetails");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOrderDetails.getColumns().addAll(col1);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							dataOrderDetails.add(row);
						}
						tableviewOrderDetails.setItems(dataOrderDetails);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oDetailsPane;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BorderPane ordersPane() {
		BorderPane orderPane = new BorderPane();
		try {

			String orderNumber = "SELECT orderNumber FROM orders";
			java.sql.Statement stmtON = getConnection().createStatement();
			java.sql.ResultSet oNumCodes = stmtON.executeQuery(orderNumber);
			oNumCodes.last();
			int orderCounter = oNumCodes.getRow();
			oNumCodes.first();
			String[] ordersArray = new String[orderCounter - 1];
			orderCounter = 0;
			while (oNumCodes.next()) {
				ordersArray[orderCounter] = oNumCodes.getString("orderNumber");
				orderCounter++;
			}

			String csNumber = "SELECT customerNumber FROM customers";
			java.sql.Statement stmtCSNumber = getConnection().createStatement();
			java.sql.ResultSet rsCSNumber = stmtCSNumber.executeQuery(csNumber);
			rsCSNumber.last();
			int csNumberCount = rsCSNumber.getRow();
			rsCSNumber.first();
			String[] csNumberArray = new String[csNumberCount - 1];
			csNumberCount = 0;
			while (rsCSNumber.next()) {
				csNumberArray[csNumberCount] = rsCSNumber.getString("customerNumber");
				csNumberCount++;
			}
			Collections.sort(Arrays.asList(csNumberArray));

			// Left Pane
			VBox bpaneLeft = new VBox(5);
			bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
			Label ordersLbl = new Label("Search Orders");
			ordersLbl.setMinSize(200, 10);
			ordersLbl.setAlignment(Pos.TOP_CENTER);
			Label searchOrderNumber = new Label("Order Number: ");
			ComboBox<String> orderNumberBox = new ComboBox<String>(FXCollections.observableArrayList(ordersArray));
			Label searchDate = new Label("Date of order: ");
			DatePicker searchDatePicker = new DatePicker();
			Label searchCustomerNumber = new Label("Customer Number: ");
			ComboBox<String> customerNumberBox = new ComboBox<String>(FXCollections.observableArrayList(csNumberArray));

			Button btnSearch = new Button("Search");
			Button btnClear = new Button("Clear");
			HBox hb1 = new HBox(10);
			hb1.getChildren().add(0, customerNumberBox);
			hb1.getChildren().add(1, btnClear);
			bpaneLeft.getChildren().add(0, ordersLbl);
			bpaneLeft.getChildren().add(1, searchOrderNumber);
			bpaneLeft.getChildren().add(2, orderNumberBox);
			bpaneLeft.getChildren().add(3, searchDate);
			bpaneLeft.getChildren().add(4, searchDatePicker);
			bpaneLeft.getChildren().add(5, searchCustomerNumber);
			bpaneLeft.getChildren().add(6, hb1);
			bpaneLeft.getChildren().add(7, btnSearch);
			bpaneLeft.setAlignment(Pos.CENTER_LEFT);
			orderPane.setLeft(bpaneLeft);
			btnClear.setOnAction(e -> {
				orderNumberBox.valueProperty().set(null);
				customerNumberBox.valueProperty().set(null);
			});
			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			orderPane.setBottom(bpaneBot);

			dataOrders = FXCollections.observableArrayList();
			try {
				String ordersSQL = "SELECT * from orders";
				java.sql.ResultSet rsOrders = getConnection().createStatement().executeQuery(ordersSQL);
				for (int i = 0; i < rsOrders.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col1 = new TableColumn(rsOrders.getMetaData().getColumnName(i + 1));
					col1.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewOrders.getColumns().addAll(col1);
				}
				while (rsOrders.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsOrders.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsOrders.getString(i));
					}
					dataOrders.add(row);
				}
				tableviewOrders.setItems(dataOrders);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			orderPane.setCenter(tableviewOrders);

			btnSearch.setOnAction(actionEvent -> {
				tableviewOrders.getColumns().clear();
				tableviewOrders.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM orders";
					if (!(orderNumberBox.getValue() == null && searchDatePicker.getValue() == null
							&& customerNumberBox.getValue() == null)) {
						searchIDSQL += " WHERE ";
						if (!(orderNumberBox.getValue() == null)) {
							searchIDSQL += "orderNumber = " + orderNumberBox.getValue() + " OR ";
						}
						if (!(searchDatePicker.getValue() == null)) {
							searchIDSQL += "orderDate = '" + searchDatePicker.getValue() + "' OR ";
						}
						if (!(customerNumberBox.getValue() == null)) {
							searchIDSQL += "customerNumber = " + customerNumberBox.getValue() + " OR ";
						}

						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsOrders = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsOrders.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsOrders.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOrders.getColumns().addAll(col1);
						}
						while (rsOrders.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsOrders.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsOrders.getString(i));
							}
							dataOrders.add(row);
						}
						tableviewOrders.setItems(dataOrders);
					} else {
						java.sql.ResultSet rsO = getConnection().createStatement().executeQuery("SELECT * FROM orders");
						for (int i = 0; i < rsO.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsO.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewOrders.getColumns().addAll(col1);
						}
						while (rsO.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsO.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsO.getString(i));
							}
							dataOrders.add(row);
						}
						tableviewOrders.setItems(dataOrders);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			// right pane
			VBox bpaneRight = new VBox(5);
			bpaneRight.setPadding(new Insets(10, 10, 10, 10));
			Label ordersAddLbl = new Label("Add Orders");
			ordersAddLbl.setMinSize(200, 10);
			ordersAddLbl.setAlignment(Pos.TOP_CENTER);
			Label addDate = new Label("Date of order: ");
			DatePicker addDatePicker = new DatePicker();
			Label AddToCustomerNumber = new Label("Customer Number: ");
			ComboBox<String> AddCustomerNumberBox = new ComboBox<String>(
					FXCollections.observableArrayList(csNumberArray));
			Button btnAdd = new Button("Add");
			Button btnClearAdd = new Button("Clear");
			HBox h2 = new HBox(10);
			h2.getChildren().add(0, AddCustomerNumberBox);
			h2.getChildren().add(1, btnClearAdd);
			bpaneRight.getChildren().add(0, ordersAddLbl);
			bpaneRight.getChildren().add(1, addDate);
			bpaneRight.getChildren().add(2, addDatePicker);
			bpaneRight.getChildren().add(3, AddToCustomerNumber);
			bpaneRight.getChildren().add(4, h2);
			bpaneRight.getChildren().add(5, btnAdd);
			bpaneRight.setAlignment(Pos.CENTER_LEFT);
			orderPane.setRight(bpaneRight);
			btnClearAdd.setOnAction(e -> {
				addDatePicker.valueProperty().set(null);
				AddCustomerNumberBox.valueProperty().set(null);
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(addDatePicker.valueProperty(), AddCustomerNumberBox.valueProperty());
				}

				@Override
				protected boolean computeValue() {
					return (addDatePicker.getValue() == null || AddCustomerNumberBox.getValue() == null);
				}
			};
			btnAdd.disableProperty().bind(bb);

			btnAdd.setOnAction(actionEvent -> {
				try {

					java.sql.Statement stmtOrders = getConnection().createStatement();
					String addQuery = "INSERT INTO orders (orderDate, customerNumber) VALUES ('"
							+ addDatePicker.getValue() + "', '" + AddCustomerNumberBox.getValue() + "');";
					stmtOrders.executeUpdate(addQuery);
				}

				catch (Exception e2) {
					e2.printStackTrace();

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderPane;
	}

	@SuppressWarnings({ "unchecked" })
	public static BorderPane paymentsPane() {
		BorderPane payPane = new BorderPane();
		String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		Arrays.asList(alphabet);

		// Left Pane
		VBox bpaneLeft = new VBox(5);
		bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
		Label paymentsLbl = new Label("Payments");
		paymentsLbl.setMinSize(200, 10);
		paymentsLbl.setAlignment(Pos.TOP_CENTER);
		Label addCustomerNumberLbl = new Label("Customer Number: ");
		TextField addCustomerNumberTxt = new TextField();
		Label addCheckNumberLbl = new Label("Check Number: ");
		ComboBox<String> letter1 = new ComboBox<String>(FXCollections.observableArrayList(alphabet));
		ComboBox<String> letter2 = new ComboBox<String>(FXCollections.observableArrayList(alphabet));
		TextField addCheckNumberTxt = new TextField();
		HBox hbNumber = new HBox(2);
		hbNumber.getChildren().add(0, letter1);
		hbNumber.getChildren().add(1, letter2);
		hbNumber.getChildren().add(2, addCheckNumberTxt);

		Label addDate = new Label("Date of Payment: ");
		DatePicker addDatePicker = new DatePicker();
		Label addAmountLbl = new Label("Amount: ");
		TextField addAmountTxt = new TextField();
		Button btnAdd = new Button("Add");
		Button btnSearch = new Button("Search");
		HBox hb1 = new HBox(10);
		hb1.getChildren().add(0, btnSearch);
		hb1.getChildren().add(0, btnAdd);

		try {
			bpaneLeft.getChildren().add(0, paymentsLbl);
			bpaneLeft.getChildren().add(1, addCustomerNumberLbl);
			bpaneLeft.getChildren().add(2, addCustomerNumberTxt);
			bpaneLeft.getChildren().add(3, addCheckNumberLbl);
			bpaneLeft.getChildren().add(4, hbNumber);
			bpaneLeft.getChildren().add(5, addDate);
			bpaneLeft.getChildren().add(6, addDatePicker);
			bpaneLeft.getChildren().add(7, addAmountLbl);
			bpaneLeft.getChildren().add(8, addAmountTxt);
			bpaneLeft.getChildren().add(9, hb1);

			bpaneLeft.setAlignment(Pos.CENTER_LEFT);

			payPane.setLeft(bpaneLeft);

			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			payPane.setBottom(bpaneBot);

			dataPayments = FXCollections.observableArrayList();
			try {
				String sqlPayments = "SELECT * from payments";
				java.sql.ResultSet rsPayments = getConnection().createStatement().executeQuery(sqlPayments);
				for (int i = 0; i < rsPayments.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col2 = new TableColumn(rsPayments.getMetaData().getColumnName(i + 1));
					col2.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewPayments.getColumns().addAll(col2);
				}
				while (rsPayments.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsPayments.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsPayments.getString(i));
					}
					dataPayments.add(row);
				}
				tableviewPayments.setItems(dataPayments);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			payPane.setCenter(tableviewPayments);

			btnSearch.setOnAction(actionEvent -> {
				tableviewPayments.getColumns().clear();
				tableviewPayments.getItems().clear();
				String checkNumber = "";
				if (!(letter1.getValue() == null && letter2.getValue() == null
						&& addCheckNumberTxt.getText().isEmpty())) {
					checkNumber = letter1.getValue().toString() + letter2.getValue().toString()
							+ addCheckNumberTxt.getText().toString();
					System.out.println(checkNumber);
				}
				if (!(letter1.getValue() == null)) {
					checkNumber += letter1.getValue().toString();
				}
				if (!(letter2.getValue() == null)) {
					checkNumber += letter2.getValue().toString();
				}
				if (!(addCheckNumberTxt.getText().isEmpty())) {
					checkNumber += addCheckNumberTxt.getText().toString();
				}
				try {

					String searchIDSQL = "SELECT * FROM payments";
					if (!(addCustomerNumberTxt.getText().isEmpty() && checkNumber.isEmpty()
							&& addDatePicker.getValue() == null && addAmountTxt.getText().isEmpty())) {

						searchIDSQL += " WHERE ";
						if (!(addCustomerNumberTxt.getText().isEmpty())) {
							boolean b = true;

							while (b) {

								if (isNumber(addCustomerNumberTxt.getText()) == true) {
									b = false;
									searchIDSQL += "customerNumber = "
											+ Integer.parseInt(addCustomerNumberTxt.getText()) + " OR ";
								} else if (isNumber(addCustomerNumberTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}

						}
						if (!(checkNumber.isEmpty())) {
							searchIDSQL += "checkNumber LIKE '%" + checkNumber + "%' OR ";
						}
						if (!(addDatePicker.getValue() == null)) {
							searchIDSQL += "paymentDate = '" + addDatePicker.getValue() + "' OR ";
						}
						if (!(addAmountTxt.getText().isEmpty())) {
							boolean b = true;
							while (b) {
								if (isDouble(addAmountTxt.getText()) == true) {
									b = false;
									searchIDSQL += "amount LIKE '%" + addAmountTxt.getText().trim() + "%' OR ";
								} else if (isNumber(addAmountTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsPaymentSearch = getConnection().createStatement()
								.executeQuery(searchIDSQL);
						for (int i = 0; i < rsPaymentSearch.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col1 = new TableColumn(rsPaymentSearch.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewPayments.getColumns().addAll(col1);
						}
						while (rsPaymentSearch.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsPaymentSearch.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsPaymentSearch.getString(i));
							}
							dataPayments.add(row);
						}
						tableviewPayments.setItems(dataPayments);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement()
								.executeQuery("SELECT * FROM payments");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							// We are using non property style for making dynamic table
							final int j = i;
							TableColumn col2 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col2.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewPayments.getColumns().addAll(col2);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							dataPayments.add(row);
						}
						tableviewPayments.setItems(dataPayments);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(addCustomerNumberTxt.textProperty(), letter1.valueProperty(), letter2.valueProperty(),
							addCheckNumberTxt.textProperty(), addDatePicker.valueProperty(),
							addAmountTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (addCustomerNumberTxt.getText().isEmpty() || letter1.getValue() == null
							|| letter2.getValue() == null || addCheckNumberTxt.getText().isEmpty()
							|| addDatePicker.getValue() == null || addAmountTxt.getText().isEmpty());
				}
			};
			btnAdd.disableProperty().bind(bb);

			btnAdd.setOnAction(actionEvent -> {
				String checkNumberToAdd = "";
				if (!(letter1.getValue() == null && letter2.getValue() == null
						&& addCheckNumberTxt.getText().isEmpty())) {
					checkNumberToAdd = letter1.getValue().toString() + letter2.getValue().toString()
							+ addCheckNumberTxt.getText().toString();
					System.out.println(checkNumberToAdd);
				}
				if ((letter1.getValue() == null)) {
					checkNumberToAdd += letter1.getValue().toString();
					System.out.println(checkNumberToAdd);
				}
				if ((letter2.getValue() == null)) {
					checkNumberToAdd += letter2.getValue().toString();
					System.out.println(checkNumberToAdd);

				}
				if ((addCheckNumberTxt.getText().isEmpty())) {
					checkNumberToAdd += addCheckNumberTxt.getText().toString();
					System.out.println(checkNumberToAdd);

				}
				try {
					java.sql.Statement stmt4 = getConnection().createStatement();
					String addQuery = "INSERT INTO payments (customerNumber, checkNumber, paymentDate,amount) VALUES ('"
							+ Integer.parseInt(addCustomerNumberTxt.getText()) + "', '" + checkNumberToAdd + "', '"
							+ addDatePicker.getValue() + "', '" + addAmountTxt.getText() + "');";
					stmt4.executeUpdate(addQuery);
					resultTxt.setText("Payment with check number " + checkNumberToAdd + " was added.");
				}

				catch (Exception e2) {
					e2.printStackTrace();

				}
			});
			payPane.setCenter(tableviewPayments);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return payPane;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BorderPane productLinesPane() {

		BorderPane productLinesPane = new BorderPane();

		try {

			// Left Pane
			VBox bpaneLeft = new VBox();
			bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
			Label productLinesLbl = new Label("Product Lines");
			productLinesLbl.setMinSize(200, 10);
			productLinesLbl.setAlignment(Pos.TOP_CENTER);
			Label productLineLbl = new Label("ProductLine: ");
			TextField productLineTxt = new TextField();
			Label textDescriptionLbl = new Label("Text Description: ");
			TextField textDescriptionTxt = new TextField();
			Button btnSearch = new Button("Search");
			Button btnAdd = new Button("Add");
			HBox hb2 = new HBox(5);
			hb2.getChildren().add(0, btnSearch);
			hb2.getChildren().add(1, btnAdd);
			bpaneLeft.getChildren().add(0, productLinesLbl);
			bpaneLeft.getChildren().add(1, productLineLbl);
			bpaneLeft.getChildren().add(2, productLineTxt);
			bpaneLeft.getChildren().add(3, textDescriptionLbl);
			bpaneLeft.getChildren().add(4, textDescriptionTxt);
			bpaneLeft.getChildren().add(5, hb2);
			bpaneLeft.setAlignment(Pos.CENTER_LEFT);

			productLinesPane.setLeft(bpaneLeft);

			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			productLinesPane.setBottom(bpaneBot);

			dataProductLines = FXCollections.observableArrayList();
			try {
				String orderDetailsSQL = "SELECT * from productlines";
				java.sql.ResultSet rsPL = getConnection().createStatement().executeQuery(orderDetailsSQL);
				for (int i = 0; i < rsPL.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col1 = new TableColumn(rsPL.getMetaData().getColumnName(i + 1));
					col1.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewProductLines.getColumns().addAll(col1);
				}
				while (rsPL.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsPL.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsPL.getString(i));
					}
					dataProductLines.add(row);
				}
				tableviewProductLines.setItems(dataProductLines);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			productLinesPane.setCenter(tableviewProductLines);

			btnSearch.setOnAction(actionEvent -> {
				tableviewProductLines.getColumns().clear();
				tableviewProductLines.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM productlines";
					if (!(productLineTxt.getText().isEmpty() && textDescriptionTxt.getText().isEmpty())) {
						searchIDSQL += " WHERE ";
						if (!(productLineTxt.getText().isEmpty())) {
							searchIDSQL += "productLine LIKE '%" + productLineTxt.getText().trim() + "%' OR ";
						}
						if (!(textDescriptionTxt.getText().isEmpty())) {
							searchIDSQL += "textDescription LIKE '%" + textDescriptionTxt.getText().trim() + "%' OR ";
						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsProductLines = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsProductLines.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsProductLines.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewProductLines.getColumns().addAll(col1);
						}
						while (rsProductLines.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsProductLines.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsProductLines.getString(i));
							}
							dataProductLines.add(row);
						}
						tableviewProductLines.setItems(dataProductLines);
					} else {
						java.sql.ResultSet rs = getConnection().createStatement()
								.executeQuery("SELECT * FROM productlines");
						for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rs.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewProductLines.getColumns().addAll(col1);
						}
						while (rs.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rs.getString(i));
							}
							dataProductLines.add(row);
						}
						tableviewProductLines.setItems(dataProductLines);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(productLineTxt.textProperty(), textDescriptionTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (productLineTxt.getText().isEmpty() || textDescriptionTxt.getText().isEmpty());
				}
			};
			btnAdd.disableProperty().bind(bb);

			btnAdd.setOnAction(actionEvent -> {

				try {
					java.sql.Statement stmtPLines = getConnection().createStatement();
					String addQuery = "INSERT INTO productlines (productLine, textDescription) VALUES ('"
							+ productLineTxt.getText() + "', '" + textDescriptionTxt.getText() + "');";
					stmtPLines.executeUpdate(addQuery);
					resultTxt.setText("Product Line " + productLineTxt.getText().toString() + " was added.");
				}

				catch (Exception e2) {
					e2.printStackTrace();

				}
			});
			productLinesPane.setCenter(tableviewProductLines);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productLinesPane;
	}

	@SuppressWarnings("unchecked")
	public static BorderPane productsPane() {
		BorderPane prodPane = new BorderPane();
		try {

			String productLine = "SELECT productLine FROM productlines";
			java.sql.Statement stmtProd = getConnection().createStatement();
			java.sql.ResultSet rsProdLines = stmtProd.executeQuery(productLine);
			rsProdLines.last();
			int prodLCounter = rsProdLines.getRow();
			rsProdLines.first();
			String[] prodLineArray = new String[prodLCounter - 1];
			prodLCounter = 0;
			while (rsProdLines.next()) {
				prodLineArray[prodLCounter] = rsProdLines.getString("productLine");
				prodLCounter++;
			}

			// Left Pane
			VBox bpaneLeft = new VBox(5);
			bpaneLeft.setPadding(new Insets(10, 10, 10, 10));
			Label productsLbl = new Label("Products");
			productsLbl.setMinSize(200, 10);
			productsLbl.setAlignment(Pos.TOP_CENTER);
			Label productCodeLbl = new Label("Product Code: ");
			TextField productCodeTxt = new TextField();
			Label productNameLbl = new Label("Product Name: ");
			TextField productNameTxt = new TextField();
			Label productLineLbl = new Label("Product Line: ");
			ComboBox<String> productLineBox = new ComboBox<String>(FXCollections.observableArrayList(prodLineArray));
			Label productDescriptionLbl = new Label("Product Description: ");
			TextField productDescriptionTxt = new TextField();
			Label quantityInStockLbl = new Label("Quantity in Stock: ");
			TextField quantityInStockTxt = new TextField();
			Label buyPriceLbl = new Label("Buy Price: ");
			TextField buyPriceTxt = new TextField();
			Button btnSearch = new Button("Search");
			Button btnClear = new Button("Clear");
			Button btnAdd = new Button("Add");
			HBox hb1 = new HBox(10);
			hb1.getChildren().add(0, productLineBox);
			hb1.getChildren().add(1, btnClear);
			HBox hb2 = new HBox(10);
			hb2.getChildren().add(0, btnSearch);
			hb2.getChildren().add(1, btnAdd);
			bpaneLeft.getChildren().add(0, productsLbl);
			bpaneLeft.getChildren().add(1, productCodeLbl);
			bpaneLeft.getChildren().add(2, productCodeTxt);
			bpaneLeft.getChildren().add(3, productNameLbl);
			bpaneLeft.getChildren().add(4, productNameTxt);
			bpaneLeft.getChildren().add(5, productLineLbl);
			bpaneLeft.getChildren().add(6, hb1);
			bpaneLeft.getChildren().add(7, productDescriptionLbl);
			bpaneLeft.getChildren().add(8, productDescriptionTxt);
			bpaneLeft.getChildren().add(9, quantityInStockLbl);
			bpaneLeft.getChildren().add(10, quantityInStockTxt);
			bpaneLeft.getChildren().add(11, buyPriceLbl);
			bpaneLeft.getChildren().add(12, buyPriceTxt);
			bpaneLeft.getChildren().add(13, hb2);
			bpaneLeft.setAlignment(Pos.CENTER_LEFT);
			prodPane.setLeft(bpaneLeft);
			btnClear.setOnAction(e -> {
				productLineBox.valueProperty().set(null);
			});
			VBox bpaneBot = new VBox();
			Label lblResult = new Label("Result Console");
			TextArea resultTxt = new TextArea();
			resultTxt.setEditable(false);
			bpaneBot.getChildren().add(0, lblResult);
			bpaneBot.getChildren().add(1, resultTxt);
			prodPane.setBottom(bpaneBot);

			dataProducts = FXCollections.observableArrayList();
			try {
				String productsSQL = "SELECT * from products";
				java.sql.ResultSet rsProducts = getConnection().createStatement().executeQuery(productsSQL);
				for (int i = 0; i < rsProducts.getMetaData().getColumnCount(); i++) {
					final int j = i;
					TableColumn col1 = new TableColumn(rsProducts.getMetaData().getColumnName(i + 1));
					col1.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j) + "");
								}
							});
					tableviewProducts.getColumns().addAll(col1);
				}
				while (rsProducts.next()) {
					// Iterate Row
					ObservableList<String> row = FXCollections.observableArrayList();
					for (int i = 1; i <= rsProducts.getMetaData().getColumnCount(); i++) {
						// Iterate Column
						row.add(rsProducts.getString(i));
					}
					dataProducts.add(row);
				}
				tableviewProducts.setItems(dataProducts);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			prodPane.setCenter(tableviewProducts);

			btnSearch.setOnAction(actionEvent -> {
				tableviewProducts.getColumns().clear();
				tableviewProducts.getItems().clear();
				try {
					String searchIDSQL = "SELECT * FROM products";
					if (!(productCodeTxt.getText().isEmpty() && productNameTxt.getText().isEmpty()
							&& productLineBox.getValue() == null) && productDescriptionTxt.getText().isEmpty()
							&& quantityInStockTxt.getText().isEmpty() && buyPriceTxt.getText().isEmpty()) {
						searchIDSQL += " WHERE ";
						if (!(productCodeTxt.getText().isEmpty())) {
							searchIDSQL += "productCode LIKE '%" + productCodeTxt.getText().trim() + "%' OR ";
						}
						if (!(productNameTxt.getText().isEmpty())) {
							searchIDSQL += "productName LIKE '%" + productNameTxt.getText().trim() + "%' OR ";
						}
						if (!(productLineBox.getValue() == null)) {
							searchIDSQL += "productLine LIKE '%" + productLineBox.getValue() + "%' OR ";
						}

						if (!(productDescriptionTxt.getText().isEmpty())) {
							searchIDSQL += "productDescription LIKE '%" + productDescriptionTxt.getText().trim()
									+ "%' OR ";
						}
						if (!(quantityInStockTxt.getText().isEmpty())) {
							boolean b = true;

							while (b) {

								if (isNumber(quantityInStockTxt.getText()) == true) {

									b = false;
									searchIDSQL += "quantityInStock LIKE '%"
											+ Integer.parseInt(quantityInStockTxt.getText().trim()) + "%' OR ";
								} else if (isNumber(quantityInStockTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}

						}
						if (!(buyPriceTxt.getText().isEmpty())) {
							boolean b = true;

							while (b) {

								if (isDouble(buyPriceTxt.getText()) == true) {

									b = false;
									searchIDSQL += "buyPrice LIKE '%" + Integer.parseInt(buyPriceTxt.getText().trim())
											+ "%' OR ";
								} else if (isNumber(buyPriceTxt.getText()) == false) {
									resultTxt.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									return;
								}
							}

						}
						if (searchIDSQL.endsWith("OR ")) {
							searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
						}
						java.sql.ResultSet rsProducts = getConnection().createStatement().executeQuery(searchIDSQL);
						for (int i = 0; i < rsProducts.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsProducts.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewProducts.getColumns().addAll(col1);
						}
						while (rsProducts.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsProducts.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsProducts.getString(i));
							}
							dataProducts.add(row);
						}
						tableviewProducts.setItems(dataProducts);
					} else {
						java.sql.ResultSet rsP = getConnection().createStatement()
								.executeQuery("SELECT * FROM products");
						for (int i = 0; i < rsP.getMetaData().getColumnCount(); i++) {
							final int j = i;
							TableColumn col1 = new TableColumn(rsP.getMetaData().getColumnName(i + 1));
							col1.setCellValueFactory(
									new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
										public ObservableValue<String> call(
												CellDataFeatures<ObservableList, String> param) {
											return new SimpleStringProperty(param.getValue().get(j) + "");
										}
									});
							tableviewProducts.getColumns().addAll(col1);
						}
						while (rsP.next()) {
							// Iterate Row
							ObservableList<String> row = FXCollections.observableArrayList();
							for (int i = 1; i <= rsP.getMetaData().getColumnCount(); i++) {
								// Iterate Column
								row.add(rsP.getString(i));
							}
							dataProducts.add(row);
						}
						tableviewProducts.setItems(dataProducts);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			btnClear.setOnAction(e -> {
				productLineBox.valueProperty().set(null);
			});

			BooleanBinding bb = new BooleanBinding() {
				{
					super.bind(productCodeTxt.textProperty(), productNameTxt.textProperty(),
							productLineBox.valueProperty(), productDescriptionTxt.textProperty(),
							quantityInStockTxt.textProperty(), buyPriceTxt.textProperty());
				}

				@Override
				protected boolean computeValue() {
					return (productCodeTxt.getText().isEmpty() || productNameTxt.getText().isEmpty()
							|| productLineBox.getValue() == null || productDescriptionTxt.getText().isEmpty()
							|| quantityInStockTxt.getText().isEmpty() || buyPriceTxt.getText().isEmpty());
				}
			};
			btnAdd.disableProperty().bind(bb);
			btnAdd.setOnAction(actionEvent -> {
				try {

					java.sql.Statement stmtAddProducts = getConnection().createStatement();
					String addQuery = "INSERT INTO products (productCode, productName, productLine, productDescription, quantityInStock, buyPrice) VALUES ('"
							+ productCodeTxt.getText() + "', '" + productNameTxt.getText() + "', '"
							+ productLineBox.getValue() + "', '" + productDescriptionTxt.getText() + "', '"
							+ Integer.parseInt(quantityInStockTxt.getText()) + "', '"
							+ Double.parseDouble(buyPriceTxt.getText()) + "');";
					stmtAddProducts.executeUpdate(addQuery);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodPane;
	}

	public static SplitPane informationPane() {
		SplitPane split_pane = new SplitPane();
		Text txt1 = new Text();
		txt1.setText("StudentID: 201904045\n"
				+ "A database that manages the work of a retailer. It contains typical business data such as customers, \r\n"
				+ "products, sales orders, sales order line items.");
		txt1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		txt1.setFill(Color.BLACK);
		txt1.setTextAlignment(TextAlignment.CENTER);
		txt1.prefHeight(40);

		WebView infoPane = new WebView();
		WebEngine infoEngine = new WebEngine();
		infoEngine = infoPane.getEngine();
		split_pane.getItems().add(txt1);
		split_pane.getItems().add(infoPane);
		infoEngine.load("http://201904045.epizy.com/?i=1");
		split_pane.setOrientation(Orientation.VERTICAL);

		return split_pane;
	}

	public static BorderPane queriesPane() {
		BorderPane queriesPane = new BorderPane();
		VBox vb1 = new VBox(5);
		Label query1Lbl = new Label(
				"Query to show products that had been ordered by customers from certain city or certain country.");
		Label query2Lbl = new Label(" Query to show products details that its price is more than particular price.");
		Label query3Lbl = new Label(" Query to show number of customers who their credit limits within a given range.");
		Label query4Lbl = new Label(" Query to show customers names and products names they had ordered.");
		Label query5Lbl = new Label(" Query of products that they have in the description some one or more keywords");
		Label query6Lbl = new Label(" Query to show employees names and the orders they facilitated.");
		Label query7Lbl = new Label(" Query to show employees names and total prices of products they sold.");
		Label query8Lbl = new Label(
				" Query to show offices names and amount of money earned through their sales representatives.");
		Label query9Lbl = new Label(" Query to show employees names who don’t have sell any product.");
		Label query10Lbl = new Label(" Query to show employees names who sold at least one product.");
		Button btnQuery1 = new Button("Query 1");
		Button btnQuery2 = new Button("Query 2");
		Button btnQuery3 = new Button("Query 3");
		Button btnQuery4 = new Button("Query 4");
		Button btnQuery5 = new Button("Query 5");
		Button btnQuery6 = new Button("Query 6");
		Button btnQuery7 = new Button("Query 7");
		Button btnQuery8 = new Button("Query 8");
		Button btnQuery9 = new Button("Query 9");
		Button btnQuery10 = new Button("Query 10");
		HBox hb1 = new HBox();
		hb1.getChildren().add(0, query1Lbl);
		hb1.getChildren().add(1, btnQuery1);
		HBox hb2 = new HBox();
		hb2.getChildren().add(0, query2Lbl);
		hb2.getChildren().add(1, btnQuery2);
		HBox hb3 = new HBox();
		hb3.getChildren().add(0, query3Lbl);
		hb3.getChildren().add(1, btnQuery3);
		HBox hb4 = new HBox();
		hb4.getChildren().add(0, query4Lbl);
		hb4.getChildren().add(1, btnQuery4);
		HBox hb5 = new HBox();
		hb5.getChildren().add(0, query5Lbl);
		hb5.getChildren().add(1, btnQuery5);
		HBox hb6 = new HBox();
		hb6.getChildren().add(0, query6Lbl);
		hb6.getChildren().add(1, btnQuery6);
		HBox hb7 = new HBox();
		hb7.getChildren().add(0, query7Lbl);
		hb7.getChildren().add(1, btnQuery7);
		HBox hb8 = new HBox();
		hb8.getChildren().add(0, query8Lbl);
		hb8.getChildren().add(1, btnQuery8);
		HBox hb9 = new HBox();
		hb9.getChildren().add(0, query9Lbl);
		hb9.getChildren().add(1, btnQuery9);
		HBox hb10 = new HBox();
		hb10.getChildren().add(0, query10Lbl);
		hb10.getChildren().add(1, btnQuery10);

		vb1.getChildren().add(0, hb1);
		vb1.getChildren().add(1, hb2);
		vb1.getChildren().add(2, hb3);
		vb1.getChildren().add(3, hb4);
		vb1.getChildren().add(4, hb5);
		vb1.getChildren().add(5, hb6);
		vb1.getChildren().add(6, hb7);
		vb1.getChildren().add(7, hb8);
		vb1.getChildren().add(8, hb9);
		vb1.getChildren().add(9, hb10);
		vb1.setAlignment(Pos.CENTER_LEFT);
		vb1.setStyle("-fx-border-width: 1; -fx-border-color: grey");
		queriesPane.setLeft(vb1);
		btnQuery1.setOnAction(e -> {
			queriesPane.setCenter(query1Pane());
		});
		btnQuery2.setOnAction(e -> {
			queriesPane.setCenter(query2Pane());
		});
		btnQuery3.setOnAction(e -> {
			queriesPane.setCenter(query3Pane());
		});
		btnQuery4.setOnAction(e -> {
			queriesPane.setCenter(query4Pane());
		});
		btnQuery5.setOnAction(e -> {
			queriesPane.setCenter(query5Pane());
		});
		btnQuery6.setOnAction(e -> {
			queriesPane.setCenter(query6Pane());
		});
		btnQuery7.setOnAction(e -> {
			queriesPane.setCenter(query7Pane());
		});
		btnQuery8.setOnAction(e -> {
			queriesPane.setCenter(query8Pane());
		});
		btnQuery9.setOnAction(e -> {
			queriesPane.setCenter(query9Pane());
		});
		btnQuery10.setOnAction(e -> {
			queriesPane.setCenter(query10Pane());
		});
		return queriesPane;
	}

	public static BorderPane query1Pane() {
		BorderPane query1Pane = new BorderPane();
		VBox vbLeft = new VBox(4);
		Label cityLabel = new Label("City");
		Label countryLabel = new Label("Country");
		TextField cityTxt = new TextField();
		TextField countryTxt = new TextField();
		Button btnSearch = new Button("Search");
		vbLeft.getChildren().add(0, cityLabel);
		vbLeft.getChildren().add(1, cityTxt);
		vbLeft.getChildren().add(2, countryLabel);
		vbLeft.getChildren().add(3, countryTxt);
		vbLeft.getChildren().add(4, btnSearch);
		query1Pane.setLeft(vbLeft);
		btnSearch.setOnAction(e -> {
			dataQuery1 = FXCollections.observableArrayList();
			try {
				String searchIDSQL = "SELECT products.productCode, products.productName FROM products, orderdetails, orders, customers WHERE products.productCode = orderdetails.productCode AND orderdetails.orderNumber = orders.orderNumber AND orders.customerNumber = customers.customerNumber AND";
				if (!(cityTxt.getText().isEmpty() && countryTxt.getText().isEmpty())) {
					if (!(countryTxt.getText().isEmpty())) {
						searchIDSQL += " customers.country like '%" + countryTxt.getText().trim() + "%' OR";
					}
					if (!(cityTxt.getText().isEmpty())) {
						searchIDSQL += "customers.city LIKE '%" + cityTxt.getText().trim() + "%' OR ";
					}

					if (searchIDSQL.endsWith("OR ")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
					} else if (searchIDSQL.endsWith("AND")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("AND"));
					}

					java.sql.ResultSet rsquery1 = getConnection().createStatement().executeQuery(searchIDSQL);
					for (int i = 0; i < rsquery1.getMetaData().getColumnCount(); i++) {
						final int j = i;
						TableColumn col1 = new TableColumn(rsquery1.getMetaData().getColumnName(i + 1));
						col1.setCellValueFactory(
								new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(
											CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j) + "");
									}
								});
						tableviewQuery1.getColumns().addAll(col1);
					}
					while (rsquery1.next()) {
						// Iterate Row
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= rsquery1.getMetaData().getColumnCount(); i++) {
							// Iterate Column
							row.add(rsquery1.getString(i));
						}
						dataQuery1.add(row);
					}
					tableviewQuery1.setItems(dataQuery1);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		query1Pane.setCenter(tableviewQuery1);
		return query1Pane;
	}

	public static BorderPane query2Pane() {
		BorderPane query2Pane = new BorderPane();
		VBox vbLeft = new VBox(4);
		Label priceLabel = new Label("Price");
		TextField priceTxt = new TextField();
		Button btnSearch = new Button("Search");
		vbLeft.getChildren().add(0, priceLabel);
		vbLeft.getChildren().add(1, priceTxt);
		vbLeft.getChildren().add(2, btnSearch);
		query2Pane.setLeft(vbLeft);

		btnSearch.setOnAction(e -> {
			dataQuery2 = FXCollections.observableArrayList();
			try {
				String searchIDSQL = "";
				if (!(priceTxt.getText().isEmpty())) {
					boolean b = true;

					while (b) {

						if (isNumber(priceTxt.getText()) == true) {

							b = false;
							searchIDSQL += "SELECT * FROM products WHERE buyPrice >" + priceTxt.getText().trim();
						} else if (isNumber(priceTxt.getText()) == false) {
							return;
						}
					}
					try {
						double bprice = Double.parseDouble(priceTxt.getText());

					} catch (NumberFormatException bpException) {
					}
					if (searchIDSQL.endsWith("OR ")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
					} else if (searchIDSQL.endsWith("AND")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("AND"));
					}

					java.sql.ResultSet rsquery2 = getConnection().createStatement().executeQuery(searchIDSQL);
					for (int i = 0; i < rsquery2.getMetaData().getColumnCount(); i++) {
						final int j = i;
						TableColumn col1 = new TableColumn(rsquery2.getMetaData().getColumnName(i + 1));
						col1.setCellValueFactory(
								new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(
											CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j) + "");
									}
								});
						tableviewQuery2.getColumns().addAll(col1);
					}
					while (rsquery2.next()) {
						// Iterate Row
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= rsquery2.getMetaData().getColumnCount(); i++) {
							// Iterate Column
							row.add(rsquery2.getString(i));
						}
						dataQuery2.add(row);
					}
					tableviewQuery2.setItems(dataQuery2);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		query2Pane.setCenter(tableviewQuery2);
		return query2Pane;
	}

	public static BorderPane query3Pane() {
		BorderPane query3Pane = new BorderPane();
		Label creditFromLbl = new Label("From:");
		Label creditToLbl = new Label("To:");
		TextField creditFromTxt = new TextField();
		TextField creditToTxt = new TextField();
		Button btnSearch = new Button("Search");
		VBox vbLeft = new VBox(4);

		vbLeft.getChildren().add(0, creditFromLbl);
		vbLeft.getChildren().add(1, creditFromTxt);
		vbLeft.getChildren().add(2, creditToLbl);
		vbLeft.getChildren().add(3, creditToTxt);
		vbLeft.getChildren().add(4, btnSearch);
		query3Pane.setLeft(vbLeft);
		btnSearch.setOnAction(e -> {
			tableviewQuery3.getItems().clear();
			tableviewQuery3.getColumns().clear();
			dataQuery3 = FXCollections.observableArrayList();
			try {
				String searchIDSQL = "SELECT COUNT(customers.customerNumber) FROM customers WHERE customers.creditLimit BETWEEN ";
				if (!(creditFromTxt.getText().isEmpty() && creditToTxt.getText().isEmpty())) {
					boolean b = true;

					while (b) {

						if (isNumber(creditFromTxt.getText()) == true) {

							b = false;
							searchIDSQL += creditFromTxt.getText() + " AND ";
							;
						} else if (isNumber(creditFromTxt.getText()) == false) {
							return;
						}
					}
					boolean x = true;

					while (x) {

					if (isNumber(creditToTxt.getText()) == true) {

					x = false;
					searchIDSQL += creditToTxt.getText();
					} else if (isNumber(creditToTxt.getText()) == false) {
			
					return;
						}
					}
					if (searchIDSQL.endsWith("OR ")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("OR"));
					} else if (searchIDSQL.endsWith("AND")) {
						searchIDSQL = searchIDSQL.substring(0, searchIDSQL.lastIndexOf("AND"));
					}

					java.sql.ResultSet rsquery3 = getConnection().createStatement().executeQuery(searchIDSQL);
					for (int i = 0; i < rsquery3.getMetaData().getColumnCount(); i++) {
						final int j = i;
						TableColumn col1 = new TableColumn(rsquery3.getMetaData().getColumnName(i + 1));
						col1.setCellValueFactory(
								new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(
											CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j) + "");
									}
								});
						tableviewQuery3.getColumns().addAll(col1);
					}
					while (rsquery3.next()) {
						// Iterate Row
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= rsquery3.getMetaData().getColumnCount(); i++) {
							// Iterate Column
							row.add(rsquery3.getString(i));
						}
						dataQuery3.add(row);
					}
					tableviewQuery3.setItems(dataQuery3);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		query3Pane.setCenter(tableviewQuery3);
		return query3Pane;
	}

	public static BorderPane query4Pane() {
		BorderPane query4Pane = new BorderPane();
		tableviewQuery4.getItems().clear();
		tableviewQuery4.getColumns().clear();
		dataQuery4 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT customers.customerName, customers.customerNumber, products.productName, products.productCode FROM products, orderdetails, orders, customers WHERE products.productCode = orderdetails.productCode AND orderdetails.orderNumber = orders.orderNumber AND orders.customerNumber = customers.customerNumber";
			java.sql.ResultSet rsquery4 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery4.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery4.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery4.getColumns().addAll(col1);
			}
			while (rsquery4.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery4.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery4.getString(i));
				}
				dataQuery4.add(row);
			}
			tableviewQuery4.setItems(dataQuery4);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query4Pane.setCenter(tableviewQuery4);
		return query4Pane;
	}

	public static BorderPane query5Pane() {
		BorderPane query5Pane = new BorderPane();

		Label searchLbl = new Label("Look for products:");
		Label keywordLbl = new Label("Keyword");
		TextField keywordTxt = new TextField();
		Button btnSearch = new Button("Search");
		VBox vbLeft = new VBox(4);

		vbLeft.getChildren().add(0, searchLbl);
		vbLeft.getChildren().add(1, keywordLbl);
		vbLeft.getChildren().add(2, keywordTxt);
		vbLeft.getChildren().add(3, btnSearch);
		query5Pane.setLeft(vbLeft);
		btnSearch.setOnAction(e -> {
			tableviewQuery5.getItems().clear();
			tableviewQuery5.getColumns().clear();
			dataQuery5 = FXCollections.observableArrayList();
			try {
				String searchIDSQL = "Select * FROM products WHERE productDescription LIKE '%";
				if (!(keywordTxt.getText().isEmpty())) {
					searchIDSQL += keywordTxt.getText().trim() + "%'";
					java.sql.ResultSet rsquery5 = getConnection().createStatement().executeQuery(searchIDSQL);
					for (int i = 0; i < rsquery5.getMetaData().getColumnCount(); i++) {
						final int j = i;
						TableColumn col1 = new TableColumn(rsquery5.getMetaData().getColumnName(i + 1));
						col1.setCellValueFactory(
								new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
									public ObservableValue<String> call(
											CellDataFeatures<ObservableList, String> param) {
										return new SimpleStringProperty(param.getValue().get(j) + "");
									}
								});
						tableviewQuery5.getColumns().addAll(col1);
					}
					while (rsquery5.next()) {
						// Iterate Row
						ObservableList<String> row = FXCollections.observableArrayList();
						for (int i = 1; i <= rsquery5.getMetaData().getColumnCount(); i++) {
							// Iterate Column
							row.add(rsquery5.getString(i));
						}
						dataQuery5.add(row);
					}
					tableviewQuery5.setItems(dataQuery5);
				}

			} catch (NullPointerException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		query5Pane.setCenter(tableviewQuery5);
		return query5Pane;
	}

	public static BorderPane query6Pane() {
		BorderPane query6Pane = new BorderPane();
		tableviewQuery6.getItems().clear();
		tableviewQuery6.getColumns().clear();
		dataQuery6 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT employees.firstName, employees.lastName, employees.employeeNumber, orders.orderNumber, orders.orderDate FROM employees, customers, orders WHERE employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber";
			java.sql.ResultSet rsquery6 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery6.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery6.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery6.getColumns().addAll(col1);
			}
			while (rsquery6.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery6.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery6.getString(i));
				}
				dataQuery6.add(row);
			}
			tableviewQuery6.setItems(dataQuery6);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query6Pane.setCenter(tableviewQuery6);
		return query6Pane;
	}

	public static BorderPane query7Pane() {
		BorderPane query7Pane = new BorderPane();
		tableviewQuery7.getItems().clear();
		tableviewQuery7.getColumns().clear();
		dataQuery7 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT employees.employeeNumber, employees.firstName, employees.lastName, SUM(products.buyprice) FROM employees, customers, orders, orderdetails, products WHERE employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode = products.productCode GROUP BY employeeNumber";
			java.sql.ResultSet rsquery7 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery7.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery7.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery7.getColumns().addAll(col1);
			}
			while (rsquery7.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery7.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery7.getString(i));
				}
				dataQuery7.add(row);
			}
			tableviewQuery7.setItems(dataQuery7);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query7Pane.setCenter(tableviewQuery7);
		return query7Pane;
	}

	public static BorderPane query8Pane() {
		BorderPane query8Pane = new BorderPane();
		tableviewQuery8.getItems().clear();
		tableviewQuery8.getColumns().clear();
		dataQuery8 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT offices.officeCode, SUM(payments.amount) FROM offices, employees, customers, payments WHERE offices.officeCode = employees.officeCode AND employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = payments.customerNumber GROUP BY offices.officeCode";
			java.sql.ResultSet rsquery8 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery8.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery8.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery8.getColumns().addAll(col1);
			}
			while (rsquery8.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery8.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery8.getString(i));
				}
				dataQuery8.add(row);
			}
			tableviewQuery8.setItems(dataQuery8);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query8Pane.setCenter(tableviewQuery8);
		return query8Pane;
	}

	public static BorderPane query9Pane() {
		BorderPane query9Pane = new BorderPane();
		tableviewQuery9.getItems().clear();
		tableviewQuery9.getColumns().clear();
		dataQuery9 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT employees.firstName, employees.lastName FROM employees LEFT JOIN customers ON employees.employeeNumber = customers.salesRepEmployeeNumber WHERE customers.salesRepEmployeeNumber IS NULL";
			java.sql.ResultSet rsquery9 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery9.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery9.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery9.getColumns().addAll(col1);
			}
			while (rsquery9.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery9.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery9.getString(i));
				}
				dataQuery9.add(row);
			}
			tableviewQuery9.setItems(dataQuery9);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query9Pane.setCenter(tableviewQuery9);
		return query9Pane;
	}

	public static BorderPane query10Pane() {
		BorderPane query10Pane = new BorderPane();
		tableviewQuery10.getItems().clear();
		tableviewQuery10.getColumns().clear();
		dataQuery10 = FXCollections.observableArrayList();
		try {
			String searchIDSQL = "SELECT employees.employeeNumber, employees.firstName, employees.lastName, COUNT("
					+ "products.productCode) AS quantitySold FROM employees , customers, orders,orderdetails , products WHERE employees.employeeNumber = "
					+ "customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode="
					+ " products.productCode GROUP BY employeeNumber HAVING COUNT(products.productCode) >1;";
			java.sql.ResultSet rsquery10 = getConnection().createStatement().executeQuery(searchIDSQL);
			for (int i = 0; i < rsquery10.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col1 = new TableColumn(rsquery10.getMetaData().getColumnName(i + 1));
				col1.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j) + "");
							}
						});
				tableviewQuery10.getColumns().addAll(col1);
			}
			while (rsquery10.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rsquery10.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rsquery10.getString(i));
				}
				dataQuery10.add(row);
			}
			tableviewQuery10.setItems(dataQuery10);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		query10Pane.setCenter(tableviewQuery10);
		return query10Pane;
	}

	public static boolean isNumber(String s) throws NumberFormatException {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static boolean isDouble(String s) throws NumberFormatException {

		try {
			Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
