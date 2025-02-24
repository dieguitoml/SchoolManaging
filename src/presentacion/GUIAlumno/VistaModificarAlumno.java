package presentacion.GUIAlumno;

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

import negocio.TAlumno;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaModificarAlumno extends JDialog implements IGUI {

	private static final String[] cursos= {"Infantil", "Primaria", "Secundaria"};
	public VistaModificarAlumno() {
		super();
		initGUI();
	}

	private void initGUI() {
		setTitle("Modificar Alumno");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel p1=new JPanel();
		JLabel l = new JLabel("Inserte los datos del empleado que desesa modificar: ");
		p1.add(l);
		mainPanel.add(p1);

		JLabel LDNI = new JLabel("DNI: ");
		JPanel p2 = new JPanel();
		JTextField DNI = new JTextField(20);
		mainPanel.add(LDNI);
		p2.add(DNI);
		mainPanel.add(p2);


		JPanel p3 = new JPanel();
		JLabel Lcurso = new JLabel("Curso: ");
		JComboBox<String> curso = new JComboBox<String>(cursos);
		curso.setMaximumSize( curso.getPreferredSize() );
		mainPanel.add(Lcurso);
		p3.add(curso);
		mainPanel.add(p3);	

		JPanel p4 = new JPanel();
		JLabel aModificar = new JLabel("Datos a modificar:");
		p4.add(aModificar);
		mainPanel.add(p4);

		JPanel p5 = new JPanel();
		JLabel Lnombre = new JLabel("Nombre: ");
		JTextField Nombre = new JTextField(20);
		mainPanel.add(Lnombre);
		p5.add(Nombre);
		mainPanel.add(p5);

		JPanel p6 = new JPanel();
		JLabel Lapellidos = new JLabel("Apellidos: ");
		JTextField Apellidos = new JTextField(20);
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
					Controlador.getInstancia().accion(Eventos.MODIFICAR_ALUMNOS,
							new TAlumno(DNI.getText(),Nombre.getText(), Apellidos.getText(), curso.getSelectedItem().toString()));
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
		case Eventos.MODIFICAR_ALUMNOS:{
			setVisible(true);
			break;
		}
		case Eventos.RES_MODIFICAR_ALUMNOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El alumno se ha modificado correctamente");
			}else {
				JOptionPane.showMessageDialog(this, "El alumno no existe o no se ha podido modificar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}

	}
}
