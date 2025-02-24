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
import org.json.JSONObject;

import negocio.TServicios;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

@SuppressWarnings("serial")
public class VistaBuscarServicio extends JDialog implements IGUI {
	private static final String[] Servicios= {"Comedor","Libreria","Extraescolares","Excursiones"};
	private JComboBox<String> tServ;
	private JTextField TEx;
	private JLabel Ex;
	
	
	public VistaBuscarServicio() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Buscar Servicio");
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		setModal(true);
		setContentPane(mainPanel);
		JPanel j=new JPanel();
		j.setLayout(new BoxLayout(j,BoxLayout.X_AXIS));
		JLabel Nombre=new JLabel("Introduzca el nombre del servicio a buscar:");
		j.add(Nombre);
		mainPanel.add(j);
		JPanel b=new JPanel();
		tServ=new JComboBox<String>(Servicios);
		b.add(tServ);
		mainPanel.add(b);
		
		Ex=new JLabel("Escoja el tipo de excursión o extraescolar:");
		Ex.setVisible(false);
		JPanel a=new JPanel();
		mainPanel.add(Ex);
		TEx=new JTextField(20);
		TEx.setVisible(false);
		a.add(TEx);
		mainPanel.add(a);
		
		JPanel aa=new JPanel();
		
		JButton buscar=new JButton("Buscar");
		JButton cancelar=new JButton("Cancelar");
		
		aa.add(cancelar);
		aa.add(buscar);
		
		mainPanel.add(aa);
		
		tServ.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if((tServ.getSelectedIndex()==2||tServ.getSelectedIndex()==3)) {
					Ex.setVisible(true);
					TEx.setVisible(true);
				}else {
					Ex.setVisible(false);
					TEx.setVisible(false);
				}
				
			}
			
		});
		
		
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TServicios ts;
				if(TEx.isVisible()) {
					ts=new TServicios(tServ.getSelectedItem().toString(),TEx.getText().toString());
				}else {
					ts=new TServicios(tServ.getSelectedItem().toString(),tServ.getSelectedItem().toString());
				}
				
				try {
					Controlador.getInstancia().accion(Eventos.BUSCAR_SERVICIO,ts);
				} catch (FileNotFoundException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			
		});
		
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});
		setSize(new Dimension(500,200));
		
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.BUSCAR_SERVICIO:{
			setVisible(true);
			break;
		}case Eventos.RES_BUSCAR_SERVICIO_OK:{
			if(datos!=null) {
				
				JSONObject j=((TServicios) datos).escribirServicioId();
			
				JOptionPane.showMessageDialog(this,"Nombre del servicio: "+ j.get("Nombre servicio")+
						" Subtipo: "+ j.get("Subtipo")+ " Id: "+j.get("Id")+ 
						" Numero de plazas: "+ j.get("Número de plazas disponibles")+ 
						" Precio: "+j.get("Precio de cada plaza"),"¡Servicio Encontrado!", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "No se ha encontrado el servicio buscado, inténtelo de nuevo","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	
		}
	}
}
	


