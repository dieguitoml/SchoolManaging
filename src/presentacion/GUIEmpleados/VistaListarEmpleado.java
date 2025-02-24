package presentacion.GUIEmpleados;

import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

import negocio.TEmpleados;

public class VistaListarEmpleado extends JDialog implements IGUI {

	private JPanel mainPanel;
	
	public VistaListarEmpleado() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		setModal(true);
		JLabel message=new JLabel("�Seguro que quieres listar los alumnos?");
		mainPanel.add(message);
		JPanel buttons=new JPanel();
		JButton ok=new JButton("OK");
		JButton cancel=new JButton("Cancel");
		buttons.add(ok);
		buttons.add(cancel);
		mainPanel.add(buttons);
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				try {
					Controlador.getInstancia().accion(Eventos.LISTAR_EMPLEADOS, null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		cancel.addActionListener(new ActionListener() {
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
		case Eventos.LISTAR_EMPLEADOS:{
			setVisible(true);
			break;
		}case Eventos.RES_LISTAR_EMPLEADOS_OK:{
			if(datos!=null) {
				List<TEmpleados>lista = (List<TEmpleados>)datos;
				mainPanel.removeAll();
				setTitle("Lista de Empleados");
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
				setContentPane(mainPanel);
				String[] columnas= {"DNI","Nombre","Apellidos","Cargo", "Departamento"};
				List<Object[]> data = new ArrayList<>();
				for(int i=0;i<lista.size();i++) {
					JSONObject o=lista.get(i).escribirEmpleado();
					if(o.length()==4)
					data.add(new Object[] {o.getString("DNI"),o.getString("Nombre"),
							o.getString("Apellidos"),o.getString("Cargo")});
					else
					data.add(new Object[] {o.getString("DNI"),o.getString("Nombre"),
							o.getString("Apellidos"),o.getString("Cargo"), o.getString("Departamento")});
				}
				 DefaultTableModel modelo = new DefaultTableModel();
				 modelo.setColumnIdentifiers(columnas);
				 for (Object[] dato : data) {
			            modelo.addRow(dato);
			        }
				
				 JTable tabla = new JTable(modelo);
				 JScrollPane panelDesplazable = new JScrollPane(tabla);
				 panelDesplazable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				 panelDesplazable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				 JLabel listaLabel = new JLabel("Lista de los empleados:");
				 JPanel btn=new JPanel();
				 JButton OK = new JButton("OK");
				 btn.add(OK);
				 OK.addActionListener(new ActionListener() {
					@Override	
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				 });
				 
				 mainPanel.add(listaLabel, BorderLayout.NORTH);
			     mainPanel.add(panelDesplazable, BorderLayout.CENTER);
			     mainPanel.add(btn, BorderLayout.SOUTH);


				setSize(new Dimension(400,400));
			    this.setVisible(true);
				break;
			}else {
				JOptionPane.showMessageDialog(this, "Ha ocurrido un error al listar los empleados","ERROR",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		}
	}

}
