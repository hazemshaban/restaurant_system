/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class restuarant extends Application {

    @Override
    public void start(Stage stage) {

        //==========================================
        List<Users> user = new ArrayList<>();
        List<Tables> tab = new ArrayList<>();
        List<Client> customer = new ArrayList<>();
        List<Dish> dishList = new ArrayList<>();
        Manager manager = new Manager();
        Cooker cooker = new Cooker();
        Waiter waiter = new Waiter();
        //===========================================

        //read xml files
        //===============================================================================
        try {
            String filePath = "src\\javafxapplication4\\users.xml";
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("user");
            int tLength = nodeList.getLength();
            NodeList nodeList1 = doc.getElementsByTagName("dish");
            int tLength1 = nodeList1.getLength();
            NodeList nodeList2 = doc.getElementsByTagName("table");
            int tLength2 = nodeList2.getLength();

            for (int i = 0; i < tLength; i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Users f = new Users();
                    f.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    f.setRole(element.getElementsByTagName("role").item(0).getTextContent());
                    f.setUsername(element.getElementsByTagName("username").item(0).getTextContent());
                    f.setPassword(element.getElementsByTagName("password").item(0).getTextContent());
                    user.add(f);

                }

            }

            for (int i = 0; i < tLength1; i++) {
                Node node = nodeList1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Dish f = new Dish();
                    f.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    f.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
                    f.setType(element.getElementsByTagName("type").item(0).getTextContent());
                    dishList.add(f);

                }

            }

            for (int i = 0; i < tLength2; i++) {
                Node node = nodeList2.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Tables f = new Tables();
                    f.n = element.getElementsByTagName("number").item(0).getTextContent();
                    f.nos = element.getElementsByTagName("number_of_seats").item(0).getTextContent();
                    f.s = element.getElementsByTagName("smoking").item(0).getTextContent();
                    tab.add(f);

                }

            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
        }

        //=========================================================================================
        //scene-->(1) login page
        //==============================================================================        
        Group g1 = new Group();
        Scene s1 = new Scene(g1, 600, 400);
        stage.setScene(s1);

        stage.show();
        stage.setTitle("RESTUARANT");
        stage.setX(250);
        stage.setY(70);
        stage.setResizable(false);

        Image image = new Image("4242647-restaurant.jpg");
        ImageView imageView = new ImageView(image);

        Button b1 = new Button("CONFIRM");
        Button b14 = new Button("Sign Up");

        TextField t1 = new TextField();
        PasswordField t2 = new PasswordField();

        Label l1 = new Label("UserName");
        Label l2 = new Label("Password");
        Label alartUcorrect = new Label();

        RadioButton rb1 = new RadioButton("Client");
        RadioButton rb2 = new RadioButton("Manager");
        RadioButton rb3 = new RadioButton("Cooker");
        RadioButton rb4 = new RadioButton("Waiter");
        ToggleGroup tg = new ToggleGroup();

        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);
        rb4.setToggleGroup(tg);
        rb1.setUserData("Client");
        rb2.setUserData("Manager");
        rb3.setUserData("Cooker");
        rb4.setUserData("Waiter");
        rb1.setPadding(new Insets(4));
        rb2.setPadding(new Insets(4));
        rb3.setPadding(new Insets(4));
        rb4.setPadding(new Insets(4));
        rb1.setTranslateX(120);
        rb1.setTranslateY(200);
        rb1.setFont(new Font("Arial", 13));
        rb1.setTextFill(Color.WHITE);
        rb1.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb2.setTranslateX(220);
        rb2.setTranslateY(200);
        rb2.setFont(new Font("Arial", 13));
        rb2.setTextFill(Color.WHITE);
        rb2.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb3.setTranslateX(320);
        rb3.setTranslateY(200);
        rb3.setFont(new Font("Arial", 13));
        rb3.setTextFill(Color.WHITE);
        rb3.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb4.setTranslateX(420);
        rb4.setTranslateY(200);
        rb4.setFont(new Font("Arial", 13));
        rb4.setTextFill(Color.WHITE);
        rb4.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        l1.setTranslateX(150);
        l1.setTranslateY(100);
        l1.setFont(new Font("Arial", 13));
        l1.setTextFill(Color.WHITE);
        l1.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l2.setTranslateX(150);
        l2.setTranslateY(150);
        l2.setFont(new Font("Arial", 13));
        l2.setTextFill(Color.WHITE);
        l2.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l1.setPadding(new Insets(4));
        l2.setPadding(new Insets(4));

        alartUcorrect.setTranslateX(230);
        alartUcorrect.setTranslateY(70);
        alartUcorrect.setFont(new Font("Arial", 12));
        alartUcorrect.setTextFill(Color.WHITE);

        t1.setTranslateX(230);
        t1.setTranslateY(100);
        t2.setTranslateX(230);
        t2.setTranslateY(150);

        b1.setTranslateX(170);
        b1.setTranslateY(250);
        b1.setFont(new Font("Arial", 13));
        b1.setTextFill(Color.WHITE);
        b1.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b14.setTranslateX(320);
        b14.setTranslateY(250);
        b14.setPrefSize(80, 20);
        b14.setFont(new Font("Arial", 14));
        b14.setTextFill(Color.WHITE);
        b14.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        g1.getChildren().addAll(imageView, l1, l2, t1, t2, rb1, rb2, rb3, rb4, b1, b14, alartUcorrect);
        //=================================================================================================

        //SCENE-->(2) tables page
        //==================================================================================================
        Group g2 = new Group();
        Scene s2 = new Scene(g2, 600, 400);

        Image image2 = new Image("4242647-restaurant.jpg");
        ImageView imageView2 = new ImageView(image2);
        Label l3 = new Label("Please Choose Availabe Table");
        TextField t3 = new TextField();
        Label l4 = new Label("enter the number of table");
        Button b3 = new Button("Make Order");
         Label ll1 = new Label("from 12 pm to 12 am");
