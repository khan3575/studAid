package com.example.studaid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class DashboardController implements Initializable {


    ///  All the points button ar other items are decleared
    @FXML
    private AnchorPane addStudents_form;
    @FXML
    private Button addStudents_addBtn;

    @FXML
    private DatePicker addStudents_birthdate;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<Student, Date> addStudents_col_birthdate;

    @FXML
    private TableColumn<Student,String> addStudents_col_course;

    @FXML
    private TableColumn<Student, String> addStudents_col_firstName;

    @FXML
    private TableColumn<Student, String> addStudents_col_gender;

    @FXML
    private TableColumn<Student, String> addStudents_col_lastName;

    @FXML
    private TableColumn<Student, String > addStudents_col_status;

    @FXML
    private TableColumn<Student, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<Student, String > addStudents_col_year;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private ComboBox<String> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<String> addStudents_status;

    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private TableView<Student> addStudents_tableView;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private ComboBox<String> addStudents_year;

    @FXML
    private Button availableCourse_addBtn;

    @FXML
    private Button availableCourse_btn;

    @FXML
    private Button availableCourse_clearBtn;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_course;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_degree;

    @FXML
    private TableColumn<courseData, String> availableCourse_col_description;

    @FXML
    private TextField availableCourse_course;

    @FXML
    private TextField availableCourse_degree;

    @FXML
    private Button availableCourse_deleteBtn;

    @FXML
    private TextField availableCourse_description;

    @FXML
    private AnchorPane availableCourse_form;

    @FXML
    private TableView<courseData> availableCourse_tableView;

    @FXML
    private Button availableCourse_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private BarChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalMaleChart;

    @FXML
    private Button logOut_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane student;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableColumn<?, ?> studentGrade_col_course;

    @FXML
    private TableColumn<?, ?> studentGrade_col_final;

    @FXML
    private TableColumn<?, ?> studentGrade_col_firstSem;

    @FXML
    private TableColumn<?, ?> studentGrade_col_secondSem;

    @FXML
    private TableColumn<?, ?> studentGrade_col_studentNum;

    @FXML
    private TableColumn<?, ?> studentGrade_col_year;

    @FXML
    private Label studentGrade_course;

    @FXML
    private TextField studentGrade_firstSem;

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private TextField studentGrade_secondSem;

    @FXML
    private TextField studentGrade_studentNum;

    @FXML
    private TableView<Student> studentGrade_tableView;
    private Image image;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Label studentGrade_year;

    @FXML
    private TextField stutdentGrade_search;

    @FXML
    private Label username;
    private double x=0,y=0;
    private Connection connect = DatabaseService.instance.getConnection();
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    //MARK: - Private data
    private Integer selectedStudentId;
    private Alert alert;
    public void deleteStudent() {

        String deleteData = "DELETE FROM student WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    //showing table
                    addStudentsShowListData();
                    // clearing text fields and other fields

                    clearStudent();

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addStudent() {
        String insertData = "INSERT INTO student "
                + "(studentNum, year, course, firstName,lastName,gender, birth, status,image,date)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?)";

        // connect = database.connectionDb();

        try {
            if(addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem()==null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem()==null
                    || addStudents_birthdate.getValue() ==null
                    || addStudents_status.getSelectionModel().getSelectedItem()== null
                    || getImageData.path ==null) {

               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("Please fill in the blank fields");
               alert.showAndWait();
            }
            else{
                String checkData = "SELECT studentNum FROM student WHERE studentNum = '" +addStudents_studentNum.getText()+"'";

                statement = connect.createStatement();
                result= statement.executeQuery(checkData);

                if(result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #"+ addStudents_studentNum.getText()+" already exist!");
                    alert.showAndWait();
                }
                else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addStudents_studentNum.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(addStudents_birthdate.getValue()));
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    String uri = getImageData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(9, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(10, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    clearStudent();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateStudent() {

        String uri = getImageData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE student SET "
                + "year = '" + addStudents_year.getSelectionModel().getSelectedItem()
                + "', course = '" + addStudents_course.getSelectionModel().getSelectedItem()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_lastName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', birth = '" + addStudents_birthdate.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', image = '" + uri + "' WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birthdate.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getImageData.path == null ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    clearStudent();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void clearStudent(){
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birthdate.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);
        getImageData.path ="";
    }
    public void insertImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","jpg", "*png"));
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if(file != null)
        {
            image = new Image(file.toURI().toString(),120, 150,false, true);
            addStudents_imageView.setImage(image);

            getImageData.path= file.getAbsolutePath();

        }
    }


    final String[] enrollmentStatus = {
            "Enrolled",
            "Not Enrolled",
            "In-Active"
    };

    public void enrollmentStatusList() {
        List<String> statusL= new ArrayList<>();
        for(String data: enrollmentStatus)
        {
            statusL.add(data);
        }
        ObservableList ObList= FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }



    final String [] gender ={ "Male",
            "Female"
    };
    public void gender() {

    }
    public void addStudentsGenderList(){
        List<String> genderL = new ArrayList<>();
        for(String data: gender)
        {
            genderL.add(data);
        }
        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }
    final String[] academicYears = {
            "First Year",
            "Second Year",
            "Third Year",
            "Fourth Year"
    };
    public void academicYearList(){
        List<String> yearL = new ArrayList<>();
        for(String data: academicYears)
        {
            yearL.add(data);
        }
        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);
    }


    public ObservableList<Student> fetchStudents(){
        //creating a list to store the data
        ObservableList<Student> listStudents = FXCollections.observableArrayList();

        //query to collect data from mysql server and connnection
        String sql = "SELECT * FROM student";

        // connect = database.connectionDb();
        try {
            //creating a data model and executing querys
            Student data;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                data = new Student(result.getInt("studentNum")
                        , result.getString("year")
                        ,result.getString("course")
                        , result.getString("firstName")
                        , result.getString("lastName")
                        ,result.getString("gender")
                        , result.getDate("birth")
                        , result.getString("status")
                        , result.getString("image"));
                listStudents.add(data);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //returning the query result
        return listStudents;
    }



    private ObservableList<Student> addStudentListD;

    public void addStudentsShowListData()
    {
        //creating a list to show previously added students
        addStudentListD = fetchStudents();
        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentListD);
    }

    public void addStudentsCourseList(){
        String listCourse = "Select * FROM course";
        // connect = database.connectionDb();
        try {
            ObservableList listC = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();
            while(result.next()){
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);
        }
        catch (Exception e){e.printStackTrace();}
    }




    public void selectStudent(){
        Student student = addStudents_tableView.getSelectionModel().getSelectedItem();

        addStudents_studentNum.setText(String.valueOf(student.getId()));
        addStudents_firstName.setText(student.getFirstName());
        addStudents_lastName.setText(student.getLastName());
        String uri ="file:"+ student.getImage();
        image = new Image(uri,120, 150, false, true );
        addStudents_imageView.setImage(image);
    }

    //working on available course list part;
    public ObservableList<courseData> availableCourseListData(){
        ObservableList<courseData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM course" ;
//        connect = database.connectionDb();
        try {
            courseData courseD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next())
            {
                courseD = new courseData( result.getString("course")
                        , result.getString("description")
                        ,result.getString("degree"));
                    listData.add(courseD);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }


    private  ObservableList<courseData> availableCourseList;
    public void availableCourseShowListData(){

        availableCourseList = availableCourseListData();

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        availableCourse_tableView.setItems(availableCourseList);

    }


    public void availableCourseSelect(){
        courseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)<-1)
        {
            return;
        }
        availableCourse_course.setText(courseD.getCourse());
        availableCourse_description.setText(courseD.getDescription());
        availableCourse_degree.setText(courseD.getDegree());
    }

    public void availableCourseAdd()
    {
        String insertData = "INSERT INTO course (course, description, degree) VALUES(?,?,?)";
//        connect = database.connectionDb();

        try{
            Alert alert;
            if (availableCourse_course.getText().isEmpty()
            ||availableCourse_description.getText().isEmpty()
            ||availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{
                String checkData = "SELECT course FROM course WHERE course = '"+ availableCourse_course.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                if (result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("course: "+ availableCourse_course.getText() + " was already exist!");
                    alert.showAndWait();
                }
                else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1,availableCourse_course.getText());
                    prepare.setString(2, availableCourse_description.getText());
                    prepare.setString(3, availableCourse_degree.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Sucessfully Added!");
                    alert.showAndWait();

                    availableCourseShowListData();
                    availableCourseClear();

                }


            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void availableCourseClear() {
        availableCourse_course.setText("");
        availableCourse_description.setText("");
        availableCourse_degree.setText("");
    }

    public void availableCourseUpdate(){
        String updateData = "UPDATE course SET description = '"
                +availableCourse_description.getText()+"', degree = '"
                +availableCourse_degree.getText()+"' WHERE course = '"
                +availableCourse_course.getText()+"'";

        // connect = database.connectionDb();
        try {
            Alert alert;
            if (availableCourse_course.getText().isEmpty()
                    ||availableCourse_description.getText().isEmpty()
                    ||availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK))
                {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Information Updated successfully");
                    alert.showAndWait();

                    availableCourseShowListData();
                    availableCourseClear();
                }
                else return;

            }
        }catch (Exception e)
        {e.printStackTrace();}
    }

    public void availableCourseDelete(){

        String deleteData ="DELETE FROM course WHERE course = '"+ availableCourse_course.getText()+"'";
        Alert alert;
        // connect = database.connectionDb();
        try {

            if (availableCourse_course.getText().isEmpty()
                    || availableCourse_description.getText().isEmpty()
                    || availableCourse_degree.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();


                    availableCourseShowListData();

                    availableCourseClear();

                }
                else{
                    return;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }




    public void logout()
    {

        try{
            logOut_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("fxmls/Login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) ->{
                x= event.getSceneX();
                y=event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event)->{
                stage.setX(event.getScreenX()-x);
                stage.setY(event.getScreenY()-y);
                stage.setOpacity(.8);
            });
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void close()
    {
        //close button
        System.exit(0);
    }


    public void minimize(){
        //minimize button
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }



    public void switchForm(ActionEvent event) {
        //this portion switch the 4 manus for the admin panel

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);
        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            addStudentsShowListData();
            academicYearList();
            addStudentsGenderList();
            enrollmentStatusList();
            addStudentsCourseList();
        } else if (event.getSource() == availableCourse_btn)
        {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            availableCourseShowListData();

        }
        else if(event.getSource()== studentGrade_btn){
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);
        }

    }


    public void initialize(URL location, ResourceBundle resource){
        //this call initialize when this dashboard is open.
        addStudentsShowListData();
        academicYearList();
        addStudentsGenderList();
        enrollmentStatusList();
        addStudentsCourseList();

        availableCourseShowListData();
    }


}
