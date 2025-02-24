package presentacion.GUIDepartamentos;

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

public class GUIDepartamentos extends JDialog {
	JButton _listar;
	JButton _buscar;
	JButton _modificar;
	JButton _alta;
	JButton _baja;
	
	public GUIDepartamentos(Frame parent){
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Subsistema Departamentos");
		setModal(true);
		JPanel mainPanel = new JPanel(new GridLayout(4,1,3,3));
		setContentPane(mainPanel);
		
		_alta = new JButton("Alta Departamento");
		_baja = new JButton("Baja Departamento");
		_listar =new JButton("Lista Departamento");
		_buscar = new JButton("Buscar Departamento");		
		
		_alta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_DEPARTAMENTO);
				frame.actualizar(Eventos.ALTA_DEPARTAMENTO, null);

				
			}
		});
		_baja.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_DEPARTAMENTO);
				frame.actualizar(Eventos.BAJA_DEPARTAMENTO, null);

			}
		});
		
		_listar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_DEPARTAMENTO);
				frame.actualizar(Eventos.LISTAR_DEPARTAMENTO, null);

			}
		});
		
		_buscar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI frame=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_DEPARTAMENTO);
				frame.actualizar(Eventos.BUSCAR_DEPARTAMENTO, null);

			}
		});
		

		
		
		mainPanel.add(_alta);
		mainPanel.add(_baja);
		mainPanel.add(_listar);
		mainPanel.add(_buscar);
		
		setPreferredSize(new Dimension(700, 400));
		pack();
		setVisible(true);
		
	}
	
	
	
	
	
}
