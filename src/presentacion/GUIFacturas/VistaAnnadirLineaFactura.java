package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.TLineaFactura;
import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class VistaAnnadirLineaFactura  extends JDialog implements IGUI{

	JTextField _idProducto;
	private String[] _headers = {"Servicios"};
	static private DefaultTableModel _dataTableModel;
		
	public VistaAnnadirLineaFactura() {
		super();
		IGUI();
	}
	private void IGUI() {
		setTitle("Añadir Servicio en linea Factura");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		//ID
		JPanel id = new JPanel();
		id.setLayout(new BoxLayout(id,BoxLayout.X_AXIS));
		JLabel palabras = new JLabel("ID Servicio:");
		id.add(palabras);
		_idProducto =  new JTextField();
		_idProducto.setPreferredSize(new Dimension(150,20));
		id.add(_idProducto);
		mainPanel.add(id);
		
		//Botones
		JPanel abajo = new JPanel();
		abajo.setLayout((new BoxLayout(abajo,BoxLayout.X_AXIS)));
		
		JButton anyadirLinea = new JButton();
		anyadirLinea.setText("Añadir");
		anyadirLinea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_FACTURA).actualizar(Eventos.ANNADIR_LINEA_FACTURA, _idProducto.getText());
				setVisible(false);
			}
		});
		abajo.add(anyadirLinea);
		//Cerrar
		JButton cerrar = new JButton();
		cerrar.setText("Cancelar");
		cerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
			}
		});
		abajo.add(cerrar);
		mainPanel.add(abajo);
		//Servicios
		_dataTableModel = new DefaultTableModel(_headers,0);
		JTable tabla = new JTable(_dataTableModel);
		tabla.getColumnModel().getColumn(0).setMinWidth(300);
		tabla.getColumnModel().getColumn(0).setMaxWidth(600);
		JScrollPane area = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		area.setPreferredSize(new Dimension(600,300));
		mainPanel.add(area);
		
		setPreferredSize(new Dimension(300, 300));
		pack();
		
	}
	@Override
	public void actualizar(int evento, Object datos) {
		if(evento == Eventos.ANNADIR_LINEA_FACTURA) {
			for(TLineaFactura tf : (List<TLineaFactura>) datos) {
			Object[] aux = {tf.getServiceId()};
			_dataTableModel.addRow(aux);
			}
			_dataTableModel.fireTableDataChanged();	
			setVisible(true);
		}
	}
	}


