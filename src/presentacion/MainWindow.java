package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import presentacion.GUIAlumno.GUIAlumno;
import presentacion.GUIDepartamentos.GUIDepartamentos;
import presentacion.GUIEmpleados.GUIEmpleados;
import presentacion.GUIFacturas.GUIFactura;
import presentacion.GUIServicios.GUIServicios;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	private JButton Servicios;
	private JButton Alumnos;
	private JButton Empleados;
	private JButton Departamentos;
	private JButton Facturas;
	private JButton Aulas;
	
	public MainWindow() {
		super("Gestion de colegio");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new GridLayout(3,2,5,5));
		setContentPane(mainPanel);
		Servicios=new JButton("Servicios");
		Alumnos =new JButton("Alumnos");
		Empleados=new JButton("Empleados");
		Departamentos=new JButton("Departamentos");
		Facturas =new JButton("Facturas");
		Aulas=new JButton("Aulas");
		mainPanel.add(Servicios);
		mainPanel.add(Alumnos);
		mainPanel.add(Empleados);
		mainPanel.add(Departamentos);
		mainPanel.add(Facturas);
		mainPanel.add(Aulas);
		
		Servicios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIServicios services=new GUIServicios(MainWindow.this);
			}
		});
		Facturas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIFactura facturas = new GUIFactura(MainWindow.this);
			}
		});
		
		Empleados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEmpleados empleados = new GUIEmpleados(MainWindow.this);
			}
		});
		
		Departamentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIDepartamentos departamento = new GUIDepartamentos(MainWindow.this);
			}
		});
		
		Alumnos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIAlumno alumnos = new GUIAlumno(MainWindow.this);
			}
		});
		
		addWindowListener( new WindowListener(){
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showOptionDialog(MainWindow.this, "¿Seguro que desea salir?", "Abandonar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (n == 0) {
					System.exit(0);
			}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				 
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		);
		
		
		
		setPreferredSize(new Dimension(500,500));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
