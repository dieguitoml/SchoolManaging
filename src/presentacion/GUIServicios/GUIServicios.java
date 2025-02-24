package presentacion.GUIServicios;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

@SuppressWarnings("serial")
public class GUIServicios extends JDialog {
	JButton Listar;
	JButton Alta;
	JButton Baja;
	JButton Buscar;
	JButton Modificar;
	
	public GUIServicios(Frame parent) {
		super(parent,true);
		IGUI();
	}
	
	private void IGUI() {
		setTitle("Subsistema Servicios");
		setModal(true);
		JPanel mainPanel = new JPanel(new GridLayout(5,1,3,3));
		setContentPane(mainPanel);
		Listar=new JButton("Lista Servicios");
		Alta = new JButton("Alta Servicio");
		Baja = new JButton("Baja Servicio");
		Buscar = new JButton("Buscar Servicio");
		Modificar= new JButton("Modificar Servicio");
		mainPanel.add(Alta);
		mainPanel.add(Baja);
		mainPanel.add(Buscar);
		mainPanel.add(Modificar);
		mainPanel.add(Listar);
		Alta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j= FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_SERVICIO);
				j.actualizar(Eventos.ALTA_SERVICIO, null);
			}
		});
		
		Baja.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			IGUI j=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BAJA_SERVICIO);
			j.actualizar(Eventos.BAJA_SERVICIO, null);
		}
		
		});
		
		Buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IGUI j=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_SERVICIO);
				j.actualizar(Eventos.BUSCAR_SERVICIO, null);
			}
			
		});
		
		Listar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_SERVICIO);
				j.actualizar(Eventos.LISTAR_SERVICIO, null);
			}
			
		});
		
		Modificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j=FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MODIFICAR_SERVICIO);
				j.actualizar(Eventos.MODIFICAR_SERVICIO, null);
			}
			
		});
		
		
		setPreferredSize(new Dimension(600, 400));
		pack();
		setVisible(true);
	}

	
	
}
