package presentacion.GUIFacturas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import negocio.Fecha;
import negocio.TDatosFactura;
import presentacion.Eventos;
import presentacion.FactoriaAbstractaPresentacion;
import presentacion.IGUI;

public class VistaCerrarFactura extends JDialog implements IGUI{

	JTextField _idAlumno;
	JTextField _idSecretaria;
	JSpinner _dia;
	JSpinner _mes;
	JSpinner _anno;
	
	public VistaCerrarFactura() {
		super();
		IGUI();
	}
	
	private void IGUI() {
		setTitle("Cerrar Factura");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//IDAlumno
		JPanel idAlumno = new JPanel();
		idAlumno.setLayout(new BoxLayout(idAlumno,BoxLayout.X_AXIS));
		JLabel palabrasAlumno = new JLabel("DNI Alumno:");
		idAlumno.add(palabrasAlumno);
		_idAlumno =  new JTextField();
		_idAlumno.setPreferredSize(new Dimension(150,20));
		idAlumno.add(_idAlumno);
		mainPanel.add(idAlumno);
		
		//IDSecretaria
		JPanel idSecretaria = new JPanel();
		idSecretaria.setLayout(new BoxLayout(idSecretaria,BoxLayout.X_AXIS));
		JLabel palabrasSecretaria = new JLabel("DNI Secretaria:");
		idSecretaria.add(palabrasSecretaria);
		_idSecretaria =  new JTextField();
		_idSecretaria.setPreferredSize(new Dimension(150,20));
		idSecretaria.add(_idSecretaria);
		mainPanel.add(idSecretaria);
		
		//Fecha
		JPanel fecha = new JPanel();
		fecha.setLayout(new BoxLayout(fecha,BoxLayout.X_AXIS));
		JLabel palabrasFecha= new JLabel("Fecha:");
		fecha.add(palabrasFecha);
		_dia =  new JSpinner(new SpinnerNumberModel(2,1,31,1));
		fecha.add(_dia);
		fecha.add(new JLabel("/"));
		_mes =  new JSpinner(new SpinnerNumberModel(5,1,12,1));
		fecha.add(_mes);
		fecha.add(new JLabel("/"));
		_anno =  new JSpinner(new SpinnerNumberModel(2023,2003,2103,1));
		fecha.add(_anno);
		mainPanel.add(fecha);
		
		//Botones
		JPanel abajo = new JPanel();
		abajo.setLayout((new BoxLayout(abajo,BoxLayout.X_AXIS)));
		
		JButton terminar = new JButton();
		terminar.setText("Terminar");
		terminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TDatosFactura df = new TDatosFactura(_idAlumno.getText(), _idSecretaria.getText(), new Fecha((int)_dia.getValue(),(int)_mes.getValue(),(int)_anno.getValue()));
				FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.ALTA_FACTURA).actualizar(Eventos.CERRAR_FACTURA, df);
				setVisible(false);
			}
		});
		abajo.add(terminar);
		//Cancelar
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
		
		setPreferredSize(new Dimension(300,130));
		pack();
		
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.CERRAR_FACTURA: 
			setVisible(true);
			break;
		case Eventos.ALTA_FACTURA_OK:
			int exito = (int) datos;
			switch (exito) {
			case 0:
				JOptionPane.showMessageDialog(this, "La factura se ha añadido correctamente");
				break;
			case 1:
				JOptionPane.showMessageDialog(this, "Algunos de los servicios seleccionados no estan disponibles y no se han añadido a la factura","Cuidado",JOptionPane.WARNING_MESSAGE);
				break;
			case 2:
				JOptionPane.showMessageDialog(this, "Los datos introducidos son incorrectos y no se ha podido crear la factura","Error",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}

}
