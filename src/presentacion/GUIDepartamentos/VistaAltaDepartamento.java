package presentacion.GUIDepartamentos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import negocio.TDepartamento;
import negocio.TEmpleados;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;


public class VistaAltaDepartamento extends JDialog implements IGUI{
	
	private String[] _headers = {"Profesor", "DNI"};
	private JTextField _tfNombre;
	private JSpinner _sProfesores;
	private JTable _tablaNuevosProfesores;
	private DefaultTableModel _modelTabla;
	private JPanel _TablePanel;
	private JPanel _DescPanel; 
	
	public VistaAltaDepartamento() {	
		initGUI();
	}
	 
	private void initGUI() {
		setTitle("Alta departamento");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// INTRODUCIR NOMBRE
		JPanel NombrePanel = new JPanel(); 
		NombrePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		NombrePanel.add(new JLabel("Nombre del departamento: "));
		_tfNombre = new JTextField(20);
		NombrePanel.add(_tfNombre); 
		mainPanel.add(NombrePanel);
		
		
        
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
      	setSize(new Dimension(500,400));
		pack();
		
	}
	 private void OKAction() {
				try {
					Controlador.getInstancia().accion(Eventos.ALTA_DEPARTAMENTO,new TDepartamento(_tfNombre.getText()));
				} catch (FileNotFoundException | JSONException e1) {
					e1.printStackTrace();
				}
			setVisible(false);
		}

	
@Override
public void actualizar(int evento, Object datos) {
	switch(evento) {
	case Eventos.ALTA_DEPARTAMENTO:{
		setSize(new Dimension(300,300));
		setVisible(true);
		break;
	}
	case Eventos.RES_ALTA_DEPARTAMENTO_OK:{
		if((int)datos==0) {
			JOptionPane.showMessageDialog(this, "El departamento se ha integrado correctamente");
		}else {
			JOptionPane.showMessageDialog(this, "El departamento ya existe o no se ha podido integrar","Error",JOptionPane.ERROR_MESSAGE);
		}
		break;
	}
	}
	
}
}
