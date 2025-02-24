package presentacion.GUIAlumno;


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

public class VistaAltaAlumno extends JDialog implements IGUI{
	private static final String[] Curso = {"Infantil", "Primaria", "Secundaria"};
	private JPanel mainPanel;
	private JLabel Ldni;
	private JTextField DNI;
	private JLabel Lnombre;
	private JTextField Nombre;
	private JLabel Lapellidos;
	private JTextField Apellidos;
	private JLabel Lcurso;
	private JComboBox<String> curso;

	public VistaAltaAlumno() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Alta Alumno");
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
		p2.add(Nombre);
		mainPanel.add(Lnombre);
		mainPanel.add(p2);
		
		JPanel p3 = new JPanel();
		Lapellidos = new JLabel("Apellidos: ");
		Apellidos = new JTextField(20);
		p3.add(Apellidos);
		mainPanel.add(Lapellidos);
		mainPanel.add(p3);
		
		JPanel p4 = new JPanel();
		Lcurso = new JLabel("Curso: ");
		curso = new JComboBox<String>(Curso);
		curso.setMaximumSize(curso.getPreferredSize());
		p4.add(curso);
		mainPanel.add(Lcurso);
		mainPanel.add(p4);	
		
		JPanel p5 = new JPanel();
		JButton Cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		p5.add(Cancel);
		p5.add(OK);
		mainPanel.add(p5);
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(DNI.getText().length()==0 || Nombre.getText().length()== 0 || Apellidos.getText().length()== 0
					   || curso.getSelectedItem().toString().length()== 0) 
						actualizar(Eventos.RES_ALTA_ALUMNOS_OK,1);
					
					else{
						TAlumno alum;
						alum = new TAlumno(DNI.getText(),Nombre.getText(), Apellidos.getText(), 
								curso.getSelectedItem().toString());
					
					Controlador.getInstancia().accion(Eventos.ALTA_ALUMNOS,alum);
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
		case Eventos.ALTA_ALUMNOS :{
			setVisible(true);
			break;
		}
		case Eventos.RES_ALTA_ALUMNOS_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El alumno se ha integrado correctamente");
			}else {
				JOptionPane.showMessageDialog(this, "El alumno ya existe o no se ha podido integrar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
			}
		}
		
		
	}
}