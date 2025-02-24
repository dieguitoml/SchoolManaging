package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.TDatosFactura;
import negocio.TFactura;
import negocio.TLineaFactura;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaMostrarFactura extends JDialog implements IGUI{

	private JLabel _id;
	private JLabel _alumno;
	private JLabel _secretaria;
	private JLabel _precio;
	private JLabel _fecha;
	//Servicios
	private String[] _headers = {"Servicios"};
	static private DefaultTableModel _dataTableModel;
	
	public VistaMostrarFactura(){
		super();
		IGUI();
	}
	
	private void IGUI() {
		setTitle("Mostrar Facturas");
		JPanel _mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel,BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		setModal(true);
		_id = new JLabel();
		_mainPanel.add(_id);
		_alumno = new JLabel();
		_mainPanel.add(_alumno);
		_secretaria = new JLabel();
		_mainPanel.add(_secretaria);
		_precio = new JLabel();
		_mainPanel.add(_precio);
		_fecha = new JLabel();
		_mainPanel.add(_fecha);
		//Servicios
		_dataTableModel = new DefaultTableModel(_headers,0);
		JTable tabla = new JTable(_dataTableModel);
		tabla.getColumnModel().getColumn(0).setMinWidth(300);
		tabla.getColumnModel().getColumn(0).setMaxWidth(600);
		JScrollPane area = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		area.setPreferredSize(new Dimension(600,300));
		_mainPanel.add(area);
		
		setPreferredSize(new Dimension(300, 300));
		pack();
		setVisible(false);		
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.MOSTRAR_FACTURA:
			TDatosFactura datosFactura = (TDatosFactura) datos;
			TFactura factura = datosFactura.getFactura();
			_id.setText("ID Factura: "+ factura.getID());
			_alumno.setText("Alumno: "+ datosFactura.getAlumno() + " (DNI:" + factura.getIdAlumno()+")");
			_secretaria.setText("Secretaria: "+ datosFactura.getSecretaria() + " (DNI:" + factura.getIdSecretaria()+")");
			_precio.setText("Precio Total: " + factura.getPrecio());
			_fecha.setText("Fecha: "+ factura.getFecha().toString());
			for(TLineaFactura tf : datosFactura.getCarrito()) {
				Object[] aux = {tf.getServiceId()};
				_dataTableModel.addRow(aux);
				}
				_dataTableModel.fireTableDataChanged();	
			setVisible(true);
			break;
		}
		
	}
 
}
