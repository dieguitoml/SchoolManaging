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
import negocio.TProfesor;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaAltaEmpleado extends JDialog implements IGUI{
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
	private JLabel Ldepartamento;
	private JTextField Departamento;

	public VistaAltaEmpleado() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Alta Empleado");
		setModal(true);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel p1=new JPanel();
		Ldni = new JLabel("DNI: ");
		DNI = new JTextField(20);
		p1.add(DNI);
		mainPanel.add(Ldni);
		mainPanel.add(p1);

		
		JPanel p2 = new JPanel();
		Lnombre = new JLabel("Nombre: ");
		Nombre = new JTextField(20);
		mainPanel.add(Lnombre);
		p2.add(Nombre);
		mainPanel.add(p2);
		
		JPanel p3 = new JPanel();
		Lapellidos = new JLabel("Apellidos: ");
		Apellidos = new JTextField(20);
		mainPanel.add(Lapellidos);
		p3.add(Apellidos);
		mainPanel.add(p3);
		
		JPanel p4 = new JPanel();
		Lcargo = new JLabel("Cargo: ");
		Cargo = new JComboBox<String>(cargos);
		Cargo.setMaximumSize( Cargo.getPreferredSize() );
		mainPanel.add(Lcargo);
		p4.add(Cargo);
		mainPanel.add(p4);	
		
		JPanel p5 = new JPanel();
		Ldepartamento = new JLabel("Departamento: ");
		Departamento = new JTextField(20);
		Departamento.setMaximumSize( Departamento.getPreferredSize() );
		mainPanel.add(Ldepartamento);
		p5.add(Departamento);
		mainPanel.add(p5);
		
		Cargo.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Cargo.getSelectedIndex()==0) {
					Ldepartamento.setVisible(true);
					Departamento.setVisible(true);
				}else {
					Ldepartamento.setVisible(false);
					Departamento.setVisible(false);
				}
				
			}
			
		});
		
		JPanel p6 = new JPanel();
		JButton Cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		p6.add(Cancel);
		p6.add(OK);
		mainPanel.add(p6);
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(DNI.getText().length()==0 || Nombre.getText().length()== 0 || Apellidos.getText().length()== 0
					   || Cargo.getSelectedItem().toString().length()== 0) 
						actualizar(Eventos.RES_ALTA_EMPLEADOS_OK,1);
					
					else{
						TEmpleados emp;
					if(Cargo.getSelectedIndex()==0) {
						String d = Departamento.getText();
						if(d.length()==0)d="Sin Departamento";
						emp =new TProfesor(DNI.getText(),Nombre.getText(), Apellidos.getText(), 
								Cargo.getSelectedItem().toString(), d);
					}
						else emp = new TEmpleados(DNI.getText(),Nombre.getText(), Apellidos.getText(), 
								Cargo.getSelectedItem().toString());
					
					Controlador.getInstancia().accion(Eventos.ALTA_EMPLEADOS,emp);
					}
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
		case Eventos.ALTA_EMPLEADOS:{
			setVisible(true);
			break;
		}
		case Eventos.RES_ALTA_EMPLEADOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El empleado se ha integrado correctamente");
			}else {
				JOptionPane.showMessageDialog(this, "El empleado ya existe o no se ha podido integrar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
		
	}
}