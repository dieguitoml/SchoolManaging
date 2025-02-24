package presentacion.GUIAlumno;

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

import negocio.TAlumno;
import negocio.TServicios;

public class VistaBuscarAlumno extends JDialog implements IGUI{
	private static final String[] cursos= {"Infantil", "Primaria", "Secundaria"};
	public VistaBuscarAlumno() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar Alumno");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		JPanel p1 = new JPanel();
		JLabel Ldni = new JLabel("DNI: ");
		JTextField DNI = new JTextField(20);
		mainPanel.add(Ldni);
		p1.add(DNI);
		mainPanel.add(p1);
		
		JPanel p2 = new JPanel();
		JLabel Lcurso = new JLabel("Curso: ");
		JComboBox<String> curso = new JComboBox<String>(cursos);
		curso.setMaximumSize( curso.getPreferredSize() );
		mainPanel.add(Lcurso);
		p2.add(curso);
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
					Controlador.getInstancia().accion(Eventos.BUSCAR_ALUMNOS,
							new TAlumno(DNI.getText().toString(), curso.getSelectedItem().toString()));
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
		case Eventos.BUSCAR_ALUMNOS:{
			setVisible(true);
			break;
		}case Eventos.RES_BUSCAR_ALUMNOS_OK:{
			if(datos!=null) {
				
				JSONObject j=((TAlumno) datos).escribirAlumno();
			
				JOptionPane.showMessageDialog(this,"DNI del alumno: "+ j.getString("DNI")+
						"\nNombre: "+ j.getString("Nombre")+ "\nApellidos: "+j.getString("Apellidos")+ 
						"\nCurso: "+ j.getString("Curso") ,"Alumno Encontrado!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el alumno buscado, intentelo de nuevo","Error",JOptionPane.ERROR_MESSAGE);
			}
		break;
		}
		
	}
}
}
