package presentacion.GUIDepartamentos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import negocio.TDepartamento;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaBuscarDepartamento extends JDialog implements IGUI{

	private JPanel mainPanel;
	private JLabel LModo;
	private JComboBox Modo; 
	private JLabel LNombre;
	private JTextField Nombre;
	private JLabel LID;
	private JTextField ID;
	private String[] _opciones = {"ID", "Nombre"};
	private JPanel p2;
	private JPanel p4;

	
	public VistaBuscarDepartamento() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar Departamento");
		setModal(true);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel p1 = new JPanel();
		LModo = new JLabel("Elije el modo de busqueda: ");
		Modo = new JComboBox(_opciones);
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
					if (Modo.getSelectedIndex()==0)
						Controlador.getInstancia().accion(Eventos.BUSCAR_DEPARTAMENTO, new TDepartamento(Integer.parseInt(ID.getText()),Nombre.getText()));
					else 
						Controlador.getInstancia().accion(Eventos.BUSCAR_DEPARTAMENTO, new TDepartamento(Nombre.getText()));
				} catch (Exception e1) {
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
		setSize(new Dimension (450,300));

	}


	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.BUSCAR_DEPARTAMENTO:{
			setSize(new Dimension (450,300));
			setVisible(true);
			break;
		}case Eventos.RES_BUSCAR_DEPARTAMENTO_OK:{
			if(datos!=null) {
				
				JSONObject j=((TDepartamento) datos).escribirDepartamentoID();
			
				String msg ="INFO DEPARTAMENTO: { ID: "+ j.getInt("ID") + " / Nombre: "+ j.getString("Nombre") + " / Numero profesores: "+ j.getInt("num_profesores") + " }";
							
				JOptionPane.showMessageDialog(this,msg,"Departamento Encontrado!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el departamento buscado, intentelo de nuevo","Error",JOptionPane.ERROR_MESSAGE);
			} 
		break;
		}
		
	}
}
}
