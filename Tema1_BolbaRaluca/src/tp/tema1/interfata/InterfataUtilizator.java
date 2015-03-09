package tp.tema1.interfata;
import tp.tema1.polinom.*;


import java.util.Arrays;
import java.util.StringTokenizer;

import tp.tema1.polinom.Polinom;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class InterfataUtilizator extends Application
{
	private Button ok2;
	private Button ok = new Button("Ok");
	private Button inapoi = new Button("Inapoi");
	private Label label;
	private TextArea textR = new TextArea();
	private TextField field;
	
	private Polinom pol, pol1, pol2, polR;
	private String sir1, sir2, listChoice;
	private int[] coef1, coef2, coef;
	
	public static Stage primaryStage;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void afiseazaMesajEroare(String s)
	{
		VBox vtext, vbuton, vWnd;
		vtext = new VBox();
		vbuton = new VBox();
		vWnd = new VBox();
		
		Stage mesaj = new Stage();
		Button buton = new Button("Ok");
		Text text = new Text(s);
		Scene scene ;
		
		vtext.getChildren().add(text);
		vbuton.getChildren().add(buton);
		vWnd.getChildren().addAll(vtext, vbuton);
		
		vtext.setAlignment(Pos.CENTER);
		vbuton.setAlignment(Pos.CENTER);
		vWnd.setAlignment(Pos.CENTER);
		
		scene = new Scene(vWnd, 300, 100);
		
		mesaj.setTitle("Eroare");
		mesaj.setScene(scene);
		
		mesaj.show();
		
		buton.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0) {
						mesaj.hide();
					}
				});
	}
	
	public void start(Stage stage) throws Exception{
		primaryStage = stage;
		
		textR.setMaxWidth(250);
		
		VBox root = new VBox(30);
		
		ToggleGroup grup = new ToggleGroup();
		
		RadioButton buton1 = new RadioButton("un polinom");
		RadioButton buton2 = new RadioButton("doua polinoame");
		
		Button butonOk= new Button("Inainte");
		
		buton1.setToggleGroup(grup);
		buton2.setToggleGroup(grup);
		
		Label lb_text1 = new Label("Doriti sa operati cu:");
		
		VBox vbox1 = new VBox(10);
		VBox vbox3 = new VBox(10);
		
		HBox hbox = new HBox(10);
		
		
		vbox1.getChildren().add(lb_text1);
		hbox.getChildren().addAll(buton1, buton2);
		vbox3.getChildren().add(butonOk);
		
		root.getChildren().addAll(vbox1, hbox, vbox3);
		//rootPane.getChildren().add(root);
		
		vbox1.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
		vbox3.setAlignment(Pos.CENTER);
		
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 400, 200);
		
		grup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle t, Toggle t1) {
				RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle();
				if(chk == buton1)
				{
					butonOk.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent arg0) {
							stage.close();
							try {
								fereastraOperatiiPolinom();
							} catch (Exception e) {
									e.printStackTrace();
							}
									
						}
					});
				}
				else if(chk == buton2)
				{
					butonOk.setOnAction(new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent arg0) {
									stage.close();
									try {
										fereastraOperatiiPolinoame();
									} catch (Exception e) {
											e.printStackTrace();
									}
											
								}
							});
				}
			}
			
		});
		
		stage.setTitle("Polinoame");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public void fereastraOperatiiPolinom() throws Exception

	{
		
		Stage stage = new Stage();
		
		HBox hbox1 = new HBox(40);
		HBox hbox2 = new HBox();
		HBox hbottom = new HBox(170);
		VBox leftBox = new VBox(15);
		VBox rightBox =  new VBox(15);

		
		Label label1 = new Label("Introduceti polinomul:");
		Label label2 = new Label();
		TextField field1 = new TextField();
		ListView<String> list = new ListView<String>();
		
		field1.setMaxWidth(250);
		textR.setEditable(false);
		field1.setTooltip(new Tooltip("Polinomul trebuie sa fie sub forma vectoriala. \n De exemplu pentru polinomul \n x^3+2*x^2-3*x^1+4 \n acesta este \n 1,2,-3,4."));
		
		ObservableList<String> items =FXCollections.observableArrayList (
			    "Afisare polinom", "Afisare derivata polinom", "Radacinile polinomului", "Valoarea polinomului intr-un punct", "Valoarea derivatei intr-un punct");
		list.setItems(items);
		
		hbottom.getChildren().addAll(inapoi, ok);
		
		leftBox.getChildren().addAll(label1, field1, list, hbottom);
		hbox1.getChildren().add(leftBox);
		hbox1.setPadding(new Insets(20));
		
		ok.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				hbox2.getChildren().removeAll(label, field);
				rightBox.getChildren().removeAll(hbox2, ok2);
				
				String sir = field1.getText();
				String listChoice = list.getSelectionModel().getSelectedItem();
				if(listChoice=="Afisare polinom")
				{
					try{
						rightBox.getChildren().removeAll();
						coef = getCoef(sir);
						Polinom p = new Polinom(coef.length-1, coef);
						label2.setText("Polinomul introdus este:");
						textR.setText(p.toString());
					}
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinomul este scris incorect!\n	Incercati din nou.\n");
					}
				}
				else if(listChoice == "Afisare derivata polinom")
				{
					try{
						rightBox.getChildren().removeAll();
						coef = getCoef(sir);
						Polinom p = new Polinom(coef.length-1, coef);
						label2.setText("Derivata polinomului introdus este:");
						textR.setText(p.calculeazaDerivata().toString());
					}
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinomul este scris incorect!\n	Incercati din nou.\n");
					}
				}
				else if(listChoice == "Valoarea polinomului intr-un punct")
				{
					try{
						rightBox.getChildren().removeAll();
						coef = getCoef(sir);
						label2.setText("Valoarea polinomului intr-un punct:");
						
						field = new TextField();
						field.setMaxWidth(50);
						field.setAlignment(Pos.BASELINE_RIGHT);
						label = new Label("Dati punctul in care vreti		\nsa calculati valoarea:	");
						label.setAlignment(Pos.BASELINE_LEFT);
						ok2 = new Button("Calculeaza");
						
						hbox2.getChildren().addAll(label, field);
						rightBox.getChildren().addAll(hbox2, ok2);
						
						ok2.setOnAction(new EventHandler<ActionEvent>()
								{
									@Override
									public void handle(ActionEvent arg0) {
										try{
											double x = Double.parseDouble(field.getText());
											pol = new Polinom(coef.length-1, coef);
											textR.setText(String.valueOf(pol.getFuncValue(x)));
										}
										catch(NumberFormatException e)
										{
											afiseazaMesajEroare("Valoarea introdusa nu este corecta!\nAceasta trebuie sa fie un numar intreg sau real");
										}
										
									}
								});
					}
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinomul este scris incorect!\n	Incercati din nou.\n");
					}
				}
				else if(listChoice == "Radacinile polinomului")
				{
					try{
						double rad[];
						rightBox.getChildren().removeAll();
						coef = getCoef(sir);
						label2.setText("Radacinile polinomului introdus sunt:");
						if(coef.length-1==0)
						{
							textR.setText("Polinomul nu are radacini reale");
						}
						else if(coef.length-1==1)
						{
							PolinomGrad1 p = new PolinomGrad1(coef[0], coef[1]);
							rad = p.solve();
							if(rad==null)
								textR.setText("Polinomul nu are radacini reale");
							else
								textR.setText(Arrays.toString(rad));
						}
						else if(coef.length-1==2)
						{
							PolinomGrad2 p = new PolinomGrad2(coef[0], coef[1], coef[2]);
							rad = p.solve();
							if(rad==null)
								textR.setText("Polinomul nu are radacini reale");
							else
								textR.setText(Arrays.toString(rad));
						}
						else if(coef.length-1==3)
						{
							PolinomGrad3 p = new PolinomGrad3(coef[0], coef[1], coef[2], coef[3]);
							rad = p.solve();
							if(rad==null)
								textR.setText("Polinomul nu are radacini reale");
							else
								textR.setText(Arrays.toString(rad));
						}
						else
						{
							Polinom p = new Polinom(coef.length-1, coef);
							field = new TextField();
							field.setMaxWidth(50);
							field.setAlignment(Pos.BASELINE_RIGHT);
							label = new Label("Introduceti un punct de start:");
							label.setAlignment(Pos.BASELINE_LEFT);
							ok2 = new Button("Calculeaza");
							
							hbox2.getChildren().addAll(label, field);
							rightBox.getChildren().addAll(hbox2, ok2);
							
							ok2.setOnAction(new EventHandler<ActionEvent>()
									{
										@Override
										public void handle(ActionEvent arg0) {
											try{
												double x = Double.parseDouble(field.getText());
												textR.setText(Arrays.toString((p.solve(x))));
											}
											catch(NumberFormatException e)
											{
												afiseazaMesajEroare("Valoarea introdusa nu este corecta!\nAceasta trebuie sa fie un numar intreg sau real");
											}
											catch(ArithmeticException e)
											{
												afiseazaMesajEroare("Nu se poate gasi radacina.\nIncercati alt punct de start");
											}
											
										}
									});
						}
					}
					
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinomul este scris incorect!\n	Incercati din nou.\n");
					}
					
				}
				else if(listChoice == "Valoarea derivatei intr-un punct")
				{
					try{
						rightBox.getChildren().removeAll();
						coef = getCoef(sir);
						label2.setText("Valoarea derivatei intr-un punct:");
						
						field = new TextField();
						field.setMaxWidth(50);
						field.setAlignment(Pos.BASELINE_RIGHT);
						label = new Label("Dati punctul in care vreti		\nsa calculati derivata:	");
						label.setAlignment(Pos.BASELINE_LEFT);
						ok2 = new Button("Calculeaza");
						
						hbox2.getChildren().addAll(label, field);
						rightBox.getChildren().addAll(hbox2, ok2);
						
						ok2.setOnAction(new EventHandler<ActionEvent>()
								{
									@Override
									public void handle(ActionEvent arg0) {
										try{
											double x = Double.parseDouble(field.getText());
											pol = new Polinom(coef.length-1, coef);
											textR.setText(String.valueOf(pol.getDerivValue(x)));
										}
										catch(NumberFormatException e)
										{
											afiseazaMesajEroare("Valoarea introdusa nu este corecta!\nAceasta trebuie sa fie un numar intreg sau real");
										}
										
									}
								});
					}
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinomul este scris incorect!\n	Incercati din nou.\n");
					}
				}
				
			}
			
		});
		
		rightBox.getChildren().addAll(label2, textR);
		hbox1.getChildren().add(rightBox);
		
		
		leftBox.setAlignment(Pos.TOP_CENTER);
		rightBox.setAlignment(Pos.TOP_CENTER);
		hbox1.setAlignment(Pos.CENTER);
		
		
		inapoi.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				try {
					stage.hide();
					start(primaryStage);
				} catch (Exception e) {
					afiseazaMesajEroare("Eroare!");
				}
				
			}
			
		});
		
		Scene scene = new Scene(hbox1, 600, 400);
		stage.setTitle("Operatii cu un polinom");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public void fereastraOperatiiPolinoame() throws Exception
	{
		Stage stage = new Stage();
		
		Button calcul = new Button("Calculeaza");
		
		HBox hbox = new HBox(40);
		VBox leftBox, rightBox;
		leftBox = new VBox(20);
		rightBox = new VBox(20);
		
		hbox.setPadding(new Insets(20));
		
		Label op = new Label("Alegeti operatia pe care vreti sa o efectuati:");
		Label pol = new Label("Introduceti polinoamele");
		
		TextField p1, p2;
		p1 = new TextField();
		p2 = new TextField();
		
		TextArea area = new TextArea();
		area.setEditable(false);
		
		ListView<String> list = new ListView<String>();
		
		ObservableList<String> items = FXCollections.observableArrayList("Adunare", "Scadere", "Inmultire", "Impartire");
		list.setItems(items);
		list.autosize();
		
		
		p1.setTooltip(new Tooltip("Polinomul trebuie sa fie sub forma vectoriala. \n De exemplu pentru polinomul \n x^3+2*x^2-3*x^1+4 \n acesta este \n 1,2,-3,4."));
		p2.setTooltip(new Tooltip("Polinomul trebuie sa fie sub forma vectoriala. \n De exemplu pentru polinomul \n x^3+2*x^2-3*x^1+4 \n acesta este \n 1,2,-3,4."));
		
			
			calcul.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					listChoice = list.getSelectionModel().getSelectedItem();
					
					sir1 = p1.getText();
					sir2 = p2.getText();
					
					try{
						coef1 = getCoef(sir1);
						coef2 = getCoef(sir2);
					
					
						pol1 = new Polinom(coef1.length-1, coef1);
						pol2 = new Polinom(coef2.length-1, coef2);
						
						if(listChoice == "Adunare")
						{
							polR = pol1.add(pol2);
							if(polR == null)
								area.setText("0");
							else
								area.setText(polR.toString());
						}
						else if(listChoice == "Scadere")
						{
								polR = pol1.substract(pol2);
								if(polR == null)
									area.setText("0");
								else
									area.setText(polR.toString());
						}
						else if(listChoice == "Inmultire")
						{
							polR = pol1.multiply(pol2);
							area.setText(polR.toString());
						}
						else if(listChoice == "Impartire")
						{
							String rez[] = pol1.divide(pol2);
							area.setText(rez[0]+"\n"+rez[1]);
						}
					}
					catch(ArithmeticException e)
					{
						afiseazaMesajEroare("	Gradul primului polinom trebuie\n sa fie mai mare sau egal fata de gradul\n	  celui de al doilea polinom.");
					}
					catch(Exception e)
					{
						afiseazaMesajEroare("Polinoamele sunt scrise incorect!\n	Incercati din nou.\n");
					}
				}
				
			});
			
			leftBox.getChildren().addAll(op, list, inapoi);
			rightBox.getChildren().addAll(pol, p1, p2, calcul, area);
			hbox.getChildren().addAll(leftBox, rightBox);
			
			leftBox.setAlignment(Pos.CENTER);
			rightBox.setAlignment(Pos.TOP_CENTER);
			hbox.setAlignment(Pos.CENTER);
			
			rightBox.setMaxWidth(300);
			
			inapoi.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					try {
						stage.hide();
						start(primaryStage);
					} catch (Exception e) {
						afiseazaMesajEroare("Eroare!");
					}
					
				}
				
			});
			
			Scene scene = new Scene(hbox, 600, 400);
			stage.setScene(scene);
			stage.setTitle("Operatii cu doua polinoame");
			stage.setResizable(false);
			stage.show();
			
		
	}
	public int[] getCoef(String sir)
	{
		int[] coef = null;
		int[] aux = new int[sir.length()];
		
		int i=0;
		
		StringTokenizer str = new StringTokenizer(sir, ",");
		while(str.hasMoreTokens() == true)
		{
			aux[i++] = Integer.parseInt(str.nextToken());
		}
		
		coef = new int[i];
		coef = Arrays.copyOf(aux, i);
		
		return coef;
	}
}
