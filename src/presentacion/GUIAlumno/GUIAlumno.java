package presentacion.GUIAlumno;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class GUIAlumno extends JDialog {
	JButton Listar;
	JButton Buscar;
	JButton Modificar;
	JButton Alta;
	JButton Baja;
	
	public GUIAlumno(Frame parent){
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Alumnos");
		setModal(true);
		JPanel mainPanel = new JPanel(new GridLayout(5,1,3,3));
		setContentPane(mainPanel);
		Alta = new JButton("Alta Alumno");
		Baja = new JButton("Baja Alumno");
		Listar=new JButton("Lista Alumno");
		Buscar = new JButton("Buscar Alumno");
		Modificar= new JButton("Modificar Alumno");
		
		mainPanel.add(Alta);
		mainPanel.add(Baja);
		mainPanel.add(Listar);
		mainPanel.add(Buscar);
		mainPanel.add(Modificar);
		
		Alta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_ALUMNOS);
				frame.actualizar(Eventos.ALTA_ALUMNOS, null);
			}
		});
		
		Baja.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_ALUMNOS);
				frame.actualizar(Eventos.BAJA_ALUMNOS, null);
			}
		});
		
		Listar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_ALUMNOS);
				frame.actualizar(Eventos.LISTAR_ALUMNOS, null);
			}
		});
		
		Buscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_ALUMNOS);
				frame.actualizar(Eventos.BUSCAR_ALUMNOS, null);
			}
		});
		
		Modificar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_ALUMNOS);
				frame.actualizar(Eventos.MODIFICAR_ALUMNOS, null);
			}
		});
		
		setPreferredSize(new Dimension(700, 400));
		pack();
		setVisible(true);
		
	}
}