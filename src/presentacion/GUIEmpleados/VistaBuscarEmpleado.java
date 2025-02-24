package presentacion.GUIEmpleados;

import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

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
import org.json.JSONObject;

import negocio.TEmpleados;
import negocio.TServicios;

public class VistaBuscarEmpleado extends JDialog implements IGUI{
	private static final String[] cargos= {"Profesor", "Secretaria", "PersonalLimpieza"};
	private JPanel mainPanel;
	private JLabel Ldni;
	private JTextField DNI;
	private JLabel Lcargo;
	private JComboBox<String> Cargo;
	
	public VistaBuscarEmpleado() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar Empleado");
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
		JButton Buscar = new JButton("Buscar");
		p3.add(Cancel);
		p3.add(Buscar);
		mainPanel.add(p3);
		
		Buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.getInstancia().accion(Eventos.BUSCAR_EMPLEADOS,
							new TEmpleados(DNI.getText().toString(), Cargo.getSelectedItem().toString()));
				} catch (FileNotFoundException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		case Eventos.BUSCAR_EMPLEADOS:{
			setVisible(true);
			break;
		}case Eventos.RES_BUSCAR_EMPLEADOS_OK:{
			if(datos!=null) {
				
				JSONObject j=((TEmpleados) datos).escribirEmpleado();
			
				String msg ="DNI del empleado: "+ j.getString("DNI")+
						"\nNombre: "+ j.getString("Nombre")+ "\nApellidos: "+j.getString("Apellidos")+ 
						"\nCargo: "+ j.getString("Cargo");
				if(j.length()==5) msg+= "\nDepartamento: " + j.getString("Departamento");
				
				JOptionPane.showMessageDialog(this,msg,"Empleado Encontrado!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el empleado buscado, intentelo de nuevo","Error",JOptionPane.ERROR_MESSAGE);
			}
		break;
		}
		
	}
}
}


