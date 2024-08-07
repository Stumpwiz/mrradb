package net.stumpwiz.mrradb.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.stumpwiz.mrradb.generated.tables.records.*;
import net.stumpwiz.mrradb.utilities.Functions;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static net.stumpwiz.mrradb.reports.Reports.*;
import static net.stumpwiz.mrradb.utilities.Constants.*;
import static net.stumpwiz.mrradb.utilities.Functions.*;
import static net.stumpwiz.mrradb.utilities.JooqDefs.*;
import static org.jooq.impl.DSL.field;

public class DbController implements Initializable
{
    private final ObservableList<BodyRecord> officesBodyListObs = FXCollections.observableArrayList();
    private final ObservableList<OfficeRecord> officesOfficeListObs = FXCollections.observableArrayList();
    private final ObservableList<PersonRecord> peoplePersonListObs = FXCollections.observableArrayList();
    private final ObservableList<PtoRecord> termsPtoListObs = FXCollections.observableArrayList();
    private final ObservableList<OfficeRecord> termsOfficesListObs = FXCollections.observableArrayList();
    private final ObservableList<BodyRecord> termsTermBodyListObs = FXCollections.observableArrayList();
    final Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    final DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
    private BodyRecord selectedBody = new BodyRecord();
    private OfficeRecord selectedOffice = new OfficeRecord();
    private PersonRecord selectedPerson = new PersonRecord();
    private PtoRecord selectedPto = new PtoRecord();
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%% HOME tab functionality %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @FXML
    private Button longRosterButton;
    @FXML
    private Button shortRosterButton;
    @FXML
    private Button expiringButton;
    @FXML
    private Button backupButton;
    @FXML
    private Button restoreButton;
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%% PEOPLE tab functionality %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @FXML
    private ListView<PersonRecord> peoplePersonListView;
    @FXML
    private ImageView peopleImageView;
    @FXML
    private TextField peopleImageNameTextField;
    @FXML
    private TextField peopleFirstTextField;
    @FXML
    private TextField peopleLastTextField;
    @FXML
    private TextField peopleEmailTextField;
    @FXML
    private TextField peoplePhoneTextField;
    @FXML
    private TextField peopleAptTextField;
    @FXML
    private Button peopleNewPersonButton;
    @FXML
    private Button peopleUpdatePersonButton;
    @FXML
    private Button peopleDeletePersonButton;
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%% TERMS tab functionality %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @FXML
    private Label termsPersonLabel;
    @FXML
    private ListView<PtoRecord> termsPtoListView;
    @FXML
    private ListView<BodyRecord> termsBodiesListView;
    @FXML
    private ListView<OfficeRecord> termsOfficesListView;
    @FXML
    private TextField termsTermStartTextField;
    @FXML
    private TextField termsTermEndTextField;
    @FXML
    private TextField termsTermOrdinalTextField;
    @FXML
    private Button termsTermNewButton;
    @FXML
    private Button termsTermUpdateButton;
    @FXML
    private Button termsTermDeleteButton;
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%% OFFICES tab functionality %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    @FXML
    private ListView<BodyRecord> officesBodyListView;
    @FXML
    private ImageView officesBodyImageView;
    @FXML
    private TextField officesBodyImageFileTextField;
    @FXML
    private TextField officesBodyNameTextField;
    @FXML
    private TextField officesBodyPrecedenceTextField;
    @FXML
    private TextArea officesBodyMissionTextArea;
    @FXML
    private Button officesNewBodyButton;
    @FXML
    private Button officesUpdateBodyButton;
    @FXML
    private Button officesDeleteBodyButton;
    @FXML
    private ListView<OfficeRecord> officesOfficeListView;
    @FXML
    private TextField officesOfficeTitleTextField;
    @FXML
    private TextField officesOfficePrecedenceTextField;
    @FXML
    private Button officesNewOfficeButton;
    @FXML
    private Button officesUpdateOfficeButton;
    @FXML
    private Button officesDeleteOfficeButton;

    public DbController() throws SQLException
    {
    }

