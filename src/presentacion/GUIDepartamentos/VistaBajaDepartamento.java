package presentacion.GUIDepartamentos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import negocio.TDepartamento;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaBajaDepartamento extends JDialog implements IGUI {
	
	private JLabel LModo;
	private JComboBox Modo; 
	private JLabel LNombre;
	private JTextField Nombre;
	private JLabel LID;
	private JTextField ID;
	private String[] _opciones = {"Por ID", "Por nombre"};
	private JPanel p2;
	private JPanel p4;
	
	public VistaBajaDepartamento() {
		initGUI();
	}
	 
	private void initGUI() {
		setTitle("Baja Departamento");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Descripcion Baja Departamento
		LModo = new JLabel("Elije el modo de eliminarlo: ");
		Modo = new JComboBox(_opciones);
		JPanel p1 = new JPanel();
		p1.add(LModo);
		p1.add(Modo);
		mainPanel.add(p1);
		Modo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (Modo.getSelectedIndex()==0) {
		        	p4.setVisible(true);
		        	p2.setVisible(false);

		        }
		        else {
		        	p2.setVisible(true);
	        		p4.setVisible(false);
		        }

		        
		    }
		});

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		LID = new JLabel("ID: ");
		ID = new JTextField(10);
		p4.add(LID);
		p4.add(ID);
		p4.setVisible(false);
		mainPanel.add(p4);
		
		
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		LNombre = new JLabel("Nombre: ");
		Nombre = new JTextField(20);
		p2.add(LNombre);
		p2.add(Nombre);
		p2.setVisible(false);
		mainPanel.add(p2);
		
			
		//Buttons Layout
        JPanel ButtonPanel = new JPanel(); 
      	ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      //CancelButton
      	JButton CancelButton = new JButton();
      	CancelButton.setText("Cancel");
      	CancelButton.addActionListener((e) -> setVisible(false));
      	ButtonPanel.add(CancelButton);
      		
      		//OKButton 
      	JButton OKButton = new JButton();
      	OKButton.setText("OK");
    	OKButton.addActionListener((e) -> OKAction());
      	ButtonPanel.add(OKButton);
      	mainPanel.add(ButtonPanel);
		
		setPreferredSize(new Dimension(350, 400));
		pack();
	}

	private void OKAction() {
		try {
			if (Modo.getSelectedIndex()==0)
				Controlador.getInstancia().accion(Eventos.BAJA_DEPARTAMENTO, new TDepartamento(Integer.parseInt(ID.getText()),Nombre.getText()));
			else 
				Controlador.getInstancia().accion(Eventos.BAJA_DEPARTAMENTO, new TDepartamento(Nombre.getText()));	
			} catch (FileNotFoundException | JSONException e1) {
			e1.printStackTrace();
		}
	setVisible(false);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.BAJA_DEPARTAMENTO:{
			setVisible(true);
			break;
		}
		case Eventos.RES_BAJA_DEPARTAMENTO_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El departamento se ha eliminado correctamente de la base de datos","Departamento Eliminado",JOptionPane.DEFAULT_OPTION);
			}else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el departamento o no se ha podido eliminar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
	}
}

