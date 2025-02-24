package presentacion.GUIEmpleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import negocio.TEmpleados;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaModificarEmpleado extends JDialog implements IGUI {
	private static final String[] cargos= {"Profesor", "Secretaria", "PersonalLimpieza"};
	private JPanel mainPanel;
	private JLabel Ldni;
	private JTextField DNI;
	private JLabel Lnombre;
	private JTextField Nombre;
	private JLabel Lapellidos;
	private JTextField Apellidos;
	private JLabel Lcargo;
	private JComboBox<String> Cargo;
	
	public VistaModificarEmpleado() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Modificar Empleado");
		setModal(true);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel p1=new JPanel();
		JLabel l = new JLabel("Inserte los datos del empleado que desesa modificar: ");
		p1.add(l);
		mainPanel.add(p1);
		
		Ldni = new JLabel("DNI: ");
		JPanel p2 = new JPanel();
		DNI = new JTextField(20);
		mainPanel.add(Ldni);
		p2.add(DNI);
		mainPanel.add(p2);
		
		
		JPanel p3 = new JPanel();
		Lcargo = new JLabel("Cargo: ");
		Cargo = new JComboBox<String>(cargos);
		Cargo.setMaximumSize( Cargo.getPreferredSize() );
		mainPanel.add(Lcargo);
		p3.add(Cargo);
		mainPanel.add(p3);	
		
		JPanel p4 = new JPanel();
		JLabel aModificar = new JLabel("Datos a modificar:");
		p4.add(aModificar);
		mainPanel.add(p4);
		
		JPanel p5 = new JPanel();
		Lnombre = new JLabel("Nombre: ");
		Nombre = new JTextField(20);
		mainPanel.add(Lnombre);
		p5.add(Nombre);
		mainPanel.add(p5);
		
		JPanel p6 = new JPanel();
		Lapellidos = new JLabel("Apellidos: ");
		Apellidos = new JTextField(20);
		mainPanel.add(Lapellidos);
		p6.add(Apellidos);
		mainPanel.add(p6);
		
		
		
		JPanel p7 = new JPanel();
		JButton Cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		p7.add(Cancel);
		p7.add(OK);
		mainPanel.add(p7);
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.getInstancia().accion(Eventos.MODIFICAR_EMPLEADOS,
							new TEmpleados(DNI.getText(),Nombre.getText(), Apellidos.getText(), Cargo.getSelectedItem().toString()));
				} catch (FileNotFoundException | JSONException e1) {
					e1.printStackTrace();
				}
			setVisible(false);
			}
		});
		
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
			}
		});
		
		pack();
	}


	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.MODIFICAR_EMPLEADOS:{
			setVisible(true);
			break;
		}
		case Eventos.RES_MODIFICAR_EMPLEADOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El empleado se ha modificado correctamente");
			}else {
				JOptionPane.showMessageDialog(this, "El empleado no existe o no se ha podido modificar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
		
	}

}
