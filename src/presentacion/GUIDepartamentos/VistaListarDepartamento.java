package presentacion.GUIDepartamentos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONException;
import org.json.JSONObject;

import negocio.TDepartamento;
import presentacion.Controlador;
import presentacion.Eventos;
import presentacion.IGUI;

public class VistaListarDepartamento extends JDialog implements IGUI{
	
	private JPanel mainPanel;
	
	public VistaListarDepartamento() {
		initGUI();
		
	}
	
	private void initGUI() {
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		setModal(true);
		JLabel message=new JLabel("¿Seguro que quieres listar los departamentos?");
		mainPanel.add(message); 
		JPanel buttons=new JPanel();
		JButton ok=new JButton("Aceptar");
		JButton cancelar=new JButton("Cancelar");
		buttons.add(ok);
		buttons.add(cancelar);
		mainPanel.add(buttons);
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				try {
					Controlador.getInstancia().accion(Eventos.LISTAR_DEPARTAMENTO, null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
			
		});
		pack();
		setSize(new Dimension (450,300));
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento) {
		case Eventos.LISTAR_DEPARTAMENTO:{ 
			setVisible(true);
			break;
		}case Eventos.RES_LISTAR_DEPARTAMENTO_OK:{
			if(datos!=null) {
				List<TDepartamento> l= (List<TDepartamento>) datos;
				mainPanel.removeAll();
				setTitle("Lista de Departamento");
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
				setContentPane(mainPanel);
				String[] columnas= {"ID", "Nombre", "Numero profesores"};
				List<Object[]> data = new ArrayList<>();
				for(int i=0;i<l.size();i++) {
					JSONObject o=l.get(i).escribirDepartamentoID();
					data.add(new Object[] {o.getInt("ID"),o.getString("Nombre"),o.getInt("num_profesores")});
					
				}
				 DefaultTableModel modelo = new DefaultTableModel() {
					 @Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
				 };
				 modelo.setColumnIdentifiers(columnas);
				 for (Object[] dato : data) {
			            modelo.addRow(dato);
			        }
				 JTable tabla = new JTable(modelo);

						/**
						 * 
						 */
				
				
				tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane scrollPane = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setPreferredSize(new Dimension(350,350));
				JPanel TablePanel = new JPanel(); 
				TablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
				TablePanel.add(scrollPane);
				
				JLabel listaLabel = new JLabel("Lista de los departamentos:");
				JPanel btn=new JPanel();
				JButton aceptarBtn = new JButton("Aceptar");
				btn.add(aceptarBtn);
				aceptarBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						setVisible(false);
					}
					 
				});
				mainPanel.add(listaLabel, BorderLayout.NORTH);
			    mainPanel.add(TablePanel, BorderLayout.CENTER);
			    mainPanel.add(btn, BorderLayout.SOUTH);
			    pack();
			    setResizable(false);
				setSize(new Dimension (450,300));
			    setVisible(true);
				
				break;
			}else {
				JOptionPane.showMessageDialog(this, "Ha ocurrido un error al listar los departamentos","ERROR",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		}
	}

}