    private static void addSelectedPerson(
            PersonRecord selectedPerson, ObservableList<PersonRecord> peoplePersonListObs,
            ListView<PersonRecord> peoplePersonListView)
    {
        peoplePersonListObs.add(selectedPerson);
        peoplePersonListObs.sort((p1, p2) ->
        {
            int result = p1.getFirst().compareToIgnoreCase(p2.getFirst());
            if (result == 0) {
                result = p1.getLast().compareToIgnoreCase(p2.getLast());
            }
            return result;
        });
        peoplePersonListView.getSelectionModel().select(selectedPerson);
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle)
    {
        List<PersonRecord> peoplePersonList = create.select(personFields).from(personTable).orderBy(personFirst,
                personLast).fetchInto(PersonRecord.class);
        peoplePersonListObs.setAll(peoplePersonList);
        peoplePersonListView.setCellFactory(param -> new ListCell<>()
        {
            @Override
            public void updateItem(PersonRecord item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getFirst() + " " + item.getLast());
                }
            }
        });
        peoplePersonListView.getSelectionModel().select(FIRST_ELEMENT);
        selectedPerson = peoplePersonListView.getSelectionModel().getSelectedItem();
        peoplePersonListView.scrollTo(selectedPerson);
        peoplePersonListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedPerson = newValue;
                    if (newValue != null) {
                        String imageName = selectedPerson.getPersonimage();
                        peopleImageNameTextField.setText(imageName);
                        String imageResource = "file:\\" + IMAGE_PATH + imageName;
                        Image image = new Image(imageResource);
                        if (image.errorProperty().get()) {
                            errorAlert(imageName + " not found in \n" + IMAGE_PATH);
                            peopleImageNameTextField.setText(MISSING_PERSON_IMAGE);
                        }
                        peopleImageView.setImage(new Image(imageResource));
                        peopleFirstTextField.setText(selectedPerson.getFirst());
                        peopleLastTextField.setText(selectedPerson.getLast());
                        peopleEmailTextField.setText(selectedPerson.getEmail());
                        peoplePhoneTextField.setText(selectedPerson.getPhone());
                        peopleAptTextField.setText(selectedPerson.getApt());
                        termsPersonLabel.setText(selectedPerson.getFirst() + " " + selectedPerson.getLast());

                        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                        //%%%%% TERMS tab initialization %%%%%%%%%%%%%%%%%%%%%%%%%%
                        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                        /* termsPtoListView population %%%%%%%%%%%%%%%%%%%%%%%%%%%*/
                        termsPtoListObs.clear();
                        BigInteger selectedPersonId = new BigInteger(selectedPerson.getPersonid().toString());
                        List<PtoRecord> termsPtoList = create.select(ptoFields)
                                .from(ptoTable)
                                .where(termPersonId.eq(selectedPersonId))
                                .orderBy(bodyName).fetchInto(PtoRecord.class);
                        if (!termsPtoList.isEmpty()) {
                            termsPtoListObs.setAll(termsPtoList);
                            termsPtoListView.setItems(termsPtoListObs);
                            selectedPto = termsPtoListObs.get(FIRST_ELEMENT);
                            termsPtoListView.scrollTo(selectedPto);
                            termsPtoListView.setCellFactory(param -> new ListCell<>()
                            {
                                @Override
                                public void updateItem(PtoRecord item, boolean empty)
                                {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getTitle() + ", " + item.getName() +
                                                (item.getEnd().isEqual(MAX_DATE) ? ", indefinitely" :
                                                        ", until " + item.getEnd()));
                                    }
                                }
                            });
                            termsPtoListView.getSelectionModel().selectedItemProperty().addListener(this::changed);
                            selectedPto = termsPtoListView.getItems().get(FIRST_ELEMENT);
                            termsPtoListView.getFocusModel().focus(FIRST_ELEMENT);
                        } else {
                            termsPtoListObs.removeAll();
                            selectedPto = null;
                            termsTermStartTextField.setText("");
                            termsTermEndTextField.setText("");
                            termsTermOrdinalTextField.setText("");
                        }
                        /* termsBodiesListView population %%%%%%%%%%%%%%%%%%%%%%%%%%%*/
                        List<BodyRecord> termsTermBodyRecordList =
                                create.select(bodyFields).from(bodyTable).orderBy(bodyName)
                                        .fetchInto(BodyRecord.class);
                        termsTermBodyListObs.setAll(termsTermBodyRecordList);
                        termsBodiesListView.setCellFactory(param -> new ListCell<>()
                        {
                            @Override
                            public void updateItem(BodyRecord item, boolean empty)
                            {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setText(null);
                                } else {
                                    setText(item.getName());
                                }
                            }
                        });
                        termsBodiesListView.getSelectionModel().selectedItemProperty()
                                .addListener((observable1, oldValue1, newValue1) ->
                                {
                                    selectedBody = termsBodiesListView.getSelectionModel().getSelectedItem();
                                    /* termsOfficesListView population %%%%%%%%%%%%%%%%%%%%%%%%%%%*/
                                    if (selectedBody != null) {
                                        List<OfficeRecord> termsOfficesRecordList = create
                                                .select(officeFields).from(officeTable)
                                                .where(officeBodyId.eq(
                                                        new BigInteger(selectedBody.getBodyid().toString())))
                                                .orderBy(officePrecedence).fetchInto(OfficeRecord.class);
                                        termsOfficesListView.setCellFactory(param -> new ListCell<>()
                                        {
                                            @Override
                                            public void updateItem(OfficeRecord item, boolean empty)
                                            {
                                                super.updateItem(item, empty);
                                                if (empty || item == null) {
                                                    setText(null);
                                                } else {
                                                    setText(item.getTitle());
                                                }
                                            }
                                        });
                                        termsOfficesListObs.setAll(termsOfficesRecordList);
                                        termsOfficesListView.setItems(termsOfficesListObs);
                                    }
                                });
                        termsBodiesListView.setItems(termsTermBodyListObs);
                    } else {
                        String imageResource = "file:\\" + IMAGE_PATH + MISSING_PERSON_IMAGE;
                        peopleImageView.setImage(new Image(imageResource));
                        peopleFirstTextField.setText("");
                        peopleLastTextField.setText("");
                        peopleEmailTextField.setText("");
                        peoplePhoneTextField.setText("");
                        peopleAptTextField.setText("");
                        termsPersonLabel.setText("None Selected");
                    }
                });
        peoplePersonListView.setItems(peoplePersonListObs);
        /* First item on the list is (Vacant), so get the second */
        peoplePersonListView.getSelectionModel().select(SECOND_ELEMENT);
        peoplePersonListView.getFocusModel().focus(SECOND_ELEMENT);
        selectedPerson = peoplePersonListView.getSelectionModel().getSelectedItem();


        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //%%%%% OFFICES tab initialization %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        List<BodyRecord> officesBodyRecordList = create.select(bodyFields).from(bodyTable).orderBy(bodyName)
                .fetchInto(BodyRecord.class);
        officesBodyListObs.setAll(officesBodyRecordList);
        selectedBody = officesBodyListObs.get(FIRST_ELEMENT);
        officesBodyListView.setCellFactory(param -> new ListCell<>()
        {
            @Override
            public void updateItem(BodyRecord item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " (" + item.getBodyprecedence() + ")");
                }
            }
        });
        officesBodyMissionTextArea.setWrapText(true);
        officesBodyListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedBody = newValue;
                    if (newValue != null) {
                        String imageResource = "file:\\" + IMAGE_PATH + selectedBody.getBodyimage();
                        officesBodyImageView.setImage(new Image(imageResource));
                        officesBodyImageFileTextField.setText(selectedBody.getBodyimage());
                        officesBodyNameTextField.setText(selectedBody.getName());
                        officesBodyPrecedenceTextField.setText(selectedBody.getBodyprecedence().toString());
                        officesBodyMissionTextArea.setText(selectedBody.getMission());
                    } else {
                        String imageResource = "file:\\" + IMAGE_PATH + "missingImage.jpg";
                        officesBodyImageView.setImage(new Image(imageResource));
                        officesBodyImageFileTextField.setText("");
                        officesBodyNameTextField.setText("");
                        officesBodyPrecedenceTextField.setText("");
                        officesBodyMissionTextArea.setText("");
                    }
                    List<OfficeRecord> officesOfficeRecordList = create
                            .select(officeFields).from(officeTable)
                            .where(officeBodyId.eq(new BigInteger(selectedBody.getBodyid().toString())))
                            .orderBy(officePrecedence).fetchInto(OfficeRecord.class);
                    officesOfficeListObs.setAll(officesOfficeRecordList);
                    selectedOffice = officesOfficeListObs.get(FIRST_ELEMENT);
                    officesOfficeListView.setCellFactory(param -> new ListCell<>()
                    {
                        @Override
                        public void updateItem(OfficeRecord item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getTitle() + " (" + item.getOfficeprecedence() + ")");
                            }
                        }
                    });
                    officesOfficeListView.setItems(officesOfficeListObs);
                    officesOfficeListView.getSelectionModel().selectedItemProperty().addListener(
                            (observable2, oldValue2, newValue2) -> {
                                selectedOffice = newValue2;
                                if (newValue2 != null) {
                                    officesOfficeTitleTextField.setText(selectedOffice.getTitle());
                                    officesOfficePrecedenceTextField.setText(
                                            selectedOffice.getOfficeprecedence().toString());
                                } else {
                                    officesOfficeTitleTextField.setText("");
                                    officesOfficePrecedenceTextField.setText("");
                                }
                            });
                });
        officesBodyListView.setItems(officesBodyListObs);
        officesBodyListView.getSelectionModel().select(FIRST_ELEMENT);
    }

    private void changed(ObservableValue<? extends PtoRecord> observable1, PtoRecord oldValue1, PtoRecord newValue1)
    {
        selectedPto = termsPtoListView.getSelectionModel().getSelectedItem();
        if (selectedPto != null) {
            selectedBody = create.select(bodyFields).from(bodyTable)
                    .where(bodyId.eq(new BigInteger(selectedPto.getBodyid().toString())))
                    .fetchOneInto(BodyRecord.class);
            termsBodiesListView.getSelectionModel().select(selectedBody);
            termsBodiesListView.scrollTo(selectedBody);
            List<OfficeRecord> termsOfficesRecordList = create
                    .select(officeFields).from(officeTable)
                    .where(officeBodyId.eq(new BigInteger(selectedBody.getBodyid().toString())))
                    .orderBy(officePrecedence).fetchInto(OfficeRecord.class);
            termsOfficesListView.setCellFactory(param -> new ListCell<>()
            {
                @Override
                public void updateItem(OfficeRecord item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitle());
                    }
                }
            });
            termsOfficesListObs.setAll(termsOfficesRecordList);
            termsOfficesListView.setItems(termsOfficesListObs);
            selectedOffice = create
                    .select(officeFields).from(officeTable)
                    .where(officeId.eq(BigInteger.valueOf(selectedPto.getTermofficeid())))
                    .fetchOneInto(OfficeRecord.class);
            termsOfficesListView.getSelectionModel().select(selectedOffice);
            termsOfficesListView.scrollTo(selectedOffice);
            termsTermStartTextField.setText(selectedPto.getStart().toString());
            termsTermEndTextField.setText(selectedPto.getEnd().toString());
            termsTermOrdinalTextField.setText(selectedPto.getOrdinal());
            selectedPto = termsPtoListView.getItems().get(FIRST_ELEMENT);
            termsPtoListView.getFocusModel().focus(FIRST_ELEMENT);
        }
    }

    @FXML
    protected void onLongRosterButtonClick() throws Exception
    {
        doLongRoster(create);
        formatDisplayAndClean(ROSTER_LONG);
    }

    @FXML
    protected void onShortRosterButtonClick() throws Exception
    {
        doShortRoster(create);
        formatDisplayAndClean(ROSTER_SHORT);
    }

    @FXML
    protected void onExpiringButtonClick() throws Exception
    {
        doExpirations(create);
        formatDisplayAndClean(EXPIRATIONS);
    }

    @FXML
    protected void onBackupButtonCLick() throws IOException, InterruptedException
    {
        String cmd = "cmd.exe /c mysqldump -u " + USER_NAME + " -p" + PASSWORD + " --host=TRILDA --no-tablespaces raj > "
                + OS_PATH + BACKUP_FILE;
        Process process = Runtime.getRuntime().exec(cmd);
        Functions.StreamGobbler streamGobbler = new Functions.StreamGobbler(process.getInputStream(),
                System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        process.waitFor();
        infoAlert("Database backed up to\n" + OS_PATH + BACKUP_FILE + ".");
    }

    @FXML
    protected void onRestoreButtonClick() throws IOException, InterruptedException
    {
        String cmd = "cmd.exe /c mysql -u " + USER_NAME + " -p" + PASSWORD + " raj < " + OS_PATH + BACKUP_FILE;
        gobble(cmd);
        infoAlert("Database restored from\n" + OS_PATH + BACKUP_FILE);
    }

    public void peopleNewPersonAction()
    {
        try {
            String workingImage = peopleImageNameTextField.getText();
            if (!isValidImage(workingImage)) {
                throw new RuntimeException("\"" + workingImage + "\" is not a valid image name.");
            }
            String workingFirst = peopleFirstTextField.getText();
            if (!isValidFirst(workingFirst)) {
                throw new RuntimeException("\"" + workingFirst + "\" is not a valid first name.");
            }
            String workingLast = peopleLastTextField.getText();
            if (!isValidLast(workingLast)) {
                throw new RuntimeException("\"" + workingLast + "\" is not a valid last name.");
            }
            String workingEmail = peopleEmailTextField.getText();
            if (!isValidEmail(workingEmail)) {
                throw new RuntimeException("\"" + workingEmail + "\" is not a valid email address.");
            }
            String workingPhone = peoplePhoneTextField.getText();
            if (!isValidPhone(workingPhone)) {
                throw new RuntimeException("\"" + workingPhone + "\" is not a valid phone number.");
            }
            String workingApt = peopleAptTextField.getText();
            if (!isValidApt(workingApt)) {
                throw new RuntimeException("\"" + workingApt + "\" is not a valid apartment number.");
            }
            PersonRecord workingRecord =
                    create.select(personFields).from(personTable).where(personFirst.eq(workingFirst))
                            .and(personLast.eq(workingLast)).fetchOneInto(PersonRecord.class);
            if (workingRecord != null) {
                throw new RuntimeException("Person named \"" + workingFirst + " " + workingLast + "\" already exists"
                        + ".");
            }
            workingRecord = new PersonRecord(0L, workingFirst, workingLast, workingEmail, workingPhone,
                    workingApt, workingImage);
            create.insertInto(personTable).columns(personFields).values(workingRecord)
                    .returning(field(personId)).execute();
            selectedPerson = create.select(personFields).from(personTable).where(personFirst.eq(workingFirst))
                    .and(personLast.eq(workingLast)).fetchOneInto(PersonRecord.class);
            addSelectedPerson(selectedPerson, peoplePersonListObs, peoplePersonListView);
            peoplePersonListView.getSelectionModel().select(selectedPerson);
            peoplePersonListView.scrollTo(selectedPerson);
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void peopleUpdatePersonAction()
    {
        //TODO selectedPerson is sometimes null!?!?
        try {
            BigInteger workingId = new BigInteger(selectedPerson.getPersonid().toString());
            String workingImage = peopleImageNameTextField.getText();
            if (!isValidImage(workingImage)) {
                throw new RuntimeException("\"" + workingImage + "\" is not a valid image name.");
            }
            String workingFirst = peopleFirstTextField.getText();
            if (!isValidFirst(workingFirst)) {
                throw new RuntimeException("\"" + workingFirst + "\" is not a valid first name.");
            }
            String workingLast = peopleLastTextField.getText();
            if (!isValidLast(workingLast)) {
                throw new RuntimeException("\"" + workingLast + "\" is not a valid last name.");
            }
            selectedPerson = create.select(personFields).from(personTable).where(personId.eq(workingId)).fetchOneInto(PersonRecord.class);
            String workingEmail = peopleEmailTextField.getText();
            if (!isValidEmail(workingEmail)) {
                throw new RuntimeException("\"" + workingEmail + "\" is not a valid email address.");
            }
            String workingPhone = peoplePhoneTextField.getText();
            if (!isValidPhone(workingPhone)) {
                throw new RuntimeException("\"" + workingPhone + "\" is not a valid phone number.");
            }
            String workingApt = peopleAptTextField.getText();
            if (!isValidApt(workingApt)) {
                throw new RuntimeException("\"" + workingApt + "\" is not a valid apartment number.");
            }
            PersonRecord workingRecord = new PersonRecord(selectedPerson.getPersonid(), workingFirst, workingLast,
                    workingEmail, workingPhone, workingApt, workingImage);
            create.update(personTable).set(personFirst, workingFirst).set(personLast, workingLast)
                    .set(personEmail, workingEmail).set(personPhone, workingPhone).set(personApt, workingApt)
                    .set(personImage, workingImage).where(personId.eq(workingId)).execute();
            peoplePersonListObs.remove(selectedPerson);
            addSelectedPerson(workingRecord, peoplePersonListObs, peoplePersonListView);
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void peopleDeletePersonAction()
    {
        String deleteId = selectedPerson.getPersonid().toString();
        create.deleteFrom(personTable).where(personId.eq(new BigInteger(deleteId))).execute();
        peoplePersonListObs.remove(selectedPerson);
    }

    public void termsTermNewAction()
    {
        try {
            BodyRecord selectedTermBody = termsBodiesListView.getSelectionModel().getSelectedItem();
            if (selectedTermBody == null) {
                throw new RuntimeException("No body selected for new term.");
            }
            OfficeRecord selectedTermOffice = termsOfficesListView.getSelectionModel().getSelectedItem();
            if (selectedTermOffice == null) {
                throw new RuntimeException("No office selected for new term.");
            }
            LocalDate workingStart = LocalDate.parse(termsTermStartTextField.getText());
            LocalDate workingEnd = LocalDate.parse(termsTermEndTextField.getText());
            if (!isValidStartEnd(workingStart, workingEnd)) {
                throw new RuntimeException("Start and end must be non-null, start before end.");
            }
            String workingOrdinal = termsTermOrdinalTextField.getText();
            if (!isValidOrdinal(workingOrdinal)) {
                throw new RuntimeException("Ordinal must be \"First,\" \"Second,\" or \"None.\"");
            }
            TermRecord newTerm = new TermRecord(selectedPerson.getPersonid(),
                    selectedTermOffice.getOfficeid(), workingStart, workingEnd, workingOrdinal);
            create.insertInto(termTable).columns(termFields).values(newTerm).execute();
            PtoRecord newTermOfOffice = create.select(ptoFields).from(ptoTable)
                    .where(ptoTermPersonId.eq(BigInteger.valueOf(selectedPerson.getPersonid())))
                    .and(ptoTermOfficeId.eq(BigInteger.valueOf(selectedTermOffice.getOfficeid())))
                    .orderBy(bodyName).fetchOneInto(PtoRecord.class);
            termsPtoListObs.add(newTermOfOffice);
            termsPtoListView.scrollTo(newTermOfOffice);
        } catch (DataAccessException e) {
            errorAlert("This term of office already exists for " + termsPersonLabel.getText() + ".");
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void termsTermUpdateAction()
    {
        try {
            PtoRecord termToUpdate = termsPtoListView.getSelectionModel().getSelectedItem();
            if (termToUpdate == null) {
                throw new RuntimeException("No term of office selected to update.");
            }
            LocalDate newTermStart = LocalDate.parse(termsTermStartTextField.getText());
            LocalDate newTermEnd = LocalDate.parse(termsTermEndTextField.getText());
            if (!isValidStartEnd(newTermStart, newTermEnd)) {
                throw new RuntimeException("Start and end must be non-null, start before end.");
            }
            String newTermOrdinal = termsTermOrdinalTextField.getText();
            if (!isValidOrdinal(newTermOrdinal)) {
                throw new RuntimeException("Ordinal must be \"First,\" \"Second,\" or \"None.\"");
            }
            create.update(termTable)
                    .set(termStart, newTermStart)
                    .set(termEnd, newTermEnd)
                    .set(termOrdinal, newTermOrdinal)
                    .where(termOfficeId.eq(new BigInteger(termToUpdate.getTermofficeid().toString())))
                    .and(termPersonId.eq(new BigInteger(termToUpdate.getPersonid().toString()))).execute();
            termsPtoListObs.remove(termToUpdate);
            termToUpdate.set(termStart, newTermStart);
            termToUpdate.set(termEnd, newTermEnd);
            termToUpdate.set(termOrdinal, newTermOrdinal);
            termsPtoListObs.add(termToUpdate);
            termsPtoListView.getSelectionModel().select(termToUpdate);
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void termsTermDeleteAction()
    {
        try {
            PtoRecord termToDelete = termsPtoListView.getSelectionModel().getSelectedItem();
            if (termToDelete == null) {
                throw new RuntimeException("No term of office selected to delete.");
            }
            BigInteger deleteTermPersonId = new BigInteger(String.valueOf(termToDelete.getTermpersonid()));
            BigInteger deleteTermOfficeId = new BigInteger(String.valueOf(termToDelete.getTermofficeid()));
            create.deleteFrom(termTable).where(termPersonId.eq(deleteTermPersonId)
                    .and(termOfficeId.eq(deleteTermOfficeId))).execute();
            termsPtoListObs.remove(termToDelete);
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void officesNewBodyAction()
    {
        try {
            String workingName = officesBodyNameTextField.getText();
            BodyRecord workingRecord =
                    create.select(bodyFields).from(bodyTable).where(bodyName.eq(workingName))
                            .fetchOneInto(BodyRecord.class);
            if (workingRecord != null) {
                throw new RuntimeException("Body \"" + workingName + "\" already exists.");
            }
            String newMission = officesBodyMissionTextArea.getText();
            if (!isValidMission(newMission)) {
                throw new RuntimeException("Mission is limited to " + MAX_BODY_MISSION_LENGTH + " characters.");
            }
            Double newPrecedence = Double.parseDouble(officesBodyPrecedenceTextField.getText());
            workingRecord = new BodyRecord(null, "missingImage.jpg", workingName, newMission,
                    newPrecedence);
            create.insertInto(bodyTable).columns(bodyFields).values(workingRecord).execute();
            /* Retrieve the newly added record with the autoincremented id. */
            workingRecord = create.select(bodyFields).from(bodyTable).where(bodyName.eq(workingName))
                    .fetchOneInto(BodyRecord.class);
            officesBodyListObs.add(workingRecord);
            officesBodyListObs.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            officesBodyListView.getSelectionModel().select(workingRecord);
        } catch (NumberFormatException e) {
            errorAlert(
                    "Precedence \"" + officesBodyPrecedenceTextField.getText() + "\" must be formatted as a double.");
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void officesUpdateBodyAction()
    {
        try {
            String newImageFile = officesBodyImageFileTextField.getText();
            if (!isValidImage(newImageFile)) {
                throw new RuntimeException("\"" + newImageFile + "\" is not valid image file name.");
            }
            File checkFile = new File(IMAGE_PATH + newImageFile);
            if (!checkFile.exists()) {
                officesBodyImageFileTextField.setText(selectedBody.getBodyimage());
                throw new RuntimeException(newImageFile + " not found.");
            }
            String newBody = officesBodyNameTextField.getText();
            if (!isValidName(newBody)) {
                throw new RuntimeException("Body name must be <= " + MAX_BODY_NAME_LENGTH + " and >= "
                        + MIN_BODY_NAME_LENGTH);
            }
            double newPrecedenceDouble = Double.parseDouble(officesBodyPrecedenceTextField.getText());
            BigDecimal newPrecedenceBigDecimal = new BigDecimal(newPrecedenceDouble);
            String newMission = officesBodyMissionTextArea.getText();
            if (!isValidMission(newMission)) {
                throw new RuntimeException("Mission is limited to " + MAX_BODY_MISSION_LENGTH + " characters.");
            }
            BodyRecord newBodyRecord = new BodyRecord(selectedBody.getBodyid(), newImageFile, newBody, newMission,
                    newPrecedenceDouble);
            BigInteger selectedBodyId = new BigInteger(String.valueOf(selectedBody.getBodyid()));
            create.update(bodyTable).set(bodyImage, newImageFile).set(bodyName, newBody).set(bodyMission, newMission)
                    .set(bodyPrecedence, newPrecedenceBigDecimal).where(bodyId.eq(selectedBodyId)).execute();
            officesBodyListObs.remove(selectedBody);
            officesBodyListObs.add(newBodyRecord);
            officesBodyListObs.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            officesBodyListView.getSelectionModel().select(newBodyRecord);
        } catch (NumberFormatException e) {
            errorAlert(
                    "Precedence \"" + officesBodyPrecedenceTextField.getText() + "\" must be formatted as a double.");
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void officesDeleteBodyAction()
    {
        BigInteger deleteId = new BigInteger(selectedBody.getBodyid().toString());
        create.deleteFrom(bodyTable).where(bodyId.eq(deleteId)).execute();
        officesBodyListObs.remove(selectedBody);
    }

    public void officesNewOfficeAction()
    {
        try {
            String workingTitle = officesOfficeTitleTextField.getText();
            if (!isValidTitle(workingTitle)) {
                throw new RuntimeException("Title must be <= " + MAX_OFFICE_TITLE_LENGTH + " and >= "
                        + MIN_OFFICE_TITLE_LENGTH);
            }
            OfficeRecord workingRecord = create.select(officeFields).from(officeTable)
                    .where(officeTitle.eq(workingTitle).and(officeBodyId.eq(new BigInteger(
                            String.valueOf(selectedBody.getBodyid())))))
                    .fetchOneInto(OfficeRecord.class);
            if (workingRecord != null) {
                throw new RuntimeException("Office \"" + workingTitle + "\" already exists for this body.");
            }
            Double newPrecedence = Double.parseDouble(officesOfficePrecedenceTextField.getText());
            Long newBodyId = selectedBody.getBodyid();
            workingRecord = new OfficeRecord(null, workingTitle, newPrecedence, newBodyId);
            create.insertInto(officeTable).columns(officeFields).values(workingRecord).execute();
            /* Retrieve the newly added record with the autoincremented id. */
            workingRecord = create.select(officeFields).from(officeTable).where(officeTitle.eq(workingTitle))
                    .fetchOneInto(OfficeRecord.class);
            officesOfficeListObs.add(workingRecord);
            officesOfficeListObs.sort(Comparator.comparing(OfficeRecord::getOfficeprecedence));
            officesOfficeListView.getSelectionModel().select(workingRecord);
        } catch (NumberFormatException e) {
            errorAlert("Precedence \"" + officesOfficePrecedenceTextField.getText()
                    + "\" must be formatted as a double.");
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void officesUpdateOfficeAction()
    {
        try {
            String newTitle = officesOfficeTitleTextField.getText();
            if (!isValidTitle(newTitle)) {
                throw new RuntimeException("Title must be <= " + MAX_OFFICE_TITLE_LENGTH + " and >= "
                        + MIN_OFFICE_TITLE_LENGTH);
            }
            double newPrecedenceDouble = Double.parseDouble(officesOfficePrecedenceTextField.getText());
            BigDecimal newPrecedenceBigDecimal = new BigDecimal(newPrecedenceDouble);
            OfficeRecord newOfficeRecord = new OfficeRecord(selectedOffice.getOfficeid(), newTitle, newPrecedenceDouble,
                    selectedBody.getBodyid());
            BigInteger selectedBodyId = new BigInteger(String.valueOf(selectedBody.getBodyid()));
            BigInteger selectedOfficeId = new BigInteger(String.valueOf(selectedOffice.getOfficeid()));
            create.update(officeTable).set(officeTitle, officesOfficeTitleTextField.getText()).set(officePrecedence,
                            newPrecedenceBigDecimal).where(officeId.eq(selectedOfficeId))
                    .and(officeBodyId.eq(selectedBodyId)).execute();
            officesOfficeListObs.remove(selectedOffice);
            officesOfficeListObs.add(newOfficeRecord);
            officesOfficeListObs.sort(Comparator.comparing(OfficeRecord::getOfficeprecedence));
            officesOfficeListView.getSelectionModel().select(newOfficeRecord);
        } catch (NumberFormatException e) {
            errorAlert("Precedence \"" + officesOfficePrecedenceTextField.getText()
                    + "\" must be formatted as a double.");
        } catch (RuntimeException e) {
            errorAlert(e.getMessage());
        }
    }

    public void officesDeleteOfficeAction()
    {
        BigInteger deleteId = new BigInteger(selectedOffice.getOfficeid().toString());
        create.deleteFrom(officeTable).where(officeId.eq(deleteId)).execute();
        officesOfficeListObs.remove(selectedOffice);
    }
}
