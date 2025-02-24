package presentacion.GUIEmpleados;

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

public class GUIEmpleados extends JDialog {
	JButton Listar;
	JButton Buscar;
	JButton Modificar;
	JButton Alta;
	JButton Baja;
	
	public GUIEmpleados(Frame parent){
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Empleados");
		setModal(true);
		JPanel mainPanel = new JPanel(new GridLayout(5,1,3,3));
		setContentPane(mainPanel);
		Alta = new JButton("Alta Empleado");
		Baja = new JButton("Baja Empleado");
		Listar=new JButton("Lista Empleados");
		Buscar = new JButton("Buscar Empleado");
		Modificar= new JButton("Modificar Empleado");
		
		mainPanel.add(Alta);
		mainPanel.add(Baja);
		mainPanel.add(Listar);
		mainPanel.add(Buscar);
		mainPanel.add(Modificar);
		
		Alta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_EMPLEADOS);
				frame.actualizar(Eventos.ALTA_EMPLEADOS, null);
				
			}
		});
		
		Baja.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_EMPLEADOS);
				frame.actualizar(Eventos.BAJA_EMPLEADOS, null);
			}
		});
		
		Listar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_EMPLEADOS);
				frame.actualizar(Eventos.LISTAR_EMPLEADOS, null);
			}
		});
		
		Buscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_EMPLEADOS);
				frame.actualizar(Eventos.BUSCAR_EMPLEADOS, null);
			}
		});
		
		Modificar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_EMPLEADOS);
				frame.actualizar(Eventos.MODIFICAR_EMPLEADOS, null);
				
			}
		});
		
		setPreferredSize(new Dimension(700, 400));
		pack();
		setVisible(true);
		
	}
}
