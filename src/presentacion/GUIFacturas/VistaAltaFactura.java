package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import negocio.TDatosFactura;
import negocio.TLineaFactura;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class VistaAltaFactura extends JDialog implements IGUI{
	
	static List<TLineaFactura> _carrito;
	
	
	public VistaAltaFactura(){
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Alta Factura");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2,1,5,5));
		setContentPane(mainPanel);
	
		//Añadir LineaFactura
		JButton anyadirLinea = new JButton();
		anyadirLinea.setText("Añadir Servicios");
		anyadirLinea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IGUI j =  FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ANNADIR_LINEA_FACTURA);				
				j.actualizar(Eventos.ANNADIR_LINEA_FACTURA, _carrito);
			}
		});
		mainPanel.add(anyadirLinea);
		
		//CerrarFactura
		JButton cerrarFactura = new JButton();
		cerrarFactura.setText("Cerrar Factura");
		cerrarFactura.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				IGUI j =  FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.CERRAR_FACTURA);				
				j.actualizar(Eventos.CERRAR_FACTURA, null);
			}
		});
		mainPanel.add(cerrarFactura);
		setPreferredSize(new Dimension(300,200));
		pack();
		setVisible(false);		
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.ALTA_FACTURA:
			_carrito = new ArrayList<TLineaFactura>();
			setVisible(true);
			break;
		case Eventos.ANNADIR_LINEA_FACTURA:
			String servicio = (String) datos;
			_carrito.add(new TLineaFactura(servicio));
			break;
		case Eventos.CERRAR_FACTURA:
			TDatosFactura tf = (TDatosFactura) datos;
			tf.setCarrito(_carrito);
			try {
				Controlador.getInstancia().accion(Eventos.CERRAR_FACTURA, tf);
			} catch (FileNotFoundException | JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
	
	

}
