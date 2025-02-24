package presentacion.GUIEmpleados;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import negocio.TEmpleados;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaBajaEmpleado extends JDialog implements IGUI {
	private static final String[] cargos= {"Profesor", "Secretaria", "PersonalLimpieza"};
	private JPanel mainPanel;
	private JLabel Ldni;
	private JTextField DNI;
	private JLabel Lcargo;
	private JComboBox<String> Cargo;
	
	public VistaBajaEmpleado() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Baja Empleado");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		JPanel p1 = new JPanel();
		Ldni = new JLabel("DNI: ");
		DNI = new JTextField(20);
		mainPanel.add(Ldni);
		p1.add(DNI);
		mainPanel.add(p1);
		
		JPanel p2 = new JPanel();
		Lcargo = new JLabel("Cargo: ");
		Cargo = new JComboBox<String>(cargos);
		Cargo.setMaximumSize( Cargo.getPreferredSize() );
		mainPanel.add(Lcargo);
		p2.add(Cargo);
		mainPanel.add(p2);	
		
		JPanel p3 = new JPanel();
		JButton Cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		p3.add(Cancel);
		p3.add(OK);
		mainPanel.add(p3);
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.getInstancia().accion(Eventos.BAJA_EMPLEADOS,
							new TEmpleados(DNI.getText(), Cargo.getSelectedItem().toString()));
				} catch (FileNotFoundException | JSONException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		
		Cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		
		pack();
	}


	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.BAJA_EMPLEADOS:{
			setVisible(true);
			break;
		}
		case Eventos.RES_BAJA_EMPLEADOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El empleado se ha eliminado correctamente de la base de datos","Servicio Eliminado",JOptionPane.DEFAULT_OPTION);
			}else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el empleado o no se ha podido eliminar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
		
	}

}
