package presentacion.GUIFacturas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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

import org.json.JSONException;

import negocio.Fecha;
import negocio.TDatosFactura;
import negocio.TFactura;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class VistaListarFacturas extends JDialog implements IGUI{

	private JPanel _mainPanel;
	private DefaultTableModel _dataTableModel;
	private String[] _headers = {"ID","DNI Alumno","DNi Secretaria","Precio","dd/mm/aaaa"};
	private JTextField _view;
	
	public VistaListarFacturas(){
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Lista de Facturas");
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel,BoxLayout.Y_AXIS));
		setContentPane(_mainPanel);
		setModal(true);
		//Tabla
		_dataTableModel = new DefaultTableModel(_headers,0);
		JTable tabla = new JTable(_dataTableModel);
		tabla.getColumnModel().getColumn(0).setMinWidth(100);//ID
		tabla.getColumnModel().getColumn(1).setMinWidth(100);//ID Alumno
		tabla.getColumnModel().getColumn(2).setMinWidth(100);//Id Secretaria
		tabla.getColumnModel().getColumn(3).setMinWidth(100);//Precio
		tabla.getColumnModel().getColumn(4).setMinWidth(100);//Fecha
		tabla.getColumnModel().getColumn(0).setMaxWidth(100);
		tabla.getColumnModel().getColumn(1).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(100);
		tabla.getColumnModel().getColumn(4).setMaxWidth(100);
		JScrollPane area = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		area.setPreferredSize(new Dimension(700,400));
		_mainPanel.add(area);
		//Viewer
		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer,BoxLayout.X_AXIS));
		JLabel texto = new JLabel("ID Factura");
		viewer.add(texto);
		_view = new JTextField();
		_view.setPreferredSize(new Dimension(150,20));
		_view.setMaximumSize(new Dimension(150,20));
		_view.setMinimumSize(new Dimension(150,20));
		viewer.add(_view);
		JButton mostrar = new JButton("Ver Factura");
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Controlador.getInstancia().accion(Eventos.BUSCAR_FACTURA, _view.getText());
				} catch (FileNotFoundException | JSONException e1) {
					e1.printStackTrace();
				}
			}
		});
		viewer.add(mostrar);
		_mainPanel.add(viewer);
		
		setPreferredSize(new Dimension(510,250));
		pack();
		setVisible(false);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		//datos-> lista de facturas
		switch(evento) {
		case Eventos.LISTAR_FACTURA: 
			try {
				Controlador.getInstancia().accion(Eventos.LISTAR_FACTURA, null);
			} catch (FileNotFoundException | JSONException e1) {
				e1.printStackTrace();
			}
			break;
		case Eventos.LISTAR_FACTURA_OK:
			for(Object[] tf : (List<Object[]>)datos) {
			_dataTableModel.addRow(tf);
		}
		_dataTableModel.fireTableDataChanged();		
		setVisible(true);
		break;
		}
		
	}

}
