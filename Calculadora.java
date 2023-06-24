package Calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora {

	public static void main(String[] args) {
		MarcoCalculadora marco = new MarcoCalculadora();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);
	}
}

class MarcoCalculadora extends JFrame{
	
	public MarcoCalculadora() {
		setTitle("calculadora");
		setBounds(500,300,450,300);
		CapaCalculadora capa = new CapaCalculadora();
		add(capa);
		//pack(); // tamaño adaptable 
	}
}

 class CapaCalculadora extends JPanel{
	 private Boolean clear;
	 private JTextField pantalla; 
	 private JPanel capa1;
	 private JTextField notas;
	 private float resultado; 
	 private String operacionPulsada;
	 
	 public CapaCalculadora() {
		 operacionPulsada = "=";
		 clear = true;
		 setLayout(new BorderLayout());
		 pantalla = new JTextField("0");
		 Font fuente = new Font("Calibri", 3, 40);
		 pantalla.setFont(fuente);
		 pantalla.setForeground(Color.WHITE);
		 pantalla.setHorizontalAlignment(JTextField.CENTER);
		 pantalla.setBackground(Color.darkGray);
		 pantalla.setEnabled(true);
		 add(pantalla, BorderLayout.NORTH);
		 capa1 = new JPanel();
		 capa1.setLayout(new GridLayout(4,6));
		 add(capa1, BorderLayout.CENTER);
		 notas = new JTextField("Notas: ");
		 add(notas, BorderLayout.SOUTH);
		 
		 ActionListener insert = new InsertNumero();
		 ActionListener orden = new AccionOrden();
		 CrearBoton("7", insert);
		 CrearBoton("8", insert);
		 CrearBoton("9", insert);
		 CrearBoton("/", orden);
		 CrearBoton("4", insert);
		 CrearBoton("5", insert);
		 CrearBoton("6", insert);
		 CrearBoton("*", orden);
		 CrearBoton("1", insert);
		 CrearBoton("2", insert);
		 CrearBoton("3", insert);
		 CrearBoton("-", orden);
		 CrearBoton("0", insert);
		 CrearBoton(".", insert);
		 CrearBoton("=", orden);
		 CrearBoton("+", orden);
		 
	 }
	 
	 private void CrearBoton (String rotulo , ActionListener oyente){
		 
		 JButton boton = new JButton(rotulo);
		 boton.addActionListener(oyente);
		 boton.setBackground(Color.DARK_GRAY);
		 Font fuenteNum = new Font("Calibri", 3, 40);
		 boton.setFont(fuenteNum);
		 boton.setForeground(Color.white);
		 
		 capa1.add(boton);
		 
	 }
	 
	 private class InsertNumero implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String TxtEntrada = e.getActionCommand();
			if(clear) {
				pantalla.setText("");
				clear= false;
			}
			pantalla.setText(pantalla.getText()+ TxtEntrada);
		}
		 
	 }
	 private class AccionOrden implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String operacion = e.getActionCommand();
			calcular(Double.parseDouble(pantalla.getText()));
			operacionPulsada = operacion;
			//System.out.print(operacion);
			clear = true;
			
		}
		
		public void calcular (double x) {
			
			if(operacionPulsada.equals("+")) {
				resultado +=x;
			}
			
			else if (operacionPulsada.equals("*")) {
				resultado *=x;
			}
			
			else if(operacionPulsada.equals("-")) {
				resultado -=x;
			}
			
			else if(operacionPulsada.equals("/")) {
				resultado /=x;
				
			}
			else if (operacionPulsada.equals("=")) {
				resultado = (float) x;
				
			}
			
			pantalla.setText(""+resultado);
		}
		 
	 }
 }