TextField t = new TextField();
        imageView2.setFitHeight(400);
        imageView2.setFitWidth(600);

        l3.setTranslateX(170);
        l3.setTranslateY(50);
        l3.setFont(new Font("Arial", 13));
        l3.setTextFill(Color.WHITE);
        l3.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l3.setPadding(new Insets(1));
        l4.setTranslateX(50);
        l4.setTranslateY(305);
        l4.setFont(new Font("Arial", 13));
        l4.setTextFill(Color.WHITE);
        l4.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l4.setPadding(new Insets(1));

        ll1.setTranslateX(120);
        ll1.setTranslateY(20);
        ll1.setFont(new Font("Arial", 13));
        ll1.setTextFill(Color.WHITE);
        ll1.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        ll1.setPadding(new Insets(1));

        b3.setTranslateX(220);
        b3.setTranslateY(320);
        b3.setPrefSize(120, 50);
        b3.setFont(new Font("Arial", 13));
        b3.setTextFill(Color.WHITE);
        b3.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        t3.setTranslateX(210);
        t3.setTranslateY(300);

           t.setTranslateX(300);
        t.setTranslateY(20);

        TableView table = new TableView();
        TableColumn name = new TableColumn("TABLE");
        TableColumn nos = new TableColumn("NUMBER OF SEATS");
        TableColumn type = new TableColumn("SMOKING");
        TableColumn a = new TableColumn("avilable    ");
        table.getColumns().add(name);
        table.getColumns().add(nos);
        table.getColumns().add(type);
        table.getColumns().add(a);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        nos.setCellValueFactory(new PropertyValueFactory<>("nos"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        a.setCellValueFactory(new PropertyValueFactory<>("a"));
        a.setMinWidth(120);
        table.setPrefSize(400, 220);
        table.setTranslateX(90);
        table.setTranslateY(70);
        table.setBackground(new Background(new BackgroundFill(Color.GOLD, new CornerRadii(5), Insets.EMPTY)));

        g2.getChildren().addAll(imageView2, b3, t3, l4, l3, table,ll1,t);
        //==========================================================================================

        //Scene -->(3) menu
        //===================================================================================
        Group g3 = new Group();
        Scene s3 = new Scene(g3, 1000, 500);
        Image image3 = new Image("4242647-restaurant.jpg");
        ImageView imageView3 = new ImageView(image3);
        Button b4 = new Button("logout");
        Button b5 = new Button("CHECK OUT");
        Label l6 = new Label("APPETIZER");
        Label l7 = new Label("DESERT");
        Label l5 = new Label("MAIN COURCE");
        Label l14 = new Label("*Please Select Dish First ");

        imageView3.setFitHeight(500);
        imageView3.setFitWidth(1000);

        b4.setTranslateX(900);
        b4.setTranslateY(450);
        b4.setFont(new Font("Arial", 13));
        b4.setTextFill(Color.WHITE);
        b4.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b5.setTranslateX(100);
        b5.setTranslateY(400);
        b5.setFont(new Font("Arial", 14));
        b5.setTextFill(Color.WHITE);
        b5.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b5.setPrefSize(150, 80);

        RadioButton rb5 = new RadioButton(dishList.get(0).getName() + "  " + dishList.get(0).getPrice() + " LE");
        RadioButton rb6 = new RadioButton(dishList.get(1).getName() + "  " + dishList.get(1).getPrice() + " LE");
        RadioButton rb7 = new RadioButton(dishList.get(2).getName() + "  " + dishList.get(2).getPrice() + " LE");
        RadioButton rb8 = new RadioButton(dishList.get(3).getName() + "  " + dishList.get(3).getPrice() + " LE");
        RadioButton rb9 = new RadioButton(dishList.get(4).getName() + "  " + dishList.get(4).getPrice() + " LE");
        RadioButton rb10 = new RadioButton(dishList.get(5).getName() + "  " + dishList.get(5).getPrice() + " LE");
        RadioButton rb11 = new RadioButton(dishList.get(6).getName() + "  " + dishList.get(6).getPrice() + " LE");
        rb5.setTranslateX(50);
        rb5.setTranslateY(130);
        rb5.setFont(new Font("Arial", 13));
        rb5.setTextFill(Color.WHITE);
        rb5.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb10.setTranslateX(50);
        rb10.setTranslateY(230);
        rb10.setFont(new Font("Arial", 13));
        rb10.setTextFill(Color.WHITE);
        rb10.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb11.setTranslateX(50);
        rb11.setTranslateY(330);
        rb11.setFont(new Font("Arial", 13));
        rb11.setTextFill(Color.WHITE);
        rb11.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb6.setTranslateX(400);
        rb6.setTranslateY(130);
        rb6.setFont(new Font("Arial", 13));
        rb6.setTextFill(Color.WHITE);
        rb6.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb7.setTranslateX(400);
        rb7.setTranslateY(230);
        rb7.setFont(new Font("Arial", 13));
        rb7.setTextFill(Color.WHITE);
        rb7.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb8.setTranslateX(750);
        rb8.setTranslateY(130);
        rb8.setFont(new Font("Arial", 13));
        rb8.setTextFill(Color.WHITE);
        rb8.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb9.setTranslateX(750);
        rb9.setTranslateY(230);
        rb9.setFont(new Font("Arial", 13));
        rb9.setTextFill(Color.WHITE);
        rb9.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        Spinner<Integer> sp5;
        sp5 = new Spinner<>();
        sp5.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp6;
        sp6 = new Spinner<>();
        sp6.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp7;
        sp7 = new Spinner<>();
        sp7.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp8;
        sp8 = new Spinner<>();
        sp8.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp9;
        sp9 = new Spinner<>();
        sp9.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp10;
        sp10 = new Spinner<>();
        sp10.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
        Spinner<Integer> sp11;
        sp11 = new Spinner<>();
        sp11.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));

        sp5.setTranslateX(70);
        sp5.setTranslateY(150);
        sp10.setTranslateX(70);
        sp10.setTranslateY(250);
        sp11.setTranslateX(70);
        sp11.setTranslateY(350);
        sp6.setTranslateX(420);
        sp6.setTranslateY(150);
        sp7.setTranslateX(420);
        sp7.setTranslateY(250);
        sp8.setTranslateX(770);
        sp8.setTranslateY(150);
        sp9.setTranslateX(770);
        sp9.setTranslateY(250);

        l5.setTranslateX(40);
        l5.setTranslateY(70);
        l5.setFont(new Font("Arial", 16));
        l5.setTextFill(Color.WHITE);
        l5.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l5.setPadding(new Insets(10));
        l6.setTranslateX(390);
        l6.setTranslateY(70);
        l6.setFont(new Font("Arial", 16));
        l6.setTextFill(Color.WHITE);
        l6.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l6.setPadding(new Insets(10));
        l7.setTranslateX(740);
        l7.setTranslateY(70);
        l7.setFont(new Font("Arial", 16));
        l7.setTextFill(Color.WHITE);
        l7.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l7.setPadding(new Insets(10));
        l14.setTranslateX(40);
        l14.setTranslateY(40);
        l14.setFont(new Font("Arial", 11));
        l14.setTextFill(Color.WHITE);

        g3.getChildren().addAll(imageView3, b4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, sp5, sp6, sp7, sp8, sp9, sp10, sp11, l5, l6, l7, b5, l14);
        //=================================================================================================

        //Scene -->(4) check out 
        //=================================================================================================
        Group g4 = new Group();
        Scene s4 = new Scene(g4, 600, 400);

        Image image4 = new Image("4242647-restaurant.jpg");
        ImageView imageView4 = new ImageView(image4);
        Button b6 = new Button("LOGOUT");
        Button b7 = new Button("MENU");
        Button b15 = new Button("SAVE");
        ListView lv1 = new ListView();
        Label l11 = new Label("Please Choose Save To Save Your Infromation And Order");
        Label l13 = new Label();

        imageView4.setFitHeight(400);
        imageView4.setFitWidth(600);

        l11.setTranslateX(130);
        l11.setTranslateY(310);
        l11.setFont(new Font("Arial", 13));
        l11.setTextFill(Color.WHITE);
        l11.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l11.setPadding(new Insets(1));
        l13.setTranslateX(500);
        l13.setTranslateY(310);
        l13.setFont(new Font("Arial", 13));
        l13.setTextFill(Color.WHITE);
        l13.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l13.setPadding(new Insets(1));

        b6.setTranslateX(370);
        b6.setTranslateY(350);
        b6.setFont(new Font("Arial", 14));
        b6.setTextFill(Color.WHITE);
        b6.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b6.setPrefSize(120, 30);
        b7.setTranslateX(100);
        b7.setTranslateY(350);
        b7.setFont(new Font("Arial", 14));
        b7.setTextFill(Color.WHITE);
        b7.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b7.setPrefSize(120, 30);
        b15.setTranslateX(235);
        b15.setTranslateY(350);
        b15.setFont(new Font("Arial", 14));
        b15.setTextFill(Color.WHITE);
        b15.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b15.setPrefSize(120, 30);

        lv1.setPrefSize(450, 280);
        lv1.setTranslateX(65);
        lv1.setTranslateY(20);

        g4.getChildren().addAll(imageView4, b7, b6, b15, lv1, l11, l13);
        //==============================================================================================

        //Scene -->(5) waiter dashboard 
        //==========================================================================================
        Group g5 = new Group();
        Scene s5 = new Scene(g5, 600, 400);
        Image image5 = new Image("4242647-restaurant.jpg");
        ImageView imageView5 = new ImageView(image5);
        Button b11 = new Button("Logout");

        imageView5.setFitHeight(400);
        imageView5.setFitWidth(600);

        b11.setTranslateX(420);
        b11.setTranslateY(350);
        b11.setTranslateZ(200);
        b11.setFont(new Font("Arial", 14));
        b11.setTextFill(Color.WHITE);
        b11.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b11.setPrefSize(120, 30);

        TableView table2 = new TableView();
        TableColumn name1 = new TableColumn("NAME  ");
        TableColumn number = new TableColumn("NUMBER OF Table");
        name1.setMinWidth(130);
        number.setMinWidth(160);
        table2.getColumns().add(name1);
        table2.getColumns().add(number);
        name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setCellValueFactory(new PropertyValueFactory<>("nos"));
        table2.setPrefSize(320, 280);
        table2.setTranslateX(120);
        table2.setTranslateY(30);

        g5.getChildren().addAll(imageView5, b11, table2);
        //==========================================================================================

        //Scene -->(6)cooker dashbaord
        //=========================================================================================
        Group g6 = new Group();
        Scene s6 = new Scene(g6, 600, 400);
        Image image6 = new Image("4242647-restaurant.jpg");
        ImageView imageView6 = new ImageView(image6);
        Button b12 = new Button("Logout");

        b12.setTranslateX(420);
        b12.setTranslateY(350);
        b12.setTranslateZ(200);
        b12.setFont(new Font("Arial", 14));
        b12.setTextFill(Color.WHITE);
        b12.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b12.setPrefSize(120, 30);

        imageView6.setFitHeight(400);
        imageView6.setFitWidth(600);

        TableView table3 = new TableView();
        TableColumn name2 = new TableColumn("DISH");
        TableColumn Q = new TableColumn("QUANTITY");
        TableColumn number2 = new TableColumn("NUMBER OF TABLE");
        table3.getColumns().add(name2);
        table3.getColumns().add(Q);
        table3.getColumns().add(number2);
        name2.setMinWidth(130);
        number2.setMinWidth(160);
        Q.setMinWidth(130);
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Q.setCellValueFactory(new PropertyValueFactory<>("nos"));
        number2.setCellValueFactory(new PropertyValueFactory<>("a"));
        table3.setPrefSize(420, 280);
        table3.setTranslateX(70);
        table3.setTranslateY(30);

        g6.getChildren().addAll(imageView6, b12, table3);
        //=======================================================================================

        //Scene -->(7) manger dashboard
        //=======================================================================================
        Group g7 = new Group();
        Scene s7 = new Scene(g7, 1000, 600);
        Image image7 = new Image("4242647-restaurant.jpg");
        ImageView imageView7 = new ImageView(image7);
        Button b8 = new Button("LOGOUT");
        Button b9 = new Button("GET ORDER DETAILS");
        Button b10 = new Button("GET TOTAL EARNED MONEY ");
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        Label l12 = new Label("Enter ClientNumber");

        imageView7.setFitHeight(600);
        imageView7.setFitWidth(1000);

        TableView table4 = new TableView();
        TableColumn number3 = new TableColumn("N");
        TableColumn name3 = new TableColumn("CUSTOMER NAME");
        TableColumn money = new TableColumn("PAID MONEY");
        TableColumn tb = new TableColumn("TABLE NUMBER");
        table4.getColumns().add(number3);
        table4.getColumns().add(name3);
        table4.getColumns().add(money);
        table4.getColumns().add(tb);
        number3.setCellValueFactory(new PropertyValueFactory<>("name"));
        name3.setCellValueFactory(new PropertyValueFactory<>("nos"));
        money.setCellValueFactory(new PropertyValueFactory<>("type"));
        tb.setCellValueFactory(new PropertyValueFactory<>("a"));
        table4.setPrefSize(420, 330);
        table4.setTranslateX(50);
        table4.setTranslateY(70);

        b8.setTranslateX(800);
        b8.setTranslateY(470);
        b8.setFont(new Font("Arial", 13));
        b8.setTextFill(Color.WHITE);
        b8.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b8.setPrefSize(120, 20);
        b9.setTranslateX(520);
        b9.setTranslateY(100);
        b9.setFont(new Font("Arial", 13));
        b9.setTextFill(Color.WHITE);
        b9.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b9.setPrefSize(200, 30);
        b10.setTranslateX(50);
        b10.setTranslateY(450);
        b10.setFont(new Font("Arial", 13));
        b10.setTextFill(Color.WHITE);
        b10.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        b10.setPrefSize(250, 30);

        l12.setTranslateX(520);
        l12.setTranslateY(80);
        l12.setFont(new Font("Arial", 13));
        l12.setTextFill(Color.WHITE);
        l12.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        t4.setTranslateX(590);
        t4.setTranslateY(140);
        t4.setPrefSize(60, 20);
        t5.setTranslateX(320);
        t5.setTranslateY(450);
        t5.setPrefSize(100, 20);

        ListView lv2 = new ListView();
        lv2.setPrefSize(200, 150);
        lv2.setTranslateX(750);
        lv2.setTranslateY(100);

        g7.getChildren().addAll(imageView7, b10, b8, b9, t4, t5, table4, l12);
        //===================================================================================================

        //Scene -->(8) sign up
        //==================================================================================================
        Group g8 = new Group();
        Scene s8 = new Scene(g8, 600, 400);
        Image image8 = new Image("4242647-restaurant.jpg");
        ImageView imageView8 = new ImageView(image8);

        Button b13 = new Button("Save");
        TextField t6 = new TextField();
        PasswordField t7 = new PasswordField();
        TextField t8 = new TextField();

        RadioButton rb12 = new RadioButton("Client");
        RadioButton rb13 = new RadioButton("Manager");
        RadioButton rb14 = new RadioButton("Cooker");
        RadioButton rb15 = new RadioButton("Waiter");
        ToggleGroup tg1 = new ToggleGroup();

        Label l8 = new Label("Enter New UserName");
        l8.setPadding(new Insets(4));
        Label l9 = new Label("Enter New Password");
        l9.setPadding(new Insets(4));
        Label l10 = new Label("Enter Your Name");
        l10.setPadding(new Insets(4));
        Label alartUcorrect1 = new Label();

        l8.setTranslateX(70);
        l8.setTranslateY(100);
        l8.setFont(new Font("Arial", 13));
        l8.setTextFill(Color.WHITE);
        l8.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l9.setTranslateX(70);
        l9.setTranslateY(150);
        l9.setFont(new Font("Arial", 13));
        l9.setTextFill(Color.WHITE);
        l9.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        l10.setTranslateX(70);
        l10.setTranslateY(50);
        l10.setFont(new Font("Arial", 13));
        l10.setTextFill(Color.WHITE);
        l10.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        alartUcorrect1.setTranslateX(230);
        alartUcorrect1.setTranslateY(190);
        alartUcorrect1.setFont(new Font("Arial", 12));
        alartUcorrect1.setTextFill(Color.WHITE);

        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        t6.setTranslateX(230);
        t6.setTranslateY(100);
        t7.setTranslateX(230);
        t7.setTranslateY(150);
        t8.setTranslateX(230);
        t8.setTranslateY(50);

        b13.setTranslateX(250);
        b13.setTranslateY(250);
        b13.setFont(new Font("Arial", 13));
        b13.setTextFill(Color.WHITE);
        b13.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        rb12.setToggleGroup(tg1);
        rb13.setToggleGroup(tg1);
        rb14.setToggleGroup(tg1);
        rb15.setToggleGroup(tg1);
        rb12.setUserData("Client");
        rb13.setUserData("Manager");
        rb14.setUserData("Cooker");
        rb15.setUserData("Waiter");
        rb12.setPadding(new Insets(4));
        rb13.setPadding(new Insets(4));
        rb14.setPadding(new Insets(4));
        rb15.setPadding(new Insets(4));
        rb12.setTranslateX(120);
        rb12.setTranslateY(200);
        rb12.setFont(new Font("Arial", 13));
        rb12.setTextFill(Color.WHITE);
        rb12.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb13.setTranslateX(220);
        rb13.setTranslateY(200);
        rb13.setFont(new Font("Arial", 13));
        rb13.setTextFill(Color.WHITE);
        rb13.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb14.setTranslateX(320);
        rb14.setTranslateY(200);
        rb14.setFont(new Font("Arial", 13));
        rb14.setTextFill(Color.WHITE);
        rb14.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
        rb15.setTranslateX(420);
        rb15.setTranslateY(200);
        rb15.setFont(new Font("Arial", 13));
        rb15.setTextFill(Color.WHITE);
        rb15.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));

        g8.getChildren().addAll(imageView8, l8, l9, alartUcorrect1, t6, t7, b13, rb12, rb13, rb14, rb15, l10, t8);
        //=========================================================================================

        // buttons action
        //========================================================================================
        b1.setOnAction((ActionEvent e) -> {
            int x = 0;
            alartUcorrect.setText("");
            for (Users u : user) {

                if (tg.getSelectedToggle().getUserData().toString().equals(u.getRole()) && t1.getText().equals(u.getUsername()) && t2.getText().equals(u.getPassword())) {
                    x = 1;

                    if (u.getRole().equals("Client")) {
                        Client client = new Client();
                        client.setName(u.getName());
                        client.setRole(u.getRole());
                        client.setPassword(u.getPassword());
                        client.setUsername(u.getUsername());
                        customer.add(client);
                        ObservableList<Sort> contactList = FXCollections.observableArrayList();
                        for (int j = 0; j < 7; j++) {
                            contactList.addAll(FXCollections.observableArrayList(
                                    new Sort(tab.get(j).n, tab.get(j).nos, tab.get(j).s, tab.get(j).a)));
                        }
                        table.setItems(contactList);

                        stage.setScene(s2);
                    } else if (u.getRole().equals("Waiter")) {

                        ObservableList<Sort> contactList1 = FXCollections.observableArrayList();
                        for (Client c : customer) {
                            waiter.tablesList.add(c.table);
                            contactList1.addAll(FXCollections.observableArrayList(
                                    new Sort(c.getName(), c.table.n)));
                        }
                        table2.setItems(contactList1);

                        stage.setScene(s5);
                    } else if (u.getRole().equals("Cooker")) {

                        ObservableList<Sort> contactList2 = FXCollections.observableArrayList();
                        for (Client c : customer) {
                            for (Dish s : c.dishList) {
                                cooker.dishList.add(s);
                                contactList2.addAll(FXCollections.observableArrayList(
                                        new Sort(s.getName(), "" + s.getN(), c.getTable().n)));
                            }
                        }
                        table3.setItems(contactList2);

                        stage.setScene(s6);
                    } else if (u.getRole().equals("Manager")) {
                        int i = 1;
                        ObservableList<Sort> contactList3 = FXCollections.observableArrayList();
                        for (Client c : customer) {

                            contactList3.addAll(FXCollections.observableArrayList(
                                    new Sort(i + "", c.getName(), "" + c.getMoney(), c.table.n)));
                            i++;

                        }
                        table4.setItems(contactList3);

                        stage.setScene(s7);
                    }

                }
            }

            if (x == 0) {

                alartUcorrect.setText("* username or password is uncorrect");
                t2.setText(null);
            }

        });

        b3.setOnAction(Action -> {
            String x = t3.getText();
            if (tab.get(Integer.parseInt(t3.getText()) - 1).a.equals("not Available")) {

            } else if (tab.get(Integer.parseInt(x) - 1).a.equals("Available")) {

                tab.get(Integer.parseInt(x) - 1).a = "not Available";
                customer.get(customer.size() - 1).table.a = tab.get(Integer.parseInt(x) - 1).a;
                customer.get(customer.size() - 1).table.s = tab.get(Integer.parseInt(x) - 1).s;
                customer.get(customer.size() - 1).table.nos = tab.get(Integer.parseInt(x) - 1).nos;
                customer.get(customer.size() - 1).table.n = tab.get(Integer.parseInt(x) - 1).n;
                stage.setScene(s3);
            }
        });

        b4.setOnAction((ActionEvent e) -> {

            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            stage.setScene(s1);

        });

        b5.setOnAction((ActionEvent e) -> {
            if (rb5.isSelected()) {
                int x = sp5.getValue();
                dishList.get(0).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(0));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(0).getPrice() * x + dishList.get(0).getPrice() * x * (15.0 / 100.0));

            }
            if (rb6.isSelected()) {
                int x = sp6.getValue();
                dishList.get(1).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(1));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(1).getPrice() * x + dishList.get(1).getPrice() * x * (10.0 / 100.0));

            }
            if (rb7.isSelected()) {
                int x = sp7.getValue();
                dishList.get(2).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(2));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(2).getPrice() * x + dishList.get(2).getPrice() * x * (10.0 / 100.0));

            }
            if (rb8.isSelected()) {
                int x = sp8.getValue();
                dishList.get(3).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(3));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(3).getPrice() * x + dishList.get(3).getPrice() * x * (20.0 / 100.0));

            }
            if (rb9.isSelected()) {
                int x = sp9.getValue();
                dishList.get(4).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(4));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(4).getPrice() * x + dishList.get(4).getPrice() * x * (20.0 / 100.0));

            }
            if (rb10.isSelected()) {
                int x = sp10.getValue();
                dishList.get(5).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(5));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(5).getPrice() * x + dishList.get(5).getPrice() * x * (15.0 / 100.0));

            }
            if (rb11.isSelected()) {
                int x = sp11.getValue();
                dishList.get(6).setN(x);
                customer.get(customer.size() - 1).dishList.add(dishList.get(6));
                customer.get(customer.size() - 1).setMoney(customer.get(customer.size() - 1).getMoney() + dishList.get(6).getPrice() * x + dishList.get(6).getPrice() * x * (15.0 / 100.0));

            }

            for (Dish d : customer.get(customer.size() - 1).dishList) {

                if (d.getType().equals("main_course")) {
                    lv1.getItems().add(d.getN() + "x" + d.getName() + "\t\t\t\t\t\t\t\t(15%taxes) " + d.getPrice() + "LE");
                } else if (d.getType().equals("appetizer")) {
                    lv1.getItems().add(d.getN() + "x" + d.getName() + "\t\t\t\t\t\t\t\t(10%taxes) " + d.getPrice() + "LE");
                } else if (d.getType().equals("desert")) {
                    lv1.getItems().add(d.getN() + "x" + d.getName() + "\t\t\t\t\t\t\t\t(15%taxes) " + d.getPrice() + "LE");
                }

            }
            lv1.getItems().add("Total price\t\t\t\t\t\t\t\t" + customer.get(customer.size() - 1).getMoney() + "LE");
            l13.setText("");
            stage.setScene(s4);

        });
        b6.setOnAction((ActionEvent e) -> {

            if (!customer.get(customer.size() - 1).save) {
                tab.get(Integer.parseInt(t3.getText()) - 1).a = "Available";
                customer.remove(customer.size() - 1);

            }
            lv1.getItems().clear();
            rb5.setSelected(false);
            rb6.setSelected(false);
            rb7.setSelected(false);
            rb8.setSelected(false);
            rb9.setSelected(false);
            rb10.setSelected(false);
            rb11.setSelected(false);
            sp5.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp6.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp7.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp8.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp9.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp10.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
            sp11.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));

            t1.setText(null);

            t2.setText(null);
            t3.setText(null);
            stage.setScene(s1);

        });
        b7.setOnAction((ActionEvent e) -> {
            lv1.getItems().clear();
            customer.get(customer.size() - 1).dishList.clear();
            customer.get(customer.size() - 1).setMoney(0.0);

            stage.setScene(s3);

        });

        b8.setOnAction((ActionEvent e) -> {
            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            stage.setScene(s1);

        });
        g7.getChildren().add(lv2);

        b9.setOnAction((ActionEvent e) -> {
            lv2.getItems().clear();
            int x = Integer.parseInt(t4.getText());
            for (Dish s : customer.get(x - 1).dishList) {
                lv2.getItems().add(s.getN() + "x" + s.getName() + "\n");

            }
        });

        b10.setOnAction((ActionEvent e) -> {
            manager.setTotalMoney(0.0);
            for (Client s : customer) {
                manager.setTotalMoney(manager.getTotalMoney() + s.getMoney());

            }
            t5.setText(manager.getTotalMoney() + "LE");

        });

        b11.setOnAction((ActionEvent e) -> {
            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            stage.setScene(s1);

        });

        b12.setOnAction((ActionEvent e) -> {
            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            stage.setScene(s1);

        });

        b13.setOnAction((ActionEvent e) -> {

            Users c = new Users();
            c.setName(t8.getText());
            c.setUsername(t6.getText());
            c.setPassword(t7.getText());
            c.setRole(tg1.getSelectedToggle().getUserData().toString());
            user.add(c);
            t1.setText(null);
            t2.setText(null);

            stage.setScene(s1);

        });

        b14.setOnAction((ActionEvent e) -> {
            t6.setText(null);
            t7.setText(null);
            t8.setText(null);

            stage.setScene(s8);

        });

        b15.setOnAction((ActionEvent e) -> {
            l13.setText("Done ");

            String xmlFilePath = "src\\javafxapplication4\\save.xml";

            try {

                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();

                // root element
                Element root = document.createElement("restuarant");
                document.appendChild(root);

                for (Client c : customer) {
                    Element employee = document.createElement("client");

                    root.appendChild(employee);
                    Element Name = document.createElement("name");
                    Name.appendChild(document.createTextNode(c.getName()));
                    employee.appendChild(Name);

                    Element tablenumber = document.createElement("tablenumber");
                    tablenumber.appendChild(document.createTextNode(c.table.n));
                    employee.appendChild(tablenumber);

                    Element money5 = document.createElement("money");
                    money5.appendChild(document.createTextNode(c.getMoney() + ""));
                    employee.appendChild(money5);

                    Element dish = document.createElement("dish");
                    for (Dish d : c.dishList) {
                        dish.appendChild(document.createTextNode("\n" + d.getName()));
                    }
                    dish.appendChild(document.createTextNode("\n"));
                    employee.appendChild(dish);
                }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                transformer.transform(domSource, streamResult);

                System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
            customer.get(customer.size() - 1).save = true;

        });
        //===============================================================================
    }

    public static void main(String[] args) {

        launch(args);

    }

}
