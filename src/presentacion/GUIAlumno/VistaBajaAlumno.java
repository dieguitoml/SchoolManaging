package presentacion.GUIAlumno;

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

import negocio.TAlumno;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;


public class VistaBajaAlumno extends JDialog implements IGUI {
	private static final String[] cursos= {"Infantil", "Primaria", "Secundaria"};
	
	public VistaBajaAlumno() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Baja Alumno");
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
		JButton OK = new JButton("OK");
		p3.add(Cancel);
		p3.add(OK);
		mainPanel.add(p3);
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.getInstancia().accion(Eventos.BAJA_ALUMNOS,
							new TAlumno(DNI.getText(), curso.getSelectedItem().toString()));
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
		case Eventos.BAJA_ALUMNOS:{
			setVisible(true);
			break;
		}
		case Eventos.RES_BAJA_ALUMNOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El alumno se ha eliminado correctamente de la base de datos","Servicio Eliminado",JOptionPane.DEFAULT_OPTION);
			}else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el alumno o no se ha podido eliminar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
		
	}

}
