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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.json.JSONException;

import negocio.TServicios;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

@SuppressWarnings("serial")
public class VistaAltaServicio extends JDialog implements IGUI{//PAGINA DE AÑADIR CLIENTE 	
	private JLabel NombreServ;
	private JComboBox<String> tServ;
	private JLabel Plazas;
	private JTextField tPlazas;
	private JLabel Precio;
	private JSpinner tPrecio;
	private JLabel Ex;
	private JTextField TEx;
	private JPanel mainPanel;
	private boolean selected;
	private JPanel p;
	private static final String[] Servicios= {"Comedor","Libreria","Extraescolares","Excursiones"};
	
	
	public VistaAltaServicio() {
		
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Añadir Servicio");
		setModal(true);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		NombreServ=new JLabel("Nombre del Servicio:");
		mainPanel.add(NombreServ);
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
		Plazas=new JLabel("Nº de plazas del servicio:");
		mainPanel.add(Plazas);
		JPanel aa=new JPanel();
		tPlazas=new JTextField(20);
		aa.add(tPlazas);
		mainPanel.add(aa);
		Precio=new JLabel("Precio del servicio al alumno:");
		mainPanel.add(Precio);
		SpinnerNumberModel stModel=new SpinnerNumberModel(0,0.0,10000.00,0.1);
		tPrecio=new JSpinner(stModel);
		p=new JPanel();
		JLabel p2=new JLabel("€");
		p.add(tPrecio);
		p.add(p2);
		mainPanel.add(p);
		tServ.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selected==false && (tServ.getSelectedIndex()==2||tServ.getSelectedIndex()==3)) {
					Ex.setVisible(true);
					TEx.setVisible(true);
				}else {
					Ex.setVisible(false);
					TEx.setVisible(false);
				}
				
			}
			
		});
		
		//Botones de aceptar y cancelar
		JButton ok=new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		JPanel panel=new JPanel();
		panel.add(cancelar);
		panel.add(ok);
		mainPanel.add(panel);
		
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if(tPlazas.getText().toString().length()==0 || tPrecio.getValue().toString().length()==0) {
					actualizar(Eventos.RES_ALTA_SERVICIO_OK,1);
				}else {
					TServicios ts;
					if(TEx.isVisible()) {
						ts=new TServicios(TEx.getText().toString(),tServ.getSelectedItem().toString(),Integer.parseInt(tPlazas.getText()),Double.parseDouble(tPrecio.getValue().toString()));
					}else {
						ts=new TServicios(tServ.getSelectedItem().toString(),tServ.getSelectedItem().toString(),Integer.parseInt(tPlazas.getText()),Double.parseDouble(tPrecio.getValue().toString()));
					}
					
					try {
						Controlador.getInstancia().accion(Eventos.ALTA_SERVICIO, ts);
					} catch (FileNotFoundException | JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		setPreferredSize(new Dimension(600, 300));
		pack();
			
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.ALTA_SERVICIO:{
			this.setVisible(true);
			break;
		}case Eventos.RES_ALTA_SERVICIO_OK:{
			if((int)datos==0) {
				JOptionPane.showMessageDialog(this, "El servicio se ha añadido correctamente");
			}else {
				JOptionPane.showMessageDialog(this, "El servicio ya existe o no se ha podido añadir","Error",JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		}
	}
}
