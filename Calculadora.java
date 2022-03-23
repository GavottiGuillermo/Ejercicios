import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora {

	public static void main(String[] args) {

		MarcoCalculadora calculadora = new MarcoCalculadora();

		calculadora.setVisible(true);

		calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoCalculadora extends JFrame {

	public MarcoCalculadora() {

		setTitle ("Calculadora");

		setBounds (900,300,400,350);

		add(new PanelCalculadora());
		//pack();
	}
}

class PanelCalculadora extends JPanel {

	public PanelCalculadora () {

		principio=true;

		setLayout(new BorderLayout());

		pantalla= new JButton("0");

		pantalla.setEnabled(false); //desactiva el boton de la pantalla

		pantalla.setForeground(Color.WHITE);

		pantalla.setBackground(Color.BLACK);

		add(pantalla,BorderLayout.NORTH);

		milamina2=new JPanel();

		milamina2.setLayout(new GridLayout(4,4));

		ActionListener oyenteNumero=new escucharNumeros();

		ActionListener oyenteOperaciones=new AccionOperaciones();

		add(milamina2, BorderLayout.CENTER);

		ponerBoton("7",oyenteNumero); ponerBoton("8",oyenteNumero); ponerBoton("9",oyenteNumero); ponerBoton("/",oyenteOperaciones);

		ponerBoton("4",oyenteNumero); ponerBoton("5",oyenteNumero); ponerBoton("6",oyenteNumero); ponerBoton("*",oyenteOperaciones);

		ponerBoton("1",oyenteNumero); ponerBoton("2",oyenteNumero); ponerBoton("3",oyenteNumero); ponerBoton("-",oyenteOperaciones);

		ponerBoton("0",oyenteNumero); ponerBoton(".",oyenteNumero); ponerBoton("+",oyenteOperaciones); ponerBoton("=",oyenteOperaciones);

		ultimaOperacion="=";
	}


	private void ponerBoton (String rotulo, ActionListener oyente) {

		JButton boton=new JButton(rotulo);

		milamina2.add(boton);

		boton.addActionListener(oyente);
	}


	private class escucharNumeros implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String entrada=e.getActionCommand();

			pantalla.setText(pantalla.getText() + entrada);

			if (principio) {

				pantalla.setText(entrada);

				principio=false;
			}
		}
	}


	private class  AccionOperaciones implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String operacion= e.getActionCommand();

			calcular(Double.parseDouble(pantalla.getText())); // convierto a double lo que hay en pantalla y lo paso por parámetro

			ultimaOperacion=operacion;

			principio=true;
			}
		}


		private void calcular (double x) {

		if (ultimaOperacion.equals("+")){

			resultado+=x;
		}
		else if(ultimaOperacion.equals("-")) {

			resultado-=x;
		}
		else if(ultimaOperacion.equals("*")) {

			resultado*=x;
		}
		else if(ultimaOperacion.equals("/")) {

			resultado/=x;



		}else if (ultimaOperacion.equals("=")) {

			resultado=x;

		}
		pantalla.setText("" + resultado); //manera de convertir a string

	}

	private JPanel milamina2;
	private JButton pantalla;
	private boolean principio;
	private double resultado;
	private String ultimaOperacion;
}
