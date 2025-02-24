package presentacion.GUIServicios;

import java.awt.Dimension;
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

import negocio.TServicios;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

@SuppressWarnings("serial")
public class VistaBajaServicio extends JDialog implements IGUI{
	
	private static final String[] Servicios= {"Comedor","Libreria","Extraescolares","Excursiones"};
	private JComboBox<String> tServ;
	private JTextField tNombre;
	private JLabel Nombre;
	

	public VistaBajaServicio() {
		initGUI();
	}
	
	private void initGUI() {
		// TODO Auto-generated method stub
		setTitle("Baja Servicio");
		JPanel mainPanel=new JPanel();	
		setModal(true);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		JPanel bb=new JPanel();
		tServ=new JComboBox<String>(Servicios);
		bb.add(tServ);
		mainPanel.add(bb);
		
		Nombre=new JLabel("Introduzca tipo a eliminar:");
		Nombre.setVisible(false);
		mainPanel.add(Nombre);
		
		
		JPanel a=new JPanel();
		tNombre=new JTextField(20);
		tNombre.setVisible(false);
		a.add(tNombre);
		mainPanel.add(a);

		JButton ok=new JButton("Aceptar");
		JButton cancel=new JButton("Cancelar");
		
		JPanel b=new JPanel();
		
		b.add(cancel);
		b.add(ok);
		
		mainPanel.add(b);
		
		tServ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tServ.getSelectedIndex()==2 || tServ.getSelectedIndex()==3) {
					tNombre.setVisible(true);
					Nombre.setVisible(true);
				}else {
					tNombre.setVisible(false);
					Nombre.setVisible(false);
				}
			}
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TServicios ts;
				if(!tNombre.isVisible()){
					ts=new TServicios(tServ.getSelectedItem().toString(),tServ.getSelectedItem().toString());
				}else {
					ts=new TServicios(tServ.getSelectedItem().toString(),tNombre.getText().toString());
				}
				try {
					Controlador.getInstancia().accion(Eventos.BAJA_SERVICIO, ts);
				} catch (FileNotFoundException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);		
			}
			
		});
		
			
	setSize(new Dimension(300,300));
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento) {
		case Eventos.BAJA_SERVICIO:{
			setVisible(true);
			break;
		}case Eventos.RES_BAJA_SERVICIO_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El servicio se ha eliminado correctamente de la base de datos","Servicio Eliminado",JOptionPane.DEFAULT_OPTION);
			}else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el servicio o no se ha podido eliminar","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
		
	}
	
}
