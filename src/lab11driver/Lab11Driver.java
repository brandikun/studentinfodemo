/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11driver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 *
 * @author himes
 */
public class Lab11Driver extends Application {
    private final TableView<Student> table = new TableView();
    private final ObservableList<Student> studentList  = FXCollections.observableArrayList();
            
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        
        Scene scene = new Scene(new Group());
        stage.setTitle("University Student Management");
        stage.setWidth(1200);
        stage.setHeight(600);
        
        final Label label = new Label("Student Information System");
        
        table.setEditable(true);
          
        TableColumn fNCol = new TableColumn("First Name");
        TableColumn lNCol = new TableColumn("Last Name");
        TableColumn majorCol = new TableColumn("Major");
        TableColumn gPACol = new TableColumn("GPA");
        TableColumn uINCol = new TableColumn("UIN");
        TableColumn netIDCol = new TableColumn("Net ID");
        TableColumn ageCol = new TableColumn("Age");
        TableColumn genderCol = new TableColumn("Gender");
        fNCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lNCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));
        gPACol.setCellValueFactory(new PropertyValueFactory<>("gPA"));
        uINCol.setCellValueFactory(new PropertyValueFactory<>("uIN"));
        netIDCol.setCellValueFactory(new PropertyValueFactory<>("netID"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        fNCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        lNCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        majorCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        gPACol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        uINCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        netIDCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        ageCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        genderCol.prefWidthProperty().bind(table.widthProperty().multiply(.125));
        table.getColumns().addAll(fNCol, lNCol, majorCol, gPACol, uINCol, netIDCol, ageCol, 
                genderCol);
                
        Button btAdd = new Button("Add Student");
        //btAdd.setDefaultButton(true);
        Button btRemove = new Button("Remove Student");
        HBox btBox = new HBox();
        btBox.setSpacing(5);
        btBox.setPadding(new Insets(10));
        btBox.setAlignment(Pos.BASELINE_RIGHT);
        btBox.getChildren().addAll(btAdd, btRemove);
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5));
        vbox.getChildren().addAll(label, table, btBox);
        vbox.prefWidthProperty().bind(stage.widthProperty().multiply(.985));
        vbox.prefHeightProperty().bind(stage.heightProperty().multiply(.98));
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        table.setItems(studentList);
        stage.setScene(scene);
        stage.show();
        
        btAdd.setOnAction(e-> {
            addStudent(studentList);
        });
        
        btRemove.setOnAction(e -> {
            removeStudent(studentList);
        });
    }
    
    private void addStudent(ObservableList<Student> studentList) {
        Stage popStage = new Stage();
        VBox newStudent = new VBox(10);
        
        HBox stdInfo1 = new HBox(10);
        stdInfo1.setPadding(new Insets(10));
        
        HBox stdInfo2 = new HBox(10);
        stdInfo2.setPadding(new Insets(10));
        TextField tfFName = new TextField();
        TextField tfLName = new TextField();
        TextField tfMajor = new TextField();
        TextField tfGPA = new TextField();
        TextField tfUIN = new TextField();
        TextField tfNetID = new TextField();
        TextField tfAge = new TextField();
        TextField tfGender = new TextField();
        Label lblFName = new Label("First Name");
        lblFName.setPrefWidth(70);
        Label lblLName = new Label("Last Name");
        lblLName.setPrefWidth(70);
        Label lblMajor = new Label("Major");
        lblMajor.setPrefWidth(70);
        Label lblGPA = new Label("GPA");
        lblGPA.setPrefWidth(70);
        Label lblUIN = new Label("UIN");
        lblUIN.setPrefWidth(70);
        Label lblNetID = new Label("Net ID");
        lblNetID.setPrefWidth(70);
        Label lblAge = new Label("Age");
        lblAge.setPrefWidth(70);
        Label lblGender = new Label("Gender");
        lblGender.setPrefWidth(70);
        stdInfo1.getChildren().addAll(lblFName, tfFName, lblLName, tfLName, lblMajor, tfMajor, 
                lblGPA,tfGPA);
            
        stdInfo2.getChildren().addAll(lblUIN, tfUIN, lblNetID, tfNetID, lblAge, tfAge, lblGender, 
                tfGender);
        
        Button btSubmit = new Button("Submit");
        btSubmit.setDefaultButton(true);
        HBox btHBox = new HBox(10);
        btHBox.setAlignment(Pos.BASELINE_RIGHT);
        btHBox.getChildren().add(btSubmit);
        btHBox.setPadding(new Insets(10));
        
        newStudent.getChildren().addAll(new Label("Enter New Student Info"), stdInfo1, stdInfo2, 
                btHBox);
        
        Scene popScene = new Scene(newStudent);
        popStage.setScene(popScene);
        popStage.show();
        
        btSubmit.setOnAction(e -> {
            if(tfFName.getText() == null || tfFName.getText().trim().isEmpty() || 
                    tfLName.getText() == null || tfLName.getText().trim().isEmpty() )
                        dialogBox("You must enter at least a first and last name.");
            else {
                studentList.add(new Student(tfFName.getText(), tfLName.getText(), tfMajor.getText(), 
                        tfGPA.getText(), tfUIN.getText(), tfNetID.getText(), tfAge.getText(), 
                        tfGender.getText()));
                popStage.close();
            }
        });
    }
    
    private void removeStudent(ObservableList<Student> studentList) {
        Stage popStage = new Stage();
        
        VBox remStudent = new VBox();
        remStudent.setPadding(new Insets(10));
        remStudent.setAlignment(Pos.CENTER);
        
        TextField tfUINKey = new TextField();
        Button btSubmit = new Button("Submit");
        btSubmit.setDefaultButton(true);
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.BASELINE_RIGHT);
        bottom.getChildren().add(btSubmit);
        
        remStudent.getChildren().addAll(new Text("Enter the UIN of the student you want to "
                + "remove:"), tfUINKey, bottom);
        
        Scene popScene = new Scene(remStudent);
        popStage.setScene(popScene);
        popStage.show();
        
        btSubmit.setOnAction((ActionEvent e) -> {
            if (tfUINKey.getText() == null || tfUINKey.getText().trim().isEmpty())
                dialogBox("Please Enter a UIN.");
            else {
                boolean matchFlag = false;
                for(Student student: studentList) {
                    if(tfUINKey.getText().equals(student.getUIN())) {
                        studentList.remove(student);
                        matchFlag = true;
                        break;
                    }
                }
                if(!matchFlag) dialogBox("No student match was found.");
                popStage.close();
            }
        });
    }
 
    public static class Student {
  
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty major;
        private final SimpleStringProperty gPA;
        private final SimpleStringProperty uIN;
        private final SimpleStringProperty netID;
        private final SimpleStringProperty age;
        private final SimpleStringProperty gender;

        private Student(String newFirstName, String newLastName, String newMajor, String newGPA, 
                String newUIN, String newNetID, String newAge, String newGender) 
        {
            this.firstName = new SimpleStringProperty(newFirstName);
            this.lastName = new SimpleStringProperty(newLastName);
            this.major = new SimpleStringProperty(newMajor);
            this.gPA = new SimpleStringProperty(newGPA);
            this.uIN = new SimpleStringProperty(newUIN);
            this.netID = new SimpleStringProperty(newNetID);
            this.age = new SimpleStringProperty(newAge);
            this.gender = new SimpleStringProperty(newGender);
        }

        public void setFirstName(String newFirstName){
            firstName.set(newFirstName);
        }

        public String getFirstName(){
            return firstName.get();
        }

        public void setLastName(String newLastName){
            lastName.set(newLastName);
        }

        public String getLastName(){
            return lastName.get();
        }

        public void setMajor(String newMajor){
            major.set(newMajor);
        }

        public String getMajor(){
            return major.get();
        }

        public void setGPA(String newGPA){
            gPA.set(newGPA);
        }

        public String getGPA(){
            return gPA.get();
        }

        public void setUIN(String newUIN){
            uIN.set(newUIN);
        }

        public String getUIN(){
            return uIN.get();
        }

        public void setNetID(String newNetID){
            netID.set(newNetID);
        }

        public String getNetID(){
            return netID.get();
        }

        public void setAge(String newAge){
            age.set(newAge);
        }

        public String getAge(){
            return age.get();
        }

        public void setGender(String newGender){
            gender.set(newGender);
        }

        public String getGender(){
            return gender.get();
        }
    }
    public void dialogBox(String message) {
        Stage stage = new Stage();
        HBox dialogH = new HBox(10);

        dialogH.setPadding(new Insets(20));
        dialogH.setAlignment(Pos.CENTER);
        dialogH.getChildren().add(new Label(message));

        Button btOk = new Button("Ok");
        btOk.setDefaultButton(true);
        
        VBox dialog = new VBox();
        dialog.setPadding(new Insets(10));
        dialog.getChildren().addAll(dialogH, btOk);
        dialog.setAlignment(Pos.CENTER);

        Scene scene = new Scene(dialog);
        stage.setScene(scene);
        stage.show();

        btOk.setOnAction(f-> {
            stage.close();
        });
}
}
