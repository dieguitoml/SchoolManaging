package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONException;

import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class GUIFactura extends JDialog{

	private JButton _crear;
	private JButton _listar;
	private JButton _buscar;
	
	public GUIFactura(Frame parent){
		super(parent,true);
		IGUI();
	}
	
	private void IGUI() {
		setTitle("Subsistema Facturas");
		setModal(true);
		JPanel mainPanel = new JPanel(new GridLayout(3,1,5,5));
		setContentPane(mainPanel);
		_crear = new JButton("Alta Factura");
		_listar = new JButton("Lista Facturas");
		_buscar = new JButton("Buscar Factura");
		_crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j =  FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_FACTURA);
				j.actualizar(Eventos.ALTA_FACTURA, null);
			}
		});
		_listar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j =  FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.LISTAR_FACTURA);
				j.actualizar(Eventos.LISTAR_FACTURA, null);
			}
		});
		_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j =  FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.BUSCAR_FACTURA);
				j.actualizar(Eventos.BUSCAR_FACTURA, null);
			}
		});
		mainPanel.add(_crear);
		mainPanel.add(_listar);
		mainPanel.add(_buscar);
		

		setPreferredSize(new Dimension(500, 240));
		pack();
		setVisible(true);
	}
	
}